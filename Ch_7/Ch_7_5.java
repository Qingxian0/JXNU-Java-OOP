import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

class User{
	private String ip,name;
	private int port;
	private SocketStr skt;
	public User(SocketStr s){ skt=s; }
	public String getName(){ return name; }
	public void setName(String n){ name=n; }
	public SocketStr getSocketStr(){ return skt; }
	public void setSocketStr(SocketStr ss){ skt=ss; }
	
}
class Server extends JFrame implements ActionListener{
	private boolean closeServerSocket=false; //仅当关闭服务器创建的Socket使用
	private boolean online=false; //在线标记
	private int port;
	private ServerSocket serverSocket;//无论连接多少用户，只需一个ServerSocket
	private User user[]=new User[20]; //服务端的用户列表，用于广播消息
	private int len; //用户列表类似线性表，需要表长

	private JTextArea msgArea;        //消息显示区
	private JTextField msg_jtf,online_num,port_jtf,onlineUser_jtf; //消息框
	private JButton send_bt,start_bt,stop_bt;           //发送按钮
	
	private class A extends WindowAdapter{//内部类，用于处理点击窗口的X
		public void windowClosing(WindowEvent e){//
			if(online==true)  closeServer();
			System.exit(0);
		}
	}
	private class ServerThread extends Thread{//服务端监听连接请求的线程
		ServerThread(int p){port=p;}
		public void run(){ 
			Socket sk=null;   SocketStr sks=null; 
			online=true; //online是Server的属性
			try { serverSocket = new ServerSocket(port); }
			catch(Exception e){ online=false;	e.printStackTrace(); }
			finally{ if(online==false) return; }//ServerSocket创建失败，当然不能继续
			while( online ){ //点击关闭按钮可退出此循环
				try{ sk = serverSocket.accept(); sks=new SocketStr(sk);	}
				catch(Exception e){	System.out.println("有异常："); e.printStackTrace();}
				if(closeServerSocket==true) { //如果点击关闭，则复原后退出
					closeServerSocket=false; break; } 
				addUser(sks);//System.out.println("user.len="+len);
				online_num.setText(""+len);
				new ServerMsgThread(sks).start();//启动对应的消息接收线程
			}
			try{ serverSocket.close(); }
			catch(IOException e){	System.out.println("有异常："); e.printStackTrace();}
		}
	}
	private class ServerMsgThread extends Thread{//每个用户对应一个消息线程
		SocketStr sk;//面向特定用户接收消息
		ServerMsgThread(SocketStr s){sk=s;}
		public void run(){ 	String msg=null;
			while(true){//需要区别四种类型的消息
				msg=sk.receive();//接收消息
				if(msg.equals("COMMAND@BYE")){//用户要下线
					sk.send("COMMAND@BYE");		delUser(sk); 
					online_num.setText(""+len); 
					onlineUser_jtf.setText(getOnlineUserName());
					break;
				}
				if(msg.equals("COMMAND@EXIT")){//服务器下线
						delUser(sk); 
						online_num.setText(""+len); 
						onlineUser_jtf.setText(getOnlineUserName());
						break; 
				}
				if(msg.startsWith("COMMAND@NAME:")){//用户上线后特意发来的用户名
					String s=msg.substring(13);//获取用户名
					User u=locateUser(sk);		u.setName(s);
					onlineUser_jtf.setText(getOnlineUserName());
					append(s+"上线了!");	continue;	}
				else { append(msg); broadcast(msg); }//本地显示+广播
			}
		}
	}

	public Server(){
		this.setTitle("服务器");	setSize(800, 400);
		setLayout(new BorderLayout(5,10)); //水平间距为5，纵向间距为10
		
		//----构造界面部分---begin
		JPanel pz=new JPanel(new GridLayout(2,1,5,10));//配置信息部分的面板
		pz.setBorder(new TitledBorder("配置信息"));  add(pz,BorderLayout.NORTH);

		JPanel pz1=new JPanel(new FlowLayout(FlowLayout.LEFT));//配置信息部分的面板
		pz.add(pz1);
		pz1.add(new JLabel(" 服务器端口"));	port_jtf=new JTextField("6666  ");pz1.add(port_jtf);
		pz1.add(new JLabel("    "));
		start_bt=new JButton("启动");pz1.add(start_bt);
		stop_bt=new JButton("关闭");pz1.add(stop_bt);//stop_bt.setEnabled(false);
		pz1.add(new JLabel("  "));		pz1.add(new JLabel(" 当前在线人数："));
		online_num=new JTextField("0   ");pz1.add(online_num);
		
		JPanel pz2=new JPanel(new BorderLayout(5,10));//配置信息部分的面板
		pz.add(pz2);
		pz2.add(new JLabel("在线用户"),BorderLayout.WEST);
		onlineUser_jtf=new JTextField();pz2.add(onlineUser_jtf,BorderLayout.CENTER);
		
		
		msgArea= new JTextArea();	msgArea.setEditable(false);
		msgArea.setForeground(Color.blue);
		JScrollPane p1=new JScrollPane(msgArea);
		p1.setBorder(new TitledBorder("消息显示区"));
		add(p1,BorderLayout.CENTER); 
		
		JPanel p2=new JPanel(new BorderLayout(5,10));
		p2.setBorder(new TitledBorder("消息编辑发送区"));
		msg_jtf = new JTextField(); 
		p2.add(msg_jtf,BorderLayout.CENTER);
		send_bt=new JButton("发送"); 
		p2.add(send_bt,BorderLayout.EAST);
		add(p2,BorderLayout.SOUTH); 
		setOfflineState(); //设置离线时的界面状态
		setVisible(true);
		//----构造界面部分---end
		this.addWindowListener(new A());   //点击X即可关闭窗口
		send_bt.addActionListener(this);
		msg_jtf.addActionListener(this);
		start_bt.addActionListener(this); //点击连接 创建Socket
		stop_bt.addActionListener(this);

	}
	private String getOnlineUserName(){ String s="";
		for(int i=0; i<len ; i++)s=s+user[i].getName()+"、";
		return s;
	}
	private User locateUser(SocketStr sk){int i; //定位在线用户位置
		for(i=0; i<len && sk!=user[i].getSocketStr(); i++);
		return user[i];
	}
	private void addUser(SocketStr sk){	user[len]=new User(sk); len++;	}
	private void delUser(SocketStr sk){ int i;
		for(i=0; i<len && sk!=user[i].getSocketStr(); i++);
		for(int j=i+1; j<len; j++)user[j-1]=user[j];
		len--;
		sk.close();
	}
	private void setOnlineState(){//设置在线时各关键属性的状态
		online=true;
		stop_bt.setEnabled(true);
		send_bt.setEnabled(true);
		start_bt.setEnabled(false);
	}
	private void setOfflineState(){//设置离线时各关键属性的状态
		online=false;
		stop_bt.setEnabled(false);
		send_bt.setEnabled(false);
		start_bt.setEnabled(true);
	}
	private void broadcast(String msg){//向所有用户广播消息
		for(int i=0; i<len; i++)  user[i].getSocketStr().send(msg);
	}
	private void sendMsg(){ //服务器发信息：广播
		String msg=msg_jtf.getText().trim();
		if(msg.length()==0) return; //无消息内容，不发送
		msg="服务器公告："+msg;
		append(msg);  //将发送的消息追加到本地信息框
		msg_jtf.setText(null);
		broadcast(msg);//发送
	}
	private void startServer(int port){//启动服务器
		new ServerThread(port).start();
	}	
	private void closeServer(){//关闭服务器
		closeServerSocket=true; online=false;
		try{ new Socket("localhost",port); }//用于解除accept挂起状态
		catch(Exception ee){ System.out.println("自创Socket异常。"); }
		broadcast("COMMAND@EXIT"); 
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start_bt){//点击启动按钮
			port = Integer.parseInt(port_jtf.getText().trim());//为简单，请输入合法端口号
			startServer(port); setOnlineState();  return;}
		if(e.getSource()==stop_bt){//点击停止按钮
			closeServer();	setOfflineState();	return;	}
		if(e.getSource()==send_bt||e.getSource()==msg_jtf && online)	sendMsg(); 
	}
	public void append(String msg){ msgArea.append(msg+"\n"); }
}
class Client extends JFrame implements ActionListener{
	private boolean online=false;
	private String ip,userName;
	private int port;
	private JTextArea msgArea;        //消息显示区
	private JTextField msg_jtf,name_jtf,ip_jtf,port_jtf; //消息框
	private JButton send_bt,start_bt,stop_bt;        //发送按钮
	private SocketStr sk;              //用于通信的对象

	private class A extends WindowAdapter{//内部类，用于处理点击窗口的X
		public void windowClosing(WindowEvent e){//
			if(online==true)//若用户处在离线状态，就直接退出
			      sk.send("COMMAND@BYE");
			System.exit(0);
		}
	}
	private class ClientMsgThread extends Thread{//每个用户对应一个消息线程
		SocketStr sk;//面向特定用户接收消息
		ClientMsgThread(SocketStr s){  sk=s; }
		public void run(){  //只需区分3种类型的消息（无需考虑收到用户名）
			String msg=null;
			while(true){
				msg=sk.receive();
				if(msg.equals("COMMAND@BYE"))break;//用户执行下线
				if(msg.equals("COMMAND@EXIT"))//服务器下线
					{ append("服务器下线。");  sk.send("COMMAND@EXIT");	break; }
				else append(msg);
			}
			sk.close();
			setOfflineState(); //设置离线时的界面状态
		}
	}

	public Client(){
		this.setTitle("客户端");		setSize(800, 400);
		setLayout(new BorderLayout(5,10)); //水平间距为5，纵向间距为10
		
		//----构造界面部分---begin
		JPanel pz=new JPanel(new FlowLayout(FlowLayout.LEFT));//配置信息部分的面板
		add(pz,BorderLayout.NORTH);
		pz.setBorder(new TitledBorder("配置信息"));
		pz.add(new JLabel("姓名"));
		name_jtf=new JTextField("张三      ");pz.add(name_jtf);
		pz.add(new JLabel(" 服务器IP"));
		ip_jtf=new JTextField("127.0.0.1  ");pz.add(ip_jtf);
		pz.add(new JLabel(" 服务器端口"));
		port_jtf=new JTextField("6666  ");pz.add(port_jtf);
		pz.add(new JLabel("    "));
		start_bt=new JButton("连接服务器");pz.add(start_bt);
		stop_bt=new JButton("本机下线");pz.add(stop_bt);stop_bt.setEnabled(false);
		
		msgArea= new JTextArea();	msgArea.setEditable(false);
		msgArea.setForeground(Color.blue);
		JScrollPane p1=new JScrollPane(msgArea);
		p1.setBorder(new TitledBorder("消息显示区"));
		add(p1,BorderLayout.CENTER); 
		
		JPanel p2=new JPanel(new BorderLayout(5,10));
		p2.setBorder(new TitledBorder("消息编辑发送区"));
		msg_jtf = new JTextField(); 
		p2.add(msg_jtf,BorderLayout.CENTER);
		send_bt=new JButton("发送"); 
		p2.add(send_bt,BorderLayout.EAST);
		add(p2,BorderLayout.SOUTH); 
		setOfflineState(); //设置离线时的界面状态
		setVisible(true);
		//----构造界面部分---end

		this.addWindowListener(new A());   //点击X即可关闭窗口
		send_bt.addActionListener(this);
		msg_jtf.addActionListener(this);
		start_bt.addActionListener(this); //点击连接 创建Socket
		stop_bt.addActionListener(this);
	}
	private void setOnlineState(){//设置在线时各关键属性的状态
		online=true;		      stop_bt.setEnabled(true);
		send_bt.setEnabled(true); start_bt.setEnabled(false);
	}
	private void setOfflineState(){//设置离线时各关键属性的状态
		online=false;		        stop_bt.setEnabled(false);
		send_bt.setEnabled(false);	start_bt.setEnabled(true);
	}
	private void sendMsg(){
		String msg=msg_jtf.getText().trim();
		if(msg.length()==0) return; //无消息内容，不发送
		msg=userName+"："+msg;
		msg_jtf.setText(null);
		sk.send(msg);
	}
	public void append(String msg){ msgArea.append(msg+"\n"); }
	private void userOnline(){//用户上线
		port = Integer.parseInt(port_jtf.getText().trim());//为简单，请输入合法端口号
		ip=ip_jtf.getText().trim();
		userName=name_jtf.getText().trim();
		sk=new SocketStr(ip, port);//创建连接
		sk.send("COMMAND@NAME:"+userName);//自动发送用户名
		new ClientMsgThread(sk).start();
		setOnlineState();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start_bt){ userOnline(); return;  }
		if(e.getSource()==stop_bt){	sk.send("COMMAND@BYE"); return; }
		if(e.getSource()==send_bt||e.getSource()==msg_jtf&&online){ sendMsg(); return;}
	}
}
class ServerApp{
	public static void main (String[] args) {SetDefaultFont.setAll(new Font("微软雅黑", Font.BOLD,20)); new Server(); }
}
class ClientApp{
	public static void main (String[] args) {SetDefaultFont.setAll(new Font("微软雅黑", Font.BOLD,20)); new Client();	}
}
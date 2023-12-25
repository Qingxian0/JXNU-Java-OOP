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
	private boolean closeServerSocket=false; //�����رշ�����������Socketʹ��
	private boolean online=false; //���߱��
	private int port;
	private ServerSocket serverSocket;//�������Ӷ����û���ֻ��һ��ServerSocket
	private User user[]=new User[20]; //����˵��û��б����ڹ㲥��Ϣ
	private int len; //�û��б��������Ա���Ҫ��

	private JTextArea msgArea;        //��Ϣ��ʾ��
	private JTextField msg_jtf,online_num,port_jtf,onlineUser_jtf; //��Ϣ��
	private JButton send_bt,start_bt,stop_bt;           //���Ͱ�ť
	
	private class A extends WindowAdapter{//�ڲ��࣬���ڴ��������ڵ�X
		public void windowClosing(WindowEvent e){//
			if(online==true)  closeServer();
			System.exit(0);
		}
	}
	private class ServerThread extends Thread{//����˼�������������߳�
		ServerThread(int p){port=p;}
		public void run(){ 
			Socket sk=null;   SocketStr sks=null; 
			online=true; //online��Server������
			try { serverSocket = new ServerSocket(port); }
			catch(Exception e){ online=false;	e.printStackTrace(); }
			finally{ if(online==false) return; }//ServerSocket����ʧ�ܣ���Ȼ���ܼ���
			while( online ){ //����رհ�ť���˳���ѭ��
				try{ sk = serverSocket.accept(); sks=new SocketStr(sk);	}
				catch(Exception e){	System.out.println("���쳣��"); e.printStackTrace();}
				if(closeServerSocket==true) { //�������رգ���ԭ���˳�
					closeServerSocket=false; break; } 
				addUser(sks);//System.out.println("user.len="+len);
				online_num.setText(""+len);
				new ServerMsgThread(sks).start();//������Ӧ����Ϣ�����߳�
			}
			try{ serverSocket.close(); }
			catch(IOException e){	System.out.println("���쳣��"); e.printStackTrace();}
		}
	}
	private class ServerMsgThread extends Thread{//ÿ���û���Ӧһ����Ϣ�߳�
		SocketStr sk;//�����ض��û�������Ϣ
		ServerMsgThread(SocketStr s){sk=s;}
		public void run(){ 	String msg=null;
			while(true){//��Ҫ�����������͵���Ϣ
				msg=sk.receive();//������Ϣ
				if(msg.equals("COMMAND@BYE")){//�û�Ҫ����
					sk.send("COMMAND@BYE");		delUser(sk); 
					online_num.setText(""+len); 
					onlineUser_jtf.setText(getOnlineUserName());
					break;
				}
				if(msg.equals("COMMAND@EXIT")){//����������
						delUser(sk); 
						online_num.setText(""+len); 
						onlineUser_jtf.setText(getOnlineUserName());
						break; 
				}
				if(msg.startsWith("COMMAND@NAME:")){//�û����ߺ����ⷢ�����û���
					String s=msg.substring(13);//��ȡ�û���
					User u=locateUser(sk);		u.setName(s);
					onlineUser_jtf.setText(getOnlineUserName());
					append(s+"������!");	continue;	}
				else { append(msg); broadcast(msg); }//������ʾ+�㲥
			}
		}
	}

	public Server(){
		this.setTitle("������");	setSize(800, 400);
		setLayout(new BorderLayout(5,10)); //ˮƽ���Ϊ5��������Ϊ10
		
		//----������沿��---begin
		JPanel pz=new JPanel(new GridLayout(2,1,5,10));//������Ϣ���ֵ����
		pz.setBorder(new TitledBorder("������Ϣ"));  add(pz,BorderLayout.NORTH);

		JPanel pz1=new JPanel(new FlowLayout(FlowLayout.LEFT));//������Ϣ���ֵ����
		pz.add(pz1);
		pz1.add(new JLabel(" �������˿�"));	port_jtf=new JTextField("6666  ");pz1.add(port_jtf);
		pz1.add(new JLabel("    "));
		start_bt=new JButton("����");pz1.add(start_bt);
		stop_bt=new JButton("�ر�");pz1.add(stop_bt);//stop_bt.setEnabled(false);
		pz1.add(new JLabel("  "));		pz1.add(new JLabel(" ��ǰ����������"));
		online_num=new JTextField("0   ");pz1.add(online_num);
		
		JPanel pz2=new JPanel(new BorderLayout(5,10));//������Ϣ���ֵ����
		pz.add(pz2);
		pz2.add(new JLabel("�����û�"),BorderLayout.WEST);
		onlineUser_jtf=new JTextField();pz2.add(onlineUser_jtf,BorderLayout.CENTER);
		
		
		msgArea= new JTextArea();	msgArea.setEditable(false);
		msgArea.setForeground(Color.blue);
		JScrollPane p1=new JScrollPane(msgArea);
		p1.setBorder(new TitledBorder("��Ϣ��ʾ��"));
		add(p1,BorderLayout.CENTER); 
		
		JPanel p2=new JPanel(new BorderLayout(5,10));
		p2.setBorder(new TitledBorder("��Ϣ�༭������"));
		msg_jtf = new JTextField(); 
		p2.add(msg_jtf,BorderLayout.CENTER);
		send_bt=new JButton("����"); 
		p2.add(send_bt,BorderLayout.EAST);
		add(p2,BorderLayout.SOUTH); 
		setOfflineState(); //��������ʱ�Ľ���״̬
		setVisible(true);
		//----������沿��---end
		this.addWindowListener(new A());   //���X���ɹرմ���
		send_bt.addActionListener(this);
		msg_jtf.addActionListener(this);
		start_bt.addActionListener(this); //������� ����Socket
		stop_bt.addActionListener(this);

	}
	private String getOnlineUserName(){ String s="";
		for(int i=0; i<len ; i++)s=s+user[i].getName()+"��";
		return s;
	}
	private User locateUser(SocketStr sk){int i; //��λ�����û�λ��
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
	private void setOnlineState(){//��������ʱ���ؼ����Ե�״̬
		online=true;
		stop_bt.setEnabled(true);
		send_bt.setEnabled(true);
		start_bt.setEnabled(false);
	}
	private void setOfflineState(){//��������ʱ���ؼ����Ե�״̬
		online=false;
		stop_bt.setEnabled(false);
		send_bt.setEnabled(false);
		start_bt.setEnabled(true);
	}
	private void broadcast(String msg){//�������û��㲥��Ϣ
		for(int i=0; i<len; i++)  user[i].getSocketStr().send(msg);
	}
	private void sendMsg(){ //����������Ϣ���㲥
		String msg=msg_jtf.getText().trim();
		if(msg.length()==0) return; //����Ϣ���ݣ�������
		msg="���������棺"+msg;
		append(msg);  //�����͵���Ϣ׷�ӵ�������Ϣ��
		msg_jtf.setText(null);
		broadcast(msg);//����
	}
	private void startServer(int port){//����������
		new ServerThread(port).start();
	}	
	private void closeServer(){//�رշ�����
		closeServerSocket=true; online=false;
		try{ new Socket("localhost",port); }//���ڽ��accept����״̬
		catch(Exception ee){ System.out.println("�Դ�Socket�쳣��"); }
		broadcast("COMMAND@EXIT"); 
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start_bt){//���������ť
			port = Integer.parseInt(port_jtf.getText().trim());//Ϊ�򵥣�������Ϸ��˿ں�
			startServer(port); setOnlineState();  return;}
		if(e.getSource()==stop_bt){//���ֹͣ��ť
			closeServer();	setOfflineState();	return;	}
		if(e.getSource()==send_bt||e.getSource()==msg_jtf && online)	sendMsg(); 
	}
	public void append(String msg){ msgArea.append(msg+"\n"); }
}
class Client extends JFrame implements ActionListener{
	private boolean online=false;
	private String ip,userName;
	private int port;
	private JTextArea msgArea;        //��Ϣ��ʾ��
	private JTextField msg_jtf,name_jtf,ip_jtf,port_jtf; //��Ϣ��
	private JButton send_bt,start_bt,stop_bt;        //���Ͱ�ť
	private SocketStr sk;              //����ͨ�ŵĶ���

	private class A extends WindowAdapter{//�ڲ��࣬���ڴ��������ڵ�X
		public void windowClosing(WindowEvent e){//
			if(online==true)//���û���������״̬����ֱ���˳�
			      sk.send("COMMAND@BYE");
			System.exit(0);
		}
	}
	private class ClientMsgThread extends Thread{//ÿ���û���Ӧһ����Ϣ�߳�
		SocketStr sk;//�����ض��û�������Ϣ
		ClientMsgThread(SocketStr s){  sk=s; }
		public void run(){  //ֻ������3�����͵���Ϣ�����迼���յ��û�����
			String msg=null;
			while(true){
				msg=sk.receive();
				if(msg.equals("COMMAND@BYE"))break;//�û�ִ������
				if(msg.equals("COMMAND@EXIT"))//����������
					{ append("���������ߡ�");  sk.send("COMMAND@EXIT");	break; }
				else append(msg);
			}
			sk.close();
			setOfflineState(); //��������ʱ�Ľ���״̬
		}
	}

	public Client(){
		this.setTitle("�ͻ���");		setSize(800, 400);
		setLayout(new BorderLayout(5,10)); //ˮƽ���Ϊ5��������Ϊ10
		
		//----������沿��---begin
		JPanel pz=new JPanel(new FlowLayout(FlowLayout.LEFT));//������Ϣ���ֵ����
		add(pz,BorderLayout.NORTH);
		pz.setBorder(new TitledBorder("������Ϣ"));
		pz.add(new JLabel("����"));
		name_jtf=new JTextField("����      ");pz.add(name_jtf);
		pz.add(new JLabel(" ������IP"));
		ip_jtf=new JTextField("127.0.0.1  ");pz.add(ip_jtf);
		pz.add(new JLabel(" �������˿�"));
		port_jtf=new JTextField("6666  ");pz.add(port_jtf);
		pz.add(new JLabel("    "));
		start_bt=new JButton("���ӷ�����");pz.add(start_bt);
		stop_bt=new JButton("��������");pz.add(stop_bt);stop_bt.setEnabled(false);
		
		msgArea= new JTextArea();	msgArea.setEditable(false);
		msgArea.setForeground(Color.blue);
		JScrollPane p1=new JScrollPane(msgArea);
		p1.setBorder(new TitledBorder("��Ϣ��ʾ��"));
		add(p1,BorderLayout.CENTER); 
		
		JPanel p2=new JPanel(new BorderLayout(5,10));
		p2.setBorder(new TitledBorder("��Ϣ�༭������"));
		msg_jtf = new JTextField(); 
		p2.add(msg_jtf,BorderLayout.CENTER);
		send_bt=new JButton("����"); 
		p2.add(send_bt,BorderLayout.EAST);
		add(p2,BorderLayout.SOUTH); 
		setOfflineState(); //��������ʱ�Ľ���״̬
		setVisible(true);
		//----������沿��---end

		this.addWindowListener(new A());   //���X���ɹرմ���
		send_bt.addActionListener(this);
		msg_jtf.addActionListener(this);
		start_bt.addActionListener(this); //������� ����Socket
		stop_bt.addActionListener(this);
	}
	private void setOnlineState(){//��������ʱ���ؼ����Ե�״̬
		online=true;		      stop_bt.setEnabled(true);
		send_bt.setEnabled(true); start_bt.setEnabled(false);
	}
	private void setOfflineState(){//��������ʱ���ؼ����Ե�״̬
		online=false;		        stop_bt.setEnabled(false);
		send_bt.setEnabled(false);	start_bt.setEnabled(true);
	}
	private void sendMsg(){
		String msg=msg_jtf.getText().trim();
		if(msg.length()==0) return; //����Ϣ���ݣ�������
		msg=userName+"��"+msg;
		msg_jtf.setText(null);
		sk.send(msg);
	}
	public void append(String msg){ msgArea.append(msg+"\n"); }
	private void userOnline(){//�û�����
		port = Integer.parseInt(port_jtf.getText().trim());//Ϊ�򵥣�������Ϸ��˿ں�
		ip=ip_jtf.getText().trim();
		userName=name_jtf.getText().trim();
		sk=new SocketStr(ip, port);//��������
		sk.send("COMMAND@NAME:"+userName);//�Զ������û���
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
	public static void main (String[] args) {SetDefaultFont.setAll(new Font("΢���ź�", Font.BOLD,20)); new Server(); }
}
class ClientApp{
	public static void main (String[] args) {SetDefaultFont.setAll(new Font("΢���ź�", Font.BOLD,20)); new Client();	}
}
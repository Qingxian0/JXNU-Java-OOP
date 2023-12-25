import java.awt.Font;
import javax.swing.JFrame;
import java.awt.BorderLayout;
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

//ChatFrame: 聊天界面：消息显示区+消息框+发送按钮
class ChatFrame extends JFrame implements ActionListener{//主体界面
	private JTextArea infoArea;        //消息显示区
	private JTextField sendJTextField; //消息框
	private JButton sendButton;        //发送按钮
	private SocketStr sk;              //用于通信的对象
	private String name;               //使用本界面的用户的名称

	private class A extends WindowAdapter{//内部类，用于处理点击窗口的X
		public void windowClosing(WindowEvent e){	sk.send("BYE-1");	}
	}
	private class ReceiveMsgThread extends Thread{//接收消息线程
		public void run(){	String msg=null;
			while(true){
				msg=sk.receive();
				if(msg.equals("BYE-1")||msg.equals("BYE-2")) break;
				infoArea.append(msg+"\n");
			}
			if(msg.equals("BYE-1"))	sk.send("BYE-2");
			sk.close();//关闭操作
			System.exit(0);//让自己结束
		}
	}

	public void setSocketStr(SocketStr s){
		sk=s; //被叫方收到SocketStr对象，可以通讯了
		sendJTextField.setEnabled(true);
		sendButton.setEnabled(true);

		System.out.println("接收线程启动");
		new ReceiveMsgThread().start(); //创建并启动接收消息的线程
	}
	public ChatFrame(String n, SocketStr s){
		sk=s; name=n; this.setTitle(name);
		setSize(800, 400);
		setLayout(new BorderLayout(5,10)); //水平间距为5，纵向间距为10

		//----构造界面部分---begin
		infoArea= new JTextArea();	infoArea.setEditable(false);
		infoArea.setForeground(Color.blue);
		JScrollPane p1=new JScrollPane(infoArea);
		p1.setBorder(new TitledBorder("消息显示区"));
		add(p1,BorderLayout.CENTER);

		JPanel p2=new JPanel(new BorderLayout(5,10));
		p2.setBorder(new TitledBorder("消息编辑发送区"));
		sendJTextField = new JTextField();
		p2.add(sendJTextField,BorderLayout.CENTER);
		sendButton=new JButton("发送");
		p2.add(sendButton,BorderLayout.EAST);
		add(p2,BorderLayout.SOUTH);
		setVisible(true);
		//----构造界面部分---end

		this.addWindowListener(new A());   //点击X即可关闭窗口
		sendButton.addActionListener(this);
		sendJTextField.addActionListener(this);

		if(sk==null){//作为被叫窗口，初始时SocketStr对象为空，不能启动接收消息线程
			sendJTextField.setEnabled(false);
			sendButton.setEnabled(false);
		}else new ReceiveMsgThread().start(); //创建并启动接收消息的线程
	}
	private void sendMsg(){ //发信息 + 将信息放在自己的消息区
		String msg=sendJTextField.getText().trim();
		if(msg.length()==0) return; //无消息内容，不发送
		msg=name+"："+msg;
		infoArea.append(msg+"\n");  //将发送的消息追加到本地信息框
		sendJTextField.setText(null);
		sk.send(msg);//发送
	}
	//此方法主要用于被叫accept()启动服务、主叫连接成功后的信息显示
	public void append(String msg){ infoArea.append(msg+"\n"); }
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==sendButton||e.getSource()==sendJTextField)
			this.sendMsg(); //用线程只能发，不能收，否则会死锁，消息内容为文本框内容
	}
}
class Callee4{//被叫方
	//执行方式 java Callee4 李四 6666
	public static void main(String[] args)throws IOException{
		SetDefaultFont.setAll(new Font("微软雅黑", Font.BOLD,20));
		String name=args[0];
		int port=Integer.parseInt(args[1]);
		ChatFrame frame=new ChatFrame(name,null);//被叫方暂无SocketStr对象，故为null

		ServerSocket srv= new ServerSocket(port);
		frame.append("被叫方服务端启动，等待接受连接……");
		Socket skt = srv.accept();
		frame.append("已与对方建立连接，可以开始通信了……");

		SocketStr sks=new SocketStr(skt); //必须使用ServerSocket返回的Socket
		frame.setSocketStr(sks);          //向frame传送发送信息的SocketStr对象
	}
}
class Caller4{//主叫方
	//执行方式 java Caller4 张三 127.0.0.1 6666
	public static void main(String[] args){
		SetDefaultFont.setAll(new Font("微软雅黑", Font.BOLD,20));
		String name=args[0];		String ip=args[1];
		int port=Integer.parseInt(args[2]);
		SocketStr sk=new SocketStr(ip, port);
		ChatFrame frame=new ChatFrame(name,sk);
	}
}

//执行方式 java Callee4 李四 6666
//执行方式 java Caller4 张三 127.0.0.1 6666

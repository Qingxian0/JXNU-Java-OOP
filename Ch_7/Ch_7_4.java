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

//ChatFrame: ������棺��Ϣ��ʾ��+��Ϣ��+���Ͱ�ť
class ChatFrame extends JFrame implements ActionListener{//�������
	private JTextArea infoArea;        //��Ϣ��ʾ��
	private JTextField sendJTextField; //��Ϣ��
	private JButton sendButton;        //���Ͱ�ť
	private SocketStr sk;              //����ͨ�ŵĶ���
	private String name;               //ʹ�ñ�������û�������

	private class A extends WindowAdapter{//�ڲ��࣬���ڴ��������ڵ�X
		public void windowClosing(WindowEvent e){	sk.send("BYE-1");	}
	}
	private class ReceiveMsgThread extends Thread{//������Ϣ�߳�
		public void run(){	String msg=null;
			while(true){
				msg=sk.receive();
				if(msg.equals("BYE-1")||msg.equals("BYE-2")) break;
				infoArea.append(msg+"\n");
			}
			if(msg.equals("BYE-1"))	sk.send("BYE-2");
			sk.close();//�رղ���
			System.exit(0);//���Լ�����
		}
	}

	public void setSocketStr(SocketStr s){
		sk=s; //���з��յ�SocketStr���󣬿���ͨѶ��
		sendJTextField.setEnabled(true);
		sendButton.setEnabled(true);

		System.out.println("�����߳�����");
		new ReceiveMsgThread().start(); //����������������Ϣ���߳�
	}
	public ChatFrame(String n, SocketStr s){
		sk=s; name=n; this.setTitle(name);
		setSize(800, 400);
		setLayout(new BorderLayout(5,10)); //ˮƽ���Ϊ5��������Ϊ10

		//----������沿��---begin
		infoArea= new JTextArea();	infoArea.setEditable(false);
		infoArea.setForeground(Color.blue);
		JScrollPane p1=new JScrollPane(infoArea);
		p1.setBorder(new TitledBorder("��Ϣ��ʾ��"));
		add(p1,BorderLayout.CENTER);

		JPanel p2=new JPanel(new BorderLayout(5,10));
		p2.setBorder(new TitledBorder("��Ϣ�༭������"));
		sendJTextField = new JTextField();
		p2.add(sendJTextField,BorderLayout.CENTER);
		sendButton=new JButton("����");
		p2.add(sendButton,BorderLayout.EAST);
		add(p2,BorderLayout.SOUTH);
		setVisible(true);
		//----������沿��---end

		this.addWindowListener(new A());   //���X���ɹرմ���
		sendButton.addActionListener(this);
		sendJTextField.addActionListener(this);

		if(sk==null){//��Ϊ���д��ڣ���ʼʱSocketStr����Ϊ�գ���������������Ϣ�߳�
			sendJTextField.setEnabled(false);
			sendButton.setEnabled(false);
		}else new ReceiveMsgThread().start(); //����������������Ϣ���߳�
	}
	private void sendMsg(){ //����Ϣ + ����Ϣ�����Լ�����Ϣ��
		String msg=sendJTextField.getText().trim();
		if(msg.length()==0) return; //����Ϣ���ݣ�������
		msg=name+"��"+msg;
		infoArea.append(msg+"\n");  //�����͵���Ϣ׷�ӵ�������Ϣ��
		sendJTextField.setText(null);
		sk.send(msg);//����
	}
	//�˷�����Ҫ���ڱ���accept()���������������ӳɹ������Ϣ��ʾ
	public void append(String msg){ infoArea.append(msg+"\n"); }
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==sendButton||e.getSource()==sendJTextField)
			this.sendMsg(); //���߳�ֻ�ܷ��������գ��������������Ϣ����Ϊ�ı�������
	}
}
class Callee4{//���з�
	//ִ�з�ʽ java Callee4 ���� 6666
	public static void main(String[] args)throws IOException{
		SetDefaultFont.setAll(new Font("΢���ź�", Font.BOLD,20));
		String name=args[0];
		int port=Integer.parseInt(args[1]);
		ChatFrame frame=new ChatFrame(name,null);//���з�����SocketStr���󣬹�Ϊnull

		ServerSocket srv= new ServerSocket(port);
		frame.append("���з�������������ȴ��������ӡ���");
		Socket skt = srv.accept();
		frame.append("����Է��������ӣ����Կ�ʼͨ���ˡ���");

		SocketStr sks=new SocketStr(skt); //����ʹ��ServerSocket���ص�Socket
		frame.setSocketStr(sks);          //��frame���ͷ�����Ϣ��SocketStr����
	}
}
class Caller4{//���з�
	//ִ�з�ʽ java Caller4 ���� 127.0.0.1 6666
	public static void main(String[] args){
		SetDefaultFont.setAll(new Font("΢���ź�", Font.BOLD,20));
		String name=args[0];		String ip=args[1];
		int port=Integer.parseInt(args[2]);
		SocketStr sk=new SocketStr(ip, port);
		ChatFrame frame=new ChatFrame(name,sk);
	}
}

//ִ�з�ʽ java Callee4 ���� 6666
//ִ�з�ʽ java Caller4 ���� 127.0.0.1 6666

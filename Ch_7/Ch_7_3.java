import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ConnectException;

class SocketStr{//读写字符串的Socket应用类：即可用于主叫，也可用于被叫
	private Socket socket;         //相当于手机，代表对方
	private BufferedReader in;     //基于socket产生的输入流，相当于听筒
	private PrintWriter out;       //基于socket产生的输出流，相当于麦克风

	//构造函数用于创建socket对象和创建输入输出流。主叫、被叫使用时的参数不同
	public SocketStr(String ip, int port){//由主叫方使用
		try{ socket=new Socket(ip,port);	creatInOut(); }
		catch(ConnectException ee){
			System.out.println("被叫的accept服务未启动，不接受呼叫！");
			System.exit(0);
		}
		catch(Exception e){	System.out.println("有异常："); e.printStackTrace();}
	}
	public SocketStr(Socket sk){//由被叫方使用，其中sk是accept()的返回值
		socket=sk;  creatInOut(); 
	}

	private void creatInOut(){//构造输入流和输出流
		try{
        			out = new PrintWriter(socket.getOutputStream(), true); //true表示自动刷新autoFlush
        			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}//socket.getOutputStream()/getInputStream()可能产生：IOException
		catch(IOException e){ System.out.println("有异常："); e.printStackTrace();}
	}
	public void send(String info){ out.println(info); }	//发送消息info
	public String receive(){//接收消息
		String s=null;
		try{ s=in.readLine(); }
		catch(IOException e){ System.out.println("有异常："); e.printStackTrace();}
		return s;
	}
	public void close(){
		try{ in.close(); out.close(); socket.close(); }
		catch(IOException e){ System.out.println("有异常："); e.printStackTrace();}

	}
}
class Callee3{//被叫方
	public static void main(String[] args){//throws IOException{
		String name="李四";  ServerSocket srv=null;  Socket skt=null;
		try{ srv= new ServerSocket(6666);//本机使用6666端口提供通信服务
		   System.out.println( "服务端启动，等待连接。。。");
		   skt = srv.accept();//无连接时等待，连接成功后返回代表“主叫方”的Socket对象
		   System.out.println( "连接成功！开始通话。。。");
		}catch(IOException e){ System.out.println("有异常："); e.printStackTrace();}
		SocketStr sk=new SocketStr(skt); //必须使用ServerSocket返回的Socket
		//自己创建的Socket对象为何不行？

		String msg; //存放发送或接收的消息

		//发
		msg="还没呢!";                //发送的内容（即自己说的话）
		System.out.println( name+": "+msg);//输出发送的内容
		sk.send( name+": "+msg );          //实施发送
		//收
		msg=sk.receive();          //实施接收
		System.out.println( msg ); //输出收到的信息

		sk.close();//关闭Socket和输入流、输出流
	}
}
class Caller3{//主叫方
	public static void main(String[] args){//throws IOException{
		String name="张三";
		SocketStr sk=new SocketStr("127.0.0.1", 6666);//127.0.0.1代表本机
		//SocketStr sk=new SocketStr("192.168.1.3", 6666);//假设知晓被叫的ip是192.168.1.3
		String msg; //存放发送或接收的消息

		//发
		msg="李四，你吃了吗?"; //发送消息的内容
		System.out.println( name+": "+ msg);//在本地屏幕显示自己说的话
		sk.send(name+": "+msg );    //向对方发送消息
		//收
		msg=sk.receive();//接收对方说的话
		System.out.println( msg );

		sk.close();//关闭Socket和输入流、输出流
	}
}

/*设计一个DatagramEndpoint，内含DatagramPacket、DatagramSocket，可直接发送、接收数据
 *
 * 发送数据报和接收数据报不同
 * 接收数据报：无需知晓何人发送，只需根据缓冲区大小创建数据报
 * 发送数据报：除了缓冲区及大小外，还必须知晓对方的ip和port，这样才能创建数据报
 *
 *  关于创建DatagramSocket
 *  创建时可以不指定端口（随机选一个可用端口），如果指定，作用类似服务器，接收任何发来的数据
 *  作为最初的发送Socket，必须要知道接收方的ip+port
 *
 *  发送数据报，一般面向特定ip+port，如果要更改，需要调整ip+port
 *
 *  接收有两种：
 *  1）接收任何消息：
 *  2）接收反馈的消息
 *
 *  发送也有两种情形：
 *  1）发送反馈消息（从收到的数据报中获取ip+port构造数据报）
 *  2）第一次发送（主动发送）
 *
 *  InetAddress ipv4Address1 = InetAddress.getByName("1.2.3.4");
 *  InetAddress ipv4Address2 = InetAddress.getByName("www.ibm.com");
 *  System.out.println("ipv4Address2:" + ipv4Address2.getHostAddress());
 *  //ipv4Address2:129.42.60.216
 **/
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;

class DatagramEndpoint{
	private DatagramPacket sendPac,receivePac; //发送数据报和接受数据报
	private DatagramSocket socket;
	private InetAddress address; //通信端点要连接的远程ip
	private int port;            //通信端点要连接的远程port
	private byte[] buffer=new byte[1024]; //必须提供足够大的接收消息缓冲区

	public DatagramEndpoint(int port){//作为【被叫】时使用
		try{  socket=new DatagramSocket(port);
		      receivePac=new DatagramPacket(buffer,buffer.length); //创建一个用于接收的数据报
		}catch(SocketException e){ System.out.println("有异常："); e.printStackTrace();}
	}
	public DatagramEndpoint( ){ //作为【主叫】时使用
		try{  socket=new DatagramSocket();//创建发送电报的电报机，端口（即接收消息的端口）为本机的随机可用端口
		      receivePac=new DatagramPacket(buffer,buffer.length); //创建一个用于接收的数据报
		}catch(SocketException e){ System.out.println("有异常："); e.printStackTrace();}
	}
	public void setSocket(String ip, int port){//要连接远程端点的ip和port（由主叫方调用此方法）
		try{   this.port=port;
			address = InetAddress.getByName(ip);//如ip="123.4.5.6" 或是 "www.sina.com"
		}catch(UnknownHostException e){
			System.out.println("有异常："); e.printStackTrace();}
	}
	public void setSocketByDatagramPacket(){//根据收到的数据报来获取对方ip和port（由被叫方调用此方法）
		if(receivePac==null){
			System.out.println("错误：应该先收到数据报才能调用此方法！");
			return;
		}
		address = receivePac.getAddress();
		this.port=receivePac.getPort();
	}
	public void send(String msg){	byte[] buf=msg.getBytes();
		sendPac=new DatagramPacket(buf,buf.length,address,port);
		try{ socket.send(sendPac); }
		catch(IOException e){ System.out.println("有异常："); e.printStackTrace();}
	}
	public String receive(){
		try{ socket.receive(receivePac); }
		catch(IOException e){ System.out.println("有异常："); e.printStackTrace();}
		//return new String(receivePac.getData());
		return new String(buffer,0,receivePac.getLength());
	}
	public void close(){ socket.close(); }
}
class Callee6{//被叫，必须先启动指定端口的DatagramSocket：先收消息再发消息
	public static void main(String[] args){
		DatagramEndpoint endpoint=new DatagramEndpoint(6789);
		//先收再发
		String msg=endpoint.receive();   //【接收消息】：收不到消息就等待
		System.out.println( msg);        //输出收到的内容的内容
		endpoint.setSocketByDatagramPacket(); //获取发送方的ip+port
		String name="李四";		msg=name+"：还没呢!";
		System.out.println( msg);        //输出发送的内容
		endpoint.send(msg);              //【发送消息】
		endpoint.close();
	}
}
class Caller6{//主叫，必须向指定ip+port发消息：先发消息再收消息
	public static void main(String[] args){
		DatagramEndpoint endpoint=new DatagramEndpoint();
		endpoint.setSocket("127.0.0.1", 6789);//需要告知主叫方：被叫的ip和port
		String name="张三";		String msg="李四，你吃了吗?";

		System.out.println( name+"："+msg);//输出发送的内容的内容
		endpoint.send(name+"："+msg);      //【发送消息】
		msg=endpoint.receive();            //【接收消息】
		System.out.println( msg);          //输出接收的内容
		endpoint.close();
	}
}
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

class UDP_C1{
	public static void main (String[] args) {
	   try{  //发送数据报部分 
	   		
			DatagramSocket cli=new DatagramSocket();   //客户端通信端点
			byte[] sb="你好，我是UDPClient。".getBytes();          //将待传输数据存入字节数组
			InetAddress address=InetAddress.getByName("localhost");  //获取本机IP地址
			DatagramPacket pac=new DatagramPacket(sb,sb.length,address,6777);  //将被发送的数据报
			cli.send(pac);   //发送数据报pac
			//接收数据报部分
				sb=new byte[100];
			pac=new DatagramPacket(sb,sb.length);   //创建用于接收数据的数据报
		
			//pac=new DatagramPacket(sb,10); 
			//注意，上句有问题：sb可能太短，无法完全容纳接收数据
			//可考虑使用：pac=new DatagramPacket(sb,1000); 
			cli.receive(pac);                           //将接收的数据报存入pac
			String sen=new String(sb,0,pac.getLength());  //用收到的数据重构字符串
			System.out.println(sen);
		}catch(Exception e){e.printStackTrace(); }
	}
}
class UDP_S1{
	public static void main (String[] args) {
		try{//接收数据报部分
			DatagramSocket ser=new DatagramSocket(6777);  //创建服务端的通信端点
			byte[] rb=new byte[1024];                         //一次最多能接收1024字节
			DatagramPacket pac=new DatagramPacket(rb,rb.length); //创建接收数据的数据报
			ser.receive(pac);                                  //将接收到的数据报存入pac
			System.out.println(new String(rb,0,pac.getLength())); //显示接收到的数据
			//发送数据报部分
			int port=pac.getPort();                 //从收到的数据报获取对方的端口
			InetAddress address=pac.getAddress();  //从收到的数据报获取对方的IP
			rb="Server收到，Server明白!".getBytes();
			pac=new DatagramPacket(rb,rb.length,address,port);
			ser.send(pac);           //发送数据报
		}catch(Exception e){ e.printStackTrace();	}
	}
}

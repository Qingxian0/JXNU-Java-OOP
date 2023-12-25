/*���һ��DatagramEndpoint���ں�DatagramPacket��DatagramSocket����ֱ�ӷ��͡���������
 *
 * �������ݱ��ͽ������ݱ���ͬ
 * �������ݱ�������֪�����˷��ͣ�ֻ����ݻ�������С�������ݱ�
 * �������ݱ������˻���������С�⣬������֪���Է���ip��port���������ܴ������ݱ�
 *
 *  ���ڴ���DatagramSocket
 *  ����ʱ���Բ�ָ���˿ڣ����ѡһ�����ö˿ڣ������ָ�����������Ʒ������������κη���������
 *  ��Ϊ����ķ���Socket������Ҫ֪�����շ���ip+port
 *
 *  �������ݱ���һ�������ض�ip+port�����Ҫ���ģ���Ҫ����ip+port
 *
 *  ���������֣�
 *  1�������κ���Ϣ��
 *  2�����շ�������Ϣ
 *
 *  ����Ҳ���������Σ�
 *  1�����ͷ�����Ϣ�����յ������ݱ��л�ȡip+port�������ݱ���
 *  2����һ�η��ͣ��������ͣ�
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
	private DatagramPacket sendPac,receivePac; //�������ݱ��ͽ������ݱ�
	private DatagramSocket socket;
	private InetAddress address; //ͨ�Ŷ˵�Ҫ���ӵ�Զ��ip
	private int port;            //ͨ�Ŷ˵�Ҫ���ӵ�Զ��port
	private byte[] buffer=new byte[1024]; //�����ṩ�㹻��Ľ�����Ϣ������

	public DatagramEndpoint(int port){//��Ϊ�����С�ʱʹ��
		try{  socket=new DatagramSocket(port);
		      receivePac=new DatagramPacket(buffer,buffer.length); //����һ�����ڽ��յ����ݱ�
		}catch(SocketException e){ System.out.println("���쳣��"); e.printStackTrace();}
	}
	public DatagramEndpoint( ){ //��Ϊ�����С�ʱʹ��
		try{  socket=new DatagramSocket();//�������͵籨�ĵ籨�����˿ڣ���������Ϣ�Ķ˿ڣ�Ϊ������������ö˿�
		      receivePac=new DatagramPacket(buffer,buffer.length); //����һ�����ڽ��յ����ݱ�
		}catch(SocketException e){ System.out.println("���쳣��"); e.printStackTrace();}
	}
	public void setSocket(String ip, int port){//Ҫ����Զ�̶˵��ip��port�������з����ô˷�����
		try{   this.port=port;
			address = InetAddress.getByName(ip);//��ip="123.4.5.6" ���� "www.sina.com"
		}catch(UnknownHostException e){
			System.out.println("���쳣��"); e.printStackTrace();}
	}
	public void setSocketByDatagramPacket(){//�����յ������ݱ�����ȡ�Է�ip��port���ɱ��з����ô˷�����
		if(receivePac==null){
			System.out.println("����Ӧ�����յ����ݱ����ܵ��ô˷�����");
			return;
		}
		address = receivePac.getAddress();
		this.port=receivePac.getPort();
	}
	public void send(String msg){	byte[] buf=msg.getBytes();
		sendPac=new DatagramPacket(buf,buf.length,address,port);
		try{ socket.send(sendPac); }
		catch(IOException e){ System.out.println("���쳣��"); e.printStackTrace();}
	}
	public String receive(){
		try{ socket.receive(receivePac); }
		catch(IOException e){ System.out.println("���쳣��"); e.printStackTrace();}
		//return new String(receivePac.getData());
		return new String(buffer,0,receivePac.getLength());
	}
	public void close(){ socket.close(); }
}
class Callee6{//���У�����������ָ���˿ڵ�DatagramSocket��������Ϣ�ٷ���Ϣ
	public static void main(String[] args){
		DatagramEndpoint endpoint=new DatagramEndpoint(6789);
		//�����ٷ�
		String msg=endpoint.receive();   //��������Ϣ�����ղ�����Ϣ�͵ȴ�
		System.out.println( msg);        //����յ������ݵ�����
		endpoint.setSocketByDatagramPacket(); //��ȡ���ͷ���ip+port
		String name="����";		msg=name+"����û��!";
		System.out.println( msg);        //������͵�����
		endpoint.send(msg);              //��������Ϣ��
		endpoint.close();
	}
}
class Caller6{//���У�������ָ��ip+port����Ϣ���ȷ���Ϣ������Ϣ
	public static void main(String[] args){
		DatagramEndpoint endpoint=new DatagramEndpoint();
		endpoint.setSocket("127.0.0.1", 6789);//��Ҫ��֪���з������е�ip��port
		String name="����";		String msg="���ģ��������?";

		System.out.println( name+"��"+msg);//������͵����ݵ�����
		endpoint.send(name+"��"+msg);      //��������Ϣ��
		msg=endpoint.receive();            //��������Ϣ��
		System.out.println( msg);          //������յ�����
		endpoint.close();
	}
}
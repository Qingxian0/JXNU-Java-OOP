import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

class UDP_C1{
	public static void main (String[] args) {
	   try{  //�������ݱ����� 
	   		
			DatagramSocket cli=new DatagramSocket();   //�ͻ���ͨ�Ŷ˵�
			byte[] sb="��ã�����UDPClient��".getBytes();          //�����������ݴ����ֽ�����
			InetAddress address=InetAddress.getByName("localhost");  //��ȡ����IP��ַ
			DatagramPacket pac=new DatagramPacket(sb,sb.length,address,6777);  //�������͵����ݱ�
			cli.send(pac);   //�������ݱ�pac
			//�������ݱ�����
				sb=new byte[100];
			pac=new DatagramPacket(sb,sb.length);   //�������ڽ������ݵ����ݱ�
		
			//pac=new DatagramPacket(sb,10); 
			//ע�⣬�Ͼ������⣺sb����̫�̣��޷���ȫ���ɽ�������
			//�ɿ���ʹ�ã�pac=new DatagramPacket(sb,1000); 
			cli.receive(pac);                           //�����յ����ݱ�����pac
			String sen=new String(sb,0,pac.getLength());  //���յ��������ع��ַ���
			System.out.println(sen);
		}catch(Exception e){e.printStackTrace(); }
	}
}
class UDP_S1{
	public static void main (String[] args) {
		try{//�������ݱ�����
			DatagramSocket ser=new DatagramSocket(6777);  //��������˵�ͨ�Ŷ˵�
			byte[] rb=new byte[1024];                         //һ������ܽ���1024�ֽ�
			DatagramPacket pac=new DatagramPacket(rb,rb.length); //�����������ݵ����ݱ�
			ser.receive(pac);                                  //�����յ������ݱ�����pac
			System.out.println(new String(rb,0,pac.getLength())); //��ʾ���յ�������
			//�������ݱ�����
			int port=pac.getPort();                 //���յ������ݱ���ȡ�Է��Ķ˿�
			InetAddress address=pac.getAddress();  //���յ������ݱ���ȡ�Է���IP
			rb="Server�յ���Server����!".getBytes();
			pac=new DatagramPacket(rb,rb.length,address,port);
			ser.send(pac);           //�������ݱ�
		}catch(Exception e){ e.printStackTrace();	}
	}
}

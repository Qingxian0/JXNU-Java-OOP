import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ConnectException;

class SocketStr{//��д�ַ�����SocketӦ���ࣺ�����������У�Ҳ�����ڱ���
	private Socket socket;         //�൱���ֻ�������Է�
	private BufferedReader in;     //����socket���������������൱����Ͳ
	private PrintWriter out;       //����socket��������������൱����˷�

	//���캯�����ڴ���socket����ʹ�����������������С�����ʹ��ʱ�Ĳ�����ͬ
	public SocketStr(String ip, int port){//�����з�ʹ��
		try{ socket=new Socket(ip,port);	creatInOut(); }
		catch(ConnectException ee){
			System.out.println("���е�accept����δ�����������ܺ��У�");
			System.exit(0);
		}
		catch(Exception e){	System.out.println("���쳣��"); e.printStackTrace();}
	}
	public SocketStr(Socket sk){//�ɱ��з�ʹ�ã�����sk��accept()�ķ���ֵ
		socket=sk;  creatInOut(); 
	}

	private void creatInOut(){//�����������������
		try{
        			out = new PrintWriter(socket.getOutputStream(), true); //true��ʾ�Զ�ˢ��autoFlush
        			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}//socket.getOutputStream()/getInputStream()���ܲ�����IOException
		catch(IOException e){ System.out.println("���쳣��"); e.printStackTrace();}
	}
	public void send(String info){ out.println(info); }	//������Ϣinfo
	public String receive(){//������Ϣ
		String s=null;
		try{ s=in.readLine(); }
		catch(IOException e){ System.out.println("���쳣��"); e.printStackTrace();}
		return s;
	}
	public void close(){
		try{ in.close(); out.close(); socket.close(); }
		catch(IOException e){ System.out.println("���쳣��"); e.printStackTrace();}

	}
}
class Callee3{//���з�
	public static void main(String[] args){//throws IOException{
		String name="����";  ServerSocket srv=null;  Socket skt=null;
		try{ srv= new ServerSocket(6666);//����ʹ��6666�˿��ṩͨ�ŷ���
		   System.out.println( "������������ȴ����ӡ�����");
		   skt = srv.accept();//������ʱ�ȴ������ӳɹ��󷵻ش������з�����Socket����
		   System.out.println( "���ӳɹ�����ʼͨ��������");
		}catch(IOException e){ System.out.println("���쳣��"); e.printStackTrace();}
		SocketStr sk=new SocketStr(skt); //����ʹ��ServerSocket���ص�Socket
		//�Լ�������Socket����Ϊ�β��У�

		String msg; //��ŷ��ͻ���յ���Ϣ

		//��
		msg="��û��!";                //���͵����ݣ����Լ�˵�Ļ���
		System.out.println( name+": "+msg);//������͵�����
		sk.send( name+": "+msg );          //ʵʩ����
		//��
		msg=sk.receive();          //ʵʩ����
		System.out.println( msg ); //����յ�����Ϣ

		sk.close();//�ر�Socket���������������
	}
}
class Caller3{//���з�
	public static void main(String[] args){//throws IOException{
		String name="����";
		SocketStr sk=new SocketStr("127.0.0.1", 6666);//127.0.0.1������
		//SocketStr sk=new SocketStr("192.168.1.3", 6666);//����֪�����е�ip��192.168.1.3
		String msg; //��ŷ��ͻ���յ���Ϣ

		//��
		msg="���ģ��������?"; //������Ϣ������
		System.out.println( name+": "+ msg);//�ڱ�����Ļ��ʾ�Լ�˵�Ļ�
		sk.send(name+": "+msg );    //��Է�������Ϣ
		//��
		msg=sk.receive();//���նԷ�˵�Ļ�
		System.out.println( msg );

		sk.close();//�ر�Socket���������������
	}
}

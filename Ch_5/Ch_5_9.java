/*����5.9������������ԭ����Ϊ�ַ����ʼǱ����������������м��ұ����ĸ��̡߳�
 *��Ϊ�䲹�䲻ͬ��ɫ����ǣ���ѡ��ɫ����ɫ����ɫ����ɫ������������ʼǱ�����ɫ����
 *��Ϊ�䲹��CPU����ѡi3��i5��i7���������ַ�����Ϊ���ʼǱ�����ɫ i5���ȣ�
 *���߳��ڼ׵Ļ����ϲ����Կ�����ѡ���ԡ����ԣ�����������ʼǱ�����ɫ i5 ���ԡ���
 *���߳����ҵĻ����ϲ����ڴ���������ѡ4G��8G��16G������������ʼǱ�����ɫ i5 ���� 8G����
 *ÿ����Ʒ��ɺ����"��Ʒx���"������x�ǵ�x��������
 *Ҫ�󣬼��ұ������μӹ�����ֻ��������һ���ʼǱ������ܽ�����һ�εļӹ�����ʵ��5�μӹ���
 **/
import java.util.Random;
class ProductLine{
	private String str,initStr;  //str����ӹ��еĲ�Ʒ,initStr�ǳ�ʼԭ��
	private int state=0;        //״̬�����������ĸ��߳̿�ִ��
	public ProductLine(String s){str=s; initStr=s;}
	public void init(){str=initStr;}
	public void readWrite(int myState, int nextState, String s){
		//����ǰΪ״̬myState�������ΪnextState,sΪ�ӹ�������
		while(state!=myState)try{ wait();}catch(InterruptedException e){;}
		str=str+" "+s;//�Բ�Ʒ�ӹ�
		state=nextState;    notifyAll(); //��״̬����֪ͨ
	}
	public String getData(){return str;}
}
class ProcessNode extends Thread{//�����ߵ��������
	private ProductLine p;  //����Ϊ�ٽ���Դ
	private String[] data;  //data�Ǳ��ڵ�ļӹ�����
	private final int turn; //��������
	private int myState, nextState; //��p��״̬ΪmyState��ִ�У�ִ�к����ΪnextState
	public ProcessNode(ProductLine b,String[] d, int my, int next, int t){
		 p=b; data=d; myState=my; nextState=next; turn=t;
	}
	public void run(){   //��������
		int i,j,len=data.length;  Random r=new Random();
		for (i = 1; i<=turn; i++){	j=r.nextInt(len);
			synchronized(p){p.readWrite(myState,nextState,data[j]);} //ִ��ʱ��ӹ�p�е�����
			//try{sleep(1);}catch(InterruptedException e){;}
		}
	}
}

 class EndNode extends Thread{//������㣺ʵʩ��Ʒ��ɺ�Ĳ���
	private ProductLine p;
	private int myState, nextState;
	private final int turn;
	public EndNode(ProductLine b,int my, int next, int t){
		p=b; myState=my; nextState=next; turn =t;}
	public void run(){
		for (int i = 1; i<=turn; i++){
			synchronized(p){//����ַ���������װ�䡢�������ʼ��
				p.readWrite(myState,nextState," �� "+i+" �����\n");
				System.out.print(p.getData());//������յ��ַ���
				p.init();//��Ʒ�߳�ʼ��
			}
			try{sleep(1);}catch(InterruptedException e){;}
		}
	}
}
public class Ch_5_9{
	public static void main (String[] args){
		String str="�ʼǱ���";
		String[] s1={"��ɫ","��ɫ","��ɫ","��ɫ"};
		String[] s2={"i3","i5","i7"};
		String[] s3={"����","����"};
		String[] s4={" 4G"," 8G","16G"};
		ProductLine p=new ProductLine(str);
		ProcessNode j,y,b,d; //����ס��ҡ��������ĸ��߳�
		j=new ProcessNode(p,s1,0,1,5);		y=new ProcessNode(p,s2,1,2,5);
		b=new ProcessNode(p,s3,2,3,5);		d=new ProcessNode(p,s4,3,4,5);
		EndNode end=new EndNode(p,4,0,5);
		System.out.print("��ʼ������\n");
		j.start();y.start();b.start();d.start();end.start();
		try{j.join();y.join();b.join();d.join();end.join();}
			catch(InterruptedException e){;}
		System.out.print("����������\n");
	}
}

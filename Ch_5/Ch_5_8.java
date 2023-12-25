/*���ܣ�ģ������������������
 *ע�⣺���put��get����ʵʩͬ��������synchronized������ʵ�������Ľ��ࣻ
 *����������������ѵ���������ʵʩͬ��
 **/
class BufferArea{
	private int d;                       //���ڴ�Ų�Ʒ
	private boolean isEmpty=true;        //��ǻ������Ƿ�Ϊ��
	public synchronized void put(int i){ //����1
	//public void put(int i){ //����2
	    //���������գ�����벢����֪ͨ�����򣬵ȴ���
		while(!isEmpty)	try{ wait();}catch(InterruptedException e){;}
		d=i;    isEmpty=false;    notify();
	}
	public synchronized int get(){ //����1
	//public int get(){                // ����2
	    //���������գ���ȴ����������Ѳ�����֪ͨ��
		while(isEmpty)try{ wait(); }catch(InterruptedException e){;}
		isEmpty=true;   notify();    return d;
	}
}
class Producer extends Thread{
	private BufferArea ba;
	public Producer(BufferArea b){ ba=b; }
	public void run(){   //��������
		for (int i = 1; i<6; i++){
			//synchronized(ba){  //ʹ�ò���1ʱ��ע�ʹ˾�
				ba.put(i);
				System.out.print("Producer put: "+i);
				try{sleep(1);}   //ּ��ǿ�Ʒ����߳��л����Լ���л�������Ƿ�����
				catch(InterruptedException e){;}
			//} //end synchronized��ʹ�ò���1ʱ��ע�ʹ˾�
		}//end for
	}
}
class Consumer extends Thread{
	private BufferArea ba;
	public Consumer(BufferArea b){ ba=b; }
	public void run(){      //���ѹ���
		for (int i = 1; i<6; i++){
			//synchronized(ba){   //ʹ�ò���1ʱ��ע�ʹ˾�
				System.out.print("\t Consumer get: "+ba.get()+"\n");
				try{sleep(1);}             //ּ��ǿ�Ʒ����߳��л�
				catch(InterruptedException e){;}
			//}//end synchronized��ʹ�ò���1ʱ��ע�ʹ˾�
		}
	}
}
public class Ch_5_8{
	public static void main (String[] args) {
		BufferArea b=new BufferArea();
		(new Consumer(b)).start();
		(new Producer(b)).start();
	}
}

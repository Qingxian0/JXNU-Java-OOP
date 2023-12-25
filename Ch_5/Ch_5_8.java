/*功能：模拟生产者消费者问题
 *注意：针对put、get方法实施同步，即用synchronized，不能实现完整的节奏；
 *必须针对生产、消费的整个过程实施同步
 **/
class BufferArea{
	private int d;                       //用于存放产品
	private boolean isEmpty=true;        //标记缓冲区是否为空
	public synchronized void put(int i){ //策略1
	//public void put(int i){ //策略2
	    //若缓冲区空，则放入并发出通知；否则，等待。
		while(!isEmpty)	try{ wait();}catch(InterruptedException e){;}
		d=i;    isEmpty=false;    notify();
	}
	public synchronized int get(){ //策略1
	//public int get(){                // 策略2
	    //若缓冲区空，则等待；否则，消费并发出通知。
		while(isEmpty)try{ wait(); }catch(InterruptedException e){;}
		isEmpty=true;   notify();    return d;
	}
}
class Producer extends Thread{
	private BufferArea ba;
	public Producer(BufferArea b){ ba=b; }
	public void run(){   //生产过程
		for (int i = 1; i<6; i++){
			//synchronized(ba){  //使用策略1时需注释此句
				ba.put(i);
				System.out.print("Producer put: "+i);
				try{sleep(1);}   //旨在强制发生线程切换，以检测切换后节奏是否被扰乱
				catch(InterruptedException e){;}
			//} //end synchronized：使用策略1时需注释此句
		}//end for
	}
}
class Consumer extends Thread{
	private BufferArea ba;
	public Consumer(BufferArea b){ ba=b; }
	public void run(){      //消费过程
		for (int i = 1; i<6; i++){
			//synchronized(ba){   //使用策略1时需注释此句
				System.out.print("\t Consumer get: "+ba.get()+"\n");
				try{sleep(1);}             //旨在强制发生线程切换
				catch(InterruptedException e){;}
			//}//end synchronized：使用策略1时需注释此句
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

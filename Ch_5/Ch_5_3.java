class MyThread implements Runnable{
	private int n,max; 
	Thread t;
	public MyThread(String name, int n1, int max1){ 
		n=n1; max=max1;	t=new Thread(this,name); //t是真正的线程对象
	}
	public void start(){ t.start(); }//这不是对Thread类中start()的重写
	public void run(){ int i=1;
		while(n*i<=max){ System.out.print(n*i+" "); i++; }
		System.out.print(t.getName()+" 结束."); //借助t获取线程名字
	} 
} 
class Ch_5_3{
	public static void main (String[] args) {
		System.out.print("Main 开始");
		MyThread m1=new MyThread("A",2,70);
		MyThread m2=new MyThread("B",3,70);
		MyThread m3=new MyThread("C",5,70);
		m1.start(); m2.start();m3.start();
		System.out.print("当前共有"+ Thread.activeCount()+"个线程");
		System.out.print("Main 结束");
	}
}
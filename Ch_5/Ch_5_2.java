class MyThread implements Runnable{
	private int n,max;
	public MyThread(int n1, int max1){ 	n=n1; max=max1;	}
	public void run(){ int i=1;
		while(n*i<=max){ System.out.print(n*i+" "); i++; }
		System.out.print(Thread.currentThread().getName()+" 结束.");
	} 
} 
class Ch_5_2{
	public static void main (String[] args) {
		System.out.print("Main 开始");
		MyThread m1,m2,m3;  
		Thread t1,t2,t3;
		m1=new MyThread(2,70);   //注：m1不是线程对象
		m2=new MyThread(3,70);   m3=new MyThread(5,70);
		t1=new Thread(m1,"A");	   //t1才是线程	
		t3=new Thread(m3,"C");   t2=new Thread(m2,"B");
		t1.start(); t2.start();t3.start();   //启动线程 --- 仅仅是有资格运行
		System.out.print("当前共有"+ Thread.activeCount()+"个线程");
		System.out.print("Main 结束");
	}
}
class MyThread extends Thread{
	private int n,max;
	public MyThread(String name, int n1, int max1){ 
		super(name); n=n1; max=max1;
	}
	public void run(){ int i=1;
		while(n*i<=max){ System.out.print(n*i+" "); i++; }
		System.out.print(getName()+" 结束.");
	} 
}
class Ch_5_1{
	public static void main (String[] args) {
		System.out.print("Main 开始");
		MyThread m1=new MyThread("A",2,70);   //创建A线程（未启动）
		MyThread m2=new MyThread("B",3,70);
		MyThread m3=new MyThread("C",5,70);
		m1.start(); m2.start();m3.start();   //启动线程 --- 仅仅是有资格运行
		System.out.print("当前共有"+ Thread.activeCount()+"个线程");
		System.out.print("Main 结束");
	}
}
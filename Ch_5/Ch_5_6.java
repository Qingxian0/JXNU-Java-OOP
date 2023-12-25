class Printer extends Thread{
	private String flag;
	private String name;
	private String[] content;
	public Printer(String f,String n, String []s){flag=f; name=n; content=s;}
	public void run(){
		synchronized(flag){
			System.out.print(name+" : ");
			for(String x: content){
				System.out.print(" "+x);
				try{sleep(10); } catch(InterruptedException e)  {;}
			}
			System.out.println ();
		}
	}
}
class Ch_5_6{
	public static void main (String[] args){
		String shareFlag="abc"; //线程间的共享资源
		String[] s1={"1","2","3"};
		String[] s2={"A","B","C","D","E"};
		String[] s3={"你好，","我好，","大家都好！"};
		Printer p1=new Printer(shareFlag,"张三",s1);
		Printer p2=new Printer(shareFlag,"李四",s2);
		Printer p3=new Printer(shareFlag,"王五",s3);
		p1.start ();  p2.start ();   p3.start ();
		try{p1.join ();    p2.join ();     p3.join ();}
			catch(InterruptedException e){;}
		System.out.print("所有打印作业结束。");
	}
}
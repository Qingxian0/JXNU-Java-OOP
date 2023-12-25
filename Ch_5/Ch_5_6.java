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
		String shareFlag="abc"; //�̼߳�Ĺ�����Դ
		String[] s1={"1","2","3"};
		String[] s2={"A","B","C","D","E"};
		String[] s3={"��ã�","�Һã�","��Ҷ��ã�"};
		Printer p1=new Printer(shareFlag,"����",s1);
		Printer p2=new Printer(shareFlag,"����",s2);
		Printer p3=new Printer(shareFlag,"����",s3);
		p1.start ();  p2.start ();   p3.start ();
		try{p1.join ();    p2.join ();     p3.join ();}
			catch(InterruptedException e){;}
		System.out.print("���д�ӡ��ҵ������");
	}
}
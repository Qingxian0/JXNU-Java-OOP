class T{
	class MyThread extends Thread{
		private int n,max;
		public MyThread(String name, int n1, int max1){ 
			super(name); n=n1; max=max1;
		}
		public void run(){ int i=1;
			while(n*i<=max){ System.out.print(n*i+" "); i++; }
			System.out.print(getName()+" ����.");
		} 
	}
	public T(String name, int n1, int max1){
		MyThread m=new MyThread(name,n1,max1);
		m.start();
	}
}
class Ch_5_4{
	public static void main (String[] args) {
		System.out.print("Main ��ʼ");    T t1,t2,t3;
		t1=new T("A",2,70);	t2=new T("B",3,70);	t3=new T("C",5,70);	
		System.out.print("��ǰ����"+ Thread.activeCount()+"���߳�");
		System.out.print("Main ����");
	}
}
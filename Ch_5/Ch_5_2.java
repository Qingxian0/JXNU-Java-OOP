class MyThread implements Runnable{
	private int n,max;
	public MyThread(int n1, int max1){ 	n=n1; max=max1;	}
	public void run(){ int i=1;
		while(n*i<=max){ System.out.print(n*i+" "); i++; }
		System.out.print(Thread.currentThread().getName()+" ����.");
	} 
} 
class Ch_5_2{
	public static void main (String[] args) {
		System.out.print("Main ��ʼ");
		MyThread m1,m2,m3;  
		Thread t1,t2,t3;
		m1=new MyThread(2,70);   //ע��m1�����̶߳���
		m2=new MyThread(3,70);   m3=new MyThread(5,70);
		t1=new Thread(m1,"A");	   //t1�����߳�	
		t3=new Thread(m3,"C");   t2=new Thread(m2,"B");
		t1.start(); t2.start();t3.start();   //�����߳� --- ���������ʸ�����
		System.out.print("��ǰ����"+ Thread.activeCount()+"���߳�");
		System.out.print("Main ����");
	}
}
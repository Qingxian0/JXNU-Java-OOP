class MyThread implements Runnable{
	private int n,max; 
	Thread t;
	public MyThread(String name, int n1, int max1){ 
		n=n1; max=max1;	t=new Thread(this,name); //t���������̶߳���
	}
	public void start(){ t.start(); }//�ⲻ�Ƕ�Thread����start()����д
	public void run(){ int i=1;
		while(n*i<=max){ System.out.print(n*i+" "); i++; }
		System.out.print(t.getName()+" ����."); //����t��ȡ�߳�����
	} 
} 
class Ch_5_3{
	public static void main (String[] args) {
		System.out.print("Main ��ʼ");
		MyThread m1=new MyThread("A",2,70);
		MyThread m2=new MyThread("B",3,70);
		MyThread m3=new MyThread("C",5,70);
		m1.start(); m2.start();m3.start();
		System.out.print("��ǰ����"+ Thread.activeCount()+"���߳�");
		System.out.print("Main ����");
	}
}
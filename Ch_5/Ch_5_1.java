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
class Ch_5_1{
	public static void main (String[] args) {
		System.out.print("Main ��ʼ");
		MyThread m1=new MyThread("A",2,70);   //����A�̣߳�δ������
		MyThread m2=new MyThread("B",3,70);
		MyThread m3=new MyThread("C",5,70);
		m1.start(); m2.start();m3.start();   //�����߳� --- ���������ʸ�����
		System.out.print("��ǰ����"+ Thread.activeCount()+"���߳�");
		System.out.print("Main ����");
	}
}
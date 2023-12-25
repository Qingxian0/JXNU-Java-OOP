//���ܣ�ģ��PC�����ܲ�װ�����и���PCI�豸
//���磺��������5��PCI��ۣ������������Ⱦ�ΪPCI�豸
//�����ϲ�װ�����������г�����Ч��

interface PCI{//�κ�һ��PCI�豸�������������С�ֹͣ
	void start();
	void run();
	void stop();
}
class DisplayCard implements PCI{//�Կ�
	//ʵ�ֽӿڣ����ǶԽӿ��ж�������г��󷽷��ṩ������
	//ע�⣺���ڽӿ��еķ�����Ϊpublic����������д�ķ����������public
	public void start() {System.out.print("\t�Կ�����");};
	public void run(){System.out.print("--�Կ�����");};
	public void stop(){System.out.print("\t�Կ�ֹͣ");};
}
class NetCard implements PCI{//����
	//ʵ�ֽӿڣ����ǶԽӿ��ж�������г��󷽷��ṩ������
	//ע�⣺���ڽӿ��еķ�����Ϊpublic����������д�ķ����������public
	public void start() {System.out.print("\t��������");};
	public void run(){System.out.print("--��������");};
	public void stop(){System.out.print("\t����ֹͣ");};
}
class SoundCard implements PCI{//����
	public void start() {System.out.print("\t��������");};
	public void run(){System.out.print("--��������");};
	public void stop(){System.out.print("\t����ֹͣ");};

}
//��������࣬�ɲ�װ����PCI�豸
class Mainboard{
	PCI[] pci=new PCI[5]; //�����ṩ5��PCI���
	void add(PCI p) {     //���������PCI�豸p
		for(int i=0; i<pci.length; i++)
			if(pci[i]==null) {pci[i]=p; return;}
	}
	void run() {//���������ϵ������豸
		for(int i=0; i<pci.length; i++)
			if(pci[i]!=null) { pci[i].start();  pci[i].run();}
	}
	void stop() {//ֹͣ�����ϵ������豸
		for(int i=0; i<pci.length; i++)
			if(pci[i]!=null) pci[i].stop();
	}
}
class Computer{
	private Mainboard mb=new Mainboard();
	Computer(){	//��������ʱҪ������ְ忨
		mb.add(new DisplayCard());
		mb.add(new SoundCard()); mb.add(new NetCard());}
	void start() { System.out.print("��������");mb.run();}
	void stop() { System.out.print("\n���ػ���");mb.stop();}
}
class App {
	public static void main(String[] args) {
		Computer c=new Computer();
		c.start();		c.stop();
	}
}


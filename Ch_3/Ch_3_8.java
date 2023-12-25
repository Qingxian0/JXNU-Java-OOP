//功能：模拟PC机上能插装并运行各种PCI设备
//例如：主板上有5个PCI插槽，声卡、网卡等均为PCI设备
//主板上插装声卡，可运行出声卡效果

interface PCI{//任何一个PCI设备都能启动、运行、停止
	void start();
	void run();
	void stop();
}
class DisplayCard implements PCI{//显卡
	//实现接口，就是对接口中定义的所有抽象方法提供方法体
	//注意：由于接口中的方法均为public，故下面重写的方法必须加上public
	public void start() {System.out.print("\t显卡启动");};
	public void run(){System.out.print("--显卡运行");};
	public void stop(){System.out.print("\t显卡停止");};
}
class NetCard implements PCI{//网卡
	//实现接口，就是对接口中定义的所有抽象方法提供方法体
	//注意：由于接口中的方法均为public，故下面重写的方法必须加上public
	public void start() {System.out.print("\t网卡启动");};
	public void run(){System.out.print("--网卡运行");};
	public void stop(){System.out.print("\t网卡停止");};
}
class SoundCard implements PCI{//声卡
	public void start() {System.out.print("\t声卡启动");};
	public void run(){System.out.print("--声卡运行");};
	public void stop(){System.out.print("\t声卡停止");};

}
//设计主板类，可插装各种PCI设备
class Mainboard{
	PCI[] pci=new PCI[5]; //主板提供5个PCI插槽
	void add(PCI p) {     //向主板插入PCI设备p
		for(int i=0; i<pci.length; i++)
			if(pci[i]==null) {pci[i]=p; return;}
	}
	void run() {//运行主板上的所有设备
		for(int i=0; i<pci.length; i++)
			if(pci[i]!=null) { pci[i].start();  pci[i].run();}
	}
	void stop() {//停止主板上的所有设备
		for(int i=0; i<pci.length; i++)
			if(pci[i]!=null) pci[i].stop();
	}
}
class Computer{
	private Mainboard mb=new Mainboard();
	Computer(){	//创建对象时要插入各种板卡
		mb.add(new DisplayCard());
		mb.add(new SoundCard()); mb.add(new NetCard());}
	void start() { System.out.print("【开机】");mb.run();}
	void stop() { System.out.print("\n【关机】");mb.stop();}
}
class App {
	public static void main(String[] args) {
		Computer c=new Computer();
		c.start();		c.stop();
	}
}


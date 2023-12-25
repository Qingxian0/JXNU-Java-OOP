package homework.week10.Answers;
/*
4、微信程序中可以插装青桔等小程序。某公司编写了一款名为“菜场”的软件，计划支持插装小程序，插装标准为
CaiChangApplet，符合该标准的小程序都可插装到菜场中。该标准内有start()、run()、stop()三项功能。
main中演示了插装“白菜”、“萝卜”等小程序，以及插装后的运行结果。要求，菜场中至多能插装10个小程序。
为简化处理，添加小程序时，可不考虑小程序插满的情形。
请完成CaiChangApplet标准的设计，以及菜场（CaiChang）、白菜（BaiCai）、萝卜（LuoBo）等类的设计。
class App1{
	public static void main (String[] args) {
		CaiChang c=new CaiChang();
		BaiCai bc=new BaiCai();
		LuoBo lb=new LuoBo();
		c.add(bc); c.add(lb);
		c.run();
	}
}
【运行结果为：】
白菜启动    白菜运行    白菜停止
萝卜启动    萝卜运行    萝卜停止
 */
interface CaiChangApplet {// 任何一个小程序都能启动、运行、停止
	void start();

	void run();

	void stop();
}

class BaiCai implements CaiChangApplet {// 白菜小程序
	// 实现接口，就是对接口中定义的所有抽象方法提供方法体
	// 注意：由于接口中的方法均为public，故下面重写的方法必须加上public
	public void start() {
		System.out.print("白菜启动");
	}

	public void run() {
		System.out.print("   白菜运行");
	}

	public void stop() {
		System.out.println("   白菜停止");
	}
}

class LuoBo implements CaiChangApplet {// 萝卜小程序
	// 实现接口，就是对接口中定义的所有抽象方法提供方法体
	// 注意：由于接口中的方法均为public，故下面重写的方法必须加上public
	public void start() {
		System.out.print("萝卜启动");
	};

	public void run() {
		System.out.print("   萝卜运行");
	};

	public void stop() {
		System.out.println("   萝卜停止");
	};
}

// 设计菜场类，可插装各种蔬菜小程序
class CaiChang {
	CaiChangApplet[] CC = new CaiChangApplet[10]; // 菜场中至多能插装10个小程序

	void add(CaiChangApplet c) { // 向菜场插入小程序
		for (int i = 0; i < CC.length; i++)
			if (CC[i] == null) {
				CC[i] = c;
				return;
			}
	}

	void run() {// 运行菜场中的所有小程序
		for (int i = 0; i < CC.length; i++)
			if (CC[i] != null) {
				CC[i].start();
				CC[i].run();
				CC[i].stop();
			}
	}
}

class App4 {
	public static void main(String[] args) {
		CaiChang c = new CaiChang();
		BaiCai bc = new BaiCai();
		LuoBo lb = new LuoBo();
		c.add(bc);
		c.add(lb);
		c.run();
	}
}

/*
二、设计一个综合性的形状计算器。功能为：对一组不同的形状（梯形、三角形、圆形、正方形）对象，
能正确识别形状类型，显示其周长、面积和对象的个性化信息。例如输出信息为：
    类型：圆形，周长=XXX，面积=XXX，半径=XXX  或是  
    类型：梯形，周长=“无法计算”，面积=XXX，上底=XXX 下底=XXX 高=XXX
【要求】
1、提示：类型的个性化信息，如可考虑toString()；
2、若梯形、三角形周长无法计算，则显示“周长=无法计算”
3、该形状计算器必须单独提供计算周长、面积的服务。
   提示：若遇到周长、面积为-1，则提示：“无法计算！”

*/

class XingZhuang {
	private String type;

	public int getMJ() {
		return -1;
	} // 面积

	public int getZC() {
		return -1;
	} // 周长

	public XingZhuang(String typeName) {
		type = typeName;
	}

	public String getType() {
		return type;
	}
}

class ShiBieQi {
	static void shiBie(XingZhuang x) {
		int y = x.getZC();
		String zc = y + "";// 转成字符串
		if (y == -1)
			zc = "无法计算";
		System.out.print("类型：" + x.getType() + "，周长：" + zc);
		System.out.print("，面积：" + x.getMJ() + x + "\n");
	}
}

class Yuan extends XingZhuang {
	private int r, pi = 3;

	public Yuan(int x) {
		super("圆形");
		r = x;
	}

	public int getMJ() {
		return pi * r * r;
	} // 面积

	public int getZC() {
		return 2 * pi * r;
	} // 周长

	public String toString() {
		return "，半径：" + r;
	}// 形状相关信息之外的个性化信息
}

class ZFX extends XingZhuang {// 正方形
	private int a;

	public ZFX(int x) {
		super("正方形");
		a = x;
	}

	public int getMJ() {
		return a * a;
	} // 面积

	public int getZC() {
		return 4 * a;
	} // 周长

	public String toString() {
		return "，边长：" + a;
	}// 形状相关信息之外的个性化信息
}

class TX extends XingZhuang {// 梯形
	private int sd, xd, h;

	public TX(int x, int y, int z) {
		super("梯形");
		sd = x;
		xd = y;
		h = z;
	}

	public int getMJ() {
		return (sd + xd) * h / 2;
	} // 面积
	// public int getZC() { return -1; } //周长无法计算，不要乱写，即用超类的，以便统一处理

	public String toString() {
		return "，上底：" + sd + "，下底：" + xd + "，高：" + h;
	}
}

class SJX extends XingZhuang {// 三角形
	private int d, h;

	public SJX(int x, int y) {
		super("三角形");
		d = x;
		h = y;
	}

	public int getMJ() {
		return d * h / 2;
	} // 面积
	// public int getZC() { return -1; } //周长无法计算，不要乱写，即用超类的，以便统一处理

	public String toString() {
		return "，底：" + d + "，高：" + h;
	}
}

class TestXZ {
	public static void main(String[] args) {
		XingZhuang[] xz = { new Yuan(2), new ZFX(5), new TX(4, 5, 2), new SJX(10, 20) };
		for (XingZhuang x : xz)
			ShiBieQi.shiBie(x);
	}
}
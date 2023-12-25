/*
�������һ���ۺ��Ե���״������������Ϊ����һ�鲻ͬ����״�����Ρ������Ρ�Բ�Ρ������Σ�����
����ȷʶ����״���ͣ���ʾ���ܳ�������Ͷ���ĸ��Ի���Ϣ�����������ϢΪ��
    ���ͣ�Բ�Σ��ܳ�=XXX�����=XXX���뾶=XXX  ����  
    ���ͣ����Σ��ܳ�=���޷����㡱�����=XXX���ϵ�=XXX �µ�=XXX ��=XXX
��Ҫ��
1����ʾ�����͵ĸ��Ի���Ϣ����ɿ���toString()��
2�������Ρ��������ܳ��޷����㣬����ʾ���ܳ�=�޷����㡱
3������״���������뵥���ṩ�����ܳ�������ķ���
   ��ʾ���������ܳ������Ϊ-1������ʾ�����޷����㣡��

*/

class XingZhuang {
	private String type;

	public int getMJ() {
		return -1;
	} // ���

	public int getZC() {
		return -1;
	} // �ܳ�

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
		String zc = y + "";// ת���ַ���
		if (y == -1)
			zc = "�޷�����";
		System.out.print("���ͣ�" + x.getType() + "���ܳ���" + zc);
		System.out.print("�������" + x.getMJ() + x + "\n");
	}
}

class Yuan extends XingZhuang {
	private int r, pi = 3;

	public Yuan(int x) {
		super("Բ��");
		r = x;
	}

	public int getMJ() {
		return pi * r * r;
	} // ���

	public int getZC() {
		return 2 * pi * r;
	} // �ܳ�

	public String toString() {
		return "���뾶��" + r;
	}// ��״�����Ϣ֮��ĸ��Ի���Ϣ
}

class ZFX extends XingZhuang {// ������
	private int a;

	public ZFX(int x) {
		super("������");
		a = x;
	}

	public int getMJ() {
		return a * a;
	} // ���

	public int getZC() {
		return 4 * a;
	} // �ܳ�

	public String toString() {
		return "���߳���" + a;
	}// ��״�����Ϣ֮��ĸ��Ի���Ϣ
}

class TX extends XingZhuang {// ����
	private int sd, xd, h;

	public TX(int x, int y, int z) {
		super("����");
		sd = x;
		xd = y;
		h = z;
	}

	public int getMJ() {
		return (sd + xd) * h / 2;
	} // ���
	// public int getZC() { return -1; } //�ܳ��޷����㣬��Ҫ��д�����ó���ģ��Ա�ͳһ����

	public String toString() {
		return "���ϵף�" + sd + "���µף�" + xd + "���ߣ�" + h;
	}
}

class SJX extends XingZhuang {// ������
	private int d, h;

	public SJX(int x, int y) {
		super("������");
		d = x;
		h = y;
	}

	public int getMJ() {
		return d * h / 2;
	} // ���
	// public int getZC() { return -1; } //�ܳ��޷����㣬��Ҫ��д�����ó���ģ��Ա�ͳһ����

	public String toString() {
		return "���ף�" + d + "���ߣ�" + h;
	}
}

class TestXZ {
	public static void main(String[] args) {
		XingZhuang[] xz = { new Yuan(2), new ZFX(5), new TX(4, 5, 2), new SJX(10, 20) };
		for (XingZhuang x : xz)
			ShiBieQi.shiBie(x);
	}
}
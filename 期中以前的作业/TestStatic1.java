/*
һ�������������Triangle�������ߣ���Ϊ�������Ҳ��������޸ģ���������Ҫ��
1������ʱ�������߲���ȷ���޷���������������ȷ�����๹���3�����Ƕ���
*/

class Triangle {// ��Ե�1�ʣ���2�����ⵥ����
	private int a, b, c;
	private static int count = 0, total = 3;

	private Triangle(int x, int y, int z) {
		a = x;
		b = y;
		z = c;
		count++;
	}

	public static boolean limit(int x, int y, int z) {
		return x > 0 && y > 0 && z > 0 && (x + y > z) && (x + z > y) && (y + z > x)
				&& count < total;
	}

	public static Triangle create(int x, int y, int z) {
		if (limit(x, y, z) == false)
			return null;
		return new Triangle(x, y, z);
	}

	public String toString() {
		return "a=" + a + ", b=" + b + ", c=" + c;
	}
}

class Test1 {
	public static void main(String[] args) {
		Triangle[] t = new Triangle[5];
		t[0] = Triangle.create(1, 2, 3);
		t[1] = Triangle.create(2, 2, 3);
		t[2] = Triangle.create(2, 2, 3);
		t[3] = Triangle.create(2, 2, 3);
		t[4] = Triangle.create(2, 2, 3);
		for (Triangle x : t)
			System.out.println(x);
	}
}

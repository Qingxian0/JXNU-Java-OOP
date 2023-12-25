/*
一、设计三角形类Triangle，有三边，均为正数，且不得任意修改：满足如下要求：
1、构造时，若三边不正确，无法创建出对象；若正确，至多构造出3个三角对象；
*/

class Triangle {// 针对第1问（第2问另外单做）
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

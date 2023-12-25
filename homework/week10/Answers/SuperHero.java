package homework.week10.Answers;/*
构造超级英雄类SuperHero，该类有String型常量属性name，其值固定为“谭雅”、“伊万”。要求：
(1) 在程序运行期间，至多只能造出一个对象，要么是谭雅，要么是伊万。
(2) 如何实现：在程序运行期间，至多只能造出两个对象，其中一个是谭雅，另一个是伊万。
注：(1)、(2)代表两种功能需求，不是要求同时实现。

*/

//(1)实现：在程序运行期间，至多只能造出一个对象，要么是谭雅，要么是伊万。这题本质上与三角形创建非常类似
class SuperHero1 { // 对应至多只能造1个
	private String name;
	private static int count = 0, total = 1;

	private SuperHero1(String s) {
		name = s;
		count++;
	}

	private static boolean limit(String s) {
		return (s.equals("谭雅") || s.equals("伊万")) && count < total;
	}

	public static SuperHero1 creat(String s) {
		if (limit(s) == false)
			return null;
		return new SuperHero1(s);
	}

	public String toString() {
		return name;
	}
}

// (2) 实现：在程序运行期间，至多只能造出两个对象，其中一个是谭雅，另一个是伊万。
class SuperHero2 { // 对应至多只能造2个，且2个时只能是1个谭雅、1个伊万
	private String name;
	private static int ty = 0, yw = 0, tyTotal = 1, ywTotal = 1; // 谭雅、伊万的数量

	private SuperHero2(String s) {
		name = s;
		if (s.equals("谭雅"))
			ty++;
		if (s.equals("伊万"))
			yw++;
	}

	private static boolean limit(String s) {
		return s.equals("谭雅") && ty < tyTotal || s.equals("伊万") && yw < ywTotal;
	}

	public static SuperHero2 creat(String s) {
		if (limit(s) == false)
			return null;
		return new SuperHero2(s);
	}

	public String toString() {
		return name;
	}
}

class App1 {
	public static void main(String[] args) {
		// 验证1：在程序运行期间，至多只能造出一个对象，要么是谭雅，要么是伊万。
		SuperHero1[] s1 = new SuperHero1[4];
		s1[0] = SuperHero1.creat("谭雅");
		s1[1] = SuperHero1.creat("伊万");
		s1[2] = SuperHero1.creat("谭雅");
		s1[3] = SuperHero1.creat("伊万");
		System.out.print("测试  至多只能造出一个对象，要么是谭雅，要么是伊万：\n");
		for (SuperHero1 x : s1)
			System.out.print(x + "、");

		SuperHero2[] s2 = new SuperHero2[4];
		s2[0] = SuperHero2.creat("谭雅");
		s2[1] = SuperHero2.creat("伊万");
		s2[2] = SuperHero2.creat("谭雅");
		s2[3] = SuperHero2.creat("伊万");
		System.out.print("\n\n测试  至多只能造2个，且2个时只能是1个谭雅、1个伊万：\n");
		for (SuperHero2 x : s2)
			System.out.print(x + "、");
	}
}

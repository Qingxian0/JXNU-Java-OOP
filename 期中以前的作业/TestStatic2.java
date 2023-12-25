/*
一、设计三角形类Triangle，有三边，均为正数，且不得任意修改：满足如下要求：
1、构造时，若三边不正确，无法创建出对象；若正确，至多构造出3个三角对象；

【选作】2、增设直角三角、等边三角，其中直角三角至多2个，等边三角至多5个，且任一时刻，普通、直角、等边共计不得超过10个。
---提示：直角三角、等边三角，不要作为三角的子类

*//*

*/
/* 分析：
       若将直角三角设为三角的子类，则三角的构造函数不能设为私有（why），
       这样，将无法限制三角的数量。故直角、等边不是三角子类。
【策略】
1、三角、与直角、等边单独设计，独有约束放在所属类中；
2、共同的约束，如三类三角的总量约束，单独做个约束类Conditions来存放
*//*


//三角类，满足：三边不正确造不出对象 且 至多5个 且 三类总量不超过10个
//注意：用三角类造的对象，如3、4、5或1、1、1，均属于三角对象，不属于其它类对象
class SJ {
	private int a, b, c;
	private static int count = 0, total = 3;

	public static int getCount() {
		return count;
	}// ==方便Conditions使用

	private SJ(int x, int y, int z) {
		a = x;
		b = y;
		z = c;
		count++;
	}

	public static boolean limit(int x, int y, int z) {
		return x > 0 && y > 0 && z > 0 && (x + y > z) && (x + z > y) && (y + z > x)
				&& count < total && Conditions.limitAll() == true;
	}

	public static SJ create(int x, int y, int z) {
		if (limit(x, y, z) == false)
			return null;
		return new SJ(x, y, z);
	}

	public String toString() {
		return "a=" + a + ", b=" + b + ", c=" + c;
	}
}

class RT {// 直角三角，与普通三角区别：仅limit()有区别
	private int a, b, c;
	private static int count = 0, total = 2;

	public static int getCount() {
		return count;
	}// ==方便Conditions使用

	private RT(int x, int y) {// 两条直角边
		a = x;
		b = y;
		c = (int) Math.sqrt(x * x + y * y);
		count++;
	}

	public static boolean limit(int x, int y) {
		return x > 0 && y > 0 && count < total && Conditions.limitAll() == true;
	}

	public static RT create(int x, int y) {
		if (limit(x, y) == false)
			return null;
		return new RT(x, y);
	}

	public String toString() {
		return "a=" + a + ", b=" + b + ", c=" + c;
	}
}

class DB {// 等边三角，与普通三角区别：仅limit()有区别
	private int a, b, c;
	private static int count = 0, total = 5;

	public static int getCount() {
		return count;
	}// ==方便Conditions使用

	private DB(int x) {
		a = x;
		b = x;
		c = x;
		count++;
	}

	public static boolean limit(int x) {
		return x > 0 && count < total && Conditions.limitAll() == true;
	}

	public static DB create(int x) {
		if (limit(x) == false)
			return null;
		return new DB(x);
	}

	public String toString() {
		return "a=" + a + ", b=" + b + ", c=" + c;
	}
}

final class Conditions {
	private Conditions() {
		;
	}

	public static boolean limitAll() {// 三者间共有的限制
		// 因题目中直角三角至多2个、等边三角至多5个、普通三角至多3个
		// 为增强约束，将三类三角总量限制为6个
		return SJ.getCount() + RT.getCount() + DB.getCount() < 6;
	}
}

public class TestStatic2 {
	public static void main(String[] args) {
		// 首先三类三角各创建5次-- 边值均正确
		SJ[] t = new SJ[5];
		// for(SJ x:t) x=SJ.create(2,2,3);==注：for-each无法更改数组中的数据，只能用于显示
		for (int i = 0; i < t.length; i++)
			t[i] = SJ.create(2, 2, 3);
		RT[] r = new RT[5];
		for (int i = 0; i < r.length; i++)
			r[i] = RT.create(3, 4);
		DB[] d = new DB[5];
		for (int i = 0; i < d.length; i++)
			d[i] = DB.create(6);
		System.out.println("普通三角：");
		for (SJ x : t)
			System.out.print(x + "、");
		System.out.println("\n直角三角：");
		for (RT x : r)
			System.out.print(x + "、");
		System.out.println("\n等边三角：");
		for (DB x : d)
			System.out.print(x + "、");
	}

}
*/

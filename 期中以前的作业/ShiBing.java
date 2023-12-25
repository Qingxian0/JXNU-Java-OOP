/*
三、某游戏有不同类型的士兵，枪兵的攻击方式是“用枪射击”，
火箭兵的攻击方式是“发射火箭攻击”，手雷兵的攻击方式是“用手雷炸”。
如给定枪兵对象a，手雷兵对象b，
   a.attack(b)，输出结果是“枪兵用枪射击手雷兵”，
   b.attack(a)，输出结果是“手雷兵用手雷炸枪兵”。
模拟时，给定一个存放各类士兵对象的数组，x从头至尾，y从尾至头，执行x.attack(y)。
【提示】本题需要用到toString()

*/
class ShiBing {
	void attack(ShiBing s) {;}
}
class QiangBing extends ShiBing{
	void attack(ShiBing s) {System.out.println("枪兵：用枪射击  "+s);}
	public String toString() { return "枪兵"; }
}
class ShouLeiBing extends ShiBing{
	void attack(ShiBing s) {System.out.println("手雷兵：用手雷炸  "+s);}
	public String toString() { return "手雷兵"; }
}
class HuoJianBing extends ShiBing{
	void attack(ShiBing s) {System.out.println("火箭兵：发射火箭攻击  "+s);}
	public String toString() { return "火箭兵"; }
}
class TestAttack{
	static void attack(ShiBing a, ShiBing b) { a.attack(b); }
	public static void main(String[] args) {
		ShiBing[] sb= {new ShouLeiBing(), new HuoJianBing(),new QiangBing()};//一组不同士兵对象
		for(int i=0,j=sb.length-1; j>=0; i++,j--)
			attack(sb[i],sb[j]); //模拟士兵间的相互攻击
	}
}
/*
二、某游戏中有战士对象，可以使用（即内有use方法）各类武器，如刀、枪、剑、戟等。
使得使用不同武器，可展示出不同的效果（直接输出即可）。例如：
用刀表演关公刀、用枪施展回马枪、
用剑表演项庄舞剑、用戟横扫沙场。
请完成上述设计。

*/
class WuQi{;}//武器类
class Dao extends WuQi{	public String toString() { return "刀"; } }
class Qiang extends WuQi{	public String toString() { return "枪"; } }
class Jian extends WuQi{	public String toString() { return "剑"; } }
class Ji extends WuQi{	public String toString() { return "戟"; } }
class ZhanShi{
	public void use(Dao x) { System.out.println("用"+x+"用刀表演关公刀");	}
	public void use(Qiang x) { System.out.println("用"+x+"施展回马枪");	}
	public void use(Jian x) { System.out.println("用"+x+"表演项庄舞剑");	}
	public void use(Ji x) { System.out.println("用"+x+"横扫沙场");	}
	public void use(WuQi x){ System.out.println("用武器");	}
}
class TestW{
	public static void main(String[] args) {
		ZhanShi s=new ZhanShi();
		//下列语句均合法
//		s.use(new Dao());
//		s.use(new Qiang());
//		s.use(new Jian());
//		s.use(new Ji());
		WuQi[] w= {new Dao(), new Qiang(),new Jian(),new Ji() };
		for(WuQi x: w)s.use(x);
	}
}

class Triangle{
	private double a,b,c;
	//public Triangle(){a=1;b=1;c=1;}//创建一个单位三角
	public Triangle(double x,double y,double z){//普通三角或单位三角
		if(limit(x,y,z)==false){a=1;b=1;c=1;}
		else{a=x;b=y;c=z;}
	}
	public Triangle(double x,double y){this(x,y,y);}//等腰三角，x是底，y是腰
	public Triangle(double x){this(x,x,x);}//等边三角

	private boolean limit(double x,double y,double z){//三角形的约束条件
		return (x>0&&y>0&&z>0&&x+y>z&&x+z>y&&y+z>x);
	}
	public void setEdges(double x,double y,double z){
		if(limit(x,y,z)==false)return; //无改变
		else {a=x;b=y;c=z;}
	}
	public String toString(){
		return "a="+a+", b="+b+", c="+c;
	}
}
class RtTriangle extends Triangle{
	//public RtTriangle(double x,double y,double z){super(x,y,z);}//创建的也可能不是直角三角
	public RtTriangle(double x,double y,double z){//假设x、y是直角边，z是斜边
		super(x,y,z);
		if(x*x+y*y!=z*z)setEdges(3,4,5);
	}
	public RtTriangle(double x,double y){super(x,y,Math.sqrt(x*x+y*y));}
}
class App{
	public static void main (String[] args) {
		System.out.println("new Triangle(1,2,3)        结果："+new Triangle(1,2,3));
		System.out.println("new Triangle(2,3,4)        结果："+new Triangle(2,3,4));
		System.out.println("new Triangle(1,3)          结果："+new Triangle(1,3));
		System.out.println("new Triangle(3,1)          结果："+new Triangle(3,1));
		System.out.println("new Triangle(5)            结果："+new Triangle(5));
		System.out.println("new RtTriangle(20,30,40)   结果："+new RtTriangle(2,3,4));
		System.out.println("new RtTriangle(30,40,50)   结果："+new RtTriangle(30,40,50));
		System.out.println("new RtTriangle(6,8,10)     结果："+new RtTriangle(6,8,10));
		System.out.println("new RtTriangle(300,400,500)结果："+new RtTriangle(300,400,500));
	}
}
//认识权限、重载(构造函数重载)、封装：三条边、约束条件必须设为私有，这样，必须通过三角形自身的方法，才能修改变长
//封装：打包、使之不可见
//需求描述：三角形有三条边，无参构造时，创建边长均为1的单位三角形；否则，按三条边构造三角形
//要求：若三条边不满足约束条件（三边均为正数且任意两边之和大于第三边），则不能修改三条边
//疑问：构造函数，对象尚未创建，为何能调用对象中的方法？这种理解有误。new时，已经分配了空间，构造函数仅仅是初始化作用，因此可以调用set方法


/*需求描述：假设游戏中普通士兵、火箭兵、普通坦克、喷火坦克四类兵种，生命值分别为10、10、100、100，
 *普通士兵攻击任一类坦克，攻击力为5（即每次攻击后，坦克生命值减少5）；
 *士兵攻击士兵、士兵攻击坦克
 **/

 /*1、狗闻到主人、骨头、老虎的气味，会做不同的反应。
  *2、有藏獒（Tibetan mastiff ）、泰迪（Teddy），不同狗有不同的伤害
  *3、数据
  **/
//目的：认识继承、重载、重写
//展现：空类也有用，可实现is-A关系
/*
class Smell{;}
class BoneSmell extends Smell{;}//骨头气味
class MasterSmell extends Smell{;}//主人气味
class TigerSmell extends Smell{;}//老虎气味

class Dog{
	void smell(Smell s){System.out.println("闻到气味，不能识别。");}//动物的叫声
}
class Dog extends Animal {
	void smell(BoneSmell b){ System.out.println("哦，美味的骨头！");}
	void smell(TigerSmell t){ System.out.println("太可怕了，我逃！");}
	void smell(MasterSmell m){ System.out.println("主人，您来啦！"); }
	public void cry(){	System.out.println("汪汪"); }
}
class Cat extends Animal {
	public void cry(){ System.out.println("喵喵");}
}
class Ch_3_5{
	public static void main (String[] args) {
		System.out.println("【重载】狗：不同气味不同反应");
		Dog d=new Dog();
		d.smell(new GuTouQiWei());
		d.smell(new LaoHuQiWei());
		d.smell(new ZhuRenQiWei());

		System.out.println("【重写】动物：根据实际类型发声");
		Animal a=new Cat(); a.cry();
		       a=new Dog(); a.cry();
	}
}
*/
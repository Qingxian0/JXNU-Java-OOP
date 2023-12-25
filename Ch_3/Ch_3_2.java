/*【例3.2】用java实现如下描述：
 *狗有多种嗅的功能，嗅到骨头流口水，嗅到老虎吓得跑，嗅到主人很高兴。
 *人有生命值（假设为100）。所有狗都会咬人，藏獒/泰迪攻击一次可减少50/1个生命值。
 *我先后养了藏獒、泰迪，用相同的宠物名pp，pp咬人了。
 **/
class BoneSmell{;}//骨头气味
class MasterSmell{;}//主人气味
class TigerSmell{;}//老虎气味
class Person{
	private int blood=100;
	public int getBlood(){return blood;}
	public void setBlood(int x){ blood=(x<0||x>100)?blood:x; }
	public String toString(){ return "blood="+blood; }
}
class Animal{//旨在展现创建Dog对象，会自动调用Animal的构造函数
	public Animal(){System.out.println("调用构造函数：Animal()");}
}
class Dog extends Animal{
	public Dog(){System.out.println("调用构造函数：Dog()");}
	void smell(BoneSmell b){ System.out.println("哦，美味的骨头！");}
	void smell(TigerSmell t){ System.out.println("老虎，太可怕了，投降！");}
	void smell(MasterSmell m){ System.out.println("主人，见到你很开心！"); }
	public void bite(Person p){
		System.out.print("按照标准狗的咬法，人失血10点。");
		p.setBlood(p.getBlood()-10); //能否换成:p.setBlood(p.blood-10); why?
	}
}
class Teddy extends Dog{//泰迪
	public Teddy(){System.out.println("调用构造函数：Teddy()");}
	public void bite(Person p){
		System.out.print("按照泰迪的咬法，人失血1点。");
		p.setBlood(p.getBlood()-1);
	}
}
class TibetanMastiff extends Dog{//藏獒
	public TibetanMastiff(){System.out.println("调用构造函数：TibetanMastiff()");}
	public void bite(Person p){
		System.out.print("按照藏獒的咬法，人失血50点。");
		p.setBlood(p.getBlood()-50);
	}
}

class App{
	public static void main (String[] args) {
		Person p=new Person();
		System.out.println("====验证【构造子类对象时会自动调用超类的构造函数】====");
		System.out.println("1、构造Dog对象（注意调用构造函数的次序）：");
		Dog d=new Dog();
		System.out.println("2、构造Teddy对象：");
		Teddy td=new Teddy();
		System.out.println("3、构造TibetanMastiff对象：");
		TibetanMastiff tm=new TibetanMastiff();
		System.out.println("\n====验证【重载】：泰迪对不同气味有不同反应====");
		td.smell(new BoneSmell());
		td.smell(new TigerSmell());
		td.smell(new MasterSmell());
		System.out.println("\n====验证【重写】，即语句 pp.bite(); 的即插即用====");
		System.out.println("==即：pp处插装不同对象，语句 pp.bite()将产生不同效果==");
		System.out.println("---人被咬前的状况："+p);
		System.out.print("1、pp处插装泰迪，pp开始咬人：");
		Dog pp=td; pp.bite(p); System.out.println(p);
		System.out.print("2、pp处插装藏獒，pp开始咬人：");
		pp=tm; pp.bite(p); System.out.println(p);
	}
}

abstract class Component{//描述装饰对象--打印票据主程序基于此类编程
	abstract public void prtTicket();//装饰操作
}
class Ticket extends Component{//描述要装饰的实体，即票据本身的信息
	//……订单的具体信息，如客户、订单号等，这里略
	public void prtTicket(){System.out.println("一行行的票据内容");}//打印票据代码
}
abstract class Decorator extends Component{
	protected Component dc;  //即：decoratedContent，代表装饰后的内容
	Decorator(Component c){ dc=c; }
	//public void prtTicket();//此方法继承自Component，未实现
}
class Header1 extends Decorator{
	public Header1(Component c){ super(c); }
	public void prtTicket(){//将个性化操作加在装饰链【之前】
		System.out.println("Header1"); //Head1的个性化装饰操作
		dc.prtTicket();  //接上装饰链
	}
}
class Header2 extends Decorator{
	public Header2(Component c){ super(c);}
	public void prtTicket(){//将个性化操作加在装饰链【之前】
		System.out.println("Header2");//Head2的个性化装饰操作
		dc.prtTicket(); //装饰链
	}
}
class Footer1 extends Decorator{
	public Footer1(Component c){ super(c);}
	public void prtTicket(){ //将个性化操作加在装饰链【之后】
		dc.prtTicket(); //装饰链
		System.out.println("Footer1");//Foot1的个性化装饰操作
	}
}
class Footer2 extends Decorator{
	public Footer2(Component c){ super(c); }
	public void prtTicket(){ //将个性化操作加在装饰链【之后】
		dc.prtTicket();  //装饰链
		System.out.println("Footer2");//Foot2的个性化装饰操作
	}
}
class Factory{//用于调用各种具体装饰器，实施装配的工厂
	public Component getComponent(){
		Component c;//下面的构造方式称作“包装”，即用装饰一层层把Ticket对象包起来
		//包装的次序，就是链接的次序。
		//c=new Ticket(); c=new Footer1(c); c=new Header2(c);c=new Header1(c);
		//c=new Header1(new Header2(new Footer1(new Ticket()))); //装配方案1
		//注意：Head系列、Foot系列调用装饰链的次序不同与，下句与上句等效
		c=new Footer1(new Header1(new Header2(new Ticket()))); //装配方案1
		//c=new Header1(new Footer1(new Ticket())); //装配方案2
		return c;
	}
}
class App{
	public static void main (String[] args) {
		Factory myFactory=new Factory();
		Component component=myFactory.getComponent();
		component.prtTicket();
	}
}

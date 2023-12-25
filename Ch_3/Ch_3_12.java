//基于接口方式实现的装饰模式
interface Decorator{ void prtTicket(); }
class Ticket implements Decorator{//此处改为实现Decorator接口
	//……订单的具体信息，如客户、订单号等，这里略
	public void prtTicket(){System.out.println("一行行的票据内容");}//打印票据代码
}
class Header1 implements Decorator{//主要改动：1、实现接口；2、新增属性dc；3、构造方法赋值
	private Decorator dc;
	public Header1(Decorator c){ dc=c; }
	public void prtTicket(){//将个性化操作加在装饰链【之前】
		System.out.println("Header1"); //Head1的个性化装饰操作
		dc.prtTicket();  //接上装饰链
	}
}
class Header2 implements Decorator{
	private Decorator dc;
	public Header2(Decorator c){ dc=c; }
	public void prtTicket(){//将个性化操作加在装饰链【之前】
		System.out.println("Header2");//Head2的个性化装饰操作
		dc.prtTicket(); //装饰链
	}
}
class Footer1 implements Decorator{
	private Decorator dc;
	public Footer1(Decorator c){ dc=c; }
	public void prtTicket(){ //将个性化操作加在装饰链【之后】
		dc.prtTicket(); //装饰链
		System.out.println("Footer1");//Foot1的个性化装饰操作
	}
}
class Footer2 implements Decorator{
	private Decorator dc;
	public Footer2(Decorator c){ dc=c; }
	public void prtTicket(){ //将个性化操作加在装饰链【之后】
		dc.prtTicket();  //装饰链
		System.out.println("Footer2");//Foot2的个性化装饰操作
	}
}
class Factory{//用于调用各种具体装饰器，实施装配的工厂
	public Decorator getComponent(){
		Decorator c;//
		c=new Footer1(new Header1(new Header2(new Ticket()))); //装配方案1
		//c=new Header1(new Footer1(new Ticket())); //装配方案2
		return c;
	}
}
class App{
	public static void main (String[] args) {
		Factory myFactory=new Factory();
		Decorator dc=myFactory.getComponent();
		dc.prtTicket();
	}
}
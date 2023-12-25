//适配器模式--实现方案1：A系统提供所有类，B系统用接口实现
//下面三个类是A型系统的类【代码不可更改】
abstract class XingZhuang{
	private String name;
	public XingZhuang(String s){ name=s; }
	public abstract double mianJi();     //计算形状面积
	public String getName(){return name;}//获取形状名称
}
class SanJiao extends XingZhuang{
	private double a,b;//三角形的底和高
	public SanJiao(String s,double x, double y){ super(s); a=x;b=y; }
	public double mianJi(){ return a*b/2; }
}
class TiXing extends XingZhuang{
	private double a,b,h;//上底、下底、高
	public TiXing(String s,double x, double y,double z){ super(s); a=x;b=y;h=z; }
	public double mianJi(){ return (a+b)*h/2; }
}
//下面接口和三个类隶属B型系统
interface IShape{
	double getArea();//计算形状面积
	String getType();//获取形状名称
}
class Rectangle implements IShape{
	private String type;
	private double width,high;
	public Rectangle(double x, double y){ type="矩形"; width=x;high=y; }
	public double getArea(){ return width*high; }
	public String getType(){return type;}
	//public String toString(){ return }
}
class Circle implements IShape{
	private String type;
	private final double pi=3.14;
	private double r;
	public Circle(double x){ type="圆形"; r=x; }
	public double getArea(){ return 3.14*r*r; }
	public String getType(){return type;}
}
class Recognizer{//B型系统的识别器，即框架程序
	static String recognize(IShape shape){//返回识别的结果
		return "类型："+shape.getType()+"\t面积："+shape.getArea();
	}
}
//下面设计，将A型系统识别三角形、梯形的功能，组装到B型系统中
// 关键点：如Trapezia，
//  1、继承A型系统的TiXing类，可获得该类的所有功能；
//  2、必须实现IShape接口，这样可插装到B型系统的框架程序中
//  3、要实现功能调用的转换（即适配）：将A型系统的getName()、mianJi()转换成IShape要求的格式

//用于组装梯形、三角形的类
class Trapezia extends TiXing implements IShape{//梯形
	public Trapezia(String s,double x, double y,double z){ super(s,x,y,z); }
	//下面实现功能的转换，以满足IShape的要求
	public double getArea(){ return mianJi(); }
	public String getType(){return getName();}
}
class Triangle extends SanJiao implements IShape{//基于A型系统相关类设计的三角形
	public Triangle(String s,double x, double y){ super(s,x,y); }
	public double getArea(){ return mianJi(); }
	public String getType(){return getName();}
}
class App{
	public static void main (String[] args) {
		IShape[] shapes={new Rectangle(2,3),new Circle(10),
		  new Triangle("三角形",4,5),new Trapezia("梯形",3,4,5)};

		for(IShape x: shapes)
			System.out.println(Recognizer.recognize(x));
	}
}
/*思考：如果B型系统希望打印形状的具体信息呢？如
   类型：矩形，width：2  high:3  面积：6.0
   在不允许对A型系统相关类修改前提下，会存在什么问题？
   在此基础上体会适配器模式存在的局限
*/
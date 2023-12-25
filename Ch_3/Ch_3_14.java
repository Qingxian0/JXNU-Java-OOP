//适配器模式--实现方案2：A、B两系统均用抽象类实现，A系统提供所有类，B系统相关类内嵌A系统相关类的对象
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
abstract class Shape{
	private String type;
	public Shape(String t){ type=t; }
	abstract double getArea();//计算形状面积
	String getType(){return type;}//获取形状名称
}
class Rectangle extends Shape{
	private double width,high;
	public Rectangle(double x, double y){ super("矩形"); width=x;high=y; }
	public double getArea(){ return width*high; }
}
class Circle extends Shape{
	private final double pi=3.14;
	private double r;
	public Circle(double x){ super("圆形"); r=x; }
	public double getArea(){ return 3.14*r*r; }
}
class Recognizer{//B型系统的识别器，即框架程序
	static String recognize(Shape shape){//返回识别的结果
		return "类型："+shape.getType()+"\t面积："+shape.getArea();
	}
}

//下面设计，将A型系统识别三角形、梯形的功能，组装到B型系统中
// 关键点：如Trapezia，
//  1、为能插装到B系统中，必须继承B系统的框架程序所用类Shape
//  2、通过在Trapezia设置一个TiXing变量，来获得TiXing的相关功能。该变量在Trapezia构造时创建
//  3、在Trapezia相关方法中调用TiXing变量的功能，即实现功能调用的转换

//用于组装梯形、三角形的类

class Trapezia extends Shape{//梯形
	private TiXing tx; //注意：包装一个提供外系统功能的对象引用
	public Trapezia(String s,double x, double y,double z){
		super(s); tx=new TiXing(s,x,y,z); }
	public double getArea(){ return tx.mianJi(); }
	public String getType(){return tx.getName();}//使用A系统的相关功能
}
class Triangle extends Shape{//基于A型系统相关类设计的三角形
	private SanJiao sj;
	public Triangle(String s,double x, double y){ super(s);sj=new SanJiao(s,x,y); }
	public double getArea(){ return sj.mianJi(); }
}
class App{
	public static void main (String[] args) {
		Shape[] shapes={new Rectangle(2,3),new Circle(10)
			,new Trapezia("梯形",3,4,5),new Triangle("三角形",4,5)};
		for(Shape x: shapes)
			System.out.println(Recognizer.recognize(x));
	}
}

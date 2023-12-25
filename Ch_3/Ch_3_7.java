class Shape{//形状类
	private String type;
	public Shape(String s){ type=s; }
	public String getType(){ return type; }
	public double getArea() { return 0; } //待重写的方法
}
class Rectangle extends Shape{ //矩形类
	private int high, width; //定义矩形的高和宽

	public Rectangle(int g, int k){ super("矩形"); high=g; width=k;}
	//public int getArea() { return high*width ;}//语法错：不能根据返回值来重写
	@Override public double getArea() { return high*width ;}
		//方法前标注“@Override”后，若该方法不对超类方法形成重写，就产生编译错
	public String toString(){ return "高="+high+" 宽="+width; }
}
class Circle extends Shape{//圆形类
	private int r;                 //半径
	private final double pi=3.14;  //圆周率
	public Circle(int r1){super("圆形"); r=r1; }
	public double getArea(int r) { return pi *r *r; }
	public String toString(){ return "半径="+r; }
}
class Recognizer{ //智能识别器类
	public void showInfo(Shape s){//框架程序：必须要基于超类Shape编程
	  //注意，s必须是Shape型，s不能是Circle等型，why?
		System.out.println("类型:"+s.getType()+",面积:"+s.getArea()+",属性:"+s);
	}
}
class App{
	public static void main(String args[] ){
		Recognizer rcg=new Recognizer();
		Shape[] s={new Rectangle(2, 3),new Circle(10)};
		for(int i=0; i<s.length; i++)	rcg.showInfo(s[i]);
	}
}

//������ģʽ--ʵ�ַ���2��A��B��ϵͳ���ó�����ʵ�֣�Aϵͳ�ṩ�����࣬Bϵͳ�������ǶAϵͳ�����Ķ���
//������������A��ϵͳ���ࡾ���벻�ɸ��ġ�
abstract class XingZhuang{
	private String name;
	public XingZhuang(String s){ name=s; }
	public abstract double mianJi();     //������״���
	public String getName(){return name;}//��ȡ��״����
}
class SanJiao extends XingZhuang{
	private double a,b;//�����εĵ׺͸�
	public SanJiao(String s,double x, double y){ super(s); a=x;b=y; }
	public double mianJi(){ return a*b/2; }
}
class TiXing extends XingZhuang{
	private double a,b,h;//�ϵס��µס���
	public TiXing(String s,double x, double y,double z){ super(s); a=x;b=y;h=z; }
	public double mianJi(){ return (a+b)*h/2; }
}
//����ӿں�����������B��ϵͳ
abstract class Shape{
	private String type;
	public Shape(String t){ type=t; }
	abstract double getArea();//������״���
	String getType(){return type;}//��ȡ��״����
}
class Rectangle extends Shape{
	private double width,high;
	public Rectangle(double x, double y){ super("����"); width=x;high=y; }
	public double getArea(){ return width*high; }
}
class Circle extends Shape{
	private final double pi=3.14;
	private double r;
	public Circle(double x){ super("Բ��"); r=x; }
	public double getArea(){ return 3.14*r*r; }
}
class Recognizer{//B��ϵͳ��ʶ����������ܳ���
	static String recognize(Shape shape){//����ʶ��Ľ��
		return "���ͣ�"+shape.getType()+"\t�����"+shape.getArea();
	}
}

//������ƣ���A��ϵͳʶ�������Ρ����εĹ��ܣ���װ��B��ϵͳ��
// �ؼ��㣺��Trapezia��
//  1��Ϊ�ܲ�װ��Bϵͳ�У�����̳�Bϵͳ�Ŀ�ܳ���������Shape
//  2��ͨ����Trapezia����һ��TiXing�����������TiXing����ع��ܡ��ñ�����Trapezia����ʱ����
//  3����Trapezia��ط����е���TiXing�����Ĺ��ܣ���ʵ�ֹ��ܵ��õ�ת��

//������װ���Ρ������ε���

class Trapezia extends Shape{//����
	private TiXing tx; //ע�⣺��װһ���ṩ��ϵͳ���ܵĶ�������
	public Trapezia(String s,double x, double y,double z){
		super(s); tx=new TiXing(s,x,y,z); }
	public double getArea(){ return tx.mianJi(); }
	public String getType(){return tx.getName();}//ʹ��Aϵͳ����ع���
}
class Triangle extends Shape{//����A��ϵͳ�������Ƶ�������
	private SanJiao sj;
	public Triangle(String s,double x, double y){ super(s);sj=new SanJiao(s,x,y); }
	public double getArea(){ return sj.mianJi(); }
}
class App{
	public static void main (String[] args) {
		Shape[] shapes={new Rectangle(2,3),new Circle(10)
			,new Trapezia("����",3,4,5),new Triangle("������",4,5)};
		for(Shape x: shapes)
			System.out.println(Recognizer.recognize(x));
	}
}

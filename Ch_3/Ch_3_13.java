//������ģʽ--ʵ�ַ���1��Aϵͳ�ṩ�����࣬Bϵͳ�ýӿ�ʵ��
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
interface IShape{
	double getArea();//������״���
	String getType();//��ȡ��״����
}
class Rectangle implements IShape{
	private String type;
	private double width,high;
	public Rectangle(double x, double y){ type="����"; width=x;high=y; }
	public double getArea(){ return width*high; }
	public String getType(){return type;}
	//public String toString(){ return }
}
class Circle implements IShape{
	private String type;
	private final double pi=3.14;
	private double r;
	public Circle(double x){ type="Բ��"; r=x; }
	public double getArea(){ return 3.14*r*r; }
	public String getType(){return type;}
}
class Recognizer{//B��ϵͳ��ʶ����������ܳ���
	static String recognize(IShape shape){//����ʶ��Ľ��
		return "���ͣ�"+shape.getType()+"\t�����"+shape.getArea();
	}
}
//������ƣ���A��ϵͳʶ�������Ρ����εĹ��ܣ���װ��B��ϵͳ��
// �ؼ��㣺��Trapezia��
//  1���̳�A��ϵͳ��TiXing�࣬�ɻ�ø�������й��ܣ�
//  2������ʵ��IShape�ӿڣ������ɲ�װ��B��ϵͳ�Ŀ�ܳ�����
//  3��Ҫʵ�ֹ��ܵ��õ�ת���������䣩����A��ϵͳ��getName()��mianJi()ת����IShapeҪ��ĸ�ʽ

//������װ���Ρ������ε���
class Trapezia extends TiXing implements IShape{//����
	public Trapezia(String s,double x, double y,double z){ super(s,x,y,z); }
	//����ʵ�ֹ��ܵ�ת����������IShape��Ҫ��
	public double getArea(){ return mianJi(); }
	public String getType(){return getName();}
}
class Triangle extends SanJiao implements IShape{//����A��ϵͳ�������Ƶ�������
	public Triangle(String s,double x, double y){ super(s,x,y); }
	public double getArea(){ return mianJi(); }
	public String getType(){return getName();}
}
class App{
	public static void main (String[] args) {
		IShape[] shapes={new Rectangle(2,3),new Circle(10),
		  new Triangle("������",4,5),new Trapezia("����",3,4,5)};

		for(IShape x: shapes)
			System.out.println(Recognizer.recognize(x));
	}
}
/*˼�������B��ϵͳϣ����ӡ��״�ľ�����Ϣ�أ���
   ���ͣ����Σ�width��2  high:3  �����6.0
   �ڲ������A��ϵͳ������޸�ǰ���£������ʲô���⣿
   �ڴ˻��������������ģʽ���ڵľ���
*/
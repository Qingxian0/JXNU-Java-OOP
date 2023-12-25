//������ģʽ--ʵ�ַ���3��Aϵͳ���ɼ������޷���֪Aϵͳ������ࣩ�����ṩ��ع��ܵĵ��ýӿ�

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
class Driver{
	private TiXing tx;   //��������
	private SanJiao sj;  //����������
	void setTiXing(String s,double x, double y,double z){ tx=new TiXing(s,x,y,z); }
	public double tiXingMianJi(){
		return (tx==null)? 0 : tx.mianJi();
	}
	public String tiXingGetName(){ //��ȡ���ε�������Ϣ
		return (tx==null)? null : tx.getName();
	}
	void setSanJiao(String s,double x, double y){ sj=new SanJiao(s,x,y); }
	public double sanJiaoMianJi(){//��ȡ���������
		return (sj==null)? 0 : sj.mianJi();
	}
	public String sanJiaoGetName(){ //��ȡ�����ε�������Ϣ
		return (sj==null)? null : sj.getName();
	}
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
//ע�⣬����Aϵͳ���ṩDriver��IXingZhuang����˲�֪������ȻҲ���޷�ʹ�ã�Aϵͳ������TiXing������
	private Driver drv=new Driver();//��Ҫͨ��drv��ȡAϵͳ�Ĺ���
	public Trapezia(String s,double x, double y,double z){
		super(s);		drv.setTiXing(s,x,y,z); }
	public double getArea(){ return drv.tiXingMianJi(); }
	public String getType(){ return drv.tiXingGetName();}
}
class Triangle extends Shape{//����A��ϵͳ�������Ƶ�������
	private Driver drv=new Driver();//��Ҫͨ��drv��ȡAϵͳ�Ĺ���
	public Triangle(String s,double x, double y){
		super(s);drv.setSanJiao(s,x,y); }
	public double getArea(){ return drv.sanJiaoMianJi(); }
	public String getType(){ return drv.sanJiaoGetName();}
}
class App{
	public static void main (String[] args) {
		Shape[] shapes={new Rectangle(2,3),new Circle(10)
			,new Trapezia("����",3,4,5),new Triangle("������",4,5)};
		for(Shape x: shapes)
			System.out.println(Recognizer.recognize(x));
	}
}
/*˼�������B��ϵͳϣ����ӡ��״�ľ�����Ϣ�أ���
   ���ͣ����Σ�width��2  high:3  �����6.0
   �ڲ������A��ϵͳ������޸�ǰ���£������ʲô���⣿
   �ڴ˻��������������ģʽ���ڵľ���
*/
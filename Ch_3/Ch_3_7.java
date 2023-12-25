class Shape{//��״��
	private String type;
	public Shape(String s){ type=s; }
	public String getType(){ return type; }
	public double getArea() { return 0; } //����д�ķ���
}
class Rectangle extends Shape{ //������
	private int high, width; //������εĸߺͿ�

	public Rectangle(int g, int k){ super("����"); high=g; width=k;}
	//public int getArea() { return high*width ;}//�﷨�����ܸ��ݷ���ֵ����д
	@Override public double getArea() { return high*width ;}
		//����ǰ��ע��@Override�������÷������Գ��෽���γ���д���Ͳ��������
	public String toString(){ return "��="+high+" ��="+width; }
}
class Circle extends Shape{//Բ����
	private int r;                 //�뾶
	private final double pi=3.14;  //Բ����
	public Circle(int r1){super("Բ��"); r=r1; }
	public double getArea(int r) { return pi *r *r; }
	public String toString(){ return "�뾶="+r; }
}
class Recognizer{ //����ʶ������
	public void showInfo(Shape s){//��ܳ��򣺱���Ҫ���ڳ���Shape���
	  //ע�⣬s������Shape�ͣ�s������Circle���ͣ�why?
		System.out.println("����:"+s.getType()+",���:"+s.getArea()+",����:"+s);
	}
}
class App{
	public static void main(String args[] ){
		Recognizer rcg=new Recognizer();
		Shape[] s={new Rectangle(2, 3),new Circle(10)};
		for(int i=0; i<s.length; i++)	rcg.showInfo(s[i]);
	}
}

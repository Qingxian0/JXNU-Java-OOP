class Triangle{
	private double a,b,c;
	//public Triangle(){a=1;b=1;c=1;}//����һ����λ����
	public Triangle(double x,double y,double z){//��ͨ���ǻ�λ����
		if(limit(x,y,z)==false){a=1;b=1;c=1;}
		else{a=x;b=y;c=z;}
	}
	public Triangle(double x,double y){this(x,y,y);}//�������ǣ�x�ǵף�y����
	public Triangle(double x){this(x,x,x);}//�ȱ�����

	private boolean limit(double x,double y,double z){//�����ε�Լ������
		return (x>0&&y>0&&z>0&&x+y>z&&x+z>y&&y+z>x);
	}
	public void setEdges(double x,double y,double z){
		if(limit(x,y,z)==false)return; //�޸ı�
		else {a=x;b=y;c=z;}
	}
	public String toString(){
		return "a="+a+", b="+b+", c="+c;
	}
}
class RtTriangle extends Triangle{
	//public RtTriangle(double x,double y,double z){super(x,y,z);}//������Ҳ���ܲ���ֱ������
	public RtTriangle(double x,double y,double z){//����x��y��ֱ�Ǳߣ�z��б��
		super(x,y,z);
		if(x*x+y*y!=z*z)setEdges(3,4,5);
	}
	public RtTriangle(double x,double y){super(x,y,Math.sqrt(x*x+y*y));}
}
class App{
	public static void main (String[] args) {
		System.out.println("new Triangle(1,2,3)        �����"+new Triangle(1,2,3));
		System.out.println("new Triangle(2,3,4)        �����"+new Triangle(2,3,4));
		System.out.println("new Triangle(1,3)          �����"+new Triangle(1,3));
		System.out.println("new Triangle(3,1)          �����"+new Triangle(3,1));
		System.out.println("new Triangle(5)            �����"+new Triangle(5));
		System.out.println("new RtTriangle(20,30,40)   �����"+new RtTriangle(2,3,4));
		System.out.println("new RtTriangle(30,40,50)   �����"+new RtTriangle(30,40,50));
		System.out.println("new RtTriangle(6,8,10)     �����"+new RtTriangle(6,8,10));
		System.out.println("new RtTriangle(300,400,500)�����"+new RtTriangle(300,400,500));
	}
}
//��ʶȨ�ޡ�����(���캯������)����װ�������ߡ�Լ������������Ϊ˽�У�����������ͨ������������ķ����������޸ı䳤
//��װ�������ʹ֮���ɼ�
//�����������������������ߣ��޲ι���ʱ�������߳���Ϊ1�ĵ�λ�����Σ����򣬰������߹���������
//Ҫ���������߲�����Լ�����������߾�Ϊ��������������֮�ʹ��ڵ����ߣ��������޸�������
//���ʣ����캯����������δ������Ϊ���ܵ��ö����еķ����������������newʱ���Ѿ������˿ռ䣬���캯�������ǳ�ʼ�����ã���˿��Ե���set����


/*����������������Ϸ����ͨʿ�������������̹ͨ�ˡ����̹��������֣�����ֵ�ֱ�Ϊ10��10��100��100��
 *��ͨʿ��������һ��̹�ˣ�������Ϊ5����ÿ�ι�����̹������ֵ����5����
 *ʿ������ʿ����ʿ������̹��
 **/

 /*1�����ŵ����ˡ���ͷ���ϻ�����ζ��������ͬ�ķ�Ӧ��
  *2���в��ᣨTibetan mastiff ����̩�ϣ�Teddy������ͬ���в�ͬ���˺�
  *3������
  **/
//Ŀ�ģ���ʶ�̳С����ء���д
//չ�֣�����Ҳ���ã���ʵ��is-A��ϵ
/*
class Smell{;}
class BoneSmell extends Smell{;}//��ͷ��ζ
class MasterSmell extends Smell{;}//������ζ
class TigerSmell extends Smell{;}//�ϻ���ζ

class Dog{
	void smell(Smell s){System.out.println("�ŵ���ζ������ʶ��");}//����Ľ���
}
class Dog extends Animal {
	void smell(BoneSmell b){ System.out.println("Ŷ����ζ�Ĺ�ͷ��");}
	void smell(TigerSmell t){ System.out.println("̫�����ˣ����ӣ�");}
	void smell(MasterSmell m){ System.out.println("���ˣ���������"); }
	public void cry(){	System.out.println("����"); }
}
class Cat extends Animal {
	public void cry(){ System.out.println("����");}
}
class Ch_3_5{
	public static void main (String[] args) {
		System.out.println("�����ء�������ͬ��ζ��ͬ��Ӧ");
		Dog d=new Dog();
		d.smell(new GuTouQiWei());
		d.smell(new LaoHuQiWei());
		d.smell(new ZhuRenQiWei());

		System.out.println("����д���������ʵ�����ͷ���");
		Animal a=new Cat(); a.cry();
		       a=new Dog(); a.cry();
	}
}
*/
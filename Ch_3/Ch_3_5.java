/* ���Ͳ���static����
 * Ҫ��1��ֻ�ܴ�����ȷ�������Σ�2�������ܴ���3��
 **/
class Triangle{
	private int a,b,c;
	private static final int total=3; //�����ζ�����޶�
	private static int count=0;       //��ǰ�����Ͷ��������
	private Triangle(int x,int y,int z){a=x;b=y;c=z; count++;}

	private static boolean limit(int x,int y,int z){//�����ε�Լ������
		return (x>0&&y>0&&z>0&&x+y>z&&x+z>y&&y+z>x&&count<total);
	}
	public static Triangle creatTriangle(int x,int y,int z){
		return (limit(x,y,z)==true)?new Triangle(x,y,z):null;
	}
	public void showCount(){System.out.println("count= "+count);}
	public String toString(){ return "{"+a+","+b+","+c+"}";	}
}
class App{
	public static void main (String[] args) { int i,j;
		Triangle[] t=new Triangle[10]; //ע�⣺��������Triangle������󣬶���Triangle����
		t[0]=Triangle.creatTriangle(1,2,3); //�Ƿ����ݣ��޷����������Ƕ��󣬼�t[0]��ֵΪnull
		t[1]=Triangle.creatTriangle(2,3,4);; //�Ϸ�����
		for(i=2; i<t.length; i++)//����10�κϷ������Σ�ע�������������ƣ��ܶ��޷������ɹ�
			t[i]=Triangle.creatTriangle(3,4,5);
		for(i=0; i<t.length; i++)System.out.print(t[i]+"��");
		t[1].showCount();//ע����ȷ��t[1]�ǿ�
	}
}

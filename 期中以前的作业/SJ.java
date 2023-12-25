/*
һ�������������SJ��������RtSJ��Ҫ��ͬʱ��������������
1�����߾�Ϊ����������������֮�ʹ��ڵ����ߣ�
2������1/2/3�����ܹ������ǡ�����ֵ������Լ������ֻ�ܴ�����λ���ǣ�������ֵ��Ϊ1��
3����������߿��Ա��޸ģ��������Ժ��ַ�ʽ�޸ģ�����ֵ����ȷ����ȷ������֮��������ֵ���󣬶�������߲��ᱻ���ġ�
4��SJ����ͨ��equals(t)�ж��Ƿ���������tȫ�ȡ�
5��SJ������ֱ������RtSJ�������������캯����ͨ������ֱ�Ǳ߹��죻��ͨ�������߹��죬�����߲�����ֱ������Լ����ֻ�ܴ�����3��4��5Ϊ�߳��ĵ�λֱ�����ǡ�

����Ƴ���������Ҫ�����SJ��RtSJ��

*/
class SJ {
	private int a,b,c; //��Ϊprivate��ּ�ڽ�ֹ�������a��b��c
	public boolean limit(int x, int y, int z) {
		return x>0&&y>0&&z>0&&(x+y>z)&&(x+z>y)&&(y+z>x);
	}
	//�����������캯����ʵ������2
	public SJ(int x, int y, int z) {
		if(limit(x,y,z)==false) { a=1;b=1;c=1; return;} //��ֵ����ֻ�ܹ��쵥λ����
		a=x;b=y;c=z;
	}
	public SJ(int x, int y) {
		if(limit(x,x,y)==false) { a=1;b=1;c=1; return;} //��ֵ����ֻ�ܹ��쵥λ����
		a=x;b=x;c=y;
	}
	//ע���������캯����Ҳ��д�����·�ʽ��why��
//	public SJ(int x, int y) { this(x,x,y);	}
	public SJ(int x) { this(x,x,x);	}
	//���淽����ʵ������3
	public void setEdges(int x, int y, int z) {//��ֵ��Ϊprivate�󣬸��ı�ֵ��ֻ��ͨ���˷���
		//����ֵ��Ϊ˽�к�ֻ��ͨ��Ԥ���趨�ķ�ʽ�����������������ı�ֵ���ô�����
		if(limit(x,y,z)==false) return;
		a=x;b=y;c=z;
	}
	//���淽����ʵ������4
	public boolean equals(SJ t) {
		if(a+b+c!=t.a+t.b+t.c)return false; //�˾�ּ�ڳ�ɸ�������Ч��
		int x,y,z;
		x=this.a;  y=this.b;  z=this.c;
		if(t.a==x)x=0; //ע�⣺��3�������else���ӣ����������⣬why?
			else if(t.a==y)y=0;
				 else if(t.a==z)z=0;
		if(t.b==x)x=0; 
			else if(t.b==y)y=0;
				 else if(t.b==z)z=0;
		if(t.c==x)x=0; 
			else if(t.c==y)y=0;
				 else if(t.c==z)z=0;
		return x==0 && y==0 && z==0;
	}
	public String toString() {
		return "a="+a+", b="+b+", c="+c;
	}
}
class RtSJ extends SJ{
	public boolean limitRt(int x, int y, int z) {
		return (x*x+y*y)==z*z;
	}
	public RtSJ(int x, int y, int z) {//ǿ����zΪб��
		super(x,y,z);//super��this���ù��캯����������ڵ�һ��
		//������������ܴ��ڴ������ߡ��������жϣ������ߴ��󣬾������趨����ֵ
		if(limit(x,y,z)==false ||limitRt(x,y,z)==false) 
			setEdges(3,4,5);
	}
	public RtSJ(int x,int y){//Math.sqrt(x*x+y*y)���ص���double���ݣ��������ǿ������ת��
		super(x,y,(int)Math.sqrt(x*x+y*y));
	}
}
class TestSJ{
	public static void main(String[] args) {
		System.out.println("������������δ�����");
		System.out.println("new SJ(-1,-1,3)��"+new SJ(-1,-1,3));
		System.out.println("new SJ(2,2,1)��"+new SJ(2,2,1));
		System.out.println("new RtSJ(2,3,4)��"+new RtSJ(2,3,4));
		System.out.println("new RtSJ(30,40)��"+new RtSJ(30,40));

		System.out.println("\n�������������ȫ�ȣ�");
		SJ s1=new SJ(5,3,4); SJ s2=new RtSJ(3,4,5);
		System.out.println(s1+" == "+s2+": "+s1.equals(s2));
		s1=new SJ(2,2,3); s2=new SJ(2,3,3);
		System.out.println(s1+" == "+s2+": "+s1.equals(s2));
	}
}
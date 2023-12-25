/*
一、设计三角形类SJ及其子类RtSJ，要求同时满足如下条件：
1、三边均为正数，且任意两边之和大于第三边；
2、输入1/2/3条边能构造三角。若边值不满足约束，则只能创建单位三角（即三边值均为1）
3、对象的三边可以被修改，但无论以何种方式修改，三边值均能确保正确，换言之，若三边值错误，对象的三边不会被更改。
4、SJ对象通过equals(t)判断是否与三角形t全等。
5、SJ有子类直角三角RtSJ，它有两个构造函数：通过两条直角边构造；或通过三条边构造，若三边不满足直角三角约束，只能创建以3、4、5为边长的单位直角三角。

请设计出符合上述要求的类SJ和RtSJ。

*/
class SJ {
	private int a,b,c; //设为private，旨在禁止任意更改a、b、c
	public boolean limit(int x, int y, int z) {
		return x>0&&y>0&&z>0&&(x+y>z)&&(x+z>y)&&(y+z>x);
	}
	//下面三个构造函数，实现条件2
	public SJ(int x, int y, int z) {
		if(limit(x,y,z)==false) { a=1;b=1;c=1; return;} //边值错误，只能构造单位三角
		a=x;b=y;c=z;
	}
	public SJ(int x, int y) {
		if(limit(x,x,y)==false) { a=1;b=1;c=1; return;} //边值错误，只能构造单位三角
		a=x;b=x;c=y;
	}
	//注：上述构造函数，也可写成如下方式，why？
//	public SJ(int x, int y) { this(x,x,y);	}
	public SJ(int x) { this(x,x,x);	}
	//下面方法，实现条件3
	public void setEdges(int x, int y, int z) {//边值设为private后，更改边值，只能通过此方法
		//即边值设为私有后，只能通过预先设定的方式（即本方法）来更改边值（好处？）
		if(limit(x,y,z)==false) return;
		a=x;b=y;c=z;
	}
	//下面方法，实现条件4
	public boolean equals(SJ t) {
		if(a+b+c!=t.a+t.b+t.c)return false; //此句旨在初筛，可提高效率
		int x,y,z;
		x=this.a;  y=this.b;  z=this.c;
		if(t.a==x)x=0; //注意：此3句必须用else连接，否则有问题，why?
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
	public RtSJ(int x, int y, int z) {//强调：z为斜边
		super(x,y,z);//super、this调用构造函数，必须放在第一句
		//但上述构造可能存在错误三边。故下面判断，若三边错误，就重新设定三边值
		if(limit(x,y,z)==false ||limitRt(x,y,z)==false) 
			setEdges(3,4,5);
	}
	public RtSJ(int x,int y){//Math.sqrt(x*x+y*y)返回的是double数据，这里进行强制类型转换
		super(x,y,(int)Math.sqrt(x*x+y*y));
	}
}
class TestSJ{
	public static void main(String[] args) {
		System.out.println("下面测试三角形创建：");
		System.out.println("new SJ(-1,-1,3)："+new SJ(-1,-1,3));
		System.out.println("new SJ(2,2,1)："+new SJ(2,2,1));
		System.out.println("new RtSJ(2,3,4)："+new RtSJ(2,3,4));
		System.out.println("new RtSJ(30,40)："+new RtSJ(30,40));

		System.out.println("\n下面测试三角形全等：");
		SJ s1=new SJ(5,3,4); SJ s2=new RtSJ(3,4,5);
		System.out.println(s1+" == "+s2+": "+s1.equals(s2));
		s1=new SJ(2,2,3); s2=new SJ(2,3,3);
		System.out.println(s1+" == "+s2+": "+s1.equals(s2));
	}
}
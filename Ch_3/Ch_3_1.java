class Triangle{
	private double a,b,c;
	public Triangle(){a=1;b=1;c=1;}//创建一个单位三角
	private boolean limit(double x,double y,double z){//三角形的约束条件
		return (x>0&&y>0&&z>0&&x+y>z&&x+z>y&&y+z>x);
	}
	public void setEdges(double x,double y,double z){
		if(limit(x,y,z)==false)return; //无改变
		else {a=x;b=y;c=z;}
	}
	public String toString(){
		return "a="+a+", b="+b+", c="+c;
	}
	public boolean equals(Triangle t){
		String s=a+","+b+","+c; //将三边连接成字符串
		String x,y,z; x=t.a+"";y=t.b+"";z=t.c+"";
		if(s.indexOf(x)<0)return false;
		else s=s.replaceFirst(x,"#");//把s中的x用#替换，即删除其中的x
		if(s.indexOf(y)<0)return false;
		else s=s.replaceFirst(y,"#");//把s中的y用#替换，即删除其中的y
		if(s.indexOf(z)<0)return false;
		else return true;
	}
/*
 	public boolean equals(Triangle t){
		double[] x={a,b,c}; double[] y={t.a,t.b,t.c};
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(x[i]==y[j]) y[j]=-1;
		return (y[0]==-1 && y[1]==-1 && y[2]==-1);
	}

 */
}
class App{
	public static void main (String[] args) {Triangle t1,t2,t3;
		t1=new Triangle();
		//下面模拟能否不通过t的对外接口（即public方法），直接存取t的private成员
		//t.a=2;t.limit(2,3,4);//两条语句产生编译错
		t1.setEdges(1,2,3);	System.out.println("赋值1,2,3，t1: "+t1);
		t1.setEdges(2,3,4);	System.out.println("赋值2,3,4，t1: "+t1);
		t2=new Triangle();  t2.setEdges(3,4,2); System.out.print("t2: "+t2);
		t3=new Triangle();  t3.setEdges(3,4,5); System.out.println("\tt3: "+t3);
		System.out.print("t1==t2: "+t1.equals(t2));
		System.out.print("\tt1==t3: "+t1.equals(t3));
	}
}

/* 理解和测试static修饰
 * 要求：1、只能创建正确的三角形；2、至多能创建3个
 **/
class Triangle{
	private int a,b,c;
	private static final int total=3; //三角形对象的限额
	private static int count=0;       //当前三角型对象的数量
	private Triangle(int x,int y,int z){a=x;b=y;c=z; count++;}

	private static boolean limit(int x,int y,int z){//三角形的约束条件
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
		Triangle[] t=new Triangle[10]; //注意：创建的是Triangle数组对象，而非Triangle对象
		t[0]=Triangle.creatTriangle(1,2,3); //非法数据，无法创建出三角对象，即t[0]的值为null
		t[1]=Triangle.creatTriangle(2,3,4);; //合法数据
		for(i=2; i<t.length; i++)//创建10次合法三角形，注：鉴于数量限制，很多无法创建成功
			t[i]=Triangle.creatTriangle(3,4,5);
		for(i=0; i<t.length; i++)System.out.print(t[i]+"、");
		t[1].showCount();//注：已确定t[1]非空
	}
}

class T{
	void f( int x, int [ ]a, int [ ] b){
		// 其中x是基本型代表，a是引用型代表， b用于展示如何真正修改引用对象
		x=11;  a=new int[3];  a[0]=22;  b[0]=33;   //注意a、b的修改方式不同
		System.out.print("\n函数中："); show(x,a,b);
	}
	void show( int x, int [ ]a, int [ ] b){
		System.out.print("x= "+x+", a= {" );
		for(int i=0; i<a.length; i++)System.out.print(a[i]+",");
		System.out.print("}, b={ " );
		for(int i=0; i<b.length; i++)System.out.print(b[i]+",");
		System.out.print(" } " );
	}	
}
class App{
	public static void main (String[] args) {
		T t=new T(); 
		int x=1; int []a={2,3}; int []b={4,5,6}; //将作为实参的变量
		System.out.print("原始值：");  t.show(x,a,b);
		t.f(x,a,b);  //执行函数调用，注意对比执行前后a、b、c值的变化
		System.out.print("\n返回后：");  t.show(x,a,b);
	}
}

class T{
	void f( int x, int [ ]a, int [ ] b){
		// ����x�ǻ����ʹ���a�������ʹ��� b����չʾ��������޸����ö���
		x=11;  a=new int[3];  a[0]=22;  b[0]=33;   //ע��a��b���޸ķ�ʽ��ͬ
		System.out.print("\n�����У�"); show(x,a,b);
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
		int x=1; int []a={2,3}; int []b={4,5,6}; //����Ϊʵ�εı���
		System.out.print("ԭʼֵ��");  t.show(x,a,b);
		t.f(x,a,b);  //ִ�к������ã�ע��Ա�ִ��ǰ��a��b��cֵ�ı仯
		System.out.print("\n���غ�");  t.show(x,a,b);
	}
}

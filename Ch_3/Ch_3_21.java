import java.io.IOException;
class ThrowsApp{
	void a( )throws IOException{  b( ); }
	void b( )throws IOException{  c( ); }
	void c( )throws IOException{  d(5,6 ); }
	void d(int x, int y )throws IOException{
		if(x>y)	throw  new IOException( );   //创建检查型异常对象
		else throw new ArithmeticException();//创建非检查型异常对象
		//x=5;//编译错
	}
	public static void main (String[] args) throws IOException{
		new ThrowsApp().a();
	}
}

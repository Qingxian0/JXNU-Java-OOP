import java.io.IOException;
class ThrowsApp{
	void a( )throws IOException{  b( ); }
	void b( )throws IOException{  c( ); }
	void c( )throws IOException{  d(5,6 ); }
	void d(int x, int y )throws IOException{
		if(x>y)	throw  new IOException( );   //����������쳣����
		else throw new ArithmeticException();//�����Ǽ�����쳣����
		//x=5;//�����
	}
	public static void main (String[] args) throws IOException{
		new ThrowsApp().a();
	}
}

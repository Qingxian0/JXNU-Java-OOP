//目的：展示按字节读写时，顺序必须保持一致
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
class Ch_6_7{
	public static void main (String[] args){
		int i=5; double d=7.6; char c='x';
		try( DataOutputStream  out=new  DataOutputStream(new FileOutputStream("a.dat"));
		     DataInputStream in=new DataInputStream(new FileInputStream("a.dat"));
			){ 
			out.writeInt(i); out.writeDouble(d); out.writeChar(c);//注意写入次序
			//d=in.readDouble();i=in.readInt();  c=in.readChar();   //错误顺序
			  i=in.readInt();d=in.readDouble();c=in.readChar();       //正确顺序
			 } //注意：读取数据的顺序要与写入数据顺序相同，不同则数据错误
		catch (IOException e) {e.printStackTrace(); }
		System.out.print(" i="+i+" d="+d+" c="+c);
	  }
}

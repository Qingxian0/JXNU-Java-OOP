//Ŀ�ģ�չʾ���ֽڶ�дʱ��˳����뱣��һ��
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
			out.writeInt(i); out.writeDouble(d); out.writeChar(c);//ע��д�����
			//d=in.readDouble();i=in.readInt();  c=in.readChar();   //����˳��
			  i=in.readInt();d=in.readDouble();c=in.readChar();       //��ȷ˳��
			 } //ע�⣺��ȡ���ݵ�˳��Ҫ��д������˳����ͬ����ͬ�����ݴ���
		catch (IOException e) {e.printStackTrace(); }
		System.out.print(" i="+i+" d="+d+" c="+c);
	  }
}

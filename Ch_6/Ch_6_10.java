import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.Externalizable; //新增
import java.io.IOException;    //新增
import java.io.ObjectInput;    //新增
import java.io.ObjectOutput;   //新增


class User extends Thread implements Externalizable{ //注：Thread类未实现Serializable接口
  //此处User继承Thread，旨在表明User继承了一个未实现Serializable接口的系统类
	private String name, password; //期望特殊处理的成员
	int age; boolean vip;
	private A a;
	public User(){;} //用Externalizable反序列化会自动调用public、无参的构造函数
	public User(String n, String p, int ag, boolean vp, A aa){
		name=n; password=p; age=ag; vip=vp; a=aa; 
	}
	public String toString(){
		return "name=\""+name+"\" password=\""+ password+"\" age="+age+" vip="+vip+" a="+a;
	}
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(age);  out.writeBoolean(vip);
		out.writeObject( JiaMiJieMi.jiaMi(password) );
		out.writeObject( name );	out.writeObject(a);
	}
	public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException{
		age=in.readInt(); vip=in.readBoolean();
		String p=(String)in.readObject(); 
			password=JiaMiJieMi.jieMi(p);
		name=(String)in.readObject();
		a=(A)in.readObject();
	}
}
class A implements Serializable{ //用于测试在手动序列化中能否包含自动的部分
	String s;
	private B b;
	public A(String x, B y){s=x; b=y; }
	public String toString(){	return "A{ s=\""+s+"\", b="+b+"}";	}
}
class B implements Serializable{
	private int x;
	public B(int v){x=v;}
	public String toString(){	return "B{ x="+x+"}";	}
}
class JiaMiJieMi{
	public static String jiaMi(String s){//加密算法：返回加密后的字符串
		if (s==null)return null;
		char[] c=s.toCharArray();
		for(int i=0; i<c.length; i++)c[i]=(char)(c[i]*10+5);
		String news=String.valueOf(c);
		System.out.print("\n加密前的字符串为："+s);
		System.out.print("\n加密后的字符串为："+news);
		return news;
	}
	public static String jieMi(String s){//解密算法：返回解密后的字符串
		if (s==null)return null;
		char[] c=s.toCharArray();
		for(int i=0; i<c.length; i++)c[i]=(char)((c[i]-5)/10);
		return String.valueOf(c);		
	}
}
public class Ch_6_10{
	public static void main (String[] args) {
		B b=new B(5);
		A a=new A("aaa",b);
		User u=new User("张三","abcde",18,true,a);
		System.out.print("写入文件前："+u);
		try( //out、in是用于序列化和反序列化的对象流
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data2.dat"));
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("data2.dat"));
			){	out.writeObject(u);        //执行序列化：将对象“融入”对象流
			    u=(User)in.readObject();      //执行反序列化：从对象流提取对象。思考：为何需要强制类型转换？
		}catch(Exception ee){ ee.printStackTrace(); }
		System.out.print("\n从文件恢复："+u);
	}
}
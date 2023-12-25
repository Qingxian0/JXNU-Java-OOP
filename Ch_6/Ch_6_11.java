import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.IOException;

class User extends Thread implements Serializable{//���Զ����л�
	private String name;
	private transient String password; //�������⴦��ĳ�ԱҪ��transient����
	private void writeObject(ObjectOutputStream out) throws IOException{//���ⷽ��1
		out.defaultWriteObject();//����ȱʡ�����л�����
		out.writeObject( JiaMiJieMi.jiaMi(password) );
	}
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{//���ⷽ��2
		in.defaultReadObject();  //����ȱʡ�ķ����л�����
		String p=(String)in.readObject();
		password=JiaMiJieMi.jieMi(p);
	}

	//----�����������6.10��ȫ��ͬ
	int age; boolean vip;
	private A a;
	public User(){;} //��Externalizable�����л����Զ�����public���޲εĹ��캯��
	public User(String n, String p, int ag, boolean vp, A aa){
		name=n; password=p; age=ag; vip=vp; a=aa;
	}
	public String toString(){
		return "name=\""+name+"\" password=\""+ password+"\" age="+age+" vip="+vip+" a="+a;
	}
/*
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
*/
}
class A implements Serializable{ //���ڲ������ֶ����л����ܷ�����Զ��Ĳ���
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
	public static String jiaMi(String s){//�����㷨�����ؼ��ܺ���ַ���
		if (s==null)return null;
		char[] c=s.toCharArray();
		for(int i=0; i<c.length; i++)c[i]=(char)(c[i]*10+5);
		String news=String.valueOf(c);
		System.out.print("\n����ǰ���ַ���Ϊ��"+s);
		System.out.print("\n���ܺ���ַ���Ϊ��"+news);
		return news;
	}
	public static String jieMi(String s){//�����㷨�����ؽ��ܺ���ַ���
		if (s==null)return null;
		char[] c=s.toCharArray();
		for(int i=0; i<c.length; i++)c[i]=(char)((c[i]-5)/10);
		return String.valueOf(c);
	}
}
public class Ch_6_11{
	public static void main (String[] args) {
		B b=new B(5);
		A a=new A("aaa",b);
		User u=new User("����","abcde",18,true,a);
		System.out.print("д���ļ�ǰ��"+u);
		try( //out��in���������л��ͷ����л��Ķ�����
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data3.dat"));
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("data3.dat"));
			){	out.writeObject(u);        //ִ�����л������������롱������
			    u=(User)in.readObject();      //ִ�з����л����Ӷ�������ȡ����˼����Ϊ����Ҫǿ������ת����
		}catch(Exception ee){ ee.printStackTrace(); }
		System.out.print("\n���ļ��ָ���"+u);
	}
}
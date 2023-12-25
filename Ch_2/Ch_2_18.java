import java.util.regex.Pattern;
import java.util.regex.Matcher;
class Recognizer{//����ʶ����
	static String[] getWords(String text) {//��Ӣ����ĸ�����֡��»��ߡ�.��@������ַ���Ϊ�ָ���
		System.out.print("�������ַ����ǣ�"+text);
		String[] words=text.split("[\\W&&[^.@]]+");//����.��@֮������С���Ӣ����ĸ�����顢�»��ߡ�����Ϊ�ָ���
		System.out.print("\n������ĵ����ǣ�");
		for(String x:words)System.out.print(x+"��");//����Ϊ�ָ���
		return words;
	}
	static void recognize(String text, String reg) {//��ʽ1����String��matches()ʶ��
		String[] words=getWords(text);
		System.out.print("\nʶ����ĵ����ǣ�");
		for(int i=0; i<words.length; i++)//ʶ�����ʶ����
			if(words[i].matches(reg)==true)
				System.out.print(words[i]+"��");
	}
	static void recognize1(String text, String reg) {//��ʽ2����Pattern��Matcherʶ��
		String[] words=getWords(text);
		System.out.print("\nʶ����ĵ����ǣ�");
		Matcher m;  Pattern p=Pattern.compile(reg); //�ȶ�������ʽreg���루��Ԥ����
		for(int i=0; i<words.length; i++) {
			m=p.matcher(words[i]);
			if(m.matches()==true)
				System.out.print(words[i]+"��");
		}
	}
	static void test() {//��������ʱ���룬���ڲ���ǿ��������
		//������ʽ-ǿ����-��������ĸ���ּ������ַ���������ĸ��ͷ��8λ���ϡ�
		String reg="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[a-zA-Z0-9\\S]{8,}$";
			//��������$��ʾ�н�������?=������ṹ�����Pattern��
		   //(?=.*[A-Z])�������ַ�����.����0��1������*�������һ����д��ĸ
		   //����(?=.*[A-Z])��ʾ�д�д��ĸ
		   //reg=(�д�д��ĸ)(��Сд��ĸ)(������)(�зǿհ��ַ�)[��Сд��ĸ�����֡��ǿհ׷�]����8��

		//ע����Щ���������淽ʽ���ǿ�ַ���ʵ��������
		  //reg="^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{8}$";//����
		  //reg="^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{8,}$";
		//�京��Ϊ���ų�(ֻ��������)(����������)(ֻ������Сд��ĸ)(��������Сд��ĸ)(ֻ������Сд��ĸ������)[�ɴ�Сд��ĸ���ַǿհ׷����]{����8��}
		
		String t1,t2;
		t1="1qQqq131@3"; t2="abc124@@";
		System.out.print(t1+"Ϊǿ���룿"+t1.matches(reg)+"\n");
		System.out.print(t2+"Ϊǿ���룿"+t2.matches(reg)+"\n");

	}
}
class App18{
	public static void main(String[] args) {String text,reg;
		System.out.print("==== ����ʶ��C��ʶ�� =====\n");//
		text="int  x,y,a12; m=567+y11; z= ���� + 15L";
		reg="[a-zA-Z_]([0-9a-zA-Z_])*";
		Recognizer.recognize(text, reg); //��String�����ʶ��

		System.out.print("\n==== ����ʶ��绰���� =====\n");//
		text="�����ĵ绰��15907911234   1681234567   12345678912   13107911234";
		reg="(15[0-35-9]|13[0-9]){1}(\\d){8}";
		Recognizer.recognize1(text, reg); //����Pattern��Matcher��ʶ��

		System.out.print("\n==== ����ʶ������ =====\n");//
		text="���������� aa.bb.cc - 341234@qq.com abc.@123.com xyz@123.com.  abc@163.com; xyz@sina.com.cn; ";
		reg="([\\w]+@[\\w]+.[\\w]+)|([\\w]+@[\\w]+.[\\w]+.[\\w]+)";
		Recognizer.recognize(text, reg);
		
		System.out.print("\n==== �������ǿ����Լ�� =====\n");
		Recognizer.test();
	}
}

import java.util.Scanner;
import java.util.Arrays;
class StringArray{
	String[] str=new String[10];
	void inputStrings(){//����һ���ַ�����
      Scanner sc = new Scanner(System.in);
      for(int i=0; i<str.length; i++)  	str[i]=sc.next();
	}
	void sortString(){		Arrays.sort(str);	}
	void showInfo(){
		for(String s: str)
			System.out.print(s+"__");
	}
}
public class Ch_2_10{
	public static void main (String[] args) {
		StringArray sa=new StringArray();
		System.out.print("������10�������ֻ�Ӣ����ĸ��ɵ��ַ������Կո�Ϊ���:\n");
		sa.inputStrings();
		System.out.print("ԭʼ����");sa.showInfo();
		sa.sortString();
		System.out.print("\n�����");sa.showInfo();
	}
}
/*
 *�ڶ������н����
������10���ַ������Կո�Ϊ���:
���� С�� ���� ���� ���� ���� ���� ţ�� �ž�
ԭʼ��������__С��__����__����__����__����__����__ţ��__�ž�__
����󣺾ž�__С��__����__����__����__ţ��__����__����__����__

 *      //Scanner in = new Scanner(System.in);//�ÿո����ָ����� 
      	 //�˸�ʽΪĬ���������ʾ�ÿո����ָ�����
      //Scanner in = new Scanner(System.in).useDelimiter("[\\n\\r#]+"); 
      	 //�˸�ʽ�ر�ָ�����ûس�����#���ָ�����

      //String s1="001  ����  M  18  true  88.6  77.9";//�ÿո����ָ�����
      //String s2="001#����#M#18#true#88.6#77.9##";    //��#���ָ�����
      //Scanner in = new Scanner(s1);
     //Scanner in = new Scanner(s2).useDelimiter("\\s*#\\s*");//ֻ����һ��#Ϊ���

 **/

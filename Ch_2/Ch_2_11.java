import java.util.Scanner;
import java.util.Arrays;
class StringArray{
	String[] str=new String[10];
	void inputStrings(){//输入一组字符串，
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
		System.out.print("请输入10个由数字或英文字母组成的字符串，以空格为间隔:\n");
		sa.inputStrings();
		System.out.print("原始串：");sa.showInfo();
		sa.sortString();
		System.out.print("\n排序后：");sa.showInfo();
	}
}
/*
 *第二次运行结果：
请输入10个字符串，以空格为间隔:
易云 小二 张三 李四 王五 马六 赵七 牛八 九九
原始串：易云__小二__张三__李四__王五__马六__赵七__牛八__九九__
排序后：九九__小二__张三__易云__李四__牛八__王五__赵七__马六__

 *      //Scanner in = new Scanner(System.in);//用空格区分各数据 
      	 //此格式为默认情况，表示用空格区分各数据
      //Scanner in = new Scanner(System.in).useDelimiter("[\\n\\r#]+"); 
      	 //此格式特别指明：用回车或是#区分各数据

      //String s1="001  张三  M  18  true  88.6  77.9";//用空格区分各数据
      //String s2="001#张三#M#18#true#88.6#77.9##";    //用#区分各数据
      //Scanner in = new Scanner(s1);
     //Scanner in = new Scanner(s2).useDelimiter("\\s*#\\s*");//只能用一个#为间隔

 **/

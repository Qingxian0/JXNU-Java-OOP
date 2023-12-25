import java.util.regex.Pattern;
import java.util.regex.Matcher;
class Recognizer{//智能识别器
	static String[] getWords(String text) {//以英文字母、数字、下划线、.、@以外的字符作为分隔符
		System.out.print("给定的字符串是："+text);
		String[] words=text.split("[\\W&&[^.@]]+");//将除.、@之外的所有【非英文字母、数组、下划线】均视为分隔符
		System.out.print("\n分离出的单词是：");
		for(String x:words)System.out.print(x+"、");//、作为分隔符
		return words;
	}
	static void recognize(String text, String reg) {//方式1：用String的matches()识别
		String[] words=getWords(text);
		System.out.print("\n识别出的单词是：");
		for(int i=0; i<words.length; i++)//识别并输出识别结果
			if(words[i].matches(reg)==true)
				System.out.print(words[i]+"、");
	}
	static void recognize1(String text, String reg) {//方式2：用Pattern、Matcher识别
		String[] words=getWords(text);
		System.out.print("\n识别出的单词是：");
		Matcher m;  Pattern p=Pattern.compile(reg); //先对正则表达式reg编译（即预处理）
		for(int i=0; i<words.length; i++) {
			m=p.matcher(words[i]);
			if(m.matches()==true)
				System.out.print(words[i]+"、");
		}
	}
	static void test() {//本方法临时加入，用于测试强密码描述
		//正则表达式-强密码-【必填字母数字及特殊字符，且以字母开头，8位以上】
		String reg="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[a-zA-Z0-9\\S]{8,}$";
			//其中其中$表示行结束符，?=是特殊结构（详见Pattern）
		   //(?=.*[A-Z])：任意字符（即.）有0或1个（即*），后接一个大写字母
		   //即：(?=.*[A-Z])表示有大写字母
		   //reg=(有大写字母)(有小写字母)(有数字)(有非空白字符)[大小写字母、数字、非空白符]至少8个

		//注：有些资料用下面方式表达强字符，实际上有误：
		  //reg="^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{8}$";//有误
		  //reg="^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{8,}$";
		//其含义为：排除(只包含数字)(不包含数字)(只包含大小写字母)(不包含大小写字母)(只包含大小写字母和数字)[由大小写字母数字非空白符组成]{至少8个}
		
		String t1,t2;
		t1="1qQqq131@3"; t2="abc124@@";
		System.out.print(t1+"为强密码？"+t1.matches(reg)+"\n");
		System.out.print(t2+"为强密码？"+t2.matches(reg)+"\n");

	}
}
class App18{
	public static void main(String[] args) {String text,reg;
		System.out.print("==== 下面识别C标识符 =====\n");//
		text="int  x,y,a12; m=567+y11; z= 张三 + 15L";
		reg="[a-zA-Z_]([0-9a-zA-Z_])*";
		Recognizer.recognize(text, reg); //用String类进行识别

		System.out.print("\n==== 下面识别电话号码 =====\n");//
		text="张三的电话是15907911234   1681234567   12345678912   13107911234";
		reg="(15[0-35-9]|13[0-9]){1}(\\d){8}";
		Recognizer.recognize1(text, reg); //借助Pattern、Matcher类识别

		System.out.print("\n==== 下面识别邮箱 =====\n");//
		text="张三的邮箱 aa.bb.cc - 341234@qq.com abc.@123.com xyz@123.com.  abc@163.com; xyz@sina.com.cn; ";
		reg="([\\w]+@[\\w]+.[\\w]+)|([\\w]+@[\\w]+.[\\w]+.[\\w]+)";
		Recognizer.recognize(text, reg);
		
		System.out.print("\n==== 下面测试强密码约束 =====\n");
		Recognizer.test();
	}
}

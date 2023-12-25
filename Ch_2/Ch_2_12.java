import java.util.Scanner;
import java.time.LocalDate;

class StringFormat{//自定义格式化字符串，用于格式化包含汉字的字符串
    private static boolean isChinese(char c) {//识别是否为汉字
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) 
            return true;
        return false;
    }
	public static int length(String s){//s的实际占用宽度：一个汉字占两个字符位置
		char[] chAr = s.toCharArray();
		int c=0;
		for(char x: chAr)
			c=(isChinese(x))?c+2:c+1;
		return c;
	}
	public static String stringHead(String s, int len){//取s中从头开始的len个字符宽度（注：中文占2个字符）
		char[] chAr = s.toCharArray();
		int c=0,i=0;
		for( ; i<chAr.length&&c<len; i++)//当c满足宽度要求时，i就是所取字符的实际数量
			c=(isChinese(chAr[i]))?c+2:c+1;//c用于计算实际宽度
		return s.substring(0,i);//substring(0,i)源自String，从头取i个字符
	}
	public static String repeat(char c, int n){//将字符c重复n次
		if(n<=0) return null;
		String s="";
		for(int i=0; i<n; i++)s=s+c;
		return s;
	}
	public static String formatR(String s, int n, char c ){//右对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在左部
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return repeat(c,n-len)+s;
	} 
	public static String formatL(String s, int n, char c ){//左对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在右部
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return s+repeat(c,n-len);
	} 
	public static String formatC(String s, int n, char c ){//居中对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在右部
		int left,right,len=length(s);
		if(len>=n) return stringHead(s,n);
		left=(n-len)/2; right=n-len-left;
		return repeat(c,left)+s+repeat(c,right);
	}
}
class Student{//学生信息：学号、姓名、性别、生日、是否党员、语文、数学
	String ID,name;  char sex;  boolean partyMember;  double math,chinese; 
	LocalDate birthday;
	static void titleHint(){//用于读取数据时给出的输入格式提示。
    	System.out.print("\n请输入一组学生，输入Ctrl+Z结束，格式为：");
    	System.out.print("\n学号 姓名 性别 出生日期    党员 数学 语文， 例如：\n");
		System.out.println(" 001 张三 M 2001-07-15 true 84.2 93.7");
	}
	static void showTitle(){//作为打印输出时的标题行
		System.out.print(" 学号   姓名   性别  出生日期   党员    数学  语文\n");
		System.out.print("-----------------------------------------------------\n");
	}
	void read(Scanner sc){//从sc对象读取所需的所有数据，读取次序与titleHint()相同
		ID=sc.next(); name=sc.next(); //读取学号、姓名两个字符串
		sex=sc.next().charAt(0);     //先读取String型数据，如"M"，再取其首字符'M'
		String bd=sc.next();  //用于读取生日字符串
		birthday=LocalDate.parse(bd); //将日期型字符串转变成日期型
		partyMember=sc.nextBoolean();        //读取boolean型数据
		math=sc.nextDouble();  
		chinese=sc.nextDouble();  //读取double型数据
	}
	public String toString(){//格式化后的字符串
		String id,xm,xb,dy,sx,yw;
		id=String.format("%-5s",ID);
		xm=StringFormat.formatL(name,8,' ');//姓名固定占用8个字符，不足部分用空格补充。注一个汉字占两个字符位置，
		xb=(sex=='F'||sex=='f')?"女":( (sex=='M'||sex=='m')?"男":"  ?  ");
		dy=(partyMember==true)?"共产党员":"非党员  ";
		sx=String.format("%-6.2f",math); //'-'表示左对齐（不写则默认是右对齐），f表示是实数
		yw=String.format("%-6.2f",chinese);  // 6表示总长度(如100.00长度为6)，.2表示保留两位小数
		return " "+id+" "+xm+" "+xb+" "+birthday+" "+dy+" "+sx+" "+yw; 
	}
}
class BanJi{//班级：班级容量+存储Student对象的数组+实际人数
	final int maxN;	  Student [] st;	 int renShu;
	BanJi(int max){ maxN=max; st=new Student[max]; renShu=0; }	
	void add(Student s){st[renShu]=s; renShu++; }//向班级中追加学生s
	void append( ){	//向数组尾部“追加”输入一批学生，以Ctrl+Z结束		
		Student.titleHint();  //给出输入次序和格式的示例
		Scanner sc=new Scanner(System.in); //用空白符作为间隔符
		//Scanner sc =new Scanner(System.in).useDelimiter("[\\n\\r#]+");//用#、回车等作为间隔符
		Student s;  //下面循环：造对象--向对象填充数据--将对象加入班级
		while(sc.hasNext()==true){ s=new Student(); s.read(sc); add(s); }
	}
	void show(){
		Student.showTitle();
		for(int i=0; i<renShu; i++) System.out.println(st[i]);
		System.out.println("班级中共有 "+renShu+" 人。");
	}
}
class App{
	public static void main (String[] args) {
		BanJi bj=new BanJi(20);   bj.append();
		System.out.println("班级信息如下：");	bj.show();
	}
}

/* 注意：复制时每行要完整，如复制全部时，鼠标要在003下面那行
   004 赵芬 F 1998-09-18 true 100 20
002 李晓明 M 1997-08-03 false 89 76
001 司马相如 F 2002-07-15 true 73.1 98.6
003    马凯   M 2001-04-01 true 78 99
 
 */
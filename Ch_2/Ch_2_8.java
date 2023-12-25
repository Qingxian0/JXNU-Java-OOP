import java.util.Scanner;
class Student{//学生信息：学号、姓名、性别、年龄、是否党员、语文、数学
	String ID,name;  char sex;  int age;  boolean partyMember;  double math,chinese; 
	static void titleHint(){//用于读取数据时给出的输入格式提示。思考：为何设为静态？
    	System.out.print("\n请输入一组学生，输入Ctrl+Z结束，格式为：");
    	System.out.print("\n学号 姓名 性别 年龄 党员 数学 语文， 例如：");
		System.out.print("\n001 张三 M 18 true 84.2 93.7\n");
	}
	void read(Scanner sc){//从sc对象读取所需的所有数据，读取次序与titleHint()相同
		ID=sc.next(); name=sc.next(); //读取学号、姓名两个字符串
		sex=sc.next().charAt(0);     //先读取String型数据，如"M"，再取其首字符'M'
		age=sc.nextInt();            //读取int型数据
		partyMember=sc.nextBoolean();        //读取boolean型数据
		math=sc.nextDouble();  
			chinese=sc.nextDouble();  //读取double型数据
	}
	public String toString(){//自定义Student型对象的输出信息
		String xb=(sex=='F'||sex=='f')?"女":( (sex=='M'||sex=='m')?"男":"未知" );
		String dy=(partyMember==true) ? "中共党员":"非党员";
		return ID+" "+name+" "+xb+" "+age+"岁 "+dy+" 数学："+math+" 语文："+chinese; 
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
/* 以空白作为间隔符
001 赵颖 F 18 true 73.1 98.6
002 李晓明 M 19 false 89 76
003 罗亮   M 20 true 78 99
004 王大川 F 18 true 100 20
 */
/* 以#作为间隔符
#001###赵  颖##F#18#true#73.1#98.6
002#李晓明#M#19#false#89#76
003#罗  亮#M#20#true#78#99
004#王大川#F#18#true#100#20
 */
/*设计类MathUtil，该类包含成员 int[][] a，要求：
（1）编写构造函数：借助随机数，创建数组对象a，并向其中填入数据。
要求：a的行数、每列的元素个数均基于随机数产生（均小于10），
创建完数组对象后，向a中填入随机数产生的数据（数值均小于20）；
（2）编写int sum()，计算并输出a中所有数据的累加和。
（3）编写void showMe()，打印输出a中的所有元素，最后显示a中共有多少元素，
以及累加和结果。要求：相同a[i]排成一行，不同a[i]分别排在不同行。

 **/
import java.util.Random;
import java.util.Scanner;
import java.nio.charset.Charset;
class MathUtil{
	int[][] a;
	MathUtil(){
		Random r=new Random();
		int row=r.nextInt(9)+1;
		a=new int[row][];
		for(int i=0; i<row; i++){
			int cow=r.nextInt(9)+1;
			a[i]=new int[cow];
			for(int j=0; j<cow; j++) a[i][j]=r.nextInt(20);
		}
	}
	int sum(){
		int s=0;
		for(int i=0; i<a.length; i++)
			for(int j=0; j<a[i].length; j++)s=s+a[i][j];
		return s;
	}
	void showMe(){int c=0;
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a[i].length; j++){
				System.out.printf("%3d",a[i][j]); c++;
			}
			System.out.print("\n");
		}
		System.out.print(a.length+"行，元素数量="+c+"个，累加和="+sum());
	}
}
class TT{

}
class App{
	public static void main (String[] args) {
		int x=1; char c='a'+5;
		int i=8,k=9;
		System.out.println("i="+i+"  k="+k);
		/*
		System.out.println("当前JRE：" + System.getProperty("java.version"));
		System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset());
		int[] a=null;
		 System.out.println("a.length="+a.length);
		 */
	}
	public static void main1(String[] x){
		String text="001###赵  颖##F#18#true##73.1#98.6";
		text="#001###赵  颖##F#18#true#73.1#98.6\n002#李晓明#M#19#false#89#76\n003#罗亮#M#20#true#78#99\n004#王大川#F#18#true#100#20";
		//Scanner s =new Scanner(text).useDelimiter("[\\n#]+");
		Scanner s =new Scanner(System.in).useDelimiter("[\\n\\r#]+");
		while(s.hasNext()){
			System.out.println("1:"+s.next());
			System.out.println("2:"+s.next());
			System.out.println("3:"+s.next());
			System.out.println("4:"+s.nextInt());
			System.out.println("5:"+s.nextBoolean());
			System.out.println("6:"+s.nextDouble());
			System.out.println("7:"+s.nextDouble());
		}
		//String[] word=text.split("\\s(\\n#)+\\s");
		//for(String s: word) System.out.println(s);
		//MathUtil m=new MathUtil();
		//m.showMe();
	}
}
/*
#001###赵  颖##F#18#true#73.1#98.6
002#李晓明#M#19#false#89#76
003#罗亮#M#20#true#78#99
004#王大川#F#18#true#100#20
 */

/*�����MathUtil�����������Ա int[][] a��Ҫ��
��1����д���캯��������������������������a�����������������ݡ�
Ҫ��a��������ÿ�е�Ԫ�ظ����������������������С��10����
����������������a��������������������ݣ���ֵ��С��20����
��2����дint sum()�����㲢���a���������ݵ��ۼӺ͡�
��3����дvoid showMe()����ӡ���a�е�����Ԫ�أ������ʾa�й��ж���Ԫ�أ�
�Լ��ۼӺͽ����Ҫ����ͬa[i]�ų�һ�У���ͬa[i]�ֱ����ڲ�ͬ�С�

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
		System.out.print(a.length+"�У�Ԫ������="+c+"�����ۼӺ�="+sum());
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
		System.out.println("��ǰJRE��" + System.getProperty("java.version"));
		System.out.println("��ǰJVM��Ĭ���ַ�����" + Charset.defaultCharset());
		int[] a=null;
		 System.out.println("a.length="+a.length);
		 */
	}
	public static void main1(String[] x){
		String text="001###��  ӱ##F#18#true##73.1#98.6";
		text="#001###��  ӱ##F#18#true#73.1#98.6\n002#������#M#19#false#89#76\n003#����#M#20#true#78#99\n004#����#F#18#true#100#20";
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
#001###��  ӱ##F#18#true#73.1#98.6
002#������#M#19#false#89#76
003#����#M#20#true#78#99
004#����#F#18#true#100#20
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
class Ch_6_2{
	static String f1()throws IOException{//方法1：用System.in直接读字节数据
		byte[] b=new byte[100];     //存储读入数据的字节数组
		int len=System.in.read(b);  //读取数据放入字节数组，返回读取的字节数
		return new String(b,0,len); //为提取读取的信息，需要将字节转换成字符
	}
	static String f2()throws IOException{//方法2：逐行读取字符串
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	static void f3(){//方法3：直接读取基本型数据（无需先获得字符串，继而转换成基本型）
		Scanner sc=new Scanner(System.in);
		int i=sc.nextInt();
		double d=sc.nextDouble();
		boolean b=sc.nextBoolean();
		System.out.println("int型："+i+"，double型："+d+"，boolean型："+b);
		//System.out.println("int型："+i);
		//System.out.println("double型："+d);
		//System.out.println("boolean型："+b);
	}
	static void showData(String data){ //从字符串提取数据并打印
		String[] dataStr=data.split("[ ,]+");//用空格、,作为分隔符
		int i=Integer.parseInt(dataStr[0]);  //展示用不同方式获取int、double数据
		double d=Double.valueOf(dataStr[1]);
		boolean b=Boolean.valueOf(dataStr[2]);
		System.out.println("int型："+i+"，double型："+d+"，boolean型："+b);
		//System.out.println("double型："+d);
		//System.out.println("boolean型："+b);
	}
	public static void main (String[] args)throws Exception {
		System.out.print("请依次输入int、double、boolean数据：\n");
		//String s1=f1(); showData(s1); //方式1
		//String s2=f2(); showData(s2); //方式2
		f3();                           //方式3
	}
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
class Ch_6_2{
	static String f1()throws IOException{//����1����System.inֱ�Ӷ��ֽ�����
		byte[] b=new byte[100];     //�洢�������ݵ��ֽ�����
		int len=System.in.read(b);  //��ȡ���ݷ����ֽ����飬���ض�ȡ���ֽ���
		return new String(b,0,len); //Ϊ��ȡ��ȡ����Ϣ����Ҫ���ֽ�ת�����ַ�
	}
	static String f2()throws IOException{//����2�����ж�ȡ�ַ���
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	static void f3(){//����3��ֱ�Ӷ�ȡ���������ݣ������Ȼ���ַ������̶�ת���ɻ����ͣ�
		Scanner sc=new Scanner(System.in);
		int i=sc.nextInt();
		double d=sc.nextDouble();
		boolean b=sc.nextBoolean();
		System.out.println("int�ͣ�"+i+"��double�ͣ�"+d+"��boolean�ͣ�"+b);
		//System.out.println("int�ͣ�"+i);
		//System.out.println("double�ͣ�"+d);
		//System.out.println("boolean�ͣ�"+b);
	}
	static void showData(String data){ //���ַ�����ȡ���ݲ���ӡ
		String[] dataStr=data.split("[ ,]+");//�ÿո�,��Ϊ�ָ���
		int i=Integer.parseInt(dataStr[0]);  //չʾ�ò�ͬ��ʽ��ȡint��double����
		double d=Double.valueOf(dataStr[1]);
		boolean b=Boolean.valueOf(dataStr[2]);
		System.out.println("int�ͣ�"+i+"��double�ͣ�"+d+"��boolean�ͣ�"+b);
		//System.out.println("double�ͣ�"+d);
		//System.out.println("boolean�ͣ�"+b);
	}
	public static void main (String[] args)throws Exception {
		System.out.print("����������int��double��boolean���ݣ�\n");
		//String s1=f1(); showData(s1); //��ʽ1
		//String s2=f2(); showData(s2); //��ʽ2
		f3();                           //��ʽ3
	}
}
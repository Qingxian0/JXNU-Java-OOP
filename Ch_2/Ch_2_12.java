import java.util.Scanner;
import java.time.LocalDate;

class StringFormat{//�Զ����ʽ���ַ��������ڸ�ʽ���������ֵ��ַ���
    private static boolean isChinese(char c) {//ʶ���Ƿ�Ϊ����
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) 
            return true;
        return false;
    }
	public static int length(String s){//s��ʵ��ռ�ÿ�ȣ�һ������ռ�����ַ�λ��
		char[] chAr = s.toCharArray();
		int c=0;
		for(char x: chAr)
			c=(isChinese(x))?c+2:c+1;
		return c;
	}
	public static String stringHead(String s, int len){//ȡs�д�ͷ��ʼ��len���ַ���ȣ�ע������ռ2���ַ���
		char[] chAr = s.toCharArray();
		int c=0,i=0;
		for( ; i<chAr.length&&c<len; i++)//��c������Ҫ��ʱ��i������ȡ�ַ���ʵ������
			c=(isChinese(chAr[i]))?c+2:c+1;//c���ڼ���ʵ�ʿ��
		return s.substring(0,i);//substring(0,i)Դ��String����ͷȡi���ַ�
	}
	public static String repeat(char c, int n){//���ַ�c�ظ�n��
		if(n<=0) return null;
		String s="";
		for(int i=0; i<n; i++)s=s+c;
		return s;
	}
	public static String formatR(String s, int n, char c ){//�Ҷ��룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ�����������
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return repeat(c,n-len)+s;
	} 
	public static String formatL(String s, int n, char c ){//����룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ����������Ҳ�
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return s+repeat(c,n-len);
	} 
	public static String formatC(String s, int n, char c ){//���ж��룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ����������Ҳ�
		int left,right,len=length(s);
		if(len>=n) return stringHead(s,n);
		left=(n-len)/2; right=n-len-left;
		return repeat(c,left)+s+repeat(c,right);
	}
}
class Student{//ѧ����Ϣ��ѧ�š��������Ա����ա��Ƿ�Ա�����ġ���ѧ
	String ID,name;  char sex;  boolean partyMember;  double math,chinese; 
	LocalDate birthday;
	static void titleHint(){//���ڶ�ȡ����ʱ�����������ʽ��ʾ��
    	System.out.print("\n������һ��ѧ��������Ctrl+Z��������ʽΪ��");
    	System.out.print("\nѧ�� ���� �Ա� ��������    ��Ա ��ѧ ���ģ� ���磺\n");
		System.out.println(" 001 ���� M 2001-07-15 true 84.2 93.7");
	}
	static void showTitle(){//��Ϊ��ӡ���ʱ�ı�����
		System.out.print(" ѧ��   ����   �Ա�  ��������   ��Ա    ��ѧ  ����\n");
		System.out.print("-----------------------------------------------------\n");
	}
	void read(Scanner sc){//��sc�����ȡ������������ݣ���ȡ������titleHint()��ͬ
		ID=sc.next(); name=sc.next(); //��ȡѧ�š����������ַ���
		sex=sc.next().charAt(0);     //�ȶ�ȡString�����ݣ���"M"����ȡ�����ַ�'M'
		String bd=sc.next();  //���ڶ�ȡ�����ַ���
		birthday=LocalDate.parse(bd); //���������ַ���ת���������
		partyMember=sc.nextBoolean();        //��ȡboolean������
		math=sc.nextDouble();  
		chinese=sc.nextDouble();  //��ȡdouble������
	}
	public String toString(){//��ʽ������ַ���
		String id,xm,xb,dy,sx,yw;
		id=String.format("%-5s",ID);
		xm=StringFormat.formatL(name,8,' ');//�����̶�ռ��8���ַ������㲿���ÿո񲹳䡣עһ������ռ�����ַ�λ�ã�
		xb=(sex=='F'||sex=='f')?"Ů":( (sex=='M'||sex=='m')?"��":"  ?  ");
		dy=(partyMember==true)?"������Ա":"�ǵ�Ա  ";
		sx=String.format("%-6.2f",math); //'-'��ʾ����루��д��Ĭ�����Ҷ��룩��f��ʾ��ʵ��
		yw=String.format("%-6.2f",chinese);  // 6��ʾ�ܳ���(��100.00����Ϊ6)��.2��ʾ������λС��
		return " "+id+" "+xm+" "+xb+" "+birthday+" "+dy+" "+sx+" "+yw; 
	}
}
class BanJi{//�༶���༶����+�洢Student���������+ʵ������
	final int maxN;	  Student [] st;	 int renShu;
	BanJi(int max){ maxN=max; st=new Student[max]; renShu=0; }	
	void add(Student s){st[renShu]=s; renShu++; }//��༶��׷��ѧ��s
	void append( ){	//������β����׷�ӡ�����һ��ѧ������Ctrl+Z����		
		Student.titleHint();  //�����������͸�ʽ��ʾ��
		Scanner sc=new Scanner(System.in); //�ÿհ׷���Ϊ�����
		//Scanner sc =new Scanner(System.in).useDelimiter("[\\n\\r#]+");//��#���س�����Ϊ�����
		Student s;  //����ѭ���������--������������--���������༶
		while(sc.hasNext()==true){ s=new Student(); s.read(sc); add(s); }
	}
	void show(){
		Student.showTitle();
		for(int i=0; i<renShu; i++) System.out.println(st[i]);
		System.out.println("�༶�й��� "+renShu+" �ˡ�");
	}
}
class App{
	public static void main (String[] args) {
		BanJi bj=new BanJi(20);   bj.append();
		System.out.println("�༶��Ϣ���£�");	bj.show();
	}
}

/* ע�⣺����ʱÿ��Ҫ�������縴��ȫ��ʱ�����Ҫ��003��������
   004 �Է� F 1998-09-18 true 100 20
002 ������ M 1997-08-03 false 89 76
001 ˾������ F 2002-07-15 true 73.1 98.6
003    ��   M 2001-04-01 true 78 99
 
 */
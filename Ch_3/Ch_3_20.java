import java.util.Scanner;
import java.util.InputMismatchException;
class TryApp{
    public static void main(String[] args) {
    	int []x={1,2};   int y,z;
    	//Scanner s=new Scanner(System.in); //������Ƿ���Խ������ʱ���쳣���ƻ�Scanner����
    	//����֮���쳣��Scanner�������޷���ȷ��ȡ���ݣ���s.nextInt()���ʼ�ղ��䣬�����ѭ��
    	while(true)//try-catch-finally�������ṹ���ɵ���һ�����
	    	try{   System.out.print("������һ������,9��ʾ��������");
	    		   Scanner s=new Scanner(System.in);
	    		   y=s.nextInt();
	    		   if (y==9) break;
	    		   z=x[y]/y;
	    	}
	    	catch(ArrayIndexOutOfBoundsException e){ System.out.println("���������±�Խ���쳣!");}
	    	catch(ArithmeticException e){	System.out.println("���������쳣!");}
	    	catch(InputMismatchException e){ System.out.println("�����������ݷǷ����쳣��");}
	    	catch(Exception e){ System.out.println("����"+e.getMessage()+"�쳣!");}
	    	finally{  System.out.println("finally�Ӿ䱻ִ��");}
    }
}

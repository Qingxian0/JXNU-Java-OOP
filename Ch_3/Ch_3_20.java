import java.util.Scanner;
import java.util.InputMismatchException;
class TryApp{
    public static void main(String[] args) {
    	int []x={1,2};   int y,z;
    	//Scanner s=new Scanner(System.in); //当输入非法或越界数据时，异常将破坏Scanner对象
    	//换言之，异常的Scanner对象因无法正确读取数据，即s.nextInt()结果始终不变，造成死循环
    	while(true)//try-catch-finally是完整结构，可当成一条语句
	    	try{   System.out.print("请输入一个整数,9表示结束。：");
	    		   Scanner s=new Scanner(System.in);
	    		   y=s.nextInt();
	    		   if (y==9) break;
	    		   z=x[y]/y;
	    	}
	    	catch(ArrayIndexOutOfBoundsException e){ System.out.println("捕获数组下标越界异常!");}
	    	catch(ArithmeticException e){	System.out.println("捕获算术异常!");}
	    	catch(InputMismatchException e){ System.out.println("捕获“输入数据非法”异常！");}
	    	catch(Exception e){ System.out.println("捕获"+e.getMessage()+"异常!");}
	    	finally{  System.out.println("finally子句被执行");}
    }
}

//假设a.txt已放置到d:\k目录中，
//下面代码无法编译
//要求-1：请使用异常处理的捕获机制（即try-catch），让代码正确编译、运行
//要求-2：请使用异常处理的传播机制（即throws），让代码正确编译、运行

package homework.week11;
// 第一题
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 使用异常处理的捕获机制（即try-catch）
class TestException{
	void a(){b();}
	void b() {c();}
	void c(){d();}
	void d() {
		try {
			Scanner sc = new Scanner(new File("d.k/a.txt"));
			System.out.println("a.txt的内容：\n" + sc.nextLine());
		}catch (FileNotFoundException e){
			System.out.println("文件不存在");
		}
	}
}

class App{
	public static void main(String[] args) {
		new TestException().a();
	}
}


// 使用异常处理的传播机制（即throws）
class  TestException2{
	void  a() throws FileNotFoundException {  b(); }
	void  b() throws FileNotFoundException {  c(); }
	void  c() throws FileNotFoundException {  d(); }
	void  d() throws FileNotFoundException {
		Scanner sc=new Scanner(new File("d:/k/a.txt"));  //注：a.txt必须放在d:\k目录中
		System.out.println("a.txt的内容：\n"+sc.nextLine());
	}
}
class App2{
	public static void main(String[] args) throws FileNotFoundException { new  TestException2().a();  }
}
//思考：new File("d:\k\a.txt")可能产生java.lang.NullPointerException，为何不影响编译，它是检查型异常吗，为什么？

/*
答：在Java中，FileNotFoundException是检查型异常，如果在方法中抛出了检查型异常，
	要么使用try-catch捕获异常，要么使用throws将异常向上层方法传播，
	否则编译器会报错。NullPointerException通常是运行时异常，不需要强制处理。
	虽然new File("d:\k\a.txt")可能产生NullPointerException，但它不是检查型异常，
	所以不影响编译。
 */


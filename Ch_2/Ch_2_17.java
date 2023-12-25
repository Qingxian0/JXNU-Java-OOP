/**本例模拟浦丰投针实验
*/
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

class TestPF{
	double d,dd,width,high;    //dd为间距，d为针长,width,high为区域的宽和高
	//假设在(0,0)到(100,100)的单位空间中，分100条横线行间距是1.0，针长就是0.5
	TestPF(){width=100; high=100; dd=1; d=0.5; }
	double x,y,x1,y1,jd; //坐标(x,y)和(x1,y1)，jd为随机角度
		//注意：对坐标(x,y)，x是没有作用的，因为横向不会越界（如x=r.nextInt(99)），
		//     且横坐标不用于判断针与线是否相交，故下面代码未生成横坐标
	void PF(int n){//n是试验次数
		Random r=new Random();
		int count=0; 
		for(int i=0; i<n; i++){
			jd=r.nextInt(360)+r.nextDouble();
			y=r.nextInt(99)+r.nextDouble();   y1=y+d*Math.sin(jd);
			//向下取整，如Math.floor(2.9)结果为2，向上取整，如Math.ceil(1.1)结果为2
			if(Math.floor(y)!=Math.floor(y1)) count++;//若整数位相等，必不相交
		}
		System.out.println("投针"+n+"次，相交"+count+"次，PI="+n*1.0/count);
	}
}
class App{
	public static void main (String[] args) {
		Instant begin,end;
		TestPF pf=new TestPF();
		begin = Instant.now(); pf.PF(2122);	end = Instant.now();
		System.out.println("程序运行了 "+Duration.between(begin, end).toMillis() +"毫秒");
		begin = Instant.now(); pf.PF(21220);	end = Instant.now();
		System.out.println("程序运行了 "+Duration.between(begin, end).toMillis() +"毫秒");
		begin = Instant.now(); pf.PF(212200);	end = Instant.now();
		System.out.println("程序运行了 "+Duration.between(begin, end).toMillis() +"毫秒");
		begin = Instant.now(); pf.PF(2122000);	end = Instant.now();
		System.out.println("程序运行了 "+Duration.between(begin, end).toMillis() +"毫秒");
	}
}
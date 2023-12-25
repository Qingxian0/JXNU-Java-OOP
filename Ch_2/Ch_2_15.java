import java.util.Scanner;
import java.time.LocalDate;//1.8新增
import java.time.Period;//1.8新增，主要是Period类方法getYears()，getMonths()和getDays()来计算
import java.time.temporal.ChronoUnit;//1.8新增，可用于在单个时间单位内测量一段时间，例如天数或秒。
import java.time.Duration; //1.8新增，提供了使用基于时间的值（如秒，纳秒）测量时间量的方法。 
import java.time.Instant;  //1.8新增
class App{
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("请输入生日(yyyy-mm-dd):");
		String stringDate=sc.nextLine(); //读入字符串表达的日期，必须形如yyyy-mm-dd
		  //如若输入：1998-7-2，则会产生错误，必须是：1998-07-02
		Instant begin = Instant.now();
		LocalDate brithDate=LocalDate.parse(stringDate);  //将字符串日期转换成真正的日期
		LocalDate today=LocalDate.now();            //获取当前日期
		
		//日期计算
		int y,m,d;
		//从日期中读取年月日
		y=brithDate.getYear(); 
		m=brithDate.getMonthValue(); //注：getMonth()返回的是英文表示的月(Month型)，如May
		d=brithDate.getDayOfMonth();
		Period p=Period.between(brithDate,today);
		long days=ChronoUnit.DAYS.between(brithDate,today);//注：返回值是long型
		System.out.printf("你出生于 %d 年 %d 月 %d 日",y,m,d);
		System.out.printf("\n你活了 %d年 %d个月 %d天，共计 %d天.",p.getYears(),p.getMonths(),p.getDays(),days);
		System.out.print("\n你已经吃了 "+days*3+" 顿饭。");
		System.out.print("\n你有几件引以为傲大事？");
		Instant end = Instant.now();
		System.out.print("\n程序运行了 "+Duration.between(begin, end).toMillis() +"毫秒");
		System.out.print("\n程序运行了 "+Duration.between(begin, end).getSeconds() +"秒");
	}
}
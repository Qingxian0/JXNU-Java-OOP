/**本例生成5位验证码
*    给定验证码候选字集合，Random类将根据候选字符集的大小随机产生该集合的下标
*/
import java.util.Random;

class App{
	public static void main (String[] args) {
		int len=5;                        //假定产生5位验证码
		String s="589abcdfgiTjklW4Vmno1pq0rtu6vwxyhzAB章CeD王EFG李UHI3赵7JKsLM2NOPQRSXYZ@#$%&()!+-";
		   //无规则的验证码候选字符的字符串
		char[] charSet=s.toCharArray();    //产生验证码候选字符数组
		char[] yzmCh=new char[len];        //用于存储生成的验证码
			
		int pos;
		Random r=new Random(System.currentTimeMillis()); //基于当前时间创建随机数产生器
		for(int i=0; i<len; i++){
			pos=r.nextInt(charSet.length); //产生一个int型随机数pos，0<=pos<charSet.length
			yzmCh[i]=charSet[ pos ];          //读取对应的候选字符
		}
		System.out.print("产生的验证码为："); 
			for(char c: yzmCh)
				System.out.print(" "+c);
	}
}
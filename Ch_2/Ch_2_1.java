/*入门示例：计算累加和
 *目的：
 *（1）认识java程序的基本框架；
 *（2）通过与C语言对比，掌握java常用语法元素的使用，
 *     包括标识符定义、常/变量中定义、函数定义及对返回类型的约束规则、
 *     各类语句（赋值、分支、循环、跳转、输出）的使用；
 *（3）认识对象、对象的成员，理解并掌握“必须先造对象才能引用对象的成员”，
 *     以及如何引用对象的成员；
 *（4）认识main函数定义的格式。
 **/
class Ch_2_1{
	final int maxN=7000;  //定义常量。类中定义的变量/常量，也称作类的属性
       //属性的作用域是整个类，故maxN可直接在sum()中使用
	int sum(int n){ //定义函数。类中定义的函数，称作类的行为/方法
		if(n<0||n>maxN)  { 
			System.out.printf("不能计算！"); //作用类似C中的printf()
			return 0; 
		}
		int s=0;      //定义变量
		for(int i=1; i<=n; i++) // 变量i在for中定义i，出循环则不存在
			s=s+i;
		return s;                  //此句不可缺少，否则编译错
	}
	public static void main (String[] s){
		Ch_2_1 c=new Ch_2_1();  //必须创建对象，才能调用sum()函数
		int n=10;          //定义变量
		System.out.printf("sum(%d)= %d", n, c.sum(n));
        //printf()用法与相同，但必须加前缀：System.out.
 	}
}

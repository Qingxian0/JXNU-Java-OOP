import java.util.Scanner; import java.util.Stack;
class MyExpression{
		String exp;//代表待计算的文本表达式
		MyExpression(String strData){	exp=strData;	}
		boolean isOpChar(char c){//判断是否是操作符
			return ( c=='+' || c=='-' || c=='*' || c=='/' || c=='(' ||c==')' || c=='#' );
           //也可写成：return "+-*/()#".indexof(c)!=-1;  //-1表示c未在"+-*/()#"找到
		}
		char comparePRI(char a, char b){//比较运算符的优先级
		/* 优先级比较列表：第一列代表栈顶算符，第一行代表栈外运算符，第一列代表栈顶运算符
			        +     -     *     /     (     )     #
		--------------------------------------------------
			+   |   >     >     <     <     <     >     >
			-   |   >     >     <     <     <     >     >
			*   |   >     >     >     >     <     >     >
			/   |   >     >     >     >     <     >     >
			(   |   <     <     <     <     <     =     e
			)   |   >     >     >     >     e     >     >
			#   |   <     <     <     <     <     e     =
		 *注意上述规则共有49个，其中26个‘>’，18个‘<’，3个‘e’，2个‘=’
		 **/
			if( (a=='('&& b=='#') || (a==')'&& b=='(') || (a=='#'&& b==')') )   return 'e';/*error*/
			if( (a=='('&& b==')')|| (a=='#'&& b=='#') )  return  '=';
	    	if( a=='('||a=='#' || (a=='+'||a=='-')&&(b=='*'||b=='/'||b=='(') || (a=='*'|| a=='/')&& b=='(')
	       		return '<';
	    	else return '>';
		}
		double compute(double x, double y, char c){
	    		if(c=='+') return x+y;
	    		if(c=='-')  return x-y;
	    		if(c=='*')  return x*y;
	    		if(c=='/'&& y!=0)  return x/y;
	   		return 0;
		}
		double expCompute(){//表达式计算
			Stack<Character> stkChar=new Stack<Character>();//操作符栈
			Stack<Double>    stkData=new Stack<Double>();   //操作数栈
			double a,b,r;           //为计算设置，形如r=a+b;
			char opChar, pr;       //操作符、 pr是优先级比较结果
			int i=0,k=0;           //i用于扫描表达式，k用于扫描操作数数组
			String s1=exp+"#";  stkChar.push('#'); //预处理：当栈顶='#'，栈外运算符='#'时，计算结束
			String[] strData=exp.split("[+\\-*/() \t#]+");  //提取所有的操作数，注：-需要转义，即\\-，因为诸如[a-z]中，-有特殊含义
			if(strData[0].equals("")) k=1; //形如(1+2)*3会在第一个位置产生空串，直接跨过
			char[] c=s1.toCharArray();  //数组更易于文本的逐字符扫描
			while( i<c.length||stkChar.empty()==false){
				if(c[i]>='0'&&c[i]<='9'){//一旦扫描到数字，就跨过整个操作数，并从操作数数组获得该数
					while(i<c.length&&(c[i]>='0'&&c[i]<='9'||c[i]=='.') )i++; //跨过数据
					stkData.push(new Double(strData[k])); k++;
					continue;//注：此处已经读到非数字，故直接跳转到循环起始处
				}
				if (isOpChar(c[i])){  //c[i]是操作符
					pr=comparePRI(stkChar.peek(),c[i]); //peek()读取栈顶元素（但不出栈）
					if(pr=='<' )stkChar.push(c[i]);
					else if( pr=='='  )stkChar.pop();//左右括号相遇或两个'#'相遇
					else if(pr=='>' ){ //实施计算
						b=stkData.pop(); a=stkData.pop(); opChar=stkChar.pop();
						r=compute(a,b,opChar); stkData.push(r);
						continue;//注：计算后栈外元素不变，故直接跳转到循环起始处
					}
					else if(pr=='e')return 0; //有错
				}
				i++;
			}
			r=stkData.pop();    return r;
		}
}
class App{
	public static void main (String[] args) {
		Scanner stkChar=new Scanner(System.in);
		System.out.print("请输入四则运算表达式(支持括号)：\n");
		String exp=stkChar.nextLine();
		MyExpression e=new MyExpression(exp);
		System.out.print("="+e.expCompute());
	}
}
//(1.8+2.2)*3-4*5、1+2*(3+4)、(1.5+8.5)*(6+4/2-1)+100
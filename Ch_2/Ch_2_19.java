import java.util.Scanner; import java.util.Stack;
class MyExpression{
		String exp;//�����������ı����ʽ
		MyExpression(String strData){	exp=strData;	}
		boolean isOpChar(char c){//�ж��Ƿ��ǲ�����
			return ( c=='+' || c=='-' || c=='*' || c=='/' || c=='(' ||c==')' || c=='#' );
           //Ҳ��д�ɣ�return "+-*/()#".indexof(c)!=-1;  //-1��ʾcδ��"+-*/()#"�ҵ�
		}
		char comparePRI(char a, char b){//�Ƚ�����������ȼ�
		/* ���ȼ��Ƚ��б���һ�д���ջ���������һ�д���ջ�����������һ�д���ջ�������
			        +     -     *     /     (     )     #
		--------------------------------------------------
			+   |   >     >     <     <     <     >     >
			-   |   >     >     <     <     <     >     >
			*   |   >     >     >     >     <     >     >
			/   |   >     >     >     >     <     >     >
			(   |   <     <     <     <     <     =     e
			)   |   >     >     >     >     e     >     >
			#   |   <     <     <     <     <     e     =
		 *ע������������49��������26����>����18����<����3����e����2����=��
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
		double expCompute(){//���ʽ����
			Stack<Character> stkChar=new Stack<Character>();//������ջ
			Stack<Double>    stkData=new Stack<Double>();   //������ջ
			double a,b,r;           //Ϊ�������ã�����r=a+b;
			char opChar, pr;       //�������� pr�����ȼ��ȽϽ��
			int i=0,k=0;           //i����ɨ����ʽ��k����ɨ�����������
			String s1=exp+"#";  stkChar.push('#'); //Ԥ������ջ��='#'��ջ�������='#'ʱ���������
			String[] strData=exp.split("[+\\-*/() \t#]+");  //��ȡ���еĲ�������ע��-��Ҫת�壬��\\-����Ϊ����[a-z]�У�-�����⺬��
			if(strData[0].equals("")) k=1; //����(1+2)*3���ڵ�һ��λ�ò����մ���ֱ�ӿ��
			char[] c=s1.toCharArray();  //����������ı������ַ�ɨ��
			while( i<c.length||stkChar.empty()==false){
				if(c[i]>='0'&&c[i]<='9'){//һ��ɨ�赽���֣��Ϳ�����������������Ӳ����������ø���
					while(i<c.length&&(c[i]>='0'&&c[i]<='9'||c[i]=='.') )i++; //�������
					stkData.push(new Double(strData[k])); k++;
					continue;//ע���˴��Ѿ����������֣���ֱ����ת��ѭ����ʼ��
				}
				if (isOpChar(c[i])){  //c[i]�ǲ�����
					pr=comparePRI(stkChar.peek(),c[i]); //peek()��ȡջ��Ԫ�أ�������ջ��
					if(pr=='<' )stkChar.push(c[i]);
					else if( pr=='='  )stkChar.pop();//������������������'#'����
					else if(pr=='>' ){ //ʵʩ����
						b=stkData.pop(); a=stkData.pop(); opChar=stkChar.pop();
						r=compute(a,b,opChar); stkData.push(r);
						continue;//ע�������ջ��Ԫ�ز��䣬��ֱ����ת��ѭ����ʼ��
					}
					else if(pr=='e')return 0; //�д�
				}
				i++;
			}
			r=stkData.pop();    return r;
		}
}
class App{
	public static void main (String[] args) {
		Scanner stkChar=new Scanner(System.in);
		System.out.print("����������������ʽ(֧������)��\n");
		String exp=stkChar.nextLine();
		MyExpression e=new MyExpression(exp);
		System.out.print("="+e.expCompute());
	}
}
//(1.8+2.2)*3-4*5��1+2*(3+4)��(1.5+8.5)*(6+4/2-1)+100
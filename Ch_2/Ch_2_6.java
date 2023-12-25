//���ܣ�����������Ӧ��
import java.util.Scanner;
class ReadChar{
	int i=0;
	String data; //data���潨������������ַ�
	ReadChar(String d){ data=d; }
	char getChar(){//ר�����ڶ�ȡ���ݣ�ÿ�ζ�һ���ַ�
		char x=data.charAt(i);
		i++; return x; 
	}
}
class BinTree{
	char data;
	BinTree L,R;
	BinTree(char x){ data=x; }
	static BinTree creat(ReadChar r){
		//˼·����ȡ���룬��Ϊ#��retrun NULL;
		//        ��������t��Ϊt��������ֵ������t
		char x=r.getChar();
		if(x=='#') return null;
		BinTree t=new BinTree(x);
		t.L=creat(r); t.R=creat(r);
		return t;
	}
	void pre(){//this�Ǹ�
		System.out.print(this.data+" ");
		if(this.L!=null) this.L.pre();//this��Ϊ�գ���this.L����Ϊ��
		if(this.R!=null) this.R.pre();
	}
	void in(){
		if(this.L!=null) this.L.in();
		System.out.print(this.data+" ");
		if(this.R!=null) this.R.in();
	}
	void post(){
		if(this.L!=null) this.L.post();
		if(this.R!=null) this.R.post();
		System.out.print(this.data+" ");
	}
	class Stack{//���ǵݹ����ʹ��
		BinTree[] s=new BinTree[20];
		int top;
		boolean isEmpty(){ return top==0; }
		void push(BinTree x){ 
			if(top==s.length){//��ջ�������Զ�����20��Ԫ��
				BinTree[] news=new BinTree[s.length+20];
				for(int i=0; i<s.length; i++)news[i]=s[i]; //��������
				s=news;//���±��滻s
			}
			s[top]=x; top++;
		}
		BinTree pop(){ top--; return s[top]; }
	}
	void preN(){//����
		// while(t����||ջ����)
		//   if(t����){����t; push(t); t=t��������;}
		//   else{t=pop(s); t=t��������;}
		// ע����t����Ҫ���ʵ�������ˣ�t=t��������;�����Ϊ������t����������
		BinTree t=this;
		Stack st=new Stack();
		while(t!=null || st.isEmpty()==false)
		  if(t!=null){
		  	System.out.print(t.data+" ");
		  	st.push(t); t=t.L;
		  }else{ t=st.pop(); t=t.R;}
	}
	void inN(){
		BinTree t=this;   Stack st=new Stack();
		while(t!=null || st.isEmpty()==false)
			if(t!=null){ st.push(t); t=t.L;	}
			else{ t=st.pop(); 
			      System.out.print(t.data+" ");
			      t=t.R; }
	}
}
class App{
  public static void main(String[] x){
  	System.out.print("�����뽨�����ݣ�#��ʾnull��");
  	Scanner sc=new Scanner(System.in);
  	String str=sc.next(); //��ȡ���еĴ�����������
  	ReadChar r=new ReadChar(str);
  	BinTree t=BinTree.creat(r);
  	System.out.print("pre = ");t.pre();
  	System.out.print("\npreN= ");t.preN();
  	System.out.print("\n in = ");t.in();
  	System.out.print("\n inN= ");t.inN();
  	System.out.print("\npost= ");t.post();
  } 
}//AB#D##CE##F##
//功能：二叉树及其应用
import java.util.Scanner;
class ReadChar{
	int i=0;
	String data; //data保存建树所需的所有字符
	ReadChar(String d){ data=d; }
	char getChar(){//专门用于读取数据，每次读一个字符
		char x=data.charAt(i);
		i++; return x; 
	}
}
class BinTree{
	char data;
	BinTree L,R;
	BinTree(char x){ data=x; }
	static BinTree creat(ReadChar r){
		//思路：读取输入，若为#，retrun NULL;
		//        否则，造结点t，为t的三个域赋值，返回t
		char x=r.getChar();
		if(x=='#') return null;
		BinTree t=new BinTree(x);
		t.L=creat(r); t.R=creat(r);
		return t;
	}
	void pre(){//this是根
		System.out.print(this.data+" ");
		if(this.L!=null) this.L.pre();//this不为空，但this.L可能为空
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
	class Stack{//供非递归遍历使用
		BinTree[] s=new BinTree[20];
		int top;
		boolean isEmpty(){ return top==0; }
		void push(BinTree x){ 
			if(top==s.length){//若栈满，就自动扩容20个元素
				BinTree[] news=new BinTree[s.length+20];
				for(int i=0; i<s.length; i++)news[i]=s[i]; //回填数据
				s=news;//用新表替换s
			}
			s[top]=x; top++;
		}
		BinTree pop(){ top--; return s[top]; }
	}
	void preN(){//策略
		// while(t不空||栈不空)
		//   if(t不空){访问t; push(t); t=t的左子树;}
		//   else{t=pop(s); t=t的右子树;}
		// 注：因t代表要访问的树，因此，t=t的左子树;可理解为“访问t的左子树”
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
  	System.out.print("请输入建树数据，#表示null：");
  	Scanner sc=new Scanner(System.in);
  	String str=sc.next(); //读取所有的创建链表数据
  	ReadChar r=new ReadChar(str);
  	BinTree t=BinTree.creat(r);
  	System.out.print("pre = ");t.pre();
  	System.out.print("\npreN= ");t.preN();
  	System.out.print("\n in = ");t.in();
  	System.out.print("\n inN= ");t.inN();
  	System.out.print("\npost= ");t.post();
  } 
}//AB#D##CE##F##
import java.util.Scanner;
class Stack{//通用型栈，存储Object型（即一切对象均可存入）
	Object[] s=new Object[20];
	int top;
	boolean isEmpty(){ return top==0; }
	void push(Object x){ 
		if(top==s.length){//若栈满，就自动扩容20个元素
			Object[] news=new Object[s.length+20];
			for(int i=0; i<s.length; i++)news[i]=s[i]; //回填数据
			s=news;//用新表替换s
		}
		s[top]=x; top++;
	}
	Object pop(){ top--; return s[top]; }
	Object top(){ return (top==0)?null:s[top-1];}//返回栈顶元素，不出栈
}
class KTree{
	char data;      //数据域
	int tag;      //用于非递归遍历，指明当前待处理的孩子
	KTree[] child=new KTree[3];//默认3个孩子，不够时自动添加3个
	KTree(char x) { data=x;}
	static KTree creat(char[][] data) {//通过二维数组创建K叉树
		//如{{A,B,C,D},{B,E,F},{D,G,H,},{G,I,J,K,L}}，A是B/C/D的双亲，B是E/F的双亲，……
		//即：每个一维数组，对应一个{p，p的所有孩子}
		if(data==null)return null;
		KTree root=new KTree(data[0][0]);//创建root结点，data[0][0]是根
		KTree father, q;
		for(int i=0; i<data.length; i++) //向树root加入所有非叶子结点
			root.add(data[i][0],data[i],1,data[i].length-1);
		return root;
	}
	KTree find(char x){ //前序遍历查找
	    if(this.data==x) return this;
	    for(int i=0; i<child.length; i++)
	    	if(child[i]!=null && child[i].find(x)!=null)//若找到了
	    		return child[i].find(x);
	    return null; //出循环，即没找到
	}
	boolean add(char father,char x) { //将x作为father的孩子
		KTree f=find(father);  //先查找结点
		if(f==null) return false;
		int i=0;
		while(i<f.child.length && f.child[i]!=null) i++;//找第一个空孩子位置
		if(i==f.child.length) {//孩子已满，需要扩容
			KTree[] temp=f.child; //保留原始数据
			f.child=new KTree[child.length + 3];//扩容
			for(int j=0; j<i; j++)//将原始数据写回
				f.child[j]=temp[j];
		}
		f.child[i]=new KTree(x); //实施插入
		return true;
	}
	boolean add(char father,char[] da,int begin, int end){ //da[begin]~da[end]是father的孩子
		KTree f=find(father);  //先查找结点
		if(f==null) return false;
		int i=0;
		while(i<f.child.length && f.child[i]!=null) i++;//找第一个空孩子位置
		if(i+end-begin+1>f.child.length){//若添加后元素空间不够，需要扩容
			KTree[] temp=f.child; //保留原始数据
			f.child=new KTree[i+end-begin+3];//扩容
			for(int j=0; j<i; j++)//将原始数据写回
				f.child[j]=temp[j];
		}
		for(int k=0,j=begin; j<=end; j++,k++)
			f.child[i+k]=new KTree(da[j]); //实施插入
		return true;
	}
	void pre(){ //递归的前序遍历
		System.out.print(data);
		for(int i=0;i<child.length; i++)
			if(child[i]!=null) child[i].pre();
	}
	void post(){ //递归的后序遍历
		for(int i=0;i<child.length; i++)
			if(child[i]!=null) child[i].post();
		System.out.print(data);
	}
	void preN(){ //非递归前序遍历
		KTree t=this; //t是树根
		Stack s=new Stack();  s.push(t);//注意：Object可兼容一切对象型，当然可兼容KTree型
		while(s.isEmpty()==false){
			t=(KTree)s.top();//注意：top()返回的是Object型，需要强制类型转换
			if(t==null) s.pop();
			else if (t.tag==0) { System.out.print(t.data);
				s.push(t.child[0]); t.tag++; }
			else if (t.tag<t.child.length){
				s.push(t.child[t.tag]); t.tag++; }
			else if (t.tag==t.child.length)  { s.pop(); t.tag=0;}//出栈时置空
		}
	}
 	void postN(){
		KTree t=this; //t是树根
		Stack s=new Stack();  s.push(t);
		while(s.isEmpty()==false){
			t=(KTree)s.top();
			if(t==null) s.pop();
			else if (t.tag<t.child.length){
				s.push(t.child[t.tag]); t.tag++; }
			else if (t.tag==t.child.length){
				System.out.print(t.data); s.pop(); t.tag=0;}
		}
 	}
	void level(){ //层次遍历
	//策略：根入队；while(队不空){t=出队元素; 访问t; t的所有非空孩子入队}
		class Queue{//循环队列：局部于方法的内部类
			int max=20;
			KTree a[]=new KTree[max];
			int f,r; //队首、队尾指针
			boolean isEmpty(){ return f==r; }
			boolean isFull(){ return (r+1)%max==f; }
			void enQueue(KTree x){//入队操作
				if(isFull()==true){//满则扩容
					max=max+10; KTree[] temp=a; a=new KTree[max];
					for(int i=0; i<temp.length; i++)a[i]=temp[i]; //数据回写
				}
				r=(r+1)%max;  a[r]=x;
			}
			KTree outQueue(){f=(f+1)%max; return a[f];}
		}//队列定义结束
		KTree t=this; Queue q=new Queue(); q.enQueue(t);
		while(q.isEmpty()==false){  
			t=q.outQueue();  System.out.print(t.data);
			for(int i=0; i<t.child.length; i++)
				if(t.child[i]!=null)q.enQueue(t.child[i]);
		}
	}
}
class App{
	public static void main(String[] args) {
		char[][]s={{'A','B','C','D'},{'B','E','F'},{'D','G','H'},{'G','I','J','K','L'} };
		//s[][0]是根
		KTree t=KTree.creat(s);
		System.out.print("\n Pre = "); t.pre();
		System.out.print("\nPreN = "); t.preN();
		System.out.print("\nPost = "); t.post();
		System.out.print("\nPostN= "); t.postN();
		System.out.print("\nlevel= "); t.level();
		System.out.print("\n插入XYZ作为B的孩子，插入R作为D的孩子");
		char[] c={'X','Y','Z'};
		t.add('B',c,0,2);t.add('D','R');
		System.out.print("\n Pre = "); t.pre();
		System.out.print("\nPreN = "); t.preN();
		System.out.print("\nPost = "); t.post();
		System.out.print("\nPostN= "); t.postN();
		System.out.print("\nlevel= "); t.level();		
	}
}
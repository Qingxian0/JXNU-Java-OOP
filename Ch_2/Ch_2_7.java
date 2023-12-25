import java.util.Scanner;
class Stack{//ͨ����ջ���洢Object�ͣ���һ�ж�����ɴ��룩
	Object[] s=new Object[20];
	int top;
	boolean isEmpty(){ return top==0; }
	void push(Object x){ 
		if(top==s.length){//��ջ�������Զ�����20��Ԫ��
			Object[] news=new Object[s.length+20];
			for(int i=0; i<s.length; i++)news[i]=s[i]; //��������
			s=news;//���±��滻s
		}
		s[top]=x; top++;
	}
	Object pop(){ top--; return s[top]; }
	Object top(){ return (top==0)?null:s[top-1];}//����ջ��Ԫ�أ�����ջ
}
class KTree{
	char data;      //������
	int tag;      //���ڷǵݹ������ָ����ǰ������ĺ���
	KTree[] child=new KTree[3];//Ĭ��3�����ӣ�����ʱ�Զ����3��
	KTree(char x) { data=x;}
	static KTree creat(char[][] data) {//ͨ����ά���鴴��K����
		//��{{A,B,C,D},{B,E,F},{D,G,H,},{G,I,J,K,L}}��A��B/C/D��˫�ף�B��E/F��˫�ף�����
		//����ÿ��һά���飬��Ӧһ��{p��p�����к���}
		if(data==null)return null;
		KTree root=new KTree(data[0][0]);//����root��㣬data[0][0]�Ǹ�
		KTree father, q;
		for(int i=0; i<data.length; i++) //����root�������з�Ҷ�ӽ��
			root.add(data[i][0],data[i],1,data[i].length-1);
		return root;
	}
	KTree find(char x){ //ǰ���������
	    if(this.data==x) return this;
	    for(int i=0; i<child.length; i++)
	    	if(child[i]!=null && child[i].find(x)!=null)//���ҵ���
	    		return child[i].find(x);
	    return null; //��ѭ������û�ҵ�
	}
	boolean add(char father,char x) { //��x��Ϊfather�ĺ���
		KTree f=find(father);  //�Ȳ��ҽ��
		if(f==null) return false;
		int i=0;
		while(i<f.child.length && f.child[i]!=null) i++;//�ҵ�һ���պ���λ��
		if(i==f.child.length) {//������������Ҫ����
			KTree[] temp=f.child; //����ԭʼ����
			f.child=new KTree[child.length + 3];//����
			for(int j=0; j<i; j++)//��ԭʼ����д��
				f.child[j]=temp[j];
		}
		f.child[i]=new KTree(x); //ʵʩ����
		return true;
	}
	boolean add(char father,char[] da,int begin, int end){ //da[begin]~da[end]��father�ĺ���
		KTree f=find(father);  //�Ȳ��ҽ��
		if(f==null) return false;
		int i=0;
		while(i<f.child.length && f.child[i]!=null) i++;//�ҵ�һ���պ���λ��
		if(i+end-begin+1>f.child.length){//����Ӻ�Ԫ�ؿռ䲻������Ҫ����
			KTree[] temp=f.child; //����ԭʼ����
			f.child=new KTree[i+end-begin+3];//����
			for(int j=0; j<i; j++)//��ԭʼ����д��
				f.child[j]=temp[j];
		}
		for(int k=0,j=begin; j<=end; j++,k++)
			f.child[i+k]=new KTree(da[j]); //ʵʩ����
		return true;
	}
	void pre(){ //�ݹ��ǰ�����
		System.out.print(data);
		for(int i=0;i<child.length; i++)
			if(child[i]!=null) child[i].pre();
	}
	void post(){ //�ݹ�ĺ������
		for(int i=0;i<child.length; i++)
			if(child[i]!=null) child[i].post();
		System.out.print(data);
	}
	void preN(){ //�ǵݹ�ǰ�����
		KTree t=this; //t������
		Stack s=new Stack();  s.push(t);//ע�⣺Object�ɼ���һ�ж����ͣ���Ȼ�ɼ���KTree��
		while(s.isEmpty()==false){
			t=(KTree)s.top();//ע�⣺top()���ص���Object�ͣ���Ҫǿ������ת��
			if(t==null) s.pop();
			else if (t.tag==0) { System.out.print(t.data);
				s.push(t.child[0]); t.tag++; }
			else if (t.tag<t.child.length){
				s.push(t.child[t.tag]); t.tag++; }
			else if (t.tag==t.child.length)  { s.pop(); t.tag=0;}//��ջʱ�ÿ�
		}
	}
 	void postN(){
		KTree t=this; //t������
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
	void level(){ //��α���
	//���ԣ�����ӣ�while(�Ӳ���){t=����Ԫ��; ����t; t�����зǿպ������}
		class Queue{//ѭ�����У��ֲ��ڷ������ڲ���
			int max=20;
			KTree a[]=new KTree[max];
			int f,r; //���ס���βָ��
			boolean isEmpty(){ return f==r; }
			boolean isFull(){ return (r+1)%max==f; }
			void enQueue(KTree x){//��Ӳ���
				if(isFull()==true){//��������
					max=max+10; KTree[] temp=a; a=new KTree[max];
					for(int i=0; i<temp.length; i++)a[i]=temp[i]; //���ݻ�д
				}
				r=(r+1)%max;  a[r]=x;
			}
			KTree outQueue(){f=(f+1)%max; return a[f];}
		}//���ж������
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
		//s[][0]�Ǹ�
		KTree t=KTree.creat(s);
		System.out.print("\n Pre = "); t.pre();
		System.out.print("\nPreN = "); t.preN();
		System.out.print("\nPost = "); t.post();
		System.out.print("\nPostN= "); t.postN();
		System.out.print("\nlevel= "); t.level();
		System.out.print("\n����XYZ��ΪB�ĺ��ӣ�����R��ΪD�ĺ���");
		char[] c={'X','Y','Z'};
		t.add('B',c,0,2);t.add('D','R');
		System.out.print("\n Pre = "); t.pre();
		System.out.print("\nPreN = "); t.preN();
		System.out.print("\nPost = "); t.post();
		System.out.print("\nPostN= "); t.postN();
		System.out.print("\nlevel= "); t.level();		
	}
}
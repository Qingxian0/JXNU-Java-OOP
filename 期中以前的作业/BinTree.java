/*

//二叉树及其应用
import java.util.Scanner;
import java.util.Stack;

class Stack {
	BinTree[] s = new BinTree[20];
	int top;

	void push(BinTree x) {
		s[top] = x;
		top++;
	}

	BinTree pop() {
		top--;
		return s[top];
	}
}

class ReadBinTreeData {
	String data;// 建树所用的字符串，如ab##c##
	int pos;// 当前下标

	ReadBinTreeData(String d) {
		data = d;
	}

	char getChar() {// 自动向后读
		char x = data.charAt(pos);// 相当于data[pos]
		pos++;
		return x;
	}
}

class BinTree {
	char data;
	BinTree L, R;

	BinTree(char x) {
		data = x;
	}

	BinTree() {
		;
	}

	BinTree create(ReadBinTreeData r) {
		// char c=getChar();//==>Scanner中没有nextChar()
		// ==>需要一个对象，提供getChar()操作
		char c = r.getChar();
		if (c == '#')
			return null;
		BinTree t = new BinTree(c);
		t.L = create(r);// 造一棵二叉树交给t.L
		t.R = create(r);
		return t;
	}

	void pre() {
		System.out.print(this.data + "、");
		if (this.L != null)
			this.L.pre();
		if (this.R != null)
			this.R.pre();
	}

	void preN(t){//前序遍历非递归
		if(t==null)return;
		Stack st=new Stack();
		while(t!=null || st.isEmpty()==false )
			if(t!=null){
				System.out.print(t.data+"、");
				st.push(t); t=t.L;
			}else{
				t=st.pop(); t=t.R;
			}
	}

	void in() {
		if (this.L != null)
			this.L.in();
		System.out.print(this.data + "、");
		if (this.R != null)
			this.R.in();
	}
}

class App {
	public static void main(String[] args) {
		System.out.print("请输入建树数据，#表示空：\n");
		Scanner sc = new Scanner(System.in);
		String d = sc.nextLine();// 读取建树数据字符串
		ReadBinTreeData r = new ReadBinTreeData(d);

		BinTree t = new BinTree();
		t = t.create(r);
		System.out.print("\n pre =");
		t.pre();
		System.out.print("\npreN =");
		t.preN();
		System.out.print("\n  in =");
		t.in();
	}// AB##C##
}*/

/*

//����������Ӧ��
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
	String data;// �������õ��ַ�������ab##c##
	int pos;// ��ǰ�±�

	ReadBinTreeData(String d) {
		data = d;
	}

	char getChar() {// �Զ�����
		char x = data.charAt(pos);// �൱��data[pos]
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
		// char c=getChar();//==>Scanner��û��nextChar()
		// ==>��Ҫһ�������ṩgetChar()����
		char c = r.getChar();
		if (c == '#')
			return null;
		BinTree t = new BinTree(c);
		t.L = create(r);// ��һ�ö���������t.L
		t.R = create(r);
		return t;
	}

	void pre() {
		System.out.print(this.data + "��");
		if (this.L != null)
			this.L.pre();
		if (this.R != null)
			this.R.pre();
	}

	void preN(t){//ǰ������ǵݹ�
		if(t==null)return;
		Stack st=new Stack();
		while(t!=null || st.isEmpty()==false )
			if(t!=null){
				System.out.print(t.data+"��");
				st.push(t); t=t.L;
			}else{
				t=st.pop(); t=t.R;
			}
	}

	void in() {
		if (this.L != null)
			this.L.in();
		System.out.print(this.data + "��");
		if (this.R != null)
			this.R.in();
	}
}

class App {
	public static void main(String[] args) {
		System.out.print("�����뽨�����ݣ�#��ʾ�գ�\n");
		Scanner sc = new Scanner(System.in);
		String d = sc.nextLine();// ��ȡ���������ַ���
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

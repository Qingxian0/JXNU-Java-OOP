/*
//3��������ƺ�Ӧ��
import java.util.Scanner;
class Tree{
	final int m=3;//����Ҫô�ڶ��崦�����˴�����ֵ��Ҫô�ڹ��캯���и�ֵ����������
	Tree[] c=new Tree[m]; // int[] a=new int[10];
	int data;
	//Scanner sc=new Scanner(System.in); //����
	Tree(int x){ data=x; }
	Tree create(Scanner sc){//����
		//Scanner sc=new Scanner(System.in);
		int x;
		x=sc.nextInt();
		if(x==0)return null; //��������
		Tree t=new Tree(x); //��ǿ���������t
		for(int i=0; i<m; i++)//��m����������t�ĺ���
			t.c[i]=create(sc);
		return t;
	}
	void pre(){//�ȸ�������
		System.out.print(this.data+"��");//�����
		for(int i=0; i<m; i++)//�����������
			if(this.c[i]!=null)
				this.c[i].pre();//this���գ���this.c[i]����Ϊ��
	}
	void post(){//���������
		for(int i=0; i<m; i++)//�����������
			if(this.c[i]!=null)
				this.c[i].post();//this���գ���this.c[i]����Ϊ��
		System.out.print(this.data+"��");//�����
	}
}
class App{
	public static void main(String[] args){
		System.out.print("�����뽨�����ݣ�0��ʾ�գ�\n");
		Tree t=new Tree(0);
		Scanner sc=new Scanner(System.in);
		t=t.create(sc);
		System.out.print("\n pre =");  t.pre();
		System.out.print("\npost =");  t.post();
	}//1 2 0 0 0 3 0 0 0 4 0 0 0
}*/

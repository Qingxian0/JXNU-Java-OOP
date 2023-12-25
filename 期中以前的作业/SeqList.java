/*

//˳�����Ӧ��
//˳���=����+��+������
*/
/*1������˳������
  2��ʹ��Scanner��������
  3������������ͬ��˳���-- ���캯����ʹ��
  4������ϲ� -- �������Ļ�����
    ---��Ϥthis��ʹ��
*//*

import java.util.Scanner;

class SeqList {
	// int[] a=new int[20];//ֱ��ָ����˳��������
	int[] a;
	int len;

	SeqList() {
	}

	SeqList(int max) {
		a = new int[max];
	}

	void input1() {// ����������̶���Ԫ��
		int i;
		for (i = 0; i < 10; i++)
			a[i] = i;
		len = i;
	}

	void input() {// �������������ɸ�Ԫ��
		System.out.print("������һ��������-1������\n");
		int i, x;
		i = 0;
		// scanf("%d",&x);
		Scanner sc = new Scanner(System.in);// System.in�൱�ڼ���
		x = sc.nextInt();
		while (x != -1) {
			a[i] = x;
			i++; // scanf("%d",&x);
			x = sc.nextInt();
		}
		len = i;
	}

	void out() {
		for (int i = 0; i < len; i++)
			System.out.print(a[i] + "��");
	}

	void merge1(SeqList L1, SeqList L2) {
		// �ؼ����⣺L1��L2�ϲ�
		// �ϲ��������L1
		int i, j;
		i = L1.len;
		for (j = 0; j < L2.len; j++, i++)
			L1.a[i] = L2.a[j];
		L1.len = L1.len + L2.len;// i;
	}

	void merge(SeqList x) {
		// this+x-->x
		// ������x�����������򰴺ϲ���������������������
		int[] t; // tδ��������ڴ洢�ϲ��������
		// 1��ȷ����ʱ����Ĵ�С
		int newlen = this.len + x.len; // length==���С�������󡿶��еĳ�������
		// ��ע��1�����֪��x�е����������
		if (newlen > x.a.length)// ע�⣺��x.a.length������x.length��why?
			t = new int[2 * newlen];
		else
			t = new int[x.a.length];
		// �������ν�this��x�е����ݣ����ϲ����򣬷���t
		int i, k;
		// �Ȱ�this�е����ݷ���t
		for (i = 0, k = 0; i < this.len; i++, k++)
			t[k] = this.a[i];
		// �Ȱ�x�е����ݷ���t
		for (i = 0; i < x.len; i++, k++)
			t[k] = x.a[i];
		// �ٸ���x�е����� ==������ע2������滻����
		x.a = t;
		x.len = k;
	}// ���this:this�Ƿ���������
		// ��ԣ�s1.merge(s2);��merge�е�this����s1

	void merge3(SeqList L2) {
		// �ؼ����⣺L2��˭�ϲ� --- �͡��ҡ��ϲ�������this�ϲ�
		// �ϲ��������this
		int i, j;
		i = this.len;
		// thisͨ������ʡ�ԣ���i=len;
		for (j = 0; j < L2.len; j++, i++)
			this.a[i] = L2.a[j];
		this.len = this.len + L2.len;// i;
	}// ���this:this�Ƿ���������
		// ��ԣ�s1.merge(s2);��merge�е�this����s1

}

class App {
	public static void main(String[] args) {
		// SeqList s=new SeqList();
		SeqList s1 = new SeqList(20);
		SeqList s2 = new SeqList(10);
		s1.input();
		s2.input();
		System.out.print("\ns1: ");
		s1.out();
		System.out.print("\ns2: ");
		s2.out();
		// s1.merge(s1,s2);
		s1.merge(s2);
		System.out.print("\ns1: ");
		s1.out();
		System.out.print("\ns2: ");
		s2.out();
	}
}*/

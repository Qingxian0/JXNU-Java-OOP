/*

//��������Ӧ��
import java.util.Scanner;

class LinkedList {
	int data;
	LinkedList next;

	LinkedList(int x) {
		data = x;
	}

	// LinkedList input(LinkedList h){//�������������ɸ�Ԫ��
	void input1() {// �������������ɸ�Ԫ��
		System.out.print("������һ��������-1������\n");
		int x;
		LinkedList p, tail;
		// tail=h;
		// ����ǿ�������ò��õ�this
		tail = this; // this�ǵ�����,h.input(); �Ǵ�����hΪͷ���ġ�������h����ͷ��㣬Ҳ��this
		// �Ͼ䣬���ǲ��ò��õ�this
		Scanner sc = new Scanner(System.in);// System.in�൱�ڼ���
		x = sc.nextInt();
		while (x != -1) { // ����p����ֵ����p����β�����޸�β����������
			p = new LinkedList(x);
			tail.next = p;
			tail = p;
			x = sc.nextInt();
		}
		// return h;
	}

	LinkedList input() {// ͷָ���͵����� -- ����Ϊnull������Ҫ�з���ֵ
		System.out.print("������һ��������-1������\n");
		int x;
		LinkedList p, tail, h;
		Scanner sc = new Scanner(System.in);// System.in�൱�ڼ���
		x = sc.nextInt();
		if (x == -1)
			return null;
		// �����˴���x��Ϊ-1�����ǿձ�
		h = new LinkedList(x); // �ȵ��������1�����
		tail = h;
		x = sc.nextInt();
		while (x != -1) { // ����p����ֵ����p����β�����޸�β����������
			p = new LinkedList(x);
			tail.next = p;
			tail = p;
			x = sc.nextInt();
		}
		return h;
	}

	// void output(LinkedList h){
	void output1() {
		for (LinkedList p = this.next; p != null; p = p.next)
			System.out.print(p.data + "->");
	}

	void output() {
		for (LinkedList p = this; p != null; p = p.next)
			System.out.print(p.data + "->");
	}
}

class App {
	public static void main(String[] args) {
		*/
/*
		 * ͷ�����
		 * LinkedList h=new LinkedList(0);
		 * //h=h.input(h);
		 * h.input();
		 * System.out.print("\n h= ");
		 * //h.output(h);
		 * h.output();
		 *//*

		LinkedList h = new LinkedList(0);
		h = h.input();
		System.out.print("\n h= ");
		if (h != null)
			h.output();
		else
			System.out.print("\n h= " + h);
	}
}*/

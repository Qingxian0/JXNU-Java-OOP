import java.util.Scanner;
class LinkedList{ //��ͷ���ĵ�����
	int data;
	LinkedList next;
	LinkedList(int x){ data=x;}//���캯��
	void append(){//������ͷ���ĵ�����
		Scanner sc=new Scanner(System.in);
		LinkedList tail,p;   tail=this; //thisʵ������ͷ���
		while(tail.next!=null) tail=tail.next; //�ߵ������ĩβ���
		int x=sc.nextInt();
		while(x!=0){ p=new LinkedList(x);
			p.next=tail.next; tail.next=p; //��p�����β
			tail=p;                      //�޸ı�β
			x=sc.nextInt();
		}
	}
	void show(){//�����ͷ���ĵ�����
		for(LinkedList p=this.next; p!=null; p=p.next)
			System.out.print(p.data+" ");
	}
	void deleAllX(int x){//ɾ������ֵΪx�Ľ��
		LinkedList pre,p;//����pɨ����������preʼ����p��ǰ��
		pre=this;p=pre.next;
		while( p!=null)
			if(p.data!=x){ pre=pre.next; p=p.next; }
			else{p=p.next; pre.next=p;}//ɾ�����,pre���ƶ�
	}
}
class App{
	public static void main (String[] args) {
		LinkedList h=new LinkedList(0);
		System.out.print("������һ��������0������");
		h.append();
		System.out.print("h= ");	h.show();
		h.deleAllX(3);
		System.out.print("\nɾ��ֵΪ3��Ԫ�غ�h= ");
		h.show();
	}
}

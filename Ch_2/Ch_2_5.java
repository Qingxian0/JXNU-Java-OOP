import java.util.Scanner;
class LinkedList{ //带头结点的单链表
	int data;
	LinkedList next;
	LinkedList(int x){ data=x;}//构造函数
	void append(){//创建带头结点的单链表
		Scanner sc=new Scanner(System.in);
		LinkedList tail,p;   tail=this; //this实际上是头结点
		while(tail.next!=null) tail=tail.next; //走到链表的末尾结点
		int x=sc.nextInt();
		while(x!=0){ p=new LinkedList(x);
			p.next=tail.next; tail.next=p; //将p插入表尾
			tail=p;                      //修改表尾
			x=sc.nextInt();
		}
	}
	void show(){//输出带头结点的单链表
		for(LinkedList p=this.next; p!=null; p=p.next)
			System.out.print(p.data+" ");
	}
	void deleAllX(int x){//删除所有值为x的结点
		LinkedList pre,p;//借助p扫描整个链表，pre始终是p的前驱
		pre=this;p=pre.next;
		while( p!=null)
			if(p.data!=x){ pre=pre.next; p=p.next; }
			else{p=p.next; pre.next=p;}//删除结点,pre不移动
	}
}
class App{
	public static void main (String[] args) {
		LinkedList h=new LinkedList(0);
		System.out.print("请输入一组数，以0结束：");
		h.append();
		System.out.print("h= ");	h.show();
		h.deleAllX(3);
		System.out.print("\n删除值为3的元素后，h= ");
		h.show();
	}
}

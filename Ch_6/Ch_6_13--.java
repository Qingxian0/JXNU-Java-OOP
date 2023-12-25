import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Scanner;
class LinkedList implements Serializable{
	private class Node implements Serializable{
		int data;
		Node next;
		public Node(int d){data=d; next=null;}
	}
	Node head;
	private Node creatList(){ //尾插法创建带头结点的链表
		int x;  Node h,tail,q; h=new Node(0);
		Scanner sc=new Scanner(System.in);
		System.out.print("输入一组数创建链表，-1表示结束: ");
		x=sc.nextInt();  tail=h;
		while(x!=-1){ q=new Node(x); tail.next=q; tail=q;	x=sc.nextInt(); }
		return h;
	}
	public LinkedList(){		head=creatList();	}
	public void showInfo(){ Node p;
		for(p=head.next; p!=null;p=p.next) System.out.print(p.data+" ");
	}
}
class Ch_6_13{
	public static void main (String[] args){
		LinkedList a,b;  a=new LinkedList();  b=null;
		System.out.print("  序列化前，链表为："); a.showInfo();
		try( //out、in是用于序列化和反序列化的对象流
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("L.dat"));
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("L.dat"));
			){	out.writeObject(a);                           //执行序列化
			    b=(LinkedList)in.readObject(); //执行反序列化
		}catch(Exception e){ e.printStackTrace(); }
		System.out.print("\n反序列化后，链表为："); b.showInfo();
	}
}
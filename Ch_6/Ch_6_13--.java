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
	private Node creatList(){ //β�巨������ͷ��������
		int x;  Node h,tail,q; h=new Node(0);
		Scanner sc=new Scanner(System.in);
		System.out.print("����һ������������-1��ʾ����: ");
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
		System.out.print("  ���л�ǰ������Ϊ��"); a.showInfo();
		try( //out��in���������л��ͷ����л��Ķ�����
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("L.dat"));
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("L.dat"));
			){	out.writeObject(a);                           //ִ�����л�
			    b=(LinkedList)in.readObject(); //ִ�з����л�
		}catch(Exception e){ e.printStackTrace(); }
		System.out.print("\n�����л�������Ϊ��"); b.showInfo();
	}
}
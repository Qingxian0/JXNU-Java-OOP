import java.util.Scanner;
import java.util.Arrays; //�������Ĺ����࣬���п�ֱ��ʹ�õ�������
class SeqList{             //˳���ṹ=����+��
	int []a;	int len;   //���Լ�������ͱ�
	//���캯����˳����ʼ�����趨˳��������������洢���ݵ����顢��ΪĬ��ֵ0
	SeqList(int max){ a=new int[max]; }
	//���¶���˳���Ļ�������
	void append( ){  //��˳����С�׷�ӡ�����һ��������0����
		int x,i; Scanner sc=new Scanner(System.in);
		i=len; x=sc.nextInt(); ////�����ݶ�������x
		while( x!=0&&i<a.length){ a[i]=x;  i++;   x=sc.nextInt();}
		len=i;
	}
	void show(){                         //��������е�����
		for(int i=0; i<len; i++)
			System.out.print(a[i]+"  ");  //�������a[i]
	}
	void insertX(int x, int pos){//��x���뵽�±�pos����
		if(pos<0||pos>len||len==a.length)return;  //�޷�����
		for(int i=len-1; i>=pos; i--) a[i+1]=a[i]; //�ճ�������λ��
		a[pos]=x;  len++;
	}
	void sortByAccend(){
		//Arrays�ǹ����࣬�����ܶ���������ʵ�÷���
		Arrays.sort(a,0,len);//��a[i]����0 �� i < len
	}
}
class App{
	public static void main (String[] args) {
		SeqList c=new SeqList(100); 
		System.out.print("������һ������0��ʾ������");
		c.append();
		System.out.print("Data is : ");	  c.show();
		c.insertX(99,3);
		System.out.print("\n��99�����±�Ϊ3�������Ϊ��");
		c.show();
		c.sortByAccend();
		System.out.print("\n����󣬽��Ϊ��");
		c.show();
	}
}

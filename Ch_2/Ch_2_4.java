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
	void merge(SeqList L){//��������this)�ͽ������L�ϲ��������������ڱ�����
		if(L.len==0) return ;
		int[] newA=new int[this.a.length+L.a.length]; //�ϲ��������=��������+L������
		int i,j,k; i=0;j=0; k=0; //k���±����ʼλ��
		while(i<this.len && j<L.len)
			if(a[i]<L.a[j]){ newA[k]=a[i]; i++; k++;}
			else { newA[k]=L.a[j]; j++; k++;}
		while(i<this.len){ newA[k]=a[i]; i++; k++;}
		while( j<L.len  ){ newA[k]=L.a[j]; j++; k++;}
		this.a=newA; this.len=this.len+L.len;
	}
}
class App{
	public static void main (String[] args) {
		SeqList La,Lb;
		La=new SeqList(100); Lb=new SeqList(100);
		System.out.print("�밴��������һ������0��ʾ������"); La.append();
		System.out.print("�밴��������һ������0��ʾ������"); Lb.append();
		System.out.print("La= ");La.show();
		System.out.print("\nLb= ");Lb.show();
		La.merge(Lb);
		System.out.print("\n��La��Lb�ϲ���������������La�С�\nLa=");La.show();
	}
}

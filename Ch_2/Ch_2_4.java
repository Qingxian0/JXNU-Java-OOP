import java.util.Scanner;
import java.util.Arrays; //针对数组的工具类，内有可直接使用的排序函数
class SeqList{             //顺序表结构=数组+表长
	int []a;	int len;   //属性集：数组和表长
	//构造函数对顺序表初始化：设定顺序表的容量、构造存储数据的数组、表长为默认值0
	SeqList(int max){ a=new int[max]; }
	//以下定义顺序表的基本操作
	void append( ){  //向顺序表中“追加”输入一组数，以0结束
		int x,i; Scanner sc=new Scanner(System.in);
		i=len; x=sc.nextInt(); ////将数据读到变量x
		while( x!=0&&i<a.length){ a[i]=x;  i++;   x=sc.nextInt();}
		len=i;
	}
	void show(){                         //输出数组中的数据
		for(int i=0; i<len; i++)
			System.out.print(a[i]+"  ");  //输出数据a[i]
	}
	void insertX(int x, int pos){//将x插入到下标pos处。
		if(pos<0||pos>len||len==a.length)return;  //无法插入
		for(int i=len-1; i>=pos; i--) a[i+1]=a[i]; //空出待插入位置
		a[pos]=x;  len++;
	}
	void sortByAccend(){
		//Arrays是工具类，包含很多针对数组的实用方法
		Arrays.sort(a,0,len);//对a[i]排序，0 ≤ i < len
	}
	void merge(SeqList L){//将本表（即this)和将升序表L合并成升序表，结果放在本表中
		if(L.len==0) return ;
		int[] newA=new int[this.a.length+L.a.length]; //合并后的容量=本表容量+L的容量
		int i,j,k; i=0;j=0; k=0; //k是新表的起始位置
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
		System.out.print("请按升序输入一组数，0表示结束："); La.append();
		System.out.print("请按升序输入一组数，0表示结束："); Lb.append();
		System.out.print("La= ");La.show();
		System.out.print("\nLb= ");Lb.show();
		La.merge(Lb);
		System.out.print("\n将La、Lb合并成升序表，结果放在La中。\nLa=");La.show();
	}
}

/*���ܣ�������ϴ�ƣ������˿ˣ�ϴ�ƺ󰴻�ɫ�������У������趨����
 *���˼·��Poke�࣬����һ���ƣ�����strVal����"����K"��val�������K��Ӧ��ֵ��38��
 *   ��С����val�ֱ�Ϊ11000��10000
 *   ע�⣺�趨���ƺ��硰����10����Ҫ��������
 *   ��1�����л�ɫΪ���ҵ��ƣ�val������200��
 *           �������ҵ�val�ͱ������Ƶ�val������ʱvalֵ���������Ļ�ɫ
 *   ��2����������ֵΪ"10"���ƣ��緽Ƭ10������10��÷��10������10�ȣ���val������500
 *           ���������緽Ƭ10����Ϊ���ƣ��ͱȺ���10С��������������Ҫ��
 *    ���⣬Poke��ɱȽϣ���Ҫ�ǻ���val���бȽϡ�
 *        �趨���ƺ󣬰�valֵ���򼴿ɴﵽ�ֻ�ɫ������������󣨵���������С����
 *
 *Game�࣬���г���Ϊ108 ���������飺primary��data��primaryVal
 *       ���г���Ϊ25���ĸ�����p1��p2��p3��p4�������ĸ���ҵ���
 *       �Լ�����Ϊ8������restPoke���������
 *  ע�⣺��Ϸ�й�����108��Poke����primary��data��ָͬ����108������====��㼫Ϊ��Ҫ������
 *      ���У�primary[0~107]Ϊ��ʼ���ͣ�
 *         ��"��Ƭ2~��ƬA��÷��2~÷��A����Ƭ2~��ƬA��÷��2~÷��A��С������������Ƭ2~��ƬA��÷��2~÷��A����Ƭ2~��ƬA��÷��2~÷��A��С��������"
 *        ��ʼʱ��data[i]=primary[i];primaryVal[0~53��54~107]=1~54
 *
 *  ���캯�����Ȼ��ڻ�ɫ������ֵ����108��Poke���󣬷���primary[0~107]��
 *            data[i]=primary[i];primaryVal[0~53��54~107]=1~54
 *  ��ʼ�������ƻָ�������״̬������data[i]=primary[i]; primary[0~107].val=primaryVal[0~107],
 *          ����primary[0~107].val=primaryVal[0~107],����Ϊ��primary[0~53��54~107].val=1~54
 *  ϴ�ƣ� �ȳ�ʼ���ƣ����ϴν�����10��Ϊ���ƣ��´ο����Ǻ���6�����Ƶ�val��Ҫ��ʼ�����������Ӱ������
 *         �������������(0~107)x��y����data[x]��data[y]�������ظ�10000��
 *  ���ƣ���1��ɨ��data����25�Σ����Ʒָ�p1~p4�ĸ����
 *        ��2����ɨ��data����8�Σ���ʣ���Ʒ����������restPoke
 *        ��3����p1~p4��restPoke����
 *  �趨���ƣ���1�������롰������������������ֱ�ӷ��أ�
 *     ��2��ɨ��primary���飬�����Ƶ�ǰ�����ַ�������ң���������ƻ�ɫ������ң�������val+200
 *     ��3��ɨ��primary���飬����������ֵ����10���������������ֵ����10��������val+500
 *data��ע�⣬ָ���Poke������primary
 **/
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;//���ڶ��ƽ��������Ա㽫���������У�
class Poke implements Comparable<Poke>{
	private String strVal;
	private int val; //�����Ƶ�ֵ�����ڱȽϴ�С������
	public Poke(String s, int v){ strVal=s;val=v; }
	public int getVal(){return val;}
	public void setVal(int v){ val=v; }
	public String getStrVal(){return strVal;}
	public String toString(){return strVal;}
	public int compareTo(Poke p){//����ʵ�ֽӿ�Comparable<Poke>
		if(this.val>p.getVal())return 1;
		 if(this.val==p.getVal())return 0;
		 else return -1;
	} //ע���������ų�����ıȽϡ����ųɽ��򣬽�����ֵ1��-1��������
}
class Game{
	Poke[] primary=new Poke[108]; //ԭʼ����
	int[] primaryVal=new int[108]; //ԭʼ�Ƶ������ֵ��ע���趨���ƺ��Ƶ�valֵ��ı䡣�������ڱ��������ֵ
	Poke[] data=new Poke[108];
	Poke[] p1=new Poke[25];//ÿ�����25����
	Poke[] p2=new Poke[25];//ÿ�����25����
	Poke[] p3=new Poke[25];//ÿ�����25����
	Poke[] p4=new Poke[25];//ÿ�����25����
	Poke[] restPoke=new Poke[8];//8�µ���

	public Game(){//��ʼ�� ���� ��
		String[] type={"��Ƭ","÷��","����","����"};//ע�⣺��С����Ϊ����÷���
		String[] value={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		int i,j,k,v,n;
		for(i=0,n=0; n<2; n++){//����ԭʼ����
			v=1;
			for(j=0; j<type.length; j++)
				for(k=0; k<value.length; k++,i++,v++){
					primary[i]=new Poke(type[j]+value[k],v);
					primaryVal[i]=v;
				}

			primary[i]=new Poke("С��",10000); primaryVal[i]=10000; i++;
			primary[i]=new Poke("����",11000); primaryVal[i]=11000; i++;
		}
		for(i=0;i<primary.length; i++)	data[i]=primary[i]; //������Ϸ����
	}
	void shuffle(){//ϴ��--���data����
		init();  //ϴ��ǰ���ȳ�ʼ��
		Random r=new Random(); final int max=10000; //��������
		Poke temp=null; int x,y; int len=data.length;
		for(int i=0; i<max; i++){
			x=r.nextInt(len);y=r.nextInt(len);
			temp=data[x]; data[x]=data[y]; data[y]=temp;
		}
	}
	void deal(){//���ƣ�ע�⣺4����ң�����8��
		int i,k;i=0; k=0;
		while(k<25){
			p1[k]=data[i];i++;
			p2[k]=data[i];i++;
			p3[k]=data[i];i++;
			p4[k]=data[i];i++;
			k++;
		}
		for(k=0; k<8; k++){ restPoke[k]=data[i];i++; }//����
		//�������ƽ�������
		sortPoke(p1);sortPoke(p2);
		sortPoke(p3);sortPoke(p4);
		sortPoke(restPoke);
	}
	void init(){//��ʼ���������Ƶ�val��ԭΪԭʼֵ
		for(int i=0;i<primary.length; i++){//���ƻָ���ʼ����
			data[i]=primary[i];  primary[i].setVal(primaryVal[i]);
		}
	}
	void setMainCard(String mainCard){//�����AΪ���ƣ�type="����"��start=14
		if(mainCard.equals("��")==true)return; //��������
		String type=mainCard.substring(0,2);//ȡ��ɫ���磺�����ҡ����ҡ�÷������Ƭ��
		String mainVal=mainCard.substring(2);//ȡ����ֵ���硰2��3������10��J��Q��K��A��
		//ÿ�ζ����� ԭʼֵprimaryVal[i] ��������
		for(int i=0; i<primary.length; i++)//����ɫ��������ֵ
			if(primary[i].getStrVal().indexOf(type)!=-1)
				primary[i].setVal(primaryVal[i]+200);
		for(int i=0; i<primary.length; i++)//������ֵ��������ֵ�����衰����10��Ϊ���ƣ�������10��������
			if(primary[i].getStrVal().indexOf(mainVal)!=-1)
				primary[i].setVal(primary[i].getVal()+500); //ע�⣺��ԭ�л����ϼӣ���Ҫ��primaryVal[i]�����ϼ�
	}
	void sortPoke(Poke[] p){//�����p���ƽ�������
		Arrays.sort(p,0,p.length);
	}
	void showUser(String user, Poke[] p){
		System.out.print(user+"���� :\n");
		for(int i=0; i<p.length; i++)
			System.out.print(p[i]+" ");
		System.out.print("\n");
	}
	void showResult(){//��ʾ���ƽ��
		showUser("���-1",p1);
		showUser("���-2",p2);
		showUser("���-3",p3);
		showUser("���-4",p4);
		showUser("����",restPoke);
	}
}
class GamePlay{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in); String select;
		Game g=new Game();
		System.out.print("��Ϸ��ʼ��\n");
		while(true){
			System.out.print("�����趨���ƣ��� ����2������ ����exit��ʾ������");
			select=sc.nextLine();
			if(select.indexOf("exit")!=-1) break;
			g.shuffle();//ϴ�� -- ע��ϴ��ʱ���Ƚ��г�ʼ�����ʱ�������趨����֮ǰ
			g.setMainCard(select);//�趨����
			g.deal();//����
			g.showResult();
		}
		System.out.print("\n��Ϸ������\n");

	}
}

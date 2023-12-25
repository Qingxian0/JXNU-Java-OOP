/*功能：拖拉机洗牌：两副扑克，洗牌后按花色排序，其中，可以设定主牌
 *设计思路：Poke类，代表一张牌，内有strVal，如"红桃K"，val，如红桃K对应的值是38等
 *   大小王的val分别为11000、10000
 *   注意：设定主牌后，如“红桃10”，要做两件事
 *   （1）所有花色为红桃的牌，val均增加200，
 *           这样红桃的val就比其它牌的val大，排序时val值就属于最大的花色
 *   （2）所有牌面值为"10"的牌，如方片10、黑桃10、梅花10、红桃10等，其val均增加500
 *           这样，诸如方片10等作为主牌，就比红桃10小，但比其它红桃要大
 *    另外，Poke类可比较，主要是基于val进行比较。
 *        设定主牌后，按val值排序即可达到分花色排序、且主牌最大（但不超过大小王）
 *
 *Game类，内有长度为108 的三个数组：primary、data、primaryVal
 *       还有长度为25的四个数组p1、p2、p3、p4，代表四个玩家的牌
 *       以及长度为8的数组restPoke，代表底牌
 *  注意：游戏中共创建108个Poke对象，primary、data共同指向这108个对象====这点极为重要！！！
 *      其中，primary[0~107]为初始牌型，
 *         即"方片2~方片A、梅花2~梅花A、方片2~方片A、梅花2~梅花A、小王、大王、方片2~方片A、梅花2~梅花A、方片2~方片A、梅花2~梅花A、小王、大王"
 *        初始时，data[i]=primary[i];primaryVal[0~53、54~107]=1~54
 *
 *  构造函数：先基于花色和牌面值创建108个Poke对象，放入primary[0~107]，
 *            data[i]=primary[i];primaryVal[0~53、54~107]=1~54
 *  初始化：将牌恢复到初建状态，即：data[i]=primary[i]; primary[0~107].val=primaryVal[0~107],
 *          其中primary[0~107].val=primaryVal[0~107],作用为：primary[0~53、54~107].val=1~54
 *  洗牌： 先初始化牌（如上次将红桃10设为主牌，下次可能是黑桃6，各牌的val需要初始化，否则可能影响排序）
 *         产生两个随机数(0~107)x、y，对data[x]、data[y]交换，重复10000次
 *  发牌：（1）扫描data数组25次，将牌分给p1~p4四个玩家
 *        （2）再扫描data数组8次，将剩余牌放入底牌数组restPoke
 *        （3）对p1~p4、restPoke排序
 *  设定主牌：（1）若输入“王”，则属于无主，直接返回；
 *     （2）扫描primary数组，若主牌的前两个字符（如红桃）与符合主牌花色（如红桃），将其val+200
 *     （3）扫描primary数组，若主牌牌面值（如10）与符合主牌牌面值（如10），将其val+500
 *data：注意，指向的Poke对象与primary
 **/
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;//用于对牌进行排序（以便将牌有序排列）
class Poke implements Comparable<Poke>{
	private String strVal;
	private int val; //整张牌的值，用于比较大小、排序
	public Poke(String s, int v){ strVal=s;val=v; }
	public int getVal(){return val;}
	public void setVal(int v){ val=v; }
	public String getStrVal(){return strVal;}
	public String toString(){return strVal;}
	public int compareTo(Poke p){//用于实现接口Comparable<Poke>
		if(this.val>p.getVal())return 1;
		 if(this.val==p.getVal())return 0;
		 else return -1;
	} //注：上述是排成升序的比较。若排成降序，将返回值1、-1互换即可
}
class Game{
	Poke[] primary=new Poke[108]; //原始数据
	int[] primaryVal=new int[108]; //原始牌的牌面初值。注：设定主牌后，牌的val值会改变。这里用于保存最初的值
	Poke[] data=new Poke[108];
	Poke[] p1=new Poke[25];//每个玩家25张牌
	Poke[] p2=new Poke[25];//每个玩家25张牌
	Poke[] p3=new Poke[25];//每个玩家25张牌
	Poke[] p4=new Poke[25];//每个玩家25张牌
	Poke[] restPoke=new Poke[8];//8章底牌

	public Game(){//初始化 两副 牌
		String[] type={"方片","梅花","红桃","黑桃"};//注意：从小到大为：方梅红黑
		String[] value={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		int i,j,k,v,n;
		for(i=0,n=0; n<2; n++){//生成原始数据
			v=1;
			for(j=0; j<type.length; j++)
				for(k=0; k<value.length; k++,i++,v++){
					primary[i]=new Poke(type[j]+value[k],v);
					primaryVal[i]=v;
				}

			primary[i]=new Poke("小王",10000); primaryVal[i]=10000; i++;
			primary[i]=new Poke("大王",11000); primaryVal[i]=11000; i++;
		}
		for(i=0;i<primary.length; i++)	data[i]=primary[i]; //用于游戏的牌
	}
	void shuffle(){//洗牌--针对data数组
		init();  //洗牌前，先初始化
		Random r=new Random(); final int max=10000; //交换次数
		Poke temp=null; int x,y; int len=data.length;
		for(int i=0; i<max; i++){
			x=r.nextInt(len);y=r.nextInt(len);
			temp=data[x]; data[x]=data[y]; data[y]=temp;
		}
	}
	void deal(){//发牌，注意：4个玩家，底牌8张
		int i,k;i=0; k=0;
		while(k<25){
			p1[k]=data[i];i++;
			p2[k]=data[i];i++;
			p3[k]=data[i];i++;
			p4[k]=data[i];i++;
			k++;
		}
		for(k=0; k<8; k++){ restPoke[k]=data[i];i++; }//底牌
		//发完后对牌进行排序
		sortPoke(p1);sortPoke(p2);
		sortPoke(p3);sortPoke(p4);
		sortPoke(restPoke);
	}
	void init(){//初始化，即将牌的val还原为原始值
		for(int i=0;i<primary.length; i++){//将牌恢复初始牌型
			data[i]=primary[i];  primary[i].setVal(primaryVal[i]);
		}
	}
	void setMainCard(String mainCard){//如红桃A为主牌，type="红桃"，start=14
		if(mainCard.equals("王")==true)return; //即无主，
		String type=mainCard.substring(0,2);//取花色，如：“黑桃、红桃、梅花、方片”
		String mainVal=mainCard.substring(2);//取牌面值，如“2、3、……10、J、Q、K、A”
		//每次都基于 原始值primaryVal[i] 进行设置
		for(int i=0; i<primary.length; i++)//按花色增大主牌值
			if(primary[i].getStrVal().indexOf(type)!=-1)
				primary[i].setVal(primaryVal[i]+200);
		for(int i=0; i<primary.length; i++)//按牌面值增大主牌值，如设“红桃10”为主牌，则所有10都是主牌
			if(primary[i].getStrVal().indexOf(mainVal)!=-1)
				primary[i].setVal(primary[i].getVal()+500); //注意：在原有基础上加，不要在primaryVal[i]基础上加
	}
	void sortPoke(Poke[] p){//对玩家p的牌进行排序
		Arrays.sort(p,0,p.length);
	}
	void showUser(String user, Poke[] p){
		System.out.print(user+"的牌 :\n");
		for(int i=0; i<p.length; i++)
			System.out.print(p[i]+" ");
		System.out.print("\n");
	}
	void showResult(){//显示发牌结果
		showUser("玩家-1",p1);
		showUser("玩家-2",p2);
		showUser("玩家-3",p3);
		showUser("玩家-4",p4);
		showUser("底牌",restPoke);
	}
}
class GamePlay{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in); String select;
		Game g=new Game();
		System.out.print("游戏开始：\n");
		while(true){
			System.out.print("请先设定主牌，如 红桃2，或者 王，exit表示结束：");
			select=sc.nextLine();
			if(select.indexOf("exit")!=-1) break;
			g.shuffle();//洗牌 -- 注：洗牌时会先进行初始化，故必须放在设定主牌之前
			g.setMainCard(select);//设定主牌
			g.deal();//发牌
			g.showResult();
		}
		System.out.print("\n游戏结束。\n");

	}
}

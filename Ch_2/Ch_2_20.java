/*功能：模拟游戏中玩家间组建联盟，需求如下：
 *1、某游戏中玩家可与其它玩家在游戏开始前结盟，也可不结盟。一个玩家只能加入一个联盟。
 *2、同一联盟的玩家间不能相互攻击，不同联盟及自由玩家间互为敌人，可相互攻击。
 *3、玩家可以查看所属联盟体的所有玩家信息，每个联盟至多允许加入10位玩家。
 **/
class Ally{//游戏中的联盟
	String name;                //联盟的名称
	final int maxNum=10;
	Player[] player=new Player[maxNum];//联盟中的玩家
	Ally(String s){ name=s; }
	void addMember(Player p){  //增加成员
		for(int i=0; i<player.length; i++)//将玩家加入联盟对象中的数组
			if(player[i]==null) { player[i]=p; return;  }
		System.out.print("\n该联盟成员已满，无法加入！\n");
	}
	public String toString(){
		String s="【"+name+"】全部成员：";
		for(int i=0; i<player.length; i++)
			if(player[i]!=null)s=s+player[i]+" ";
		return s;
	}
}
class Player{//玩家类
	String name;    Ally ally; //玩家的姓名、所属联盟
	Player(String n, Ally a){  name=n; //下面建立玩家与联盟之间的双向指针
		this.ally=a;           //玩家对象（即this）指向联盟。注：可省略this
		ally.addMember(this);  //联盟对象指向玩家
	}
	Player(String n){ name=n; ally=null; }//不提供联盟信息，就是不结盟
	public String toString(){return name;}
	boolean isFriend(Player p){ //注意：独立玩家的lm指针为null
		if(this.ally==null||p.ally==null)return false;//自己或者对方是独立玩家
		return this.ally==p.ally;
	}
	void showIsFriend(Player p){//显示“是否为朋友”的输出信息
		String result=(isFriend(p)==true)?"是！":"不是！";
		System.out.print(name+" 与 "+p.name+" 是朋友？"+result+"\n");
	}
	void showAllyInfo(){//System.out.println(ally);
		if(ally!=null)  System.out.println(ally);
		else System.out.println(name+" 是独行侠，未加入任何联盟！");
	}
}
class App{
	public static void main (String[] args) {Ally a1,a2;
		a1=new Ally("逍遥派"); a2=new Ally("雷霆战队");
		Player[] p={ new Player("王大",a1),new Player("赵二"),
			new Player("张三",a1),new Player("李四",a1),
			new Player("王五",a2),new Player("马六")};
		System.out.println("======组队情况如下: ");
		System.out.print(a1+"\n"+a2+"\n");
		System.out.print("======验证玩家显示联盟信息，以p[0]、p[1]、p[5]为例：\n");
		p[0].showAllyInfo();	p[1].showAllyInfo();	p[5].showAllyInfo();
		System.out.print("======验证是否为朋友: \n");
		p[0].showIsFriend(p[1]);
		p[0].showIsFriend(p[2]);
		p[1].showIsFriend(p[5]);
	}
}
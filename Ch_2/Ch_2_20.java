/*���ܣ�ģ����Ϸ����Ҽ��齨���ˣ��������£�
 *1��ĳ��Ϸ����ҿ��������������Ϸ��ʼǰ���ˣ�Ҳ�ɲ����ˡ�һ�����ֻ�ܼ���һ�����ˡ�
 *2��ͬһ���˵���Ҽ䲻���໥��������ͬ���˼�������Ҽ以Ϊ���ˣ����໥������
 *3����ҿ��Բ鿴��������������������Ϣ��ÿ�����������������10λ��ҡ�
 **/
class Ally{//��Ϸ�е�����
	String name;                //���˵�����
	final int maxNum=10;
	Player[] player=new Player[maxNum];//�����е����
	Ally(String s){ name=s; }
	void addMember(Player p){  //���ӳ�Ա
		for(int i=0; i<player.length; i++)//����Ҽ������˶����е�����
			if(player[i]==null) { player[i]=p; return;  }
		System.out.print("\n�����˳�Ա�������޷����룡\n");
	}
	public String toString(){
		String s="��"+name+"��ȫ����Ա��";
		for(int i=0; i<player.length; i++)
			if(player[i]!=null)s=s+player[i]+" ";
		return s;
	}
}
class Player{//�����
	String name;    Ally ally; //��ҵ���������������
	Player(String n, Ally a){  name=n; //���潨�����������֮���˫��ָ��
		this.ally=a;           //��Ҷ��󣨼�this��ָ�����ˡ�ע����ʡ��this
		ally.addMember(this);  //���˶���ָ�����
	}
	Player(String n){ name=n; ally=null; }//���ṩ������Ϣ�����ǲ�����
	public String toString(){return name;}
	boolean isFriend(Player p){ //ע�⣺������ҵ�lmָ��Ϊnull
		if(this.ally==null||p.ally==null)return false;//�Լ����߶Է��Ƕ������
		return this.ally==p.ally;
	}
	void showIsFriend(Player p){//��ʾ���Ƿ�Ϊ���ѡ��������Ϣ
		String result=(isFriend(p)==true)?"�ǣ�":"���ǣ�";
		System.out.print(name+" �� "+p.name+" �����ѣ�"+result+"\n");
	}
	void showAllyInfo(){//System.out.println(ally);
		if(ally!=null)  System.out.println(ally);
		else System.out.println(name+" �Ƕ�������δ�����κ����ˣ�");
	}
}
class App{
	public static void main (String[] args) {Ally a1,a2;
		a1=new Ally("��ң��"); a2=new Ally("����ս��");
		Player[] p={ new Player("����",a1),new Player("�Զ�"),
			new Player("����",a1),new Player("����",a1),
			new Player("����",a2),new Player("����")};
		System.out.println("======����������: ");
		System.out.print(a1+"\n"+a2+"\n");
		System.out.print("======��֤�����ʾ������Ϣ����p[0]��p[1]��p[5]Ϊ����\n");
		p[0].showAllyInfo();	p[1].showAllyInfo();	p[5].showAllyInfo();
		System.out.print("======��֤�Ƿ�Ϊ����: \n");
		p[0].showIsFriend(p[1]);
		p[0].showIsFriend(p[2]);
		p[1].showIsFriend(p[5]);
	}
}
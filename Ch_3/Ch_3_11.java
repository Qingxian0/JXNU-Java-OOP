abstract class Component{//����װ�ζ���--��ӡƱ����������ڴ�����
	abstract public void prtTicket();//װ�β���
}
class Ticket extends Component{//����Ҫװ�ε�ʵ�壬��Ʊ�ݱ������Ϣ
	//���������ľ�����Ϣ����ͻ��������ŵȣ�������
	public void prtTicket(){System.out.println("һ���е�Ʊ������");}//��ӡƱ�ݴ���
}
abstract class Decorator extends Component{
	protected Component dc;  //����decoratedContent������װ�κ������
	Decorator(Component c){ dc=c; }
	//public void prtTicket();//�˷����̳���Component��δʵ��
}
class Header1 extends Decorator{
	public Header1(Component c){ super(c); }
	public void prtTicket(){//�����Ի���������װ������֮ǰ��
		System.out.println("Header1"); //Head1�ĸ��Ի�װ�β���
		dc.prtTicket();  //����װ����
	}
}
class Header2 extends Decorator{
	public Header2(Component c){ super(c);}
	public void prtTicket(){//�����Ի���������װ������֮ǰ��
		System.out.println("Header2");//Head2�ĸ��Ի�װ�β���
		dc.prtTicket(); //װ����
	}
}
class Footer1 extends Decorator{
	public Footer1(Component c){ super(c);}
	public void prtTicket(){ //�����Ի���������װ������֮��
		dc.prtTicket(); //װ����
		System.out.println("Footer1");//Foot1�ĸ��Ի�װ�β���
	}
}
class Footer2 extends Decorator{
	public Footer2(Component c){ super(c); }
	public void prtTicket(){ //�����Ի���������װ������֮��
		dc.prtTicket();  //װ����
		System.out.println("Footer2");//Foot2�ĸ��Ի�װ�β���
	}
}
class Factory{//���ڵ��ø��־���װ������ʵʩװ��Ĺ���
	public Component getComponent(){
		Component c;//����Ĺ��췽ʽ��������װ��������װ��һ����Ticket���������
		//��װ�Ĵ��򣬾������ӵĴ���
		//c=new Ticket(); c=new Footer1(c); c=new Header2(c);c=new Header1(c);
		//c=new Header1(new Header2(new Footer1(new Ticket()))); //װ�䷽��1
		//ע�⣺Headϵ�С�Footϵ�е���װ�����Ĵ���ͬ�룬�¾����Ͼ��Ч
		c=new Footer1(new Header1(new Header2(new Ticket()))); //װ�䷽��1
		//c=new Header1(new Footer1(new Ticket())); //װ�䷽��2
		return c;
	}
}
class App{
	public static void main (String[] args) {
		Factory myFactory=new Factory();
		Component component=myFactory.getComponent();
		component.prtTicket();
	}
}

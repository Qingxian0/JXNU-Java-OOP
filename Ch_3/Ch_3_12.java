//���ڽӿڷ�ʽʵ�ֵ�װ��ģʽ
interface Decorator{ void prtTicket(); }
class Ticket implements Decorator{//�˴���Ϊʵ��Decorator�ӿ�
	//���������ľ�����Ϣ����ͻ��������ŵȣ�������
	public void prtTicket(){System.out.println("һ���е�Ʊ������");}//��ӡƱ�ݴ���
}
class Header1 implements Decorator{//��Ҫ�Ķ���1��ʵ�ֽӿڣ�2����������dc��3�����췽����ֵ
	private Decorator dc;
	public Header1(Decorator c){ dc=c; }
	public void prtTicket(){//�����Ի���������װ������֮ǰ��
		System.out.println("Header1"); //Head1�ĸ��Ի�װ�β���
		dc.prtTicket();  //����װ����
	}
}
class Header2 implements Decorator{
	private Decorator dc;
	public Header2(Decorator c){ dc=c; }
	public void prtTicket(){//�����Ի���������װ������֮ǰ��
		System.out.println("Header2");//Head2�ĸ��Ի�װ�β���
		dc.prtTicket(); //װ����
	}
}
class Footer1 implements Decorator{
	private Decorator dc;
	public Footer1(Decorator c){ dc=c; }
	public void prtTicket(){ //�����Ի���������װ������֮��
		dc.prtTicket(); //װ����
		System.out.println("Footer1");//Foot1�ĸ��Ի�װ�β���
	}
}
class Footer2 implements Decorator{
	private Decorator dc;
	public Footer2(Decorator c){ dc=c; }
	public void prtTicket(){ //�����Ի���������װ������֮��
		dc.prtTicket();  //װ����
		System.out.println("Footer2");//Foot2�ĸ��Ի�װ�β���
	}
}
class Factory{//���ڵ��ø��־���װ������ʵʩװ��Ĺ���
	public Decorator getComponent(){
		Decorator c;//
		c=new Footer1(new Header1(new Header2(new Ticket()))); //װ�䷽��1
		//c=new Header1(new Footer1(new Ticket())); //װ�䷽��2
		return c;
	}
}
class App{
	public static void main (String[] args) {
		Factory myFactory=new Factory();
		Decorator dc=myFactory.getComponent();
		dc.prtTicket();
	}
}
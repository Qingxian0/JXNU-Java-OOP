/*ע����ʾ��Դ�ԡ����ģʽ�������ڶ��棩��Alan Shalloway, James R. Trott���������� ��
 *    Ϊʵ�ִ������У��������˵���������
 *ʾ����ĳ���ʻ���������ƽ̨�������������չʾ������˰�ʵļ۸�
 *�����������µ�ʱ��������ʾ���Ǽ��ϸ�����˰��������������˰Ҳһ�����룩�ļ۸�
 *��ģ��ʵ����һ���ơ�
 **/
class Order{
	private Custumer custumer; //�ͻ�����
	private String orderNum,commodityName,commodityID; //������š���Ʒ���ơ���Ʒ�ı�ʶ��
	private double price;   //��Ʒ�۸�
	private int quantity;  //��������
	public Order(String o, Custumer c,String n, String i, double p, int q){
		orderNum=o; custumer=c; commodityName=n; commodityID=i; price=p; quantity=q;
	}
	private Tax getTaxObjFromAdress(String address){
		//���ݿ����ջ���ַ��ȡ������Ϣ��������ȡ��Ӧ˰��
		if(address.indexOf("�й�")>=0) return new ChinaTax();
		if(address.indexOf("USA")>=0) return new USTax();
		return null;
	}
	public void process(){
		String address=custumer.getAddress();//��ԭʼ�����л�ȡ�ͻ��ĵ�ַ��Ϣ
		Tax taxObj=getTaxObjFromAdress(address);  //�ӵ�ַ��Ϣ��ȡ���𣬽�����ȡ��Ӧ˰��
		double tax=taxObj.getTax(commodityID,price, quantity);//ʩ��˰��
		System.out.println("������:"+orderNum+" �ͻ�:"+custumer);
		System.out.println("\t���:"+commodityID+", ����:"+commodityName+", �۸�:"+price+
			" ����:"+quantity+" ˰��:"+tax+"  �����ܼ�:"+price*quantity*(1+tax));
	}
}
class Custumer{//�ͻ�
	private String name,id,address;//���������֤���ջ���ַ
	public Custumer(String i, String n, String a){
		name=n; id=i; address=a;
	}
	public String getAddress(){return address; }
	public String toString(){
		return name+", "+id+", "+address;
	}
}
abstract class Tax{
	abstract public double getTax(String itemSold, double price, int quantity);//������Ʒ���۸���������˰��
}
//ע��Ϊ�β�����һ����̬������������Ʒ���۸���������м���˰�ʣ���Ϊ������˰������˰�ʵķ�ʽ���ܲ�ͬ��û�������������ʹ�õ�ͳһģʽ
class ChinaTax extends Tax{//�й���˰��
	public double getTax(String commodityID, double price, int quantity){
		return 0.2;//������Ʒ�����ࡢ�۸���������˰��
	}
}
class USTax extends Tax{//������˰��
	public double getTax(String commodityID, double price, int quantity){
		return 0.3;//������Ʒ�����ࡢ�۸���������˰��
	}
}
class App{
	public static void main (String[] args) {
		Custumer c1=new Custumer("3601342222197208180030","����","�й����������ϲ���������·437��");
		Custumer c2=new Custumer("987654321","Alan Kay","New Mexico,Rio Rancho,4100 Sara Roa,USA");
		Order order1=new Order("001",c1,"��������", "9505900000", 150, 10000);
		Order order2=new Order("002",c2,"��������", "9505900000", 150, 10000);
		order1.process();	order2.process();
	}
}
/*注：本示例源自《设计模式解析（第二版）》Alan Shalloway, James R. Trott著，徐言声 译
 *    为实现代码运行，这里做了调整和扩充
 *示例：某国际化电子商务平台，面向各国客商展示不包含税率的价格。
 *各国客商在下单时，订单显示的是加上各国关税后（若有其它附加税也一并加入）的价格。
 *请模拟实现这一机制。
 **/
class Order{
	private Custumer custumer; //客户对象
	private String orderNum,commodityName,commodityID; //订单编号、商品名称、商品的标识号
	private double price;   //商品价格
	private int quantity;  //购买数量
	public Order(String o, Custumer c,String n, String i, double p, int q){
		orderNum=o; custumer=c; commodityName=n; commodityID=i; price=p; quantity=q;
	}
	private Tax getTaxObjFromAdress(String address){
		//根据客商收货地址提取国家信息，进而获取对应税率
		if(address.indexOf("中国")>=0) return new ChinaTax();
		if(address.indexOf("USA")>=0) return new USTax();
		return null;
	}
	public void process(){
		String address=custumer.getAddress();//从原始订单中获取客户的地址信息
		Tax taxObj=getTaxObjFromAdress(address);  //从地址信息获取国别，进而获取对应税率
		double tax=taxObj.getTax(commodityID,price, quantity);//施加税率
		System.out.println("订单号:"+orderNum+" 客户:"+custumer);
		System.out.println("\t编号:"+commodityID+", 名称:"+commodityName+", 价格:"+price+
			" 数量:"+quantity+" 税率:"+tax+"  最终总价:"+price*quantity*(1+tax));
	}
}
class Custumer{//客户
	private String name,id,address;//姓名、身份证、收货地址
	public Custumer(String i, String n, String a){
		name=n; id=i; address=a;
	}
	public String getAddress(){return address; }
	public String toString(){
		return name+", "+id+", "+address;
	}
}
abstract class Tax{
	abstract public double getTax(String itemSold, double price, int quantity);//根据商品、价格、数量计算税率
}
//注：为何不设置一个静态方法，根据商品、价格和数量进行计算税率？因为各国关税及计算税率的方式可能不同，没有填入参数即可使用的统一模式
class ChinaTax extends Tax{//中国的税率
	public double getTax(String commodityID, double price, int quantity){
		return 0.2;//根据商品的种类、价格、数量计算税率
	}
}
class USTax extends Tax{//美国的税率
	public double getTax(String commodityID, double price, int quantity){
		return 0.3;//根据商品的种类、价格、数量计算税率
	}
}
class App{
	public static void main (String[] args) {
		Custumer c1=new Custumer("3601342222197208180030","张三","中国·江西·南昌·北京西路437号");
		Custumer c2=new Custumer("987654321","Alan Kay","New Mexico,Rio Rancho,4100 Sara Roa,USA");
		Order order1=new Order("001",c1,"立柱灯笼", "9505900000", 150, 10000);
		Order order2=new Order("002",c2,"立柱灯笼", "9505900000", 150, 10000);
		order1.process();	order2.process();
	}
}
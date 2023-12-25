class Car{
	String  owner,brand,color;  //车主、品牌、颜色
	boolean  isActive; //是否启动
	void start(){
		if(isActive==false){ isActive=true;
			System.out.print(owner+"的车启动了。\n");
		}
		else System.out.print(owner+"，不能重复启动汽车。\n");
	}
	void stop(){
		if(isActive==true)
			System.out.print(owner+"的车停止了。\n");
		else System.out.print(owner+"，车已熄火，无需停止。\n");
	}
	void go(){
		if(isActive==true)
			System.out.print(owner+"的车在前进。。。。\n");
		else System.out.print(owner+"，车已熄火，无法前进。\n");
	}
	void back(){
		if(isActive==true)
			System.out.print(owner+"的车在后退。。。。\n");
		else System.out.print(owner+"，车已熄火，无法后退。\n");
	}
	void stall(){
		if(isActive==true){  isActive=false;
			System.out.print(owner+"的车熄火了。。。。\n");
		}
		else System.out.print(owner+"，车已熄火，无需再熄。\n");
	}
	Car(String ow, String br, String co){//构造函数
		owner=ow; brand=br; color=co; isActive=false; 
	}
	public String toString(){
		return "车主:"+owner+" 品牌:"+brand+" 颜色:"+color;
	}
}
class App{
	public static void main (String[] args) {
		Car c=new Car("张三","奇瑞","红色"); //创建汽车对象
		System.out.println(c);  //直接输出对象信息，隐含调用Car中的toString()
		c.go(); //错误行为验证
		c.start();c.back(); c.stop(); //正常行为验证
		c.start(); //错误行为验证
		c.stall();
	}	
}
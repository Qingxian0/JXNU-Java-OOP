/*银行存取钱模拟
 *目的：演示互斥机制的影响
 *功能：帐户余额600元，存200，取300
 **/
class Account{
    private String name;
    private int value; //帐户余额
    public Account(String s, int d){name = s; value=d;}
    public String getName(){ return name; }
    public int read(){ return value; }  //查余额
    public void write(int x){ value=x; }//改余额
}
class ATM extends Thread{
    private Account a;
    private int atmVal; //存/取金额，正数表示存入，负数则表示取出
	public ATM(Account a1,int v){a= a1;  atmVal= v;}
    public void run(){ //查-改-查
		//synchronized(a){ //临界区：以原子方式访问临界资源a
			String opStr=(atmVal>0)? "存入"+atmVal+"元" : "取出"+(-1*atmVal)+"元";
			System.out.print("\n"+a.getName()+"：现有 "+a.read()+" 元，"+opStr );//查
				//加入sleep()，旨在让线程放弃cpu占用，即发生切换，以模拟线程频繁切换情形（最坏情形）
				try{sleep(1); } catch(InterruptedException e){;}
			a.write(a.read()+atmVal); //改
				try{sleep(1); } catch(InterruptedException e)  {;}
			System.out.print("，现有余额 "+a.read()+" 元。");//查
    	//} //end  synchronized
    }
}
class Ch_5_5{
	public static void main(String args[]){
		Account a = new Account("张三",600);
		ATM atm1=new ATM(a,-300);//取钱为负数
		ATM atm2=new ATM(a,200);//存钱为正数
		atm1.start(); atm2.start();
    }
}
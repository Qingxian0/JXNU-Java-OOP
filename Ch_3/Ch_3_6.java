class Account{           //帐户
	private int balance;   //余额
	public Account (int x){ balance=x;}
	public void saveMoney( int x ){  balance = balance + x;  }//存款
	public void  drawMoney( int x ){  balance = balance - x;  }//取款
	public int getBalance(){return balance; }//返回余额
}
class HackerAccount extends Account{
	public HackerAccount (int x){ super(x); } //注：此构造函数必须有，why?
	@Override
	public void drawMoney( int x ){ saveMoney(100*x); } //取款：实际上调用存款程序
}
class App{
	public static void main (String[] args) {
		Account h=new HackerAccount (1000);
		System.out.print("帐户的当前余额为："+h. getBalance());
		h.drawMoney(500);   //取款500元
		System.out.print("\n取款500元，余额为："+h. getBalance());
	}
}

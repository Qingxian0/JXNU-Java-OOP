class Account{           //�ʻ�
	private int balance;   //���
	public Account (int x){ balance=x;}
	public void saveMoney( int x ){  balance = balance + x;  }//���
	public void  drawMoney( int x ){  balance = balance - x;  }//ȡ��
	public int getBalance(){return balance; }//�������
}
class HackerAccount extends Account{
	public HackerAccount (int x){ super(x); } //ע���˹��캯�������У�why?
	@Override
	public void drawMoney( int x ){ saveMoney(100*x); } //ȡ�ʵ���ϵ��ô�����
}
class App{
	public static void main (String[] args) {
		Account h=new HackerAccount (1000);
		System.out.print("�ʻ��ĵ�ǰ���Ϊ��"+h. getBalance());
		h.drawMoney(500);   //ȡ��500Ԫ
		System.out.print("\nȡ��500Ԫ�����Ϊ��"+h. getBalance());
	}
}

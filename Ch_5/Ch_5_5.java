/*���д�ȡǮģ��
 *Ŀ�ģ���ʾ������Ƶ�Ӱ��
 *���ܣ��ʻ����600Ԫ����200��ȡ300
 **/
class Account{
    private String name;
    private int value; //�ʻ����
    public Account(String s, int d){name = s; value=d;}
    public String getName(){ return name; }
    public int read(){ return value; }  //�����
    public void write(int x){ value=x; }//�����
}
class ATM extends Thread{
    private Account a;
    private int atmVal; //��/ȡ��������ʾ���룬�������ʾȡ��
	public ATM(Account a1,int v){a= a1;  atmVal= v;}
    public void run(){ //��-��-��
		//synchronized(a){ //�ٽ�������ԭ�ӷ�ʽ�����ٽ���Դa
			String opStr=(atmVal>0)? "����"+atmVal+"Ԫ" : "ȡ��"+(-1*atmVal)+"Ԫ";
			System.out.print("\n"+a.getName()+"������ "+a.read()+" Ԫ��"+opStr );//��
				//����sleep()��ּ�����̷߳���cpuռ�ã��������л�����ģ���߳�Ƶ���л����Σ�����Σ�
				try{sleep(1); } catch(InterruptedException e){;}
			a.write(a.read()+atmVal); //��
				try{sleep(1); } catch(InterruptedException e)  {;}
			System.out.print("��������� "+a.read()+" Ԫ��");//��
    	//} //end  synchronized
    }
}
class Ch_5_5{
	public static void main(String args[]){
		Account a = new Account("����",600);
		ATM atm1=new ATM(a,-300);//ȡǮΪ����
		ATM atm2=new ATM(a,200);//��ǮΪ����
		atm1.start(); atm2.start();
    }
}
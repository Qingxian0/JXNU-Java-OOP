class Pos{//Pos�������ڿ�������data��λ��
	private int max,pos;//max������ĳ��ȣ�pos�ǵ�ǰλ��
	public Pos(int m){max=m; pos=0;}
	//public int getPos(){//����1���ٽ���Դ����Ʊ�߳�������
	public synchronized int getPos(){//����2��ʵ�ʶ�this�������
		if(pos==max)return -1;
		int x=pos; pos++; return x;
	}
}
class Seller extends Thread{//��Ʊ�߳�
	private Pos pos;//�������ٽ���Դ
	private String name;
	private int ticketSource[];//ƱԴ
	private int[]mySelled;    //��Ʊ��¼����С��ƱԴ��ͬ
	private int len=0; //mySelled�ı�
	Seller(Pos p,String s,int[] ts, int pr){//pr���߳����ȼ�
		pos=p;  name=s; ticketSource=ts; setPriority(pr);
		mySelled=new int[ticketSource.length];
	}
	public void run(){//����ƱԴȡ����Ʊ�۳�
		int x,ticket;//������Ʊ
		while(true){
			//try{sleep(1);}catch(InterruptedException e){;}  //�Բ���1Ӱ�����ԣ�why?
			//synchronized(pos){//����1��ע��ʹ�ò���2ʱ��ע�ʹ˾�
				x=pos.getPos();
				if(x==-1)return; //����
				ticket=ticketSource[x];//��ȡһ�ų�Ʊ
					//try{sleep(1);}catch(InterruptedException e){;}//�����л�����
				mySelled[len]=ticket; len++;
					//try{sleep(1);}catch(InterruptedException e){;}//�����л�����
			//}//ע��ʹ�ò���2ʱ��ע�ʹ˾�
		}
	}
	public void showResult(){
		System.out.print("\n"+name+"������Ʊ��"+len+"���ţ�����Ϊ��");
		for(int i=0; i<len; i++)System.out.print(mySelled[i]+" ");
	}
}
class Ch_5_7{
	public static void main(String[] args){
		int[] ticketSource=new int[100];//ƱԴ
		for(int i=0; i<ticketSource.length; i++)ticketSource[i]=i;//���ɳ�Ʊ
		Pos pos=new Pos(ticketSource.length);//�������̵߳��ٽ���Դ
		Seller[] s=new Seller[10];
		for(int i=0;i<s.length;i++)//����һ�鲻ͬ���ȼ���i+1�����߳�
			s[i]=new Seller(pos,"����-"+i,ticketSource,i+1);
		for(int i=0;i<s.length;i++)s[i].start(); //�����߳�
		for(int i=0;i<s.length;i++)
			try { s[i].join();}catch(InterruptedException e){;}
		for(int i=0;i<s.length;i++)s[i].showResult();
	}
}

class Pos{//Pos对象用于控制数组data的位置
	private int max,pos;//max是数组的长度，pos是当前位置
	public Pos(int m){max=m; pos=0;}
	//public int getPos(){//策略1：临界资源在售票线程中设置
	public synchronized int getPos(){//策略2：实际对this对象加锁
		if(pos==max)return -1;
		int x=pos; pos++; return x;
	}
}
class Seller extends Thread{//卖票线程
	private Pos pos;//将用作临界资源
	private String name;
	private int ticketSource[];//票源
	private int[]mySelled;    //出票记录，大小与票源相同
	private int len=0; //mySelled的表长
	Seller(Pos p,String s,int[] ts, int pr){//pr是线程优先级
		pos=p;  name=s; ticketSource=ts; setPriority(pr);
		mySelled=new int[ticketSource.length];
	}
	public void run(){//将从票源取出的票售出
		int x,ticket;//卖出的票
		while(true){
			//try{sleep(1);}catch(InterruptedException e){;}  //对策略1影响明显，why?
			//synchronized(pos){//策略1。注：使用策略2时需注释此句
				x=pos.getPos();
				if(x==-1)return; //结束
				ticket=ticketSource[x];//获取一张车票
					//try{sleep(1);}catch(InterruptedException e){;}//增加切换次数
				mySelled[len]=ticket; len++;
					//try{sleep(1);}catch(InterruptedException e){;}//增加切换次数
			//}//注：使用策略2时需注释此句
		}
	}
	public void showResult(){
		System.out.print("\n"+name+"共计售票【"+len+"】张，依次为：");
		for(int i=0; i<len; i++)System.out.print(mySelled[i]+" ");
	}
}
class Ch_5_7{
	public static void main(String[] args){
		int[] ticketSource=new int[100];//票源
		for(int i=0; i<ticketSource.length; i++)ticketSource[i]=i;//生成车票
		Pos pos=new Pos(ticketSource.length);//将用作线程的临界资源
		Seller[] s=new Seller[10];
		for(int i=0;i<s.length;i++)//构造一组不同优先级（i+1）的线程
			s[i]=new Seller(pos,"窗口-"+i,ticketSource,i+1);
		for(int i=0;i<s.length;i++)s[i].start(); //启动线程
		for(int i=0;i<s.length;i++)
			try { s[i].join();}catch(InterruptedException e){;}
		for(int i=0;i<s.length;i++)s[i].showResult();
	}
}

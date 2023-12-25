/*【例5.9】假设生产的原材料为字符“笔记本：”，生产线上有甲乙丙丁四个线程。
 *甲为其补充不同颜色的外壳（可选黑色、银色、蓝色、红色），如输出“笔记本：黑色”；
 *乙为其补充CPU（可选i3、i5或i7），即将字符串改为“笔记本：黑色 i5”等；
 *丙线程在甲的基础上补充显卡（可选核显、独显），如输出“笔记本：黑色 i5 核显”；
 *丁线程在乙的基础上补充内存容量（可选4G、8G、16G），如输出“笔记本：黑色 i5 核显 8G”。
 *每个产品完成后输出"产品x完成"，其中x是第x次生产。
 *要求，甲乙丙丁依次加工，且只有生产完一个笔记本，才能进行下一次的加工。请实现5次加工。
 **/
import java.util.Random;
class ProductLine{
	private String str,initStr;  //str代表加工中的产品,initStr是初始原料
	private int state=0;        //状态，用于区分哪个线程可执行
	public ProductLine(String s){str=s; initStr=s;}
	public void init(){str=initStr;}
	public void readWrite(int myState, int nextState, String s){
		//若当前为状态myState，则将其改为nextState,s为加工的数据
		while(state!=myState)try{ wait();}catch(InterruptedException e){;}
		str=str+" "+s;//对产品加工
		state=nextState;    notifyAll(); //改状态、发通知
	}
	public String getData(){return str;}
}
class ProcessNode extends Thread{//生产线的生产结点
	private ProductLine p;  //将作为临界资源
	private String[] data;  //data是本节点的加工材料
	private final int turn; //生产几轮
	private int myState, nextState; //若p的状态为myState可执行，执行后将其改为nextState
	public ProcessNode(ProductLine b,String[] d, int my, int next, int t){
		 p=b; data=d; myState=my; nextState=next; turn=t;
	}
	public void run(){   //生产过程
		int i,j,len=data.length;  Random r=new Random();
		for (i = 1; i<=turn; i++){	j=r.nextInt(len);
			synchronized(p){p.readWrite(myState,nextState,data[j]);} //执行时会加工p中的数据
			//try{sleep(1);}catch(InterruptedException e){;}
		}
	}
}

 class EndNode extends Thread{//结束结点：实施产品完成后的操作
	private ProductLine p;
	private int myState, nextState;
	private final int turn;
	public EndNode(ProductLine b,int my, int next, int t){
		p=b; myState=my; nextState=next; turn =t;}
	public void run(){
		for (int i = 1; i<=turn; i++){
			synchronized(p){//完成字符串的最终装配、输出、初始化
				p.readWrite(myState,nextState," 第 "+i+" 批完成\n");
				System.out.print(p.getData());//输出最终的字符串
				p.init();//产品线初始化
			}
			try{sleep(1);}catch(InterruptedException e){;}
		}
	}
}
public class Ch_5_9{
	public static void main (String[] args){
		String str="笔记本：";
		String[] s1={"黑色","银色","蓝色","红色"};
		String[] s2={"i3","i5","i7"};
		String[] s3={"核显","独显"};
		String[] s4={" 4G"," 8G","16G"};
		ProductLine p=new ProductLine(str);
		ProcessNode j,y,b,d; //代表甲、乙、丙、丁四个线程
		j=new ProcessNode(p,s1,0,1,5);		y=new ProcessNode(p,s2,1,2,5);
		b=new ProcessNode(p,s3,2,3,5);		d=new ProcessNode(p,s4,3,4,5);
		EndNode end=new EndNode(p,4,0,5);
		System.out.print("开始生产：\n");
		j.start();y.start();b.start();d.start();end.start();
		try{j.join();y.join();b.join();d.join();end.join();}
			catch(InterruptedException e){;}
		System.out.print("生产结束。\n");
	}
}

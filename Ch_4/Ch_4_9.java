/*功能：模拟浦丰投针实验（具体数学机理请查阅资料，或网上查阅“浦丰投针实验”）
 *原理：将长为d的针投向一组间距为2d的平行线，
 *      假设实验次数（即投针次数）为tz，相交次数为xj
 *      则圆周率=tz/xj
  **/
import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
class PFTest extends JFrame implements ActionListener{
	private JButton bt_begin,bt_exit;        //开始、退出按钮
	private JTextField jtf_testNum,jtf_crossNum,jtf_pi; //试验次数、相交次数、圆周率
	private int testNum,crossNum; private double pi;
	TouZhenPanel testPanel;//绘制投针实验的面板

	public PFTest(){
		super("浦丰投针实验模拟");		setSize(880,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p1=new JPanel(); 	p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l1=new JLabel("实验次数：");	jtf_testNum=new JTextField("2122",10);
		JLabel l2=new JLabel("相交次数：");	jtf_crossNum=new JTextField("0",10);
		JLabel l3=new JLabel("圆周率：");	 jtf_pi=new JTextField("0",10);
		bt_begin=new JButton("开始");		bt_exit=new JButton("退出");
		p1.add(l1); p1.add(jtf_testNum); p1.add(l2);  p1.add(jtf_crossNum);
		p1.add(l3); p1.add(jtf_pi); p1.add(bt_begin);  p1.add(bt_exit);
		jtf_crossNum.setEditable(false);   jtf_pi.setEditable(false);
		add(p1,BorderLayout.NORTH);
		testPanel=new TouZhenPanel(); 	add(testPanel,BorderLayout.CENTER);
		setVisible(true);
		bt_begin.addActionListener(this);	bt_exit.addActionListener(this);
		jtf_testNum.addActionListener(this);//在文本框键入回车也会触发Action事件
	}
    public void actionPerformed(ActionEvent e){
		if(e.getSource()==bt_exit) {System.exit(0);}
		if(e.getSource()==bt_begin||e.getSource()==jtf_testNum){
			try{  testNum=Integer.valueOf(jtf_testNum.getText());}
				catch(Exception ex){ jtf_testNum.setText("输入错误"); return; }
			if(testNum<=0){ jtf_crossNum.setText("0"); jtf_pi.setText("0"); return; }
			testPanel.repaint();    //重绘投针实验界面
		}
	}
	class TouZhenPanel extends JPanel{//内部类：投针实验的显示面板
		//投针面板的上下左右边距、线长width、可画平行线的高度high、针长、线距
		private int leftMargin,rightMargin,topMargin,bottomMargin,width,high,neddleSize,lineGap;
		private void init(int L, int R, int T, int B,int D){//设定边距、线长、线间距、针长
			leftMargin=L; rightMargin=R; topMargin=T; bottomMargin=B;
	        width=getWidth()-rightMargin-leftMargin;//绘图区域的横向长度
	        high=getHeight()-topMargin-bottomMargin;//绘图区域的纵向高度
	        neddleSize=D; lineGap=2*neddleSize;
		}
		private boolean judgeXJ(double y, double y1){//判断针、线是否相交
			if (y<y1){double t=y; y=y1; y1=t;} //使得y>=y1，y,y1是直线两端点的纵坐标，
			for(int i=topMargin; i<=high+topMargin+neddleSize;i=i+lineGap)  //i是平行线的纵坐标
				if(i>=y1 && i<=y) return true;//若i位于y,y1之间，定然相交
			return false;
		}
	    protected void paintComponent(Graphics g) {//被repaint()自动调用：绘制投针试验效果图
	        super.paintComponent(g); //此句用于对UI基础组件更新，如背景着色等，读者可试试缺少此句的效果
	        setBackground(Color.white);//将面板的背景色设为白色
	        init(30,30,30,30,10); //不要放在构造函数中，这样在主窗体缩放时，线长会相应变化
			g.setColor(Color.red); //将绘图线的颜色设为红色
			for(int i=topMargin; i<=high+topMargin+neddleSize;i=i+lineGap) //划一组等距离的平行线
				g.drawLine(leftMargin,i,width+leftMargin,i);
			g.setColor(Color.black); //将绘图线的颜色设为黑色
	        Random r=new Random();   crossNum=0; //初始化相交次数

			double x,y,x1,y1,angle; //针的两端坐标<x,y>、<x1,y1>、针的角度、临时变量
			/*策略：先随机产生坐标<x,y>和角度angle，之后从坐标沿角度长度为针长的线，
			 *     根据<x,y>、angle、针长计算出另一坐标<x1,y1>
			 *     若某平行线位于y,y1之间，则判为针与线相交。
			 **/
			for(int i=1; i<testNum; i++){
				//由于线左侧有左边界，故坐标不是从0开始
				//另外，科学实验时，尽量产生double型随机数，否则严重影响实验精度
				x=leftMargin+r.nextInt(width)+r.nextDouble();
				y=topMargin+r.nextInt(high)+r.nextDouble();
				angle=r.nextInt(360)+r.nextDouble();//产生角度范围
				x1=x+neddleSize*Math.cos(angle); y1=y+neddleSize*Math.sin(angle);
				g.drawLine((int)x,(int)y,(int)x1,(int)y1);//绘制针
				if (judgeXJ(y,y1)) crossNum++;
			}
			jtf_crossNum.setText(String.valueOf(crossNum));
			jtf_pi.setText(String.format("%7.4f",testNum*1.0/crossNum));// *1.0旨在避免整除
		}
	}
}
class App{
	public static void main (String[] args) {
		SetDefaultFont.setAll(new Font("微软雅黑",Font.BOLD,26));
		new PFTest();	}
}
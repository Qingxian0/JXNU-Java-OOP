/*���ܣ�ģ���ַ�Ͷ��ʵ�飨������ѧ������������ϣ������ϲ��ġ��ַ�Ͷ��ʵ�顱��
 *ԭ������Ϊd����Ͷ��һ����Ϊ2d��ƽ���ߣ�
 *      ����ʵ���������Ͷ�������Ϊtz���ཻ����Ϊxj
 *      ��Բ����=tz/xj
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
	private JButton bt_begin,bt_exit;        //��ʼ���˳���ť
	private JTextField jtf_testNum,jtf_crossNum,jtf_pi; //����������ཻ������Բ����
	private int testNum,crossNum; private double pi;
	TouZhenPanel testPanel;//����Ͷ��ʵ������

	public PFTest(){
		super("�ַ�Ͷ��ʵ��ģ��");		setSize(880,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p1=new JPanel(); 	p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l1=new JLabel("ʵ�������");	jtf_testNum=new JTextField("2122",10);
		JLabel l2=new JLabel("�ཻ������");	jtf_crossNum=new JTextField("0",10);
		JLabel l3=new JLabel("Բ���ʣ�");	 jtf_pi=new JTextField("0",10);
		bt_begin=new JButton("��ʼ");		bt_exit=new JButton("�˳�");
		p1.add(l1); p1.add(jtf_testNum); p1.add(l2);  p1.add(jtf_crossNum);
		p1.add(l3); p1.add(jtf_pi); p1.add(bt_begin);  p1.add(bt_exit);
		jtf_crossNum.setEditable(false);   jtf_pi.setEditable(false);
		add(p1,BorderLayout.NORTH);
		testPanel=new TouZhenPanel(); 	add(testPanel,BorderLayout.CENTER);
		setVisible(true);
		bt_begin.addActionListener(this);	bt_exit.addActionListener(this);
		jtf_testNum.addActionListener(this);//���ı������س�Ҳ�ᴥ��Action�¼�
	}
    public void actionPerformed(ActionEvent e){
		if(e.getSource()==bt_exit) {System.exit(0);}
		if(e.getSource()==bt_begin||e.getSource()==jtf_testNum){
			try{  testNum=Integer.valueOf(jtf_testNum.getText());}
				catch(Exception ex){ jtf_testNum.setText("�������"); return; }
			if(testNum<=0){ jtf_crossNum.setText("0"); jtf_pi.setText("0"); return; }
			testPanel.repaint();    //�ػ�Ͷ��ʵ�����
		}
	}
	class TouZhenPanel extends JPanel{//�ڲ��ࣺͶ��ʵ�����ʾ���
		//Ͷ�������������ұ߾ࡢ�߳�width���ɻ�ƽ���ߵĸ߶�high���볤���߾�
		private int leftMargin,rightMargin,topMargin,bottomMargin,width,high,neddleSize,lineGap;
		private void init(int L, int R, int T, int B,int D){//�趨�߾ࡢ�߳����߼�ࡢ�볤
			leftMargin=L; rightMargin=R; topMargin=T; bottomMargin=B;
	        width=getWidth()-rightMargin-leftMargin;//��ͼ����ĺ��򳤶�
	        high=getHeight()-topMargin-bottomMargin;//��ͼ���������߶�
	        neddleSize=D; lineGap=2*neddleSize;
		}
		private boolean judgeXJ(double y, double y1){//�ж��롢���Ƿ��ཻ
			if (y<y1){double t=y; y=y1; y1=t;} //ʹ��y>=y1��y,y1��ֱ�����˵�������꣬
			for(int i=topMargin; i<=high+topMargin+neddleSize;i=i+lineGap)  //i��ƽ���ߵ�������
				if(i>=y1 && i<=y) return true;//��iλ��y,y1֮�䣬��Ȼ�ཻ
			return false;
		}
	    protected void paintComponent(Graphics g) {//��repaint()�Զ����ã�����Ͷ������Ч��ͼ
	        super.paintComponent(g); //�˾����ڶ�UI����������£��米����ɫ�ȣ����߿�����ȱ�ٴ˾��Ч��
	        setBackground(Color.white);//�����ı���ɫ��Ϊ��ɫ
	        init(30,30,30,30,10); //��Ҫ���ڹ��캯���У�����������������ʱ���߳�����Ӧ�仯
			g.setColor(Color.red); //����ͼ�ߵ���ɫ��Ϊ��ɫ
			for(int i=topMargin; i<=high+topMargin+neddleSize;i=i+lineGap) //��һ��Ⱦ����ƽ����
				g.drawLine(leftMargin,i,width+leftMargin,i);
			g.setColor(Color.black); //����ͼ�ߵ���ɫ��Ϊ��ɫ
	        Random r=new Random();   crossNum=0; //��ʼ���ཻ����

			double x,y,x1,y1,angle; //�����������<x,y>��<x1,y1>����ĽǶȡ���ʱ����
			/*���ԣ��������������<x,y>�ͽǶ�angle��֮��������ؽǶȳ���Ϊ�볤���ߣ�
			 *     ����<x,y>��angle���볤�������һ����<x1,y1>
			 *     ��ĳƽ����λ��y,y1֮�䣬����Ϊ�������ཻ��
			 **/
			for(int i=1; i<testNum; i++){
				//�������������߽磬�����겻�Ǵ�0��ʼ
				//���⣬��ѧʵ��ʱ����������double�����������������Ӱ��ʵ�龫��
				x=leftMargin+r.nextInt(width)+r.nextDouble();
				y=topMargin+r.nextInt(high)+r.nextDouble();
				angle=r.nextInt(360)+r.nextDouble();//�����Ƕȷ�Χ
				x1=x+neddleSize*Math.cos(angle); y1=y+neddleSize*Math.sin(angle);
				g.drawLine((int)x,(int)y,(int)x1,(int)y1);//������
				if (judgeXJ(y,y1)) crossNum++;
			}
			jtf_crossNum.setText(String.valueOf(crossNum));
			jtf_pi.setText(String.format("%7.4f",testNum*1.0/crossNum));// *1.0ּ�ڱ�������
		}
	}
}
class App{
	public static void main (String[] args) {
		SetDefaultFont.setAll(new Font("΢���ź�",Font.BOLD,26));
		new PFTest();	}
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GUI_All extends JFrame{
    private JTextField text1;
    private JButton b_open,b_close;
	class A extends WindowAdapter{  //����֧�ִ��ڹر�
		public void windowClosing(WindowEvent e){System.exit(0);} 	
	}
    public GUI_All() {
        super("���Ǵ���");
        this.setSize(600,200);               
        //this.setBackground(Color.lightGray);
        //this.setLocation(300,240);          
        Font font1=new Font("����",Font.PLAIN,16);
        setFont(font1);
        
        this.setLayout(new GridLayout(2,2));
        
        JPanel p1=new JPanel();
        p1.add(new JButton("��ť1"));
        p1.add(new Label("��ǩ1����ť1�����1��"));
        Label l=new Label("��ǩ2��������Ͽ�");
        l.setFont(font1);
        p1.add(l);
        Object[] gj={"�й�","����","���"};
        JComboBox j=new JComboBox(gj);
        j.setEditable(true);
        p1.add(j);
        p1.setBackground(Color.white);
        this.add(p1);
         
        JPanel p2=new JPanel();
        p2.add(new JTextField("�ı���ֻ������1������",15));
        p2.add(new JTextArea("�ı�������\n�����������"));
        p2.add(new Label("��ǩ3���ı�����ı��������2��"));
        p2.setBackground(Color.lightGray);
        this.add(p2);
        
        JPanel p3=new JPanel();
        ButtonGroup btGroup = new ButtonGroup();
        JRadioButton r_male = new JRadioButton("��",true);
        JRadioButton r_female = new JRadioButton("Ů",false);
        btGroup.add(r_male);btGroup.add(r_female);
        p3.add(r_male);  p3.add(r_female);
        p3.add(new Label("��ǩ4����Ů��ѡťֻ��ѡ��һ��"));
        p3.setBackground(Color.pink);
        this.add(p3);

        JPanel p4=new JPanel();
        JCheckBox xq1 = new JCheckBox("ϲ������",true);
        JCheckBox xq2 = new JCheckBox("ϲ������",true);
        p4.add(xq1);  p4.add(xq2);
        p4.add(new Label("��ǩ5����ѡ����Զ�ѡ"));
        p4.setBackground(Color.gray);
        this.add(p4);

        this.setVisible(true);
        addWindowListener(new A());  //������ڹرհ�ť���ν�������
           //ʹ�����涨����ڲ���
    }
    
    public static void main(String[] args) {
    	new GUI_All();
    }
}

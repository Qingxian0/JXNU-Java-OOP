import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GUI_All extends JFrame{
    private JTextField text1;
    private JButton b_open,b_close;
	class A extends WindowAdapter{  //用于支持窗口关闭
		public void windowClosing(WindowEvent e){System.exit(0);} 	
	}
    public GUI_All() {
        super("我是窗口");
        this.setSize(600,200);               
        //this.setBackground(Color.lightGray);
        //this.setLocation(300,240);          
        Font font1=new Font("宋体",Font.PLAIN,16);
        setFont(font1);
        
        this.setLayout(new GridLayout(2,2));
        
        JPanel p1=new JPanel();
        p1.add(new JButton("按钮1"));
        p1.add(new Label("标签1：按钮1在面板1中"));
        Label l=new Label("标签2：国籍组合框");
        l.setFont(font1);
        p1.add(l);
        Object[] gj={"中国","美国","俄国"};
        JComboBox j=new JComboBox(gj);
        j.setEditable(true);
        p1.add(j);
        p1.setBackground(Color.white);
        this.add(p1);
         
        JPanel p2=new JPanel();
        p2.add(new JTextField("文本框只能输入1行数据",15));
        p2.add(new JTextArea("文本区可以\n输入多行数据"));
        p2.add(new Label("标签3：文本框和文本区在面板2中"));
        p2.setBackground(Color.lightGray);
        this.add(p2);
        
        JPanel p3=new JPanel();
        ButtonGroup btGroup = new ButtonGroup();
        JRadioButton r_male = new JRadioButton("男",true);
        JRadioButton r_female = new JRadioButton("女",false);
        btGroup.add(r_male);btGroup.add(r_female);
        p3.add(r_male);  p3.add(r_female);
        p3.add(new Label("标签4：男女单选钮只能选中一个"));
        p3.setBackground(Color.pink);
        this.add(p3);

        JPanel p4=new JPanel();
        JCheckBox xq1 = new JCheckBox("喜欢篮球",true);
        JCheckBox xq2 = new JCheckBox("喜欢下棋",true);
        p4.add(xq1);  p4.add(xq2);
        p4.add(new Label("标签5：复选框可以多选"));
        p4.setBackground(Color.gray);
        this.add(p4);

        this.setVisible(true);
        addWindowListener(new A());  //点击窗口关闭按钮，课结束程序
           //使用上面定义的内部类
    }
    
    public static void main(String[] args) {
    	new GUI_All();
    }
}

import java.awt.*;
import javax.swing.*;
public class FlowLayout_Example extends JFrame{
    public FlowLayout_Example() {
        super("FlowLayout����RIGHT���뷽ʽ����ʾ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���õ���رհ�ť
    	setSize(300,100);               
        setLayout(new FlowLayout(FlowLayout.LEFT)); //.RIGHT         
        Font font1=new Font("����",Font.PLAIN,20);
        
        add(new Label("��ǩ1"));
        JButton jbt=new JButton("�ߵİ�ť"); add(jbt);
        jbt.setFont(font1);
        add(new Label("��ǩ2")); add(new Label("��ǩ3")); add(new Label("��ǩ4"));
        setVisible(true);
    }
    public static void main(String[] args) { new FlowLayout_Example();  }
}
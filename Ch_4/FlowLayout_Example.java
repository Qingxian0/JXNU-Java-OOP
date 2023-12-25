import java.awt.*;
import javax.swing.*;
public class FlowLayout_Example extends JFrame{
    public FlowLayout_Example() {
        super("FlowLayout布局RIGHT对齐方式界面示例");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭按钮
    	setSize(300,100);               
        setLayout(new FlowLayout(FlowLayout.LEFT)); //.RIGHT         
        Font font1=new Font("宋体",Font.PLAIN,20);
        
        add(new Label("标签1"));
        JButton jbt=new JButton("高的按钮"); add(jbt);
        jbt.setFont(font1);
        add(new Label("标签2")); add(new Label("标签3")); add(new Label("标签4"));
        setVisible(true);
    }
    public static void main(String[] args) { new FlowLayout_Example();  }
}
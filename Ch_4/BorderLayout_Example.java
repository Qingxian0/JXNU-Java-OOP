import java.awt.*;  import javax.swing.*;
public class BorderLayout_Example extends JFrame{
    public BorderLayout_Example() {
        super("BorderLayout布局界面示例");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭按钮
    	setSize(250,150);               
        setLayout(new BorderLayout());
      	//add(new JButton("EAST"),BorderLayout.EAST);
      	//add(new JButton("NORTH"),BorderLayout.NORTH);
      	add(new JButton("WEST"),BorderLayout.WEST);
      	add(new JButton("SOUTH"),BorderLayout.SOUTH);
      	add(new JButton("CENTER"),BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args) { new BorderLayout_Example(); }
}

import java.awt.*;  import javax.swing.*;
public class GridLayout_Example extends JFrame{
    public GridLayout_Example() {
        super("GridLayout布局界面示例");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭按钮
    	setSize(300,200);               
        setLayout(new GridLayout(3,2,5,10));
        for(int i=1; i<6; i++)
          add(new JButton("按钮_"+i));
        setVisible(true);
    }
    public static void main(String[] args) { new GridLayout_Example(); }
}

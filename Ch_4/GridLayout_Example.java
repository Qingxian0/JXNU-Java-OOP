import java.awt.*;  import javax.swing.*;
public class GridLayout_Example extends JFrame{
    public GridLayout_Example() {
        super("GridLayout���ֽ���ʾ��");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���õ���رհ�ť
    	setSize(300,200);               
        setLayout(new GridLayout(3,2,5,10));
        for(int i=1; i<6; i++)
          add(new JButton("��ť_"+i));
        setVisible(true);
    }
    public static void main(String[] args) { new GridLayout_Example(); }
}

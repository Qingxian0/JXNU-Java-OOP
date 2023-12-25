/*
GUI编程基本步骤：
1. 构造界面：造组件对象放入界面：造容器对象p + 造组件对象obj + p.add(obj)
2. 美化界面：对容器组件合理布局，即排整齐；如逐行排列；
3. 响应界面：给界面加入事件处理机制。如：点击“确定”，可验证登录的合法性
 */
/*  第一题
    Jbutton使用getText()获得按钮上的文本信息。
    假设窗体中只有一个按钮“点击”，当点击1次后，先用getActionCommand()获取文本信息，
    之后将按钮上的文字更改为“点击1次”。重复此过程，理解getActionCommand()的效果。
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class T1 extends JFrame implements ActionListener {
    private JTextField textField; // 文本框
    private JButton button; // 按钮
    private int clickTime = 0;

    public T1() {
        super("第一题");
        this.setSize(500, 100);
        this.setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        textField = new JTextField(5);
        button = new JButton("点击");
        this.add(textField);
        this.add(button);
        textField.addActionListener(this);
        button.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("点击") ||
        e.getActionCommand().equals("点击" + clickTime + "次")){
            clickTime++;
            String buttonText = "点击" + clickTime + "次";
            button.setText(buttonText); // 更新按钮文本
            System.out.println(button.getActionCommand());
        }

    }

    public static void main(String[] args) {
        new T1();
    }
}

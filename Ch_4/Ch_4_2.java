import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;//处理点击按钮事件必须要实现的接口
import java.awt.event.ActionEvent;

public class Ch_4_2 extends JFrame implements ActionListener {
    private JButton b_ok, b_exit;      //确定和退出按钮的名称
    private JLabel t_la;               //用于显示信息的标签
    private JTextField userName;     //用户名文本框
    private JPasswordField password;  //密码框

    public Ch_4_2() {  //以下为GUI界面设计部分
        super("第一个简单界面");
        setSize(500, 100);
        setBackground(Color.lightGray);
        setLocation(300, 240);
        setLayout(new FlowLayout());
        userName = new JTextField(5);
        password = new JPasswordField(5);
        add(new JLabel("用户名："));
        add(userName);
        add(new JLabel("密  码："));
        add(password);
        b_ok = new JButton("确定");
        add(b_ok);
        b_exit = new JButton("退出");
        add(b_exit);
        t_la = new JLabel(" ");
        add(t_la); //加入一个临时标签，用来显示信息
        setVisible(true);

        //以下建立事件源与处理者之间的关联：两个按钮使用同一个处理者对象
        b_exit.addActionListener(this); //建立关联，this即Ch_4_2窗体对象
        b_ok.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {  //监听器的事件处理程序
        if (e.getSource() == b_exit) System.exit(0);  //用getSource()方法识别事件源
        if (e.getActionCommand().equals("确定")) {   //用另一种方法识别事件源
            String keyText = String.valueOf(password.getPassword());
            if (userName.getText().equals("abc") && keyText.equals("1234"))
                t_la.setText("欢迎您，abc!");
            else
                t_la.setText("用户名或密码错！");
            setVisible(true);      //请读者自行测试此句之作用
        }
    }

    public static void main(String[] args) {
        new Ch_4_2();
    }
}

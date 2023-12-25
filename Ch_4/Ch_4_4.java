import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;    //键盘事件适配器类
import java.awt.event.KeyEvent;      //注：适配器类仅实现接口，故仍需导入KeyEvent类

public class Ch_4_4 extends JFrame {
    private JButton b_ok, b_exit;      //确定和退出按钮的名称
    private JLabel t_la;               //用于显示信息的标签
    private JTextField userName;     //用户名文本框
    private JPasswordField password;  //密码框

    class Controller extends KeyAdapter implements ActionListener {//内部类：控制者

        private void click_btOk() {//点击确定
            String keyText = String.valueOf(password.getPassword());
            if (userName.getText().equals("abc") && keyText.equals("1234"))
                t_la.setText("欢迎您，abc!");
            else
                t_la.setText("用户名或密码错！");
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {  //点击按钮的处理
            if (e.getSource() == b_exit) System.exit(0);
            if (e.getActionCommand().equals("确定")) click_btOk();
        }

        //下面3个方法由KeyListener接口提供
        public void keyPressed(KeyEvent e) {  //面向ASII码处理
            if (e.getKeyChar() == 'o' && e.getSource() == b_exit) System.exit(0);
            if (e.getKeyChar() == '\n')
                if (e.getSource() == userName) password.requestFocusInWindow();
                else if (e.getSource() == password) b_ok.requestFocusInWindow();
                else if (e.getSource() == b_ok) click_btOk();
        }
    }

    public Ch_4_4() {  //以下为GUI界面设计部分
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

        Controller ctrl = new Controller();  //创建控制者（即处理者）对象
        b_exit.addActionListener(ctrl);
        b_ok.addActionListener(ctrl);
        b_ok.addKeyListener(ctrl);
        userName.addKeyListener(ctrl);
        password.addKeyListener(ctrl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭按钮：退出程序
    }

    public static void main(String[] args) {
        new Ch_4_4();
    }
}

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;      //点击按钮、菜单等产生的事件
import java.awt.event.ActionListener;   //处理点击按钮事件必须要实现的接口
import java.awt.event.KeyEvent;         //敲击键盘产生的事件
import java.awt.event.KeyListener;      //处理键盘事件必须要实现的接口
import java.awt.event.WindowEvent;      //窗体事件
import java.awt.event.WindowListener;   //处理窗口事件必须要实现的接口

public class Ch_4_3 extends JFrame implements ActionListener, KeyListener, WindowListener {
    private JButton b_ok, b_exit;      //确定和退出按钮的名称
    private JLabel t_la;               //用于显示信息的标签
    private JTextField userName;     //用户名文本框
    private JPasswordField password;  //密码框

    public Ch_4_3() {  //以下为GUI界面设计部分
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
        b_exit.addActionListener(this);
        b_ok.addActionListener(this);
        b_ok.addKeyListener(this);  //按钮需要注册键盘监听器接口，才能对按键响应
        userName.addKeyListener(this);
        password.addKeyListener(this);
        this.addWindowListener(this);//由于点击的是Ch_4_3对象上的X，即事件源是this
    }

    private void click_btOk() {//为避免重复书写，把点击确定按钮执行的动作独立出来
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
    public void keyTyped(KeyEvent e) {//产生unicode字符时触发
        if (e.getKeyChar() == '\n')
            if (e.getSource() == userName) password.requestFocusInWindow();//将焦点转移至密码框
            else if (e.getSource() == password) b_ok.requestFocusInWindow();
            else if (e.getSource() == b_ok) click_btOk();
    }

    public void keyPressed(KeyEvent e) {
        ;
    }//按下键钮时触发

    public void keyReleased(KeyEvent e) {
        ;
    }//释放键钮时触发

/*下面代码可监控Ctrl+A+B，解开注释前，注意将上面代码中对应方法注释
boolean  aIsPress, bIsPress;
public void keyPressed(KeyEvent e){  //按下按键时触发
    if( e.getKeyCode()==KeyEvent.VK_A && aIsPress==false ) aIsPress=true;
    if(e.getKeyCode()==KeyEvent.VK_B && bIsPress==false) bIsPress=true;
    if( e.isControlDown()  && aIsPress && bIsPress )
        System.out.print("\n Ctrl+A+B is pressed!");
}
public void keyReleased(KeyEvent e){//释放按键时触发
    if(e.getKeyCode()==KeyEvent.VK_A && aIsPress==true)aIsPress=false;
    if(e.getKeyCode()==KeyEvent.VK_B && bIsPress==true)bIsPress=false;
}
*/

    //下面7个方法由WindowListener接口提供
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
        ;
    }

    public void windowActivated(WindowEvent e) {
        ;
    }

    public void windowDeactivated(WindowEvent e) {
        ;
    }

    public void windowClosed(WindowEvent e) {
        ;
    }

    public void windowIconified(WindowEvent e) {
        ;
    }

    public void windowDeiconified(WindowEvent e) {
        ;
    }

    public static void main(String[] args) {
        new Ch_4_3();
    }
}

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;

public class Ch_4_1 extends JFrame {
    public Ch_4_1() {
        super("第一个简单界面");
        setSize(400, 100);                 //设置框架宽度和高度
        setBackground(Color.lightGray);   //设置框架的背景色
        setLocation(300, 240);      //指定框架左上角的显示位置，(0,0)代表屏幕的左上角
        setLayout(new FlowLayout());      //指定框架的默认布局方式
        /*注：若未指定布局，组件将自动占满整个容器，后面加入的组件将会遮盖住前面的组件*/
        add(new JLabel("用户名："));            //创建并加入新标签
        add(new JTextField(5));               //创建并加入长度为5列的文本框
        add(new JLabel("密  码："));
        add(new JPasswordField(5));          //创建并加入长度为5列的密码框
        add(new JButton("确定"));             //创建并加入标签为“确定”的按钮
        add(new JButton("退出"));
        setVisible(true);  //设置窗体为可见。注：若无此句，窗体将不会被显示出来
    }

    public static void main(String[] args) {
        new Ch_4_1();
    }
}

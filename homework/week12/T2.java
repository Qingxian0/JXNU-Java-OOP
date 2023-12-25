/*  第二题
    给登录界面加入次数限制，如：用户名或密码输错三次，则屏蔽输入框。
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T2 extends JFrame implements ActionListener {
    private JTextField userName; // 用户名
    private JPasswordField password; // 密码
    private JButton loginButton; // 登录按钮
    private JLabel infoLabel;               //用于显示信息的标签
    private int wrongCount = 0;

    public T2(){
        super("第二题");
        this.setSize(500, 100);
        this.setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userName = new JTextField(5);
        password = new JPasswordField(5);
        loginButton = new JButton("登录");
        infoLabel = new JLabel(" ");
        this.add(new JLabel("用户名"));
        this.add(userName);
        this.add(new JLabel("密码"));
        this.add(password);
        this.add(loginButton);
        this.add(infoLabel);
        loginButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("登录")){
            String keyText = String.valueOf(password.getPassword()); // 返回的是一个char[]，所以要转换为String
            if(userName.getText().equals("abc") && keyText.equals("1234")){
                infoLabel.setText("欢迎您，abc!");
                wrongCount = 0;
            }else{
                wrongCount++;
                if(wrongCount == 3){
                    this.dispose();
                }else {
                    infoLabel.setText("用户名或密码错！还有" + (3 - wrongCount) + "次机会");
                }
            }
        }
    }

    public static void main(String[] args) {
        new T2();
    }
}

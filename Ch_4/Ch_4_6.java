import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonComputer extends JFrame implements ActionListener {
    private JTextField op1, op2, result;  //操作数和结果
    private JLabel opChar;   //操作符按钮
    private JButton bt_c;   //清除按钮
    private String[] bt_Label = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "*", "/", "="};
    private JButton[] bt = new JButton[bt_Label.length];
    private boolean isOp1 = true;

    //为true，表示按钮数字应追加到op1中，否则追加到op2中。
    //当点击+-*/按钮时，isOp1置为false，点击=按钮时，置为true，其它按钮无影响
    public ButtonComputer() {
        super("整数计算器");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭按钮
        JPanel p1 = new JPanel();
        op1 = new JTextField("", 5);
        opChar = new JLabel("?");
        op2 = new JTextField("", 5);
        bt_c = new JButton("C");
        result = new JTextField("", 10);
        p1.add(op1);
        p1.add(opChar);
        p1.add(op2);
        p1.add(new JLabel("="));
        p1.add(result);
        p1.add(bt_c);
        op1.setEditable(false);
        op2.setEditable(false);
        result.setEditable(false);
        bt_c.addActionListener(this);
        add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3, 5, 5, 5));
        for (int i = 0; i < bt.length; i++) { //增加各种按钮
            bt[i] = new JButton(bt_Label[i]);
            bt[i].addActionListener(this);
            p2.add(bt[i]);
        }
        add(p2, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("C")) {//点击“C”按钮
            op1.setText("");
            op2.setText("");
            opChar.setText("?");
            result.setText("");
            isOp1 = true;
            return; //用return 减少if嵌套层次，增强可读性
        }
        //判断点击的按钮是否是数字，s1.indexOf(s2)，判断字符串s2是否是s1的子串
        if ("0123456789".indexOf(e.getActionCommand()) >= 0) {
            if (isOp1 && op1.getText().length() < 5)//最多只能输入5个数字
                op1.setText(op1.getText() + e.getActionCommand());
            else if (!isOp1 && op2.getText().length() < 5)
                op2.setText(op2.getText() + e.getActionCommand());
            return;
        }
        if ("+-*/".indexOf(e.getActionCommand()) >= 0) { //点击“+-*/”按钮
            opChar.setText(e.getActionCommand());
            isOp1 = false;
            return;
        }
        if (e.getActionCommand().equals("=")) {    //点击“=”按钮
            isOp1 = true;
            if (op1.getText().equals("") || op2.getText().equals("")) {
                result.setText("不能计算！");
                return;
            }
            int x, y;
            char op = opChar.getText().charAt(0);
            x = Integer.parseInt(op1.getText());//将文本框数字变成整数
            y = Integer.parseInt(op2.getText());
            result.setText(compute(x, y, op));
        }
    }

    private String compute(int x, int y, char op) {
        if (op == '/' && y == 0) return "除零错！";
        long r = 0;
        switch (op) {
            case '+':
                r = x + y;
                break;
            case '-':
                r = x - y;
                break;
            case '*':
                r = x * y;
                break;
            case '/':
                r = x / y;
                break;
        }
        return String.valueOf(r);
    }
}

public class Ch_4_6 {
    public static void main(String[] args) {
        SetDefaultFont.setAll(new Font("宋体", Font.BOLD, 30));
        new ButtonComputer();
    }
}

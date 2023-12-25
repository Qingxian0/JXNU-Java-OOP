import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*  第四题
    做一个简单的计算器，[1][+][2]【=】[ 3] 【退出】 其中[ ]表示文本框，【=】表示按钮，
    点击按钮后计算出结果。3是文本框（enable(false)） 要求：
    （1）当产生除零错、数据格式不正确时，借助标签显示出错信息；
    （2）自定义异常类NullTextFieldException，
        当第一个文本框为空或第二个文本框为空时抛出此异常，并借助标签显示出错信息
 */

class NullTextFieldException extends Exception { // 当操作数或运算符文本框为空时抛出此异常
    public NullTextFieldException(String msg) {
        super(msg);
    }
}

public class T4 extends JFrame {
    private JButton calculate; // 在 = 和clear之间切换
    private JTextField op1, op2, result, opChar, decimalPlaces; // 操作数1、2、运算结果、运算符、小数位数

    public T4() {
        super("第四题");
        this.setSize(1000, 120);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        // 先将各组件加入p1，之后将p1加入窗体
        op1 = new JTextField(10);
        op2 = new JTextField(10);
        opChar = new JTextField(2);
        result = new JTextField(25);
        result.setEditable(false);
        calculate = new JButton("  =  ");
        p1.add(op1);
        p1.add(opChar);
        p1.add(op2);
        p1.add(calculate);
        p1.add(result);
        this.add(p1);

        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        String s = "使用说明，依次输入： 3.1  +  2.6 ，运算符支持 +  -  *  /。保留:";
        p2.add(new JLabel("保留:"));
        decimalPlaces = new JTextField(3);
        p2.add(decimalPlaces);
        decimalPlaces.setText("2");
        p2.add(new JLabel("位小数"));

        this.add(p2);


        Controller controller = new Controller(); // 创建控制者（即处理者）对象
        calculate.addActionListener(controller);
        calculate.addKeyListener(controller);
        op1.addKeyListener(controller);
        op2.addKeyListener(controller);
        opChar.addKeyListener(controller);
        decimalPlaces.addKeyListener(controller);
        this.setVisible(true);
    }

    class Controller extends KeyAdapter implements ActionListener{
        private void clickEq() { // 点击=
            try {
                if (op1.getText().equals("") || op2.getText().equals("") || opChar.getText().equals("")) {
                    throw new NullTextFieldException("有空的文本框，不能计算！"); // Throw the custom exception with an error message
                }// 抛出此异常，借助标签显示出错信息

                double x, y;
                int dp; // 小数位数
                char op = opChar.getText().charAt(0);
                x = Double.parseDouble(op1.getText()); // 将文本框数字变成double
                y = Double.parseDouble(op2.getText());
                dp = Integer.parseInt(decimalPlaces.getText());
                result.setText(compute(x, y, op, dp));

                calculate.setText("Clear");
                op1.setEditable(false);
                op2.setEditable(false);
                opChar.setEditable(false);
                decimalPlaces.setEditable(false);
            } catch (NullTextFieldException e) {
                result.setText(e.getMessage());
            }
        }



        private void clear(){ // 点击clear
            op1.setText("");
            op2.setText("");
            result.setText("");
            opChar.setText("");
            decimalPlaces.setText("2");
            calculate.setText("  =  ");
            op1.setEditable(true);
            op2.setEditable(true);
            opChar.setEditable(true);
            decimalPlaces.setEditable(true);
            op1.requestFocusInWindow();
        }

        private String compute(double x, double y, char op, int dp) {
            if (op == '/' && y == 0)
                return "除零错！";
            double res = 0;
            if (op == '+')
                res = x + y;
            else if (op == '-')
                res = x - y;
            else if (op == '*')
                res = x * y;
            else if (op == '/')
                res = x / y;
            return String.format("%20." + dp + "f", res);// 总宽度20，dotN为小数位，右对齐
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == calculate){
                if(e.getActionCommand().equals("  =  ")){
                    clickEq();
                }else{
                    clear();
                }
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {// 可以屏蔽字符输入
            char c = e.getKeyChar();
            if (c == '\n') {// 移动焦点
                if (e.getSource() == op1)
                    opChar.requestFocusInWindow();
                else if (e.getSource() == opChar)
                    op2.requestFocusInWindow();
                else if (e.getSource() == op2) {
                    clickEq();
                    calculate.requestFocusInWindow();
                } else if (e.getSource() == calculate && calculate.getText().equals("Clear"))
                    clear();
                return;
            }
            if (e.getSource() == opChar) {// 让操作符框只能输入一个运算符
                e.consume();
                if (c == '+' || c == '-' || c == '*' || c == '/') // 然后如果是运算符，就直接对文本框赋值
                {
                    opChar.setText("" + c);
                    result.setText("");
                    return;
                } // 注：设置后，还有文本框默认的键盘事件
                result.setText("运算符数据格式不正确");
                Toolkit.getDefaultToolkit().beep(); // 蜂鸣器声响，其中Toolkit位于awt包
                return;
            }
            if (e.getSource() == decimalPlaces) {// 让小数位只能输入一个数字
                e.consume();
                if (c >= '0' && c <= '9') {
                    decimalPlaces.setText("" + c);
                    return;
                }
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            if (e.getSource() == op1 || e.getSource() == op2) {// 如num1、num2只能输入数字和.
                if (c >= '0' && c <= '9'){
                    result.setText("");
                    return; // 如果是数字，就正常输入
                }
                result.setText("输入的不是数字");
                e.consume();// 在keyTyped()中方有效果
                Toolkit.getDefaultToolkit().beep(); // 蜂鸣器声响，其中Toolkit位于awt包
            }
        }
    }



    public static void main(String[] args) {
        new T4();
    }

}

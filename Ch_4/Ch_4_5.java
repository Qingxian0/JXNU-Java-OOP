import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NoneException extends Exception {//当操作数或运算符文本框为空时抛出此异常

    public NoneException(String msg) {
        super(msg);
    }
}

class OpCharException extends Exception {//运算符过多、运算符不是+-*/时抛出此异常

    public OpCharException(String msg) {
        super(msg);
    }
}

class DotNumberException extends Exception {//小数位超出范围[0~4]时抛出此异常

    public DotNumberException(String msg) {
        super(msg);
    }
}

class Ch_4_5 extends JFrame implements ActionListener {
    private JButton bt_ok;      //在 =和clear之间切换
    private JTextField num1, num2, result, opChar, dotNum;  //操作数1、2、运算结果、运算符、小数位数
    private JLabel errorMsg;

    public Ch_4_5() {  //以下为GUI界面设计部分
        super("简单文本计算器");
        setSize(1000, 150);
        setLocation(300, 240);
        this.setLayout(new GridLayout(2, 1));//将窗体设为两行一列的网格
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        //先将各组件加入p1，之后将p1加入窗体
        num1 = new JTextField(10);
        num2 = new JTextField(10);
        opChar = new JTextField(2);
        result = new JTextField(20);
        result.setEditable(false);
        bt_ok = new JButton("  =  ");
        dotNum = new JTextField(3);
        dotNum.setText("2");      //小数位数默认为2
        p1.add(num1);
        p1.add(opChar);
        p1.add(num2);
        p1.add(bt_ok);
        p1.add(result);
        p1.add(new JLabel("保留:"));
        p1.add(dotNum);
        p1.add(new JLabel("位小数"));
        this.add(p1); //p1加入窗体
        errorMsg = new JLabel("  ");
        this.add(errorMsg); //将显示错误信息的标签加入第二个网格
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭按钮：退出程序
        bt_ok.addActionListener(this);
    }

    private double getDouble(JTextField jf, int num) throws NoneException {//从jf获取操作数
        double val;
        String s = jf.getText().trim();   //先剔除文本框的首尾空格
        if (s.length() == 0) throw new NoneException("错误：第" + num + "个操作数为空！");
        try {
            val = Double.parseDouble(s);
        } catch (NumberFormatException e) //写入错误信息后重新抛出
        {
            throw new NumberFormatException("错误：第" + num + "个操作数数据格式错误！");
        }
        return val;
    }

    private int getInt(JTextField jf) throws DotNumberException {//从jf获取小数位
        int val;
        String s = jf.getText().trim();   //先剔除文本框的首尾空格
        if (s.length() == 0) return 0;     //即小数位为0
        try {
            val = Integer.parseInt(s);
        } catch (NumberFormatException e)//写入错误信息后重新抛出
        {
            throw new NumberFormatException("错误：小数位格式错误！");
        }
        if (val < 0 || val > 4) throw new DotNumberException("错误：小数位取值范围是：0~4 !");
        return val;
    }

    private char getOpChar(JTextField jf) throws NoneException, OpCharException {//从jf获取操作符
        String s = jf.getText().trim();
        if (s.length() == 0) throw new NoneException("错误：运算符框为空！");
        if (s.length() > 1) throw new OpCharException("错误：运算符过多！");
        if ("+-*/".indexOf(s) < 0) throw new OpCharException("错误：无法识别的运算符！");
        return s.charAt(0);
    }

    private String compute(double x, double y, char op, int dotN) {
        double r = 0;
        if (y == 0 && op == '/') throw new ArithmeticException("错误：除零错！");
        //注：为了显示中文信息，在捕获除零异常后，基于中文信息重新创建除零异常并抛出
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
        return String.format("%20." + dotN + "f", r);//总宽度20，dotN为小数位，右对齐
    }

    private void clickEq() {//点击=
        double a, b;
        char c;
        int dotN;
        String r;
        try {
            a = getDouble(num1, 1);
            b = getDouble(num2, 2);
            c = getOpChar(opChar);
            dotN = getInt(dotNum);
            r = compute(a, b, c, dotN);
            result.setText(r);
            errorMsg.setText(" ");//清空错误信息
        } catch (Exception x) {
            result.setText("");
            errorMsg.setText(x.getMessage());
        }
        bt_ok.setText("Clear");
        num1.setEditable(false);
        num2.setEditable(false);
        opChar.setEditable(false);
        dotNum.setEditable(false);
    }

    public void actionPerformed(ActionEvent e) {  //点击按钮的处理
        if (e.getSource() == bt_ok) if (e.getActionCommand().equals("  =  ")) clickEq();
        else {//点击clear按钮
            num1.setText("");
            num2.setText("");
            result.setText("");
            opChar.setText("");
            dotNum.setText("2");//默认保留两位小数
            errorMsg.setText(" ");
            bt_ok.setText("  =  ");
            num1.setEditable(true);
            num2.setEditable(true);
            opChar.setEditable(true);
            dotNum.setEditable(true);
            num1.requestFocusInWindow(); //将焦点定位在第一个操作数
        }
    }

    public static void main(String[] args) {
        // SetDefaultFont.setAll(new Font("宋体", Font.BOLD, 26));
        new Ch_4_5();
    }
}

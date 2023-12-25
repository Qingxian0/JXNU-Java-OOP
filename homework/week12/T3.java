import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*  第三题
    给例4.5加入键盘事件，按下回车键后，可实现操作数1、运算符、操作数2、=按钮之间的转移。
    若焦点在“=”按钮上，按下回车相当于鼠标点击。在书本内容上新增：
    （1）点击窗口的X，结束程序
    （2）请用内部类方式实现处理者，其中，窗口事件、键盘事件处理用适配器类来实现（注：至少需要两个内部类--why?）。
 */


/*
    至少需要两个内部类的原因：
    本题中至少需要一个内部类做窗口事件处理者，一个类做键盘事件处理者。
 */

class NoneException extends Exception { //当操作数或运算符文本框为空时抛出此异常
    public NoneException(String msg) {
        super(msg);
    }
}

class OperatorException extends Exception { //运算符过多、运算符不是+-*/时抛出此异常
    public OperatorException(String msg) {
        super(msg);
    }
}

class DecimalOutOfRangeException extends Exception { // 小数位超出范围[0~4]时抛出此异常
    public DecimalOutOfRangeException(String msg) {
        super(msg);
    }
}

public class T3 extends JFrame implements ActionListener{
    private JButton calculate;      //在 = 和clear之间切换
    private JTextField op1, op2, result, opChar, decimalPlaces;  //操作数1、2、运算结果、运算符、小数位数
    private JLabel errorMsg;

    public T3() {
        super("第三题");
        this.setSize(1000, 150);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2, 1));

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        //先将各组件加入p1，之后将p1加入窗体
        op1 = new JTextField(10);
        op2 = new JTextField(10);
        opChar = new JTextField(2);
        result = new JTextField(20);
        result.setEditable(false);
        calculate = new JButton("  =  ");
        decimalPlaces = new JTextField(3);
        decimalPlaces.setText("2");
        p1.add(op1);
        p1.add(opChar);
        p1.add(op2);
        p1.add(calculate);
        p1.add(result);
        p1.add(new JLabel("保留:"));
        p1.add(decimalPlaces);
        p1.add(new JLabel("位小数"));
        this.add(p1);
        errorMsg = new JLabel(" ");
        this.add(errorMsg);

        WindowController windowController = new WindowController();
        // 注册窗口事件处理器
        this.addWindowListener(new WindowController());
        // 注册键盘事件处理器
        KeyEventController keyEventController = new KeyEventController();
        op1.addKeyListener(keyEventController);
        op2.addKeyListener(keyEventController);
        opChar.addKeyListener(keyEventController);
        calculate.addKeyListener(keyEventController);
        decimalPlaces.addKeyListener(keyEventController);
        calculate.addActionListener(this);
        // 设置默认焦点为op1
        op1.requestFocusInWindow();
        this.setVisible(true);
    }

    class WindowController extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }

    class KeyEventController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(calculate.isFocusOwner()){
                    // 如果焦点在"="按钮上，相当于鼠标点击
                    if(calculate.getText().equals("  =  ")){
                        clickEq();
                    }else {
                        op1.setText("");
                        op2.setText("");
                        result.setText("");
                        opChar.setText("");
                        decimalPlaces.setText("2");
                        errorMsg.setText(" ");
                        calculate.setText("  =  ");
                        op1.setEditable(true);
                        op2.setEditable(true);
                        opChar.setEditable(true);
                        decimalPlaces.setEditable(true);
                        op1.requestFocusInWindow(); //将焦点定位在第一个操作数
                    }
                }else{
                    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                    manager.focusNextComponent();
                }
            }
        }
    }

    private double getDouble(JTextField jf, int num) throws NoneException { //从jf获取操作数
        double val;
        String s = jf.getText().trim(); //先剔除文本框的首尾空格
        if(s.length() == 0) {
            throw new NoneException("错误：第" + num + "个操作数为空！");
        }
        try{
            val = Double.parseDouble(s);
        }catch(NumberFormatException e){ //写入错误信息后重新抛出
            throw new NumberFormatException("错误：第" + num + "个操作数数据格式错误！");
        }
        return val;
    }

    private int getInt(JTextField jf) throws DecimalOutOfRangeException { //从jf获取小数位数
        int val;
        String s = jf.getText().trim();
        if(s.length() == 0){
            return 0;
        }
        try{
            val = Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new NumberFormatException("错误：小数位格式错误！");
        }
        if(val < 0 || val > 4){
            throw new DecimalOutOfRangeException("错误：小数位取值范围是：0~4 !");
        }
        return val;
    }

    private char getOpChar(JTextField jf) throws NoneException, OperatorException{ //从jf获取操作符
        String s = jf.getText().trim();
        if (s.length() == 0) throw new NoneException("错误：运算符框为空！");
        if (s.length() > 1) throw new OperatorException("错误：运算符过多！");
        if(!"+-*/".contains(s)) throw new OperatorException("错误：无法识别的运算符！");
        return s.charAt(0);
    }

    private String compute(double x, double y, char op, int dp){
        double res = 0;
        if(y == 0 && op == '/'){
            throw new ArithmeticException("错误：除零错！");
        }
        switch(op){
            case '+':
                res = x + y;
                break;
            case '-':
                res = x - y;
                break;
            case '*':
                res = x * y;
                break;
            case '/':
                res = x / y;
                break;
        }
        return String.format("%20." + dp + "f", res); //总宽度20，dotN为小数位，右对齐
    }

    private void clickEq(){
        double a, b;
        char c;
        int dp;
        String res;
        try{
            a = getDouble(op1, 1);
            b = getDouble(op2, 2);
            c = getOpChar(opChar);
            dp = getInt(decimalPlaces);
//            System.out.println(a);
//            System.out.println(b);
//            System.out.println(c);
//            System.out.println(dp);
            res = compute(a, b, c, dp);
            result.setText(res);
            errorMsg.setText(" "); //清空错误信息
        }catch (Exception x){
            result.setText("");
            errorMsg.setText(x.getMessage());
        }
        calculate.setText("Clear");
        op1.setEditable(false);
        op2.setEditable(false);
        opChar.setEditable(false);
        decimalPlaces.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == calculate){
            if(e.getActionCommand().equals("  =  ")){
                clickEq();
            }else{ //点击clear按钮
                op1.setText("");
                op2.setText("");
                result.setText("");
                opChar.setText("");
                decimalPlaces.setText("2");
                errorMsg.setText(" ");
                calculate.setText("  =  ");
                op1.setEditable(true);
                op2.setEditable(true);
                opChar.setEditable(true);
                decimalPlaces.setEditable(true);
                op1.requestFocusInWindow(); //将焦点定位在第一个操作数
            }
        }
    }

    public static void main(String[] args) {
        new T3();
    }




}

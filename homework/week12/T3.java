import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*  ������
    ����4.5��������¼������»س����󣬿�ʵ�ֲ�����1���������������2��=��ť֮���ת�ơ�
    �������ڡ�=����ť�ϣ����»س��൱������������鱾������������
    ��1��������ڵ�X����������
    ��2�������ڲ��෽ʽʵ�ִ����ߣ����У������¼��������¼�����������������ʵ�֣�ע��������Ҫ�����ڲ���--why?����
 */


/*
    ������Ҫ�����ڲ����ԭ��
    ������������Ҫһ���ڲ����������¼������ߣ�һ�����������¼������ߡ�
 */

class NoneException extends Exception { //����������������ı���Ϊ��ʱ�׳����쳣
    public NoneException(String msg) {
        super(msg);
    }
}

class OperatorException extends Exception { //��������ࡢ���������+-*/ʱ�׳����쳣
    public OperatorException(String msg) {
        super(msg);
    }
}

class DecimalOutOfRangeException extends Exception { // С��λ������Χ[0~4]ʱ�׳����쳣
    public DecimalOutOfRangeException(String msg) {
        super(msg);
    }
}

public class T3 extends JFrame implements ActionListener{
    private JButton calculate;      //�� = ��clear֮���л�
    private JTextField op1, op2, result, opChar, decimalPlaces;  //������1��2�����������������С��λ��
    private JLabel errorMsg;

    public T3() {
        super("������");
        this.setSize(1000, 150);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2, 1));

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        //�Ƚ����������p1��֮��p1���봰��
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
        p1.add(new JLabel("����:"));
        p1.add(decimalPlaces);
        p1.add(new JLabel("λС��"));
        this.add(p1);
        errorMsg = new JLabel(" ");
        this.add(errorMsg);

        WindowController windowController = new WindowController();
        // ע�ᴰ���¼�������
        this.addWindowListener(new WindowController());
        // ע������¼�������
        KeyEventController keyEventController = new KeyEventController();
        op1.addKeyListener(keyEventController);
        op2.addKeyListener(keyEventController);
        opChar.addKeyListener(keyEventController);
        calculate.addKeyListener(keyEventController);
        decimalPlaces.addKeyListener(keyEventController);
        calculate.addActionListener(this);
        // ����Ĭ�Ͻ���Ϊop1
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
                    // ���������"="��ť�ϣ��൱�������
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
                        op1.requestFocusInWindow(); //�����㶨λ�ڵ�һ��������
                    }
                }else{
                    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                    manager.focusNextComponent();
                }
            }
        }
    }

    private double getDouble(JTextField jf, int num) throws NoneException { //��jf��ȡ������
        double val;
        String s = jf.getText().trim(); //���޳��ı������β�ո�
        if(s.length() == 0) {
            throw new NoneException("���󣺵�" + num + "��������Ϊ�գ�");
        }
        try{
            val = Double.parseDouble(s);
        }catch(NumberFormatException e){ //д�������Ϣ�������׳�
            throw new NumberFormatException("���󣺵�" + num + "�����������ݸ�ʽ����");
        }
        return val;
    }

    private int getInt(JTextField jf) throws DecimalOutOfRangeException { //��jf��ȡС��λ��
        int val;
        String s = jf.getText().trim();
        if(s.length() == 0){
            return 0;
        }
        try{
            val = Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new NumberFormatException("����С��λ��ʽ����");
        }
        if(val < 0 || val > 4){
            throw new DecimalOutOfRangeException("����С��λȡֵ��Χ�ǣ�0~4 !");
        }
        return val;
    }

    private char getOpChar(JTextField jf) throws NoneException, OperatorException{ //��jf��ȡ������
        String s = jf.getText().trim();
        if (s.length() == 0) throw new NoneException("�����������Ϊ�գ�");
        if (s.length() > 1) throw new OperatorException("������������࣡");
        if(!"+-*/".contains(s)) throw new OperatorException("�����޷�ʶ����������");
        return s.charAt(0);
    }

    private String compute(double x, double y, char op, int dp){
        double res = 0;
        if(y == 0 && op == '/'){
            throw new ArithmeticException("���󣺳����");
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
        return String.format("%20." + dp + "f", res); //�ܿ��20��dotNΪС��λ���Ҷ���
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
            errorMsg.setText(" "); //��մ�����Ϣ
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
            }else{ //���clear��ť
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
                op1.requestFocusInWindow(); //�����㶨λ�ڵ�һ��������
            }
        }
    }

    public static void main(String[] args) {
        new T3();
    }




}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*  ������
    ��һ���򵥵ļ�������[1][+][2]��=��[ 3] ���˳��� ����[ ]��ʾ�ı��򣬡�=����ʾ��ť��
    �����ť�����������3���ı���enable(false)�� Ҫ��
    ��1����������������ݸ�ʽ����ȷʱ��������ǩ��ʾ������Ϣ��
    ��2���Զ����쳣��NullTextFieldException��
        ����һ���ı���Ϊ�ջ�ڶ����ı���Ϊ��ʱ�׳����쳣����������ǩ��ʾ������Ϣ
 */

class NullTextFieldException extends Exception { // ����������������ı���Ϊ��ʱ�׳����쳣
    public NullTextFieldException(String msg) {
        super(msg);
    }
}

public class T4 extends JFrame {
    private JButton calculate; // �� = ��clear֮���л�
    private JTextField op1, op2, result, opChar, decimalPlaces; // ������1��2�����������������С��λ��

    public T4() {
        super("������");
        this.setSize(1000, 120);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        // �Ƚ����������p1��֮��p1���봰��
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
        String s = "ʹ��˵�����������룺 3.1  +  2.6 �������֧�� +  -  *  /������:";
        p2.add(new JLabel("����:"));
        decimalPlaces = new JTextField(3);
        p2.add(decimalPlaces);
        decimalPlaces.setText("2");
        p2.add(new JLabel("λС��"));

        this.add(p2);


        Controller controller = new Controller(); // ���������ߣ��������ߣ�����
        calculate.addActionListener(controller);
        calculate.addKeyListener(controller);
        op1.addKeyListener(controller);
        op2.addKeyListener(controller);
        opChar.addKeyListener(controller);
        decimalPlaces.addKeyListener(controller);
        this.setVisible(true);
    }

    class Controller extends KeyAdapter implements ActionListener{
        private void clickEq() { // ���=
            try {
                if (op1.getText().equals("") || op2.getText().equals("") || opChar.getText().equals("")) {
                    throw new NullTextFieldException("�пյ��ı��򣬲��ܼ��㣡"); // Throw the custom exception with an error message
                }// �׳����쳣��������ǩ��ʾ������Ϣ

                double x, y;
                int dp; // С��λ��
                char op = opChar.getText().charAt(0);
                x = Double.parseDouble(op1.getText()); // ���ı������ֱ��double
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



        private void clear(){ // ���clear
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
                return "�����";
            double res = 0;
            if (op == '+')
                res = x + y;
            else if (op == '-')
                res = x - y;
            else if (op == '*')
                res = x * y;
            else if (op == '/')
                res = x / y;
            return String.format("%20." + dp + "f", res);// �ܿ��20��dotNΪС��λ���Ҷ���
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
        public void keyTyped(KeyEvent e) {// ���������ַ�����
            char c = e.getKeyChar();
            if (c == '\n') {// �ƶ�����
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
            if (e.getSource() == opChar) {// �ò�������ֻ������һ�������
                e.consume();
                if (c == '+' || c == '-' || c == '*' || c == '/') // Ȼ����������������ֱ�Ӷ��ı���ֵ
                {
                    opChar.setText("" + c);
                    result.setText("");
                    return;
                } // ע�����ú󣬻����ı���Ĭ�ϵļ����¼�
                result.setText("��������ݸ�ʽ����ȷ");
                Toolkit.getDefaultToolkit().beep(); // ���������죬����Toolkitλ��awt��
                return;
            }
            if (e.getSource() == decimalPlaces) {// ��С��λֻ������һ������
                e.consume();
                if (c >= '0' && c <= '9') {
                    decimalPlaces.setText("" + c);
                    return;
                }
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            if (e.getSource() == op1 || e.getSource() == op2) {// ��num1��num2ֻ���������ֺ�.
                if (c >= '0' && c <= '9'){
                    result.setText("");
                    return; // ��������֣�����������
                }
                result.setText("����Ĳ�������");
                e.consume();// ��keyTyped()�з���Ч��
                Toolkit.getDefaultToolkit().beep(); // ���������죬����Toolkitλ��awt��
            }
        }
    }



    public static void main(String[] args) {
        new T4();
    }

}

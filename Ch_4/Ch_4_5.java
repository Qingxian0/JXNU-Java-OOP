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

class NoneException extends Exception {//����������������ı���Ϊ��ʱ�׳����쳣

    public NoneException(String msg) {
        super(msg);
    }
}

class OpCharException extends Exception {//��������ࡢ���������+-*/ʱ�׳����쳣

    public OpCharException(String msg) {
        super(msg);
    }
}

class DotNumberException extends Exception {//С��λ������Χ[0~4]ʱ�׳����쳣

    public DotNumberException(String msg) {
        super(msg);
    }
}

class Ch_4_5 extends JFrame implements ActionListener {
    private JButton bt_ok;      //�� =��clear֮���л�
    private JTextField num1, num2, result, opChar, dotNum;  //������1��2�����������������С��λ��
    private JLabel errorMsg;

    public Ch_4_5() {  //����ΪGUI������Ʋ���
        super("���ı�������");
        setSize(1000, 150);
        setLocation(300, 240);
        this.setLayout(new GridLayout(2, 1));//��������Ϊ����һ�е�����
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        //�Ƚ����������p1��֮��p1���봰��
        num1 = new JTextField(10);
        num2 = new JTextField(10);
        opChar = new JTextField(2);
        result = new JTextField(20);
        result.setEditable(false);
        bt_ok = new JButton("  =  ");
        dotNum = new JTextField(3);
        dotNum.setText("2");      //С��λ��Ĭ��Ϊ2
        p1.add(num1);
        p1.add(opChar);
        p1.add(num2);
        p1.add(bt_ok);
        p1.add(result);
        p1.add(new JLabel("����:"));
        p1.add(dotNum);
        p1.add(new JLabel("λС��"));
        this.add(p1); //p1���봰��
        errorMsg = new JLabel("  ");
        this.add(errorMsg); //����ʾ������Ϣ�ı�ǩ����ڶ�������
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���õ���رհ�ť���˳�����
        bt_ok.addActionListener(this);
    }

    private double getDouble(JTextField jf, int num) throws NoneException {//��jf��ȡ������
        double val;
        String s = jf.getText().trim();   //���޳��ı������β�ո�
        if (s.length() == 0) throw new NoneException("���󣺵�" + num + "��������Ϊ�գ�");
        try {
            val = Double.parseDouble(s);
        } catch (NumberFormatException e) //д�������Ϣ�������׳�
        {
            throw new NumberFormatException("���󣺵�" + num + "�����������ݸ�ʽ����");
        }
        return val;
    }

    private int getInt(JTextField jf) throws DotNumberException {//��jf��ȡС��λ
        int val;
        String s = jf.getText().trim();   //���޳��ı������β�ո�
        if (s.length() == 0) return 0;     //��С��λΪ0
        try {
            val = Integer.parseInt(s);
        } catch (NumberFormatException e)//д�������Ϣ�������׳�
        {
            throw new NumberFormatException("����С��λ��ʽ����");
        }
        if (val < 0 || val > 4) throw new DotNumberException("����С��λȡֵ��Χ�ǣ�0~4 !");
        return val;
    }

    private char getOpChar(JTextField jf) throws NoneException, OpCharException {//��jf��ȡ������
        String s = jf.getText().trim();
        if (s.length() == 0) throw new NoneException("�����������Ϊ�գ�");
        if (s.length() > 1) throw new OpCharException("������������࣡");
        if ("+-*/".indexOf(s) < 0) throw new OpCharException("�����޷�ʶ����������");
        return s.charAt(0);
    }

    private String compute(double x, double y, char op, int dotN) {
        double r = 0;
        if (y == 0 && op == '/') throw new ArithmeticException("���󣺳����");
        //ע��Ϊ����ʾ������Ϣ���ڲ�������쳣�󣬻���������Ϣ���´��������쳣���׳�
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
        return String.format("%20." + dotN + "f", r);//�ܿ��20��dotNΪС��λ���Ҷ���
    }

    private void clickEq() {//���=
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
            errorMsg.setText(" ");//��մ�����Ϣ
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

    public void actionPerformed(ActionEvent e) {  //�����ť�Ĵ���
        if (e.getSource() == bt_ok) if (e.getActionCommand().equals("  =  ")) clickEq();
        else {//���clear��ť
            num1.setText("");
            num2.setText("");
            result.setText("");
            opChar.setText("");
            dotNum.setText("2");//Ĭ�ϱ�����λС��
            errorMsg.setText(" ");
            bt_ok.setText("  =  ");
            num1.setEditable(true);
            num2.setEditable(true);
            opChar.setEditable(true);
            dotNum.setEditable(true);
            num1.requestFocusInWindow(); //�����㶨λ�ڵ�һ��������
        }
    }

    public static void main(String[] args) {
        // SetDefaultFont.setAll(new Font("����", Font.BOLD, 26));
        new Ch_4_5();
    }
}

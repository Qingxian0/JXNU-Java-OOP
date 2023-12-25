/*������
    ����һ���棬���С����򡱡����رա�������ť�����ı���
    һ����������һ�����ݣ���һ�ı��򲻿ɱ༭��������ʾ�����
    ����ʱ���������ı���������һ�����ݣ��������ť��
    ���ڲ�a�ɱ༭���ı�������������Ľ����
    ������رա���ť����ɽ�������
    ��Ȼ��������ڵĹر�ͼ�꣬Ҳ�ɽ������� ��չ���������븺�����磺-1.2
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class T3 extends JFrame implements ActionListener {
    private JButton sortButton;
    private JButton closeButton;
    private JTextField input;
    private JTextField output;
    private JLabel functionLabel;
    private JLabel inputLabel;
    private JLabel outputLabel;

    public T3() {
        super("������");
        this.setSize(600, 200);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        functionLabel = new JLabel("����: ");
        sortButton = new JButton("����");
        closeButton = new JButton("�ر�");
        input = new JTextField(35);
        output = new JTextField(35);
        inputLabel = new JLabel("���룺");
        outputLabel = new JLabel("�����");
        Font f1 = new Font("����", Font.PLAIN, 25);
        Font f2 = new Font("Consolas", Font.PLAIN, 25);
        functionLabel.setFont(f1);
        sortButton.setFont(f1);
        closeButton.setFont(f1);
        inputLabel.setFont(f1);
        outputLabel.setFont(f1);
        input.setFont(f2);
        output.setFont(f2);

        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1.add(functionLabel);
        p1.add(sortButton);
        p1.add(closeButton);
        p2.add(inputLabel);
        p2.add(input);
        p3.add(outputLabel);
        p3.add(output);
        output.setEditable(false);
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);

        sortButton.addActionListener(this);
        closeButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            String beforeString = input.getText().trim();
            // �������ı��ָ�Ϊ�ַ�������
            String[] numbers = beforeString.split("\\s+");
            // ���ַ�������ת��Ϊdouble����
            double[] before = new double[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                before[i] = Double.parseDouble(numbers[i]);
            }
            // ��ʾ�������
            output.setText(afterSortString(before));
        }else if(e.getSource() == closeButton){
            System.exit(0);
        }
    }

    private String afterSortString(double[] before) {
        Arrays.sort(before);
        StringBuilder after = new StringBuilder();
        for (double value : before) {
            after.append(value).append(" ");
        }
        return after.toString();
    }

    public static void main(String[] args) {
        new T3();
    }
}

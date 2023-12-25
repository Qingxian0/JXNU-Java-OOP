/*
GUI��̻������裺
1. ������棺��������������棺����������p + ���������obj + p.add(obj)
2. �������棺��������������֣��������룻���������У�
3. ��Ӧ���棺����������¼�������ơ��磺�����ȷ����������֤��¼�ĺϷ���
 */
/*  ��һ��
    Jbuttonʹ��getText()��ð�ť�ϵ��ı���Ϣ��
    ���贰����ֻ��һ����ť��������������1�κ�����getActionCommand()��ȡ�ı���Ϣ��
    ֮�󽫰�ť�ϵ����ָ���Ϊ�����1�Ρ����ظ��˹��̣����getActionCommand()��Ч����
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class T1 extends JFrame implements ActionListener {
    private JTextField textField; // �ı���
    private JButton button; // ��ť
    private int clickTime = 0;

    public T1() {
        super("��һ��");
        this.setSize(500, 100);
        this.setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        textField = new JTextField(5);
        button = new JButton("���");
        this.add(textField);
        this.add(button);
        textField.addActionListener(this);
        button.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("���") ||
        e.getActionCommand().equals("���" + clickTime + "��")){
            clickTime++;
            String buttonText = "���" + clickTime + "��";
            button.setText(buttonText); // ���°�ť�ı�
            System.out.println(button.getActionCommand());
        }

    }

    public static void main(String[] args) {
        new T1();
    }
}

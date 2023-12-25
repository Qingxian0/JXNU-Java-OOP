/*  �ڶ���
    ����¼�������������ƣ��磺�û���������������Σ������������
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T2 extends JFrame implements ActionListener {
    private JTextField userName; // �û���
    private JPasswordField password; // ����
    private JButton loginButton; // ��¼��ť
    private JLabel infoLabel;               //������ʾ��Ϣ�ı�ǩ
    private int wrongCount = 0;

    public T2(){
        super("�ڶ���");
        this.setSize(500, 100);
        this.setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userName = new JTextField(5);
        password = new JPasswordField(5);
        loginButton = new JButton("��¼");
        infoLabel = new JLabel(" ");
        this.add(new JLabel("�û���"));
        this.add(userName);
        this.add(new JLabel("����"));
        this.add(password);
        this.add(loginButton);
        this.add(infoLabel);
        loginButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("��¼")){
            String keyText = String.valueOf(password.getPassword()); // ���ص���һ��char[]������Ҫת��ΪString
            if(userName.getText().equals("abc") && keyText.equals("1234")){
                infoLabel.setText("��ӭ����abc!");
                wrongCount = 0;
            }else{
                wrongCount++;
                if(wrongCount == 3){
                    this.dispose();
                }else {
                    infoLabel.setText("�û��������������" + (3 - wrongCount) + "�λ���");
                }
            }
        }
    }

    public static void main(String[] args) {
        new T2();
    }
}

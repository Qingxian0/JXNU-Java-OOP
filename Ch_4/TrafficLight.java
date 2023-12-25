/*��ͨ��ʾ����Ϊ�β�ѡ��JLabel
 *    JLabel��setBackground�Ǽ̳���JComponent��
 *    JComponent��setOpaque(boolean)Ĭ���������false��
 *    ����͸����Ȼ�޷���ʾ�������ϣ����JLabel������Ҫ��
 *     la[i][j]=new JLabel("    ");  la[i][j].setOpaque(true);
 **/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrafficLight extends JFrame implements ActionListener {
    private JButton b_heng, b_zong;
    private Label[][] la;//��ǩ����

    public TrafficLight() {
        super("��ͨ��ʾ��");
        setSize(600, 600);
        setBackground(Color.lightGray);
        //setLocation(300,240);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���õ���رհ�ť
        Font font1 = new Font("����", Font.PLAIN, 16);  //��������
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING));
        b_zong = new JButton("����ͨ��");
        b_zong.setFont(font1);
        b_heng = new JButton("����ͨ��");
        b_heng.setFont(font1);
        p1.add(b_heng);
        p1.add(b_zong);
        add(p1, BorderLayout.NORTH);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 6));
        //Font font1=new Font("����",Font.PLAIN,16);  //��������
        la = new Label[6][6];
        for (int i = 0; i < la.length; i++)
            for (int j = 0; j < la[i].length; j++) {
                la[i][j] = new Label("    ");    //la[i][j].setOpaque(true);
                la[i][j].setFont(font1);
                p2.add(la[i][j]);
            }
        //���ó�ʼ״̬������ͨ��
        ����ͨ��();
        add(p2, BorderLayout.CENTER);
        setVisible(true);

        b_zong.addActionListener(this);
        b_heng.addActionListener(this);
    }

    private void ����ͨ��() {
        for (int i = 0; i < la.length; i++) la[3][i].setBackground(Color.red);
        for (int i = 0; i < la.length; i++) la[i][3].setBackground(Color.green);
        b_heng.setEnabled(true);
        b_zong.setEnabled(false);
    }

    private void ����ͨ��() {
        for (int i = 0; i < la.length; i++) la[i][3].setBackground(Color.red);
        for (int i = 0; i < la.length; i++) la[3][i].setBackground(Color.green);
        b_heng.setEnabled(false);
        b_zong.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_zong) ����ͨ��();
        else if (e.getSource() == b_heng) ����ͨ��();
        setVisible(true);
    }

    public static void main(String[] args) {
        new TrafficLight();
    }
}

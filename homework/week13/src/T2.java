/*�ڶ���
    ʵ�ֽ�ͨ��ʾ����ʾ
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T2 extends JFrame implements ActionListener {
    private JButton verticalButton, horizontalButton;
    private JLabel[][] labels;

    public T2(){
        super("�ڶ���");
        this.setSize(600, 600);
        this.setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font f1=new Font("����",Font.PLAIN,16);  //��������
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING));
        verticalButton = new JButton("����ͨ��");
        verticalButton.setFont(f1);
        horizontalButton = new JButton("����ͨ��");
        horizontalButton.setFont(f1);
        p1.add(horizontalButton);
        p1.add(verticalButton);
        labels = new JLabel[6][6];
        p2.setLayout(new GridLayout(6, 6));
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new JLabel("    ");
                labels[i][j].setFont(f1);
                labels[i][j].setOpaque(true);
                p2.add(labels[i][j]);
            }
        }
        //���ó�ʼ״̬������ͨ��
        verticalPass();

        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);

        verticalButton.addActionListener(this);
        horizontalButton.addActionListener(this);
        this.setVisible(true);
    }

    public void horizontalPass(){ // ����ͨ��
        for (int i = 0; i < labels.length; i++) {
            labels[i][3].setBackground(Color.RED);
        }
        for (int i = 0; i < labels.length; i++) {
            labels[3][i].setBackground(Color.GREEN);
        }
        horizontalButton.setEnabled(false);
        verticalButton.setEnabled(true);
    }

    public void verticalPass(){ // ����ͨ��
        for (int i = 0; i < labels.length; i++) {
            labels[3][i].setBackground(Color.RED);
        }
        for (int i = 0; i < labels.length; i++) {
            labels[i][3].setBackground(Color.GREEN);
        }
        verticalButton.setEnabled(false);
        horizontalButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verticalButton) verticalPass();
        else if (e.getSource() == horizontalButton) horizontalPass();
    }

    public static void main(String[] args) {
        new T2();
    }
}

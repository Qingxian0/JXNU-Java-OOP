import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;//��������ť�¼�����Ҫʵ�ֵĽӿ�
import java.awt.event.ActionEvent;

public class Ch_4_2 extends JFrame implements ActionListener {
    private JButton b_ok, b_exit;      //ȷ�����˳���ť������
    private JLabel t_la;               //������ʾ��Ϣ�ı�ǩ
    private JTextField userName;     //�û����ı���
    private JPasswordField password;  //�����

    public Ch_4_2() {  //����ΪGUI������Ʋ���
        super("��һ���򵥽���");
        setSize(500, 100);
        setBackground(Color.lightGray);
        setLocation(300, 240);
        setLayout(new FlowLayout());
        userName = new JTextField(5);
        password = new JPasswordField(5);
        add(new JLabel("�û�����"));
        add(userName);
        add(new JLabel("��  �룺"));
        add(password);
        b_ok = new JButton("ȷ��");
        add(b_ok);
        b_exit = new JButton("�˳�");
        add(b_exit);
        t_la = new JLabel(" ");
        add(t_la); //����һ����ʱ��ǩ��������ʾ��Ϣ
        setVisible(true);

        //���½����¼�Դ�봦����֮��Ĺ�����������ťʹ��ͬһ�������߶���
        b_exit.addActionListener(this); //����������this��Ch_4_2�������
        b_ok.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {  //���������¼��������
        if (e.getSource() == b_exit) System.exit(0);  //��getSource()����ʶ���¼�Դ
        if (e.getActionCommand().equals("ȷ��")) {   //����һ�ַ���ʶ���¼�Դ
            String keyText = String.valueOf(password.getPassword());
            if (userName.getText().equals("abc") && keyText.equals("1234"))
                t_la.setText("��ӭ����abc!");
            else
                t_la.setText("�û����������");
            setVisible(true);      //��������в��Դ˾�֮����
        }
    }

    public static void main(String[] args) {
        new Ch_4_2();
    }
}

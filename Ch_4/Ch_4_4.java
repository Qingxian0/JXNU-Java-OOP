import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;    //�����¼���������
import java.awt.event.KeyEvent;      //ע�����������ʵ�ֽӿڣ������赼��KeyEvent��

public class Ch_4_4 extends JFrame {
    private JButton b_ok, b_exit;      //ȷ�����˳���ť������
    private JLabel t_la;               //������ʾ��Ϣ�ı�ǩ
    private JTextField userName;     //�û����ı���
    private JPasswordField password;  //�����

    class Controller extends KeyAdapter implements ActionListener {//�ڲ��ࣺ������

        private void click_btOk() {//���ȷ��
            String keyText = String.valueOf(password.getPassword());
            if (userName.getText().equals("abc") && keyText.equals("1234"))
                t_la.setText("��ӭ����abc!");
            else
                t_la.setText("�û����������");
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {  //�����ť�Ĵ���
            if (e.getSource() == b_exit) System.exit(0);
            if (e.getActionCommand().equals("ȷ��")) click_btOk();
        }

        //����3��������KeyListener�ӿ��ṩ
        public void keyPressed(KeyEvent e) {  //����ASII�봦��
            if (e.getKeyChar() == 'o' && e.getSource() == b_exit) System.exit(0);
            if (e.getKeyChar() == '\n')
                if (e.getSource() == userName) password.requestFocusInWindow();
                else if (e.getSource() == password) b_ok.requestFocusInWindow();
                else if (e.getSource() == b_ok) click_btOk();
        }
    }

    public Ch_4_4() {  //����ΪGUI������Ʋ���
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

        Controller ctrl = new Controller();  //���������ߣ��������ߣ�����
        b_exit.addActionListener(ctrl);
        b_ok.addActionListener(ctrl);
        b_ok.addKeyListener(ctrl);
        userName.addKeyListener(ctrl);
        password.addKeyListener(ctrl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���õ���رհ�ť���˳�����
    }

    public static void main(String[] args) {
        new Ch_4_4();
    }
}

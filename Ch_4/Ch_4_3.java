import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;      //�����ť���˵��Ȳ������¼�
import java.awt.event.ActionListener;   //��������ť�¼�����Ҫʵ�ֵĽӿ�
import java.awt.event.KeyEvent;         //�û����̲������¼�
import java.awt.event.KeyListener;      //��������¼�����Ҫʵ�ֵĽӿ�
import java.awt.event.WindowEvent;      //�����¼�
import java.awt.event.WindowListener;   //�������¼�����Ҫʵ�ֵĽӿ�

public class Ch_4_3 extends JFrame implements ActionListener, KeyListener, WindowListener {
    private JButton b_ok, b_exit;      //ȷ�����˳���ť������
    private JLabel t_la;               //������ʾ��Ϣ�ı�ǩ
    private JTextField userName;     //�û����ı���
    private JPasswordField password;  //�����

    public Ch_4_3() {  //����ΪGUI������Ʋ���
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
        b_exit.addActionListener(this);
        b_ok.addActionListener(this);
        b_ok.addKeyListener(this);  //��ť��Ҫע����̼������ӿڣ����ܶ԰�����Ӧ
        userName.addKeyListener(this);
        password.addKeyListener(this);
        this.addWindowListener(this);//���ڵ������Ch_4_3�����ϵ�X�����¼�Դ��this
    }

    private void click_btOk() {//Ϊ�����ظ���д���ѵ��ȷ����ťִ�еĶ�����������
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
    public void keyTyped(KeyEvent e) {//����unicode�ַ�ʱ����
        if (e.getKeyChar() == '\n')
            if (e.getSource() == userName) password.requestFocusInWindow();//������ת���������
            else if (e.getSource() == password) b_ok.requestFocusInWindow();
            else if (e.getSource() == b_ok) click_btOk();
    }

    public void keyPressed(KeyEvent e) {
        ;
    }//���¼�ťʱ����

    public void keyReleased(KeyEvent e) {
        ;
    }//�ͷż�ťʱ����

/*�������ɼ��Ctrl+A+B���⿪ע��ǰ��ע�⽫��������ж�Ӧ����ע��
boolean  aIsPress, bIsPress;
public void keyPressed(KeyEvent e){  //���°���ʱ����
    if( e.getKeyCode()==KeyEvent.VK_A && aIsPress==false ) aIsPress=true;
    if(e.getKeyCode()==KeyEvent.VK_B && bIsPress==false) bIsPress=true;
    if( e.isControlDown()  && aIsPress && bIsPress )
        System.out.print("\n Ctrl+A+B is pressed!");
}
public void keyReleased(KeyEvent e){//�ͷŰ���ʱ����
    if(e.getKeyCode()==KeyEvent.VK_A && aIsPress==true)aIsPress=false;
    if(e.getKeyCode()==KeyEvent.VK_B && bIsPress==true)bIsPress=false;
}
*/

    //����7��������WindowListener�ӿ��ṩ
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
        ;
    }

    public void windowActivated(WindowEvent e) {
        ;
    }

    public void windowDeactivated(WindowEvent e) {
        ;
    }

    public void windowClosed(WindowEvent e) {
        ;
    }

    public void windowIconified(WindowEvent e) {
        ;
    }

    public void windowDeiconified(WindowEvent e) {
        ;
    }

    public static void main(String[] args) {
        new Ch_4_3();
    }
}

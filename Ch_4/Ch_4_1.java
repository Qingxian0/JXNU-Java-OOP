import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;

public class Ch_4_1 extends JFrame {
    public Ch_4_1() {
        super("��һ���򵥽���");
        setSize(400, 100);                 //���ÿ�ܿ�Ⱥ͸߶�
        setBackground(Color.lightGray);   //���ÿ�ܵı���ɫ
        setLocation(300, 240);      //ָ��������Ͻǵ���ʾλ�ã�(0,0)������Ļ�����Ͻ�
        setLayout(new FlowLayout());      //ָ����ܵ�Ĭ�ϲ��ַ�ʽ
        /*ע����δָ�����֣�������Զ�ռ����������������������������ڸ�סǰ������*/
        add(new JLabel("�û�����"));            //�����������±�ǩ
        add(new JTextField(5));               //���������볤��Ϊ5�е��ı���
        add(new JLabel("��  �룺"));
        add(new JPasswordField(5));          //���������볤��Ϊ5�е������
        add(new JButton("ȷ��"));             //�����������ǩΪ��ȷ�����İ�ť
        add(new JButton("�˳�"));
        setVisible(true);  //���ô���Ϊ�ɼ���ע�����޴˾䣬���彫���ᱻ��ʾ����
    }

    public static void main(String[] args) {
        new Ch_4_1();
    }
}

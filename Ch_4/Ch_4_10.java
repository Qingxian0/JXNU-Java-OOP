import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
/*
�������û�������ԭ������Ϊ��������Swing���¼��ַ��̣߳�Event Dispatch Thread��EDT����ִ�еģ�
���������߳���ִ�еġ�

����� main �����У�ֱ�ӵ����� new SimpleScrollStr()��
��������߳��д�������ʾ SimpleScrollStr ���ڡ�
�� SimpleScrollStr ���캯���ڲ������� run() ������
�������������һ������ѭ���������������߳���ִ�еġ�
���� run() �����а����� Thread.sleep(t_sleep)��
���߳������ߵ�ʱ�򲻻�����Swing���¼��ַ��̣߳�
��˴����ܹ�������ʾ��

��Swing�У�����������йصĲ�����Ӧ�����¼��ַ��̣߳�EDT����ִ�У�
��ȷ���������Ӧ�Ժ��ȶ��ԡ������ʾ���У�����û���漰��Swing������������¼�����
���ֱ�������߳�������û���������⡣
���ڸ����ӵ�SwingӦ�ó����У�ȷ������UI��صĴ��붼���¼��ַ��߳���ִ���Ǻ���Ҫ�ġ�
 */

class SimpleScrollStr extends JFrame {
    private JTextField msg = new JTextField("1-2-3-4-5-6-7-8-9-0      ");  //�������ı���
    private final int t_sleep = 250;      //���250ms����һ��

    private void run() {
        while (true) {
            try {
                Thread.sleep(t_sleep);
            } catch (InterruptedException e) {
                ;
            }//��ͣ���ɺ���
            String s = msg.getText();
            //System.out.println(s);
            //�������ҹ���
            char ch = s.charAt(0);
            s = s.substring(1, s.length()) + ch;
            //�����������
            //char ch=s.charAt(s.length()-1); s=ch+s.substring(0,s.length()-1);
            msg.setText(s);
        }
    }

    public SimpleScrollStr() {
        super("������Ļ");
        setSize(500, 100);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Font f1 = new Font("΢���ź�", Font.BOLD, 26);
        msg.setFont(f1);
        add(msg);
        setDefaultCloseOperation(EXIT_ON_CLOSE);      //�������ڹرհ�ťʱ��������������
        setVisible(true);
        run();//ע�⣺run()��Ҫ����setVisible()֮�󣬷�������ʾ
    }

    public static void main(String arg[]) {
        new SimpleScrollStr();
    }
}

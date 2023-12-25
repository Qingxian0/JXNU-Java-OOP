import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*��4��
    ˼������ϰ��10�� ������Ļ
 */
public class T4 extends JFrame implements ActionListener {
    private final JTextField t_word;
    private final JTextField t_sleep;
    private final JButton start;
    private final JButton stop;
    private Timer timer;

    public T4() {
        this.setSize(500, 200);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("������");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        t_word = new JTextField( 19);
        Font f1 = new Font("΢���ź�", Font.BOLD, 26);
        t_word.setFont(f1);
        t_sleep = new JTextField("250");
        JLabel sleepInfo = new JLabel("Sleep: ");
        start = new JButton("����");
        stop = new JButton("��ͣ");
        sleepInfo.setFont(f1);
        t_sleep.setFont(f1);
        start.setFont(f1);
        stop.setFont(f1);
        p1.add(t_word);
        p2.add(sleepInfo);
        p2.add(t_sleep);
        p2.add(start);
        p2.add(stop);
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        start.addActionListener(this);
        stop.addActionListener(this);

        //FrameShow.modifyComponentSize(this, 6,6);

        this.setVisible(true);
        this.toFront();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        ��ʹ�� Swing ����ͼ���û����棨GUI�����ʱ��
        ͨ��Ӧ�ñ������¼��ַ��߳���ִ�к�ʱ������
        ��Ϊ��ᵼ�½��涳�ᡣ
         */
        /*
        ����ʹ���� javax.swing.Timer��
        �������ں�̨�߳��ж��ڴ��� ActionListener �� actionPerformed ������
        �����������¼��ַ��̡߳�ϣ�����ܽ��������⡣
         */
        if(e.getSource() == start){
            int sleepTime = Integer.parseInt(t_sleep.getText());
            int columnCount = 47;

            // ����հ��ַ���ʹ���ı����ȴﵽt_word������
            String s = t_word.getText();
            s = s + " ".repeat(Math.max(0, columnCount - s.length()));
            t_word.setText(s);

            timer = new Timer(sleepTime, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = t_word.getText();
                    char ch1 = s.charAt(s.length() - 1);
                    s = ch1 + s.substring(0, s.length() - 1);
                    t_word.setText(s);
                }
            });
            timer.start();
        }else if(e.getSource() == stop){
            if(timer != null){
                timer.stop();
            }
        }
    }

    public static void main(String[] args) {
        new T4();
    }


}

/*ԭ����
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            while (true) {
                try { // ��ͣ
                    Thread.sleep(Integer.parseInt(this.t_sleep.getText()));
                } catch (InterruptedException ignored) {
                }
                String s = t_word.getText();
                System.out.println(s);
                // �������ҹ���
                char ch = s.charAt(s.length() - 1);
                s = ch + s.substring(0, s.length() - 1);
                t_word.setText(s);
            }
        }
    }
    �ڴ����У�ʹ����һ������ѭ�� while (true)��ѭ�����ڰ����� Thread.sleep(Integer.parseInt(this.t_sleep.getText()));��
    ��ᵼ���¼��ַ��̣߳�Swing�¼��̣߳���������

    �¼��ַ��̸߳�����Swing������¼���
    �������ƺ���Ӧ�û�����ȡ�������߳���ִ�г�ʱ�������
    �ر���ʹ�� Thread.sleep ��ɵ��������ᵼ��UI����ʱ��Ӧ�û��Ľ�����
    ��Ϊ�¼��ַ��߳��޷�ͬʱ���������¼���

    Ҫ��SwingӦ�ó�����ʵ�����ƶ�ʱ����Ч����
    �����ʹ��javax.swing.Timer�ࡣTimer������ָ��һ��ʱ������
    ��ÿ��ʱ��������һ���¼��������������¼��ַ��̡߳�
 */

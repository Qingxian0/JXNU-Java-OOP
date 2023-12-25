//由22级计科计科4班  汪蕊 同学提供

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleScrollStr extends JFrame implements ActionListener {
    private JButton b_start, b_stop;
    private JTextField t_sleep;
    private boolean isRolling;
    thread1 th1;
    private int time1;
    private JTextField t_word = new JTextField("                        MD1234号航班晚点2小时                        ");

    class thread1 extends Thread {
        public void run() {
            while (true) {
                try {
                    String s = t_word.getText();
                    char ch = s.charAt(0);
                    s = s.substring(1, s.length()) + ch;
                    t_word.setText(s);
                    sleep(time1);
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

    public SimpleScrollStr() {
        super("滚动字幕");
        setSize(500, 125);
        time1 = 100;
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        setLayout(new BorderLayout());
        add(p1, BorderLayout.NORTH);
        Font f1 = new Font("微软雅黑", Font.BOLD, 26);
        t_word.setFont(f1);
        p1.add(t_word);
        t_sleep = new JTextField(5);
        JPanel p2 = new JPanel();
        add(p2, BorderLayout.SOUTH);
        p2.setLayout(new FlowLayout());
        p2.add(new JLabel("sleep"));
        p2.add(t_sleep);
        // 创建启动和暂停按钮
        b_start = new JButton("启动");
        b_stop = new JButton("暂停");
        p2.add(b_start);
        p2.add(b_stop);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        b_start.addActionListener(this);
        b_stop.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_start) {
            time1 = Integer.parseInt(t_sleep.getText());
            th1 = new thread1();
            b_start.setEnabled(false);
            b_stop.setEnabled(true);
            th1.start();
        } else if (e.getSource() == b_stop) {
            b_start.setEnabled(true);
            b_stop.setEnabled(false);
            th1.interrupt();
        }
    }

    public static void main(String arg[]) {
        new SimpleScrollStr();
    }
}

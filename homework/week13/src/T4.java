import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*第4题
    思考与练习第10题 滚动字幕
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
        this.setTitle("第四题");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        t_word = new JTextField( 19);
        Font f1 = new Font("微软雅黑", Font.BOLD, 26);
        t_word.setFont(f1);
        t_sleep = new JTextField("250");
        JLabel sleepInfo = new JLabel("Sleep: ");
        start = new JButton("启动");
        stop = new JButton("暂停");
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
        在使用 Swing 进行图形用户界面（GUI）编程时，
        通常应该避免在事件分发线程中执行耗时的任务，
        因为这会导致界面冻结。
         */
        /*
        这里使用了 javax.swing.Timer，
        它可以在后台线程中定期触发 ActionListener 的 actionPerformed 方法，
        而不会阻塞事件分发线程。希望这能解决你的问题。
         */
        if(e.getSource() == start){
            int sleepTime = Integer.parseInt(t_sleep.getText());
            int columnCount = 47;

            // 补足空白字符，使得文本长度达到t_word的列数
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

/*原代码
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            while (true) {
                try { // 暂停
                    Thread.sleep(Integer.parseInt(this.t_sleep.getText()));
                } catch (InterruptedException ignored) {
                }
                String s = t_word.getText();
                System.out.println(s);
                // 从左向右滚动
                char ch = s.charAt(s.length() - 1);
                s = ch + s.substring(0, s.length() - 1);
                t_word.setText(s);
            }
        }
    }
    在代码中，使用了一个无限循环 while (true)，循环体内包含了 Thread.sleep(Integer.parseInt(this.t_sleep.getText()));，
    这会导致事件分发线程（Swing事件线程）被阻塞。

    事件分发线程负责处理Swing组件的事件，
    包括绘制和响应用户输入等。在这个线程上执行长时间的任务，
    特别是使用 Thread.sleep 造成的阻塞，会导致UI不及时响应用户的交互，
    因为事件分发线程无法同时处理其他事件。

    要在Swing应用程序中实现类似定时器的效果，
    你可以使用javax.swing.Timer类。Timer允许你指定一个时间间隔，
    在每个时间间隔触发一个事件，而不会阻塞事件分发线程。
 */

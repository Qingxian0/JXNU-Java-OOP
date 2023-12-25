import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
/*
这个程序没有问题的原因是因为它不是在Swing的事件分发线程（Event Dispatch Thread，EDT）中执行的，
而是在主线程中执行的。

在你的 main 方法中，直接调用了 new SimpleScrollStr()，
这会在主线程中创建并显示 SimpleScrollStr 窗口。
在 SimpleScrollStr 构造函数内部调用了 run() 方法，
这个方法包含了一个无限循环，但它是在主线程中执行的。
由于 run() 方法中包含了 Thread.sleep(t_sleep)，
主线程在休眠的时候不会阻塞Swing的事件分发线程，
因此窗口能够正常显示。

在Swing中，所有与界面有关的操作都应该在事件分发线程（EDT）中执行，
以确保界面的响应性和稳定性。在你的示例中，由于没有涉及到Swing的组件交互和事件处理，
因此直接在主线程中运行没有引发问题。
但在更复杂的Swing应用程序中，确保所有UI相关的代码都在事件分发线程中执行是很重要的。
 */

class SimpleScrollStr extends JFrame {
    private JTextField msg = new JTextField("1-2-3-4-5-6-7-8-9-0      ");  //滚动字文本行
    private final int t_sleep = 250;      //间隔250ms滚动一次

    private void run() {
        while (true) {
            try {
                Thread.sleep(t_sleep);
            } catch (InterruptedException e) {
                ;
            }//暂停若干毫秒
            String s = msg.getText();
            //System.out.println(s);
            //从左向右滚动
            char ch = s.charAt(0);
            s = s.substring(1, s.length()) + ch;
            //从右向左滚动
            //char ch=s.charAt(s.length()-1); s=ch+s.substring(0,s.length()-1);
            msg.setText(s);
        }
    }

    public SimpleScrollStr() {
        super("滚动字幕");
        setSize(500, 100);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Font f1 = new Font("微软雅黑", Font.BOLD, 26);
        msg.setFont(f1);
        add(msg);
        setDefaultCloseOperation(EXIT_ON_CLOSE);      //单击窗口关闭按钮时，结束程序运行
        setVisible(true);
        run();//注意：run()定要放在setVisible()之后，否则无显示
    }

    public static void main(String arg[]) {
        new SimpleScrollStr();
    }
}

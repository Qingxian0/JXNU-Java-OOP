/*第三题
    构造一界面，上有“排序”、“关闭”两个按钮；两文本框，
    一个用于输入一组数据，另一文本框不可编辑，用于显示结果。
    运行时，先在以文本框中输入一组数据，点击排序按钮后，
    可在不a可编辑的文本框中输出排序后的结果。
    点击“关闭”按钮，则可结束程序。
    当然，点击窗口的关闭图标，也可结束程序。 拓展：允许输入负数，如：-1.2
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class T3 extends JFrame implements ActionListener {
    private JButton sortButton;
    private JButton closeButton;
    private JTextField input;
    private JTextField output;
    private JLabel functionLabel;
    private JLabel inputLabel;
    private JLabel outputLabel;

    public T3() {
        super("第三题");
        this.setSize(600, 200);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        functionLabel = new JLabel("功能: ");
        sortButton = new JButton("排序");
        closeButton = new JButton("关闭");
        input = new JTextField(35);
        output = new JTextField(35);
        inputLabel = new JLabel("输入：");
        outputLabel = new JLabel("输出：");
        Font f1 = new Font("宋体", Font.PLAIN, 25);
        Font f2 = new Font("Consolas", Font.PLAIN, 25);
        functionLabel.setFont(f1);
        sortButton.setFont(f1);
        closeButton.setFont(f1);
        inputLabel.setFont(f1);
        outputLabel.setFont(f1);
        input.setFont(f2);
        output.setFont(f2);

        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1.add(functionLabel);
        p1.add(sortButton);
        p1.add(closeButton);
        p2.add(inputLabel);
        p2.add(input);
        p3.add(outputLabel);
        p3.add(output);
        output.setEditable(false);
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);

        sortButton.addActionListener(this);
        closeButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            String beforeString = input.getText().trim();
            // 把输入文本分隔为字符串数组
            String[] numbers = beforeString.split("\\s+");
            // 把字符串数组转换为double数组
            double[] before = new double[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                before[i] = Double.parseDouble(numbers[i]);
            }
            // 显示排序后结果
            output.setText(afterSortString(before));
        }else if(e.getSource() == closeButton){
            System.exit(0);
        }
    }

    private String afterSortString(double[] before) {
        Arrays.sort(before);
        StringBuilder after = new StringBuilder();
        for (double value : before) {
            after.append(value).append(" ");
        }
        return after.toString();
    }

    public static void main(String[] args) {
        new T3();
    }
}

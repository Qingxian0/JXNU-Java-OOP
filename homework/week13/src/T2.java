/*第二题
    实现交通灯示例演示
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T2 extends JFrame implements ActionListener {
    private JButton verticalButton, horizontalButton;
    private JLabel[][] labels;

    public T2(){
        super("第二题");
        this.setSize(600, 600);
        this.setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font f1=new Font("宋体",Font.PLAIN,16);  //定义字体
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEADING));
        verticalButton = new JButton("纵向通行");
        verticalButton.setFont(f1);
        horizontalButton = new JButton("横向通行");
        horizontalButton.setFont(f1);
        p1.add(horizontalButton);
        p1.add(verticalButton);
        labels = new JLabel[6][6];
        p2.setLayout(new GridLayout(6, 6));
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new JLabel("    ");
                labels[i][j].setFont(f1);
                labels[i][j].setOpaque(true);
                p2.add(labels[i][j]);
            }
        }
        //设置初始状态：纵向通行
        verticalPass();

        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);

        verticalButton.addActionListener(this);
        horizontalButton.addActionListener(this);
        this.setVisible(true);
    }

    public void horizontalPass(){ // 横向通行
        for (int i = 0; i < labels.length; i++) {
            labels[i][3].setBackground(Color.RED);
        }
        for (int i = 0; i < labels.length; i++) {
            labels[3][i].setBackground(Color.GREEN);
        }
        horizontalButton.setEnabled(false);
        verticalButton.setEnabled(true);
    }

    public void verticalPass(){ // 纵向通行
        for (int i = 0; i < labels.length; i++) {
            labels[3][i].setBackground(Color.RED);
        }
        for (int i = 0; i < labels.length; i++) {
            labels[i][3].setBackground(Color.GREEN);
        }
        verticalButton.setEnabled(false);
        horizontalButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verticalButton) verticalPass();
        else if (e.getSource() == horizontalButton) horizontalPass();
    }

    public static void main(String[] args) {
        new T2();
    }
}

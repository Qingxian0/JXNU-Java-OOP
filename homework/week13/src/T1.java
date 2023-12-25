/*第一题
    练习4.1第2题 开关灯
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T1 extends JFrame implements ActionListener {
    private JLabel light;
    private JLabel infoLabel;
    private JButton turnOn;
    private JButton turnOff;

    public T1(){
        this.setSize(600, 200);
        this.setBackground(Color.white);
        this.setTitle("第一题");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.light = new JLabel("        ");
        this.light.setOpaque(true); // adj.（玻璃、液体等）不透明的，不透光的；难懂的，隐晦的；迟钝的
        this.light.setBackground(Color.BLACK);
        this.infoLabel = new JLabel("灯状态： ");
        this.turnOn = new JButton("开灯");
        this.turnOff = new JButton("关灯");
        this.add(infoLabel);
        this.add(light);
        this.add(turnOn);
        this.add(turnOff);
        turnOn.addActionListener(this);
        turnOff.addActionListener(this);

        FrameShow.modifyComponentSize(this, 3,3);

        this.setVisible(true);
        this.toFront();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == turnOn){
            light.setBackground(Color.RED);
        }else if(e.getSource() == turnOff){
            light.setBackground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        new T1();
    }
}

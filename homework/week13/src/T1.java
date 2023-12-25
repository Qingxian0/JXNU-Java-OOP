/*��һ��
    ��ϰ4.1��2�� ���ص�
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
        this.setTitle("��һ��");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.light = new JLabel("        ");
        this.light.setOpaque(true); // adj.��������Һ��ȣ���͸���ģ���͸��ģ��Ѷ��ģ����޵ģ��ٶ۵�
        this.light.setBackground(Color.BLACK);
        this.infoLabel = new JLabel("��״̬�� ");
        this.turnOn = new JButton("����");
        this.turnOff = new JButton("�ص�");
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

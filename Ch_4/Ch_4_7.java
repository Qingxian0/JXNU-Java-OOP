import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class TextComputer extends JFrame {
	private JButton bt_ok; // �� =��clear֮���л�
	private JTextField num1, num2, result, opChar, dotNum; // ������1��2�����������������С��λ��

	public TextComputer() { // ����ΪGUI������Ʋ���
		super("���ı�������");
		setSize(1000, 120);
		setLayout(new FlowLayout());
		this.setLayout(new GridLayout(2, 1));// ��������Ϊ����һ�е�����
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		// �Ƚ����������p1��֮��p1���봰��
		num1 = new JTextField(10);
		num2 = new JTextField(10);
		opChar = new JTextField(2);
		result = new JTextField(25);
		result.setEditable(false);
		bt_ok = new JButton("  =  ");
		p1.add(num1);
		p1.add(opChar);
		p1.add(num2);
		p1.add(bt_ok);
		p1.add(result);
		add(p1); // p1���봰��

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		String s = "ʹ��˵�����������룺 3.1  +  2.6 �������֧�� +  -  *  /������:";
		p2.add(new JLabel(s));// ���˱�ǩ����ڶ�������
		dotNum = new JTextField(3);
		p2.add(dotNum);// С��λ��Ĭ��Ϊ0
		dotNum.setText("2");
		p2.add(new JLabel("λС��"));
		add(p2);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���õ���رհ�ť���˳�����
		Controler ctrl = new Controler(); // ���������ߣ��������ߣ�����
		bt_ok.addActionListener(ctrl);
		bt_ok.addKeyListener(ctrl);
		num1.addKeyListener(ctrl);
		num2.addKeyListener(ctrl);
		opChar.addKeyListener(ctrl);
		dotNum.addKeyListener(ctrl);
	}

	class Controler extends KeyAdapter implements ActionListener {// �ڲ��ࣺ������
		private void clickEq() {// ���=
			if (num1.getText().equals("") || num2.getText().equals("") || opChar.getText().equals("")) {
				result.setText("���ܼ��㣡");
				return;
			}
			double x, y;
			int dotN; // С��λ��
			char op = opChar.getText().charAt(0);
			x = Double.parseDouble(num1.getText());// ���ı������ֱ��double
			y = Double.parseDouble(num2.getText());
			dotN = Integer.parseInt(dotNum.getText());
			result.setText(compute(x, y, op, dotN));

			bt_ok.setText("Clear");
			num1.setEditable(false);
			num2.setEditable(false);
			opChar.setEditable(false);
			dotNum.setEditable(false);
		}

		private void clear() {// ���clear
			num1.setText("");
			num2.setText("");
			result.setText("");
			opChar.setText("");
			dotNum.setText("2");
			bt_ok.setText("  =  ");
			num1.setEditable(true);
			num2.setEditable(true);
			opChar.setEditable(true);
			dotNum.setEditable(true);
			num1.requestFocusInWindow();
		}

		private String compute(double x, double y, char op, int dotN) {
			if (op == '/' && y == 0)
				return "�����";
			double r = 0;
			if (op == '+')
				r = x + y;
			else if (op == '-')
				r = x - y;
			else if (op == '*')
				r = x * y;
			else if (op == '/')
				r = x / y;
			return String.format("%20." + dotN + "f", r);// �ܿ��20��dotNΪС��λ���Ҷ���
		}

		public void actionPerformed(ActionEvent e) { // �����ť�Ĵ���
			if (e.getSource() == bt_ok)
				if (e.getActionCommand().equals("  =  "))
					clickEq();
				else
					clear();
		}

		// public void keyPressed(KeyEvent e){//�޷������ַ�����
		public void keyTyped(KeyEvent e) {// ���������ַ�����
			// public void keyReleased(KeyEvent e){//�޷������ַ�����
			char c = e.getKeyChar();
			if (c == '\n') {// �ƶ�����
				if (e.getSource() == num1)
					opChar.requestFocusInWindow();
				else if (e.getSource() == opChar)
					num2.requestFocusInWindow();
				else if (e.getSource() == num2) {
					clickEq();
					bt_ok.requestFocusInWindow();
				} else if (e.getSource() == bt_ok && bt_ok.getText().equals("Clear"))
					clear();
				return;
			}
			if (e.getSource() == opChar) {// �ò�������ֻ������һ�������
				e.consume();
				if (c == '+' || c == '-' || c == '*' || c == '/') // Ȼ����������������ֱ�Ӷ��ı���ֵ
				{
					opChar.setText("" + c);
					return;
				} // ע�����ú󣬻����ı���Ĭ�ϵļ����¼�
				Toolkit.getDefaultToolkit().beep(); // ���������죬����Toolkitλ��awt��
				return;
			}
			if (e.getSource() == dotNum) {// ��С��λֻ������һ������
				e.consume();
				if (c >= '0' && c <= '9') {
					dotNum.setText("" + c);
					return;
				}
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			if (e.getSource() == num1 || e.getSource() == num2) {// ��num1��num2ֻ���������ֺ�.
				// if�����ַ���.����Դ��num1/num2�ҵ�ǰ�ı�����û��.������루��ֱ�ӷ��أ�
				if (c == '.' && e.getSource() == num1 && num1.getText().indexOf(".") < 0 ||
						c == '.' && e.getSource() == num2 && num2.getText().indexOf(".") < 0)
					return;// �����û������.������������
				if (c >= '0' && c <= '9')
					return; // ��������֣�����������
				e.consume();// ��keyTyped()�з���Ч��
				Toolkit.getDefaultToolkit().beep(); // ���������죬����Toolkitλ��awt��
				return;
			}
		}
	}

	public static void main(String[] args) {
		SetDefaultFont.setAll(new Font("����", Font.BOLD, 20));
		new TextComputer();
	}
}

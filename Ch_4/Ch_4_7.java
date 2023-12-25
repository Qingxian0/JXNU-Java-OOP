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
	private JButton bt_ok; // 在 =和clear之间切换
	private JTextField num1, num2, result, opChar, dotNum; // 操作数1、2、运算结果、运算符、小数位数

	public TextComputer() { // 以下为GUI界面设计部分
		super("简单文本计算器");
		setSize(1000, 120);
		setLayout(new FlowLayout());
		this.setLayout(new GridLayout(2, 1));// 将窗体设为两行一列的网格
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		// 先将各组件加入p1，之后将p1加入窗体
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
		add(p1); // p1加入窗体

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		String s = "使用说明，依次输入： 3.1  +  2.6 ，运算符支持 +  -  *  /。保留:";
		p2.add(new JLabel(s));// 将此标签加入第二个网格
		dotNum = new JTextField(3);
		p2.add(dotNum);// 小数位数默认为0
		dotNum.setText("2");
		p2.add(new JLabel("位小数"));
		add(p2);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置点击关闭按钮：退出程序
		Controler ctrl = new Controler(); // 创建控制者（即处理者）对象
		bt_ok.addActionListener(ctrl);
		bt_ok.addKeyListener(ctrl);
		num1.addKeyListener(ctrl);
		num2.addKeyListener(ctrl);
		opChar.addKeyListener(ctrl);
		dotNum.addKeyListener(ctrl);
	}

	class Controler extends KeyAdapter implements ActionListener {// 内部类：控制者
		private void clickEq() {// 点击=
			if (num1.getText().equals("") || num2.getText().equals("") || opChar.getText().equals("")) {
				result.setText("不能计算！");
				return;
			}
			double x, y;
			int dotN; // 小数位数
			char op = opChar.getText().charAt(0);
			x = Double.parseDouble(num1.getText());// 将文本框数字变成double
			y = Double.parseDouble(num2.getText());
			dotN = Integer.parseInt(dotNum.getText());
			result.setText(compute(x, y, op, dotN));

			bt_ok.setText("Clear");
			num1.setEditable(false);
			num2.setEditable(false);
			opChar.setEditable(false);
			dotNum.setEditable(false);
		}

		private void clear() {// 点击clear
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
				return "除零错！";
			double r = 0;
			if (op == '+')
				r = x + y;
			else if (op == '-')
				r = x - y;
			else if (op == '*')
				r = x * y;
			else if (op == '/')
				r = x / y;
			return String.format("%20." + dotN + "f", r);// 总宽度20，dotN为小数位，右对齐
		}

		public void actionPerformed(ActionEvent e) { // 点击按钮的处理
			if (e.getSource() == bt_ok)
				if (e.getActionCommand().equals("  =  "))
					clickEq();
				else
					clear();
		}

		// public void keyPressed(KeyEvent e){//无法屏蔽字符输入
		public void keyTyped(KeyEvent e) {// 可以屏蔽字符输入
			// public void keyReleased(KeyEvent e){//无法屏蔽字符输入
			char c = e.getKeyChar();
			if (c == '\n') {// 移动焦点
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
			if (e.getSource() == opChar) {// 让操作符框只能输入一个运算符
				e.consume();
				if (c == '+' || c == '-' || c == '*' || c == '/') // 然后如果是运算符，就直接对文本框赋值
				{
					opChar.setText("" + c);
					return;
				} // 注：设置后，还有文本框默认的键盘事件
				Toolkit.getDefaultToolkit().beep(); // 蜂鸣器声响，其中Toolkit位于awt包
				return;
			}
			if (e.getSource() == dotNum) {// 让小数位只能输入一个数字
				e.consume();
				if (c >= '0' && c <= '9') {
					dotNum.setText("" + c);
					return;
				}
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			if (e.getSource() == num1 || e.getSource() == num2) {// 如num1、num2只能输入数字和.
				// if输入字符是.，若源是num1/num2且当前文本内容没有.则可输入（即直接返回）
				if (c == '.' && e.getSource() == num1 && num1.getText().indexOf(".") < 0 ||
						c == '.' && e.getSource() == num2 && num2.getText().indexOf(".") < 0)
					return;// 即如果没有输入.，就正常输入
				if (c >= '0' && c <= '9')
					return; // 如果是数字，就正常输入
				e.consume();// 在keyTyped()中方有效果
				Toolkit.getDefaultToolkit().beep(); // 蜂鸣器声响，其中Toolkit位于awt包
				return;
			}
		}
	}

	public static void main(String[] args) {
		SetDefaultFont.setAll(new Font("宋体", Font.BOLD, 20));
		new TextComputer();
	}
}

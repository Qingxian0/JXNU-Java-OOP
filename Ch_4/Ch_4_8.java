import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileNotFoundException;

class AreaBinTree{//地区结点：child指针指向孩子,brother指向兄弟
	private String areaName;
	private AreaBinTree child,brother;
	public AreaBinTree(String aName){ areaName=aName; }
	public void setBrother(AreaBinTree x){brother=x;}
	public void setChild(AreaBinTree x){child=x;}
	public String getAreaName(){return areaName;}
	public String[] getAllSubArea(){//获得this的所有子区域
		if(this.child==null)return null; //无子区域
		String[] s=new String[50];//全国的省、自治区、直辖市、港澳台累计不超过50
		AreaBinTree p=this.child;  int n=0;
		while(p!=null){s[n]=p.areaName; p=p.brother; n++;} //扫描所有直接子区域
		String[] subArea=new String[n];
		System.arraycopy(s,0,subArea,0,n);//将s[0..n-1]拷贝到subArea[0..n-1]
		return subArea;
	}
	public AreaBinTree locateSubAreaByName(String name){//根据名称定位子区域的结点
	//注：如当前结点为北京，在北京的子区域中定位“海淀区”节点
		AreaBinTree p=this.child;
		while(p!=null){
			if(name.equals(p.areaName))return p;
			p=p.brother;
		}
		return null;
	}
}
class GenAreaTree{//生成地区树（孩子兄弟表示法）
	char[] str;  //对应原始字符串的字符数组
	String[] area;    //各地区名称
	int areaPos,strPos; //两个数组的当前下标位置
	public GenAreaTree(String fileName){
		//从地址文件中读取信息，处理后生成area和str
		Scanner sc=null;
		try{ sc=new Scanner(new File(fileName));}
		catch(FileNotFoundException e){
			System.out.print("地址文件未找到！"); System.exit(0); }
		String s=""; sc.nextLine();//先空读一行（第一行是文件中的说明）
		while(sc.hasNextLine() )s=s+sc.nextLine();
		str=s.toCharArray();
		area=s.split("[(),]+");//以左右括号和逗号为分隔符，提取地区名称
		areaPos=0; strPos=0;
		//注：用split()分离时，area[0]可能为""
		if(area[0].length()==0)areaPos=1;
	}
	private String getArea(){
		areaPos++;	return area[areaPos-1];
	}
	private void skipWord(){//跨过单词
		int i=strPos;
		while(i<str.length&&str[i]!='('&&str[i]!=')'&&str[i]!=',')i++;
		strPos=i;
	}
	public AreaBinTree genTree(){
		//从文件中读取地址结点，创建孩子兄弟法表示的二叉树
		int i,k;  AreaBinTree root,t,q;
		Stack<AreaBinTree> st=new Stack<AreaBinTree>();
		root=new AreaBinTree("中国"); t=root; i=0;st.push(t);
		while(strPos<str.length){
			if(str[strPos]=='('){
				st.push(new AreaBinTree("("));  strPos++;
				//左括号之后是数据，是t的第一个孩子
				q=new AreaBinTree(getArea());
				t.setChild(q); t=q;st.push(q);
				skipWord(); continue;
			}
			if(str[strPos]==','){
				strPos++;//跳过,逗号后面是数据，将其作为栈顶的【右孩子】
				q=new AreaBinTree(getArea());
				t.setBrother(q);t=q;st.push(q);
				skipWord();  continue;
			}
			if(str[strPos]==')'){//弹出数据，直至'('
				while((st.peek().getAreaName()).equals("(")==false) st.pop();
				//继续弹出"("，之后t是栈顶元素
				st.pop(); t=st.peek();strPos++; //strPos++用于跳过')'
			}
		}
		return root;
	}
}
class AreaComboBoxModel extends AbstractListModel implements ComboBoxModel{
	String[] data;
	String item=null;
	AreaComboBoxModel(String[] d){ data=d;	}
	public Object getElementAt(int index){
		if(index<0||index>=data.length)return null;
		return data[index];
	}
	public int getSize(){ return data.length; }
	public void setSelectedItem(Object anItem){	item=(String)anItem;}
	public Object getSelectedItem(){return item;}
}
@SuppressWarnings("unchecked")//加在类前，用于消除利用AreaComboBoxModel构造JComboBox时的警告信息
class AddressGUI extends JFrame implements ItemListener,ActionListener,FocusListener{
	private JButton bt_ok=new JButton("生成地址");        //生成地址按钮
	private String hintText="  这里输入更详细的地址信息  ";
	private JTextField streetJTF=new JTextField(hintText);
	private JLabel address=new JLabel("  ");//记录生成后的地址信息
	private JComboBox area1,area2,area3,area4; //省、市、县、乡
	private ComboBoxModel emptyModel,provinceModel;//空集model、省会model
	private AreaBinTree root1,root2,root3,root4;//分别记录四级地址的根
	JPanel p1;//由于若存在第四级地址，需要动态调整p1中的成员。因此需要将p1全局化

	public AddressGUI(String areaFileName){
		super("生成配货地址");		setSize(900,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GenAreaTree gen=new GenAreaTree(areaFileName);
		root1=gen.genTree();
		String[] temp={"        "}; //用作JComboBox的占位符，以免选择框缩在一起
		emptyModel=new AreaComboBoxModel(temp);//空数据集
		provinceModel=new AreaComboBoxModel(root1.getAllSubArea());//第一级地址固定
		String title="请在下面的选择框中选择地址信息，并在文本框中补充完善信息";
		p1=new JPanel(); p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.setBorder(BorderFactory.createTitledBorder(title));//注意：设置带有标题的边界
		area1 =new JComboBox(provinceModel);p1.add(area1);p1.add(new JLabel("省/直辖市"));
		area2 =new JComboBox(emptyModel);p1.add(area2);p1.add(new JLabel("市"));
		area3 =new JComboBox(emptyModel);p1.add(area3);p1.add(new JLabel("县/区"));
		area4 =new JComboBox(emptyModel);
		p1.add(streetJTF);
		add(p1,BorderLayout.CENTER);
		JPanel p2=new JPanel(); p2.setLayout(new FlowLayout(FlowLayout.LEFT));

		p2.add(bt_ok);  p2.add(address);

		add(p2,BorderLayout.SOUTH);
		area1.addItemListener(this);area2.addItemListener(this);
		area3.addItemListener(this);area4.addItemListener(this);
		bt_ok.addActionListener(this);
		streetJTF.addFocusListener(this);

		setVisible(true);
	}
	public void itemStateChanged(ItemEvent e){
		String s;
		if(e.getSource()==area1){//选择一级地址，如江西省
		//动作：取得二级地址列表，并清空三级、四级（如果有）列表
			s=(String)area1.getSelectedItem();//获取下拉框选择项信息
			root2=root1.locateSubAreaByName(s);//获取s在树中对应的节点
			area2.setModel(new AreaComboBoxModel(root2.getAllSubArea()));//设定二级地址
			area3.setModel(emptyModel);//清空三级地址
			if(p1.getComponentCount()==8)//即当前界面上存在第四级地址
				{area4.setModel(emptyModel); p1.remove(area4);}
			setVisible(true);   return;
		}
		if(e.getSource()==area2){//选择二级地址，如江西省·九江市
		//动作：取得三级地址列表，并清空四级（如果有）列表
			if(area2.getModel()==emptyModel)return;
			s=(String)area2.getSelectedItem();
			root3=root2.locateSubAreaByName(s);//在区域树中获取所选区域的结点
			area3.setModel(new AreaComboBoxModel(root3.getAllSubArea()));

			if(p1.getComponentCount()==8)//即当前界面上存在第四级地址
				{area4.setModel(emptyModel); p1.remove(area4);}
			setVisible(true);   return;
		}
		if(e.getSource()==area3){//选择三级地址，如江西省·九江市·庐山市
			//动作：取得四级地址列表，如果有则显示
			if(area3.getModel()==emptyModel)return;
			s=(String)area3.getSelectedItem();
			root4=root3.locateSubAreaByName(s);//在区域树中获取所选区域的结点
			String[] subArea=root4.getAllSubArea();
			if(p1.getComponentCount()==7){//若当前未显示第四级地址
				if(subArea==null) return;
				area4.setModel(new AreaComboBoxModel(subArea));
				p1.remove(streetJTF);
				p1.add(area4);p1.add(streetJTF);
				setVisible(true);   return;
			}
			if(p1.getComponentCount()==8){
				if(subArea==null) { p1.remove(area4); setVisible(true); return; }
				area4.setModel(new AreaComboBoxModel(subArea));
				return;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		String s="";
		if(e.getSource()==bt_ok){
			if(area1.getSelectedItem()!=null)s=s+(String)area1.getSelectedItem();
			if(area2.getSelectedItem()!=null)s=s+(String)area2.getSelectedItem();
			if(area3.getSelectedItem()!=null)s=s+(String)area3.getSelectedItem();
			if(area4.getSelectedItem()!=null)s=s+(String)area4.getSelectedItem();
			if(streetJTF.getText().equals("  这里输入更详细的地址信息  ")==false)
				s=s+streetJTF.getText();

			address.setText(s);
			setVisible(true);
		}
	}
	public void focusGained(FocusEvent e){
		if(e.getSource()==streetJTF){
			if(streetJTF.getText().equals(hintText))streetJTF.setText("");
			//setVisible(true);//使用将会让文本框缩小成一条竖线
		}
	}
	public void focusLost(FocusEvent e){
		if(e.getSource()==streetJTF){
			if(streetJTF.getText().equals(""))streetJTF.setText(hintText);
			//setVisible(true);
		}
	}
}
class Ch_4_8{
	public static void main (String[] args){
		SetDefaultFont.setAll(new Font("微软雅黑",Font.BOLD,26));
		new AddressGUI("area.txt");
	}
}
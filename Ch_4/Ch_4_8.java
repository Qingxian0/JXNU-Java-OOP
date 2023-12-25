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

class AreaBinTree{//������㣺childָ��ָ����,brotherָ���ֵ�
	private String areaName;
	private AreaBinTree child,brother;
	public AreaBinTree(String aName){ areaName=aName; }
	public void setBrother(AreaBinTree x){brother=x;}
	public void setChild(AreaBinTree x){child=x;}
	public String getAreaName(){return areaName;}
	public String[] getAllSubArea(){//���this������������
		if(this.child==null)return null; //��������
		String[] s=new String[50];//ȫ����ʡ����������ֱϽ�С��۰�̨�ۼƲ�����50
		AreaBinTree p=this.child;  int n=0;
		while(p!=null){s[n]=p.areaName; p=p.brother; n++;} //ɨ������ֱ��������
		String[] subArea=new String[n];
		System.arraycopy(s,0,subArea,0,n);//��s[0..n-1]������subArea[0..n-1]
		return subArea;
	}
	public AreaBinTree locateSubAreaByName(String name){//�������ƶ�λ������Ľ��
	//ע���統ǰ���Ϊ�������ڱ������������ж�λ�����������ڵ�
		AreaBinTree p=this.child;
		while(p!=null){
			if(name.equals(p.areaName))return p;
			p=p.brother;
		}
		return null;
	}
}
class GenAreaTree{//���ɵ������������ֵܱ�ʾ����
	char[] str;  //��Ӧԭʼ�ַ������ַ�����
	String[] area;    //����������
	int areaPos,strPos; //��������ĵ�ǰ�±�λ��
	public GenAreaTree(String fileName){
		//�ӵ�ַ�ļ��ж�ȡ��Ϣ�����������area��str
		Scanner sc=null;
		try{ sc=new Scanner(new File(fileName));}
		catch(FileNotFoundException e){
			System.out.print("��ַ�ļ�δ�ҵ���"); System.exit(0); }
		String s=""; sc.nextLine();//�ȿն�һ�У���һ�����ļ��е�˵����
		while(sc.hasNextLine() )s=s+sc.nextLine();
		str=s.toCharArray();
		area=s.split("[(),]+");//���������źͶ���Ϊ�ָ�������ȡ��������
		areaPos=0; strPos=0;
		//ע����split()����ʱ��area[0]����Ϊ""
		if(area[0].length()==0)areaPos=1;
	}
	private String getArea(){
		areaPos++;	return area[areaPos-1];
	}
	private void skipWord(){//�������
		int i=strPos;
		while(i<str.length&&str[i]!='('&&str[i]!=')'&&str[i]!=',')i++;
		strPos=i;
	}
	public AreaBinTree genTree(){
		//���ļ��ж�ȡ��ַ��㣬���������ֵܷ���ʾ�Ķ�����
		int i,k;  AreaBinTree root,t,q;
		Stack<AreaBinTree> st=new Stack<AreaBinTree>();
		root=new AreaBinTree("�й�"); t=root; i=0;st.push(t);
		while(strPos<str.length){
			if(str[strPos]=='('){
				st.push(new AreaBinTree("("));  strPos++;
				//������֮�������ݣ���t�ĵ�һ������
				q=new AreaBinTree(getArea());
				t.setChild(q); t=q;st.push(q);
				skipWord(); continue;
			}
			if(str[strPos]==','){
				strPos++;//����,���ź��������ݣ�������Ϊջ���ġ��Һ��ӡ�
				q=new AreaBinTree(getArea());
				t.setBrother(q);t=q;st.push(q);
				skipWord();  continue;
			}
			if(str[strPos]==')'){//�������ݣ�ֱ��'('
				while((st.peek().getAreaName()).equals("(")==false) st.pop();
				//��������"("��֮��t��ջ��Ԫ��
				st.pop(); t=st.peek();strPos++; //strPos++��������')'
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
@SuppressWarnings("unchecked")//������ǰ��������������AreaComboBoxModel����JComboBoxʱ�ľ�����Ϣ
class AddressGUI extends JFrame implements ItemListener,ActionListener,FocusListener{
	private JButton bt_ok=new JButton("���ɵ�ַ");        //���ɵ�ַ��ť
	private String hintText="  �����������ϸ�ĵ�ַ��Ϣ  ";
	private JTextField streetJTF=new JTextField(hintText);
	private JLabel address=new JLabel("  ");//��¼���ɺ�ĵ�ַ��Ϣ
	private JComboBox area1,area2,area3,area4; //ʡ���С��ء���
	private ComboBoxModel emptyModel,provinceModel;//�ռ�model��ʡ��model
	private AreaBinTree root1,root2,root3,root4;//�ֱ��¼�ļ���ַ�ĸ�
	JPanel p1;//���������ڵ��ļ���ַ����Ҫ��̬����p1�еĳ�Ա�������Ҫ��p1ȫ�ֻ�

	public AddressGUI(String areaFileName){
		super("���������ַ");		setSize(900,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GenAreaTree gen=new GenAreaTree(areaFileName);
		root1=gen.genTree();
		String[] temp={"        "}; //����JComboBox��ռλ��������ѡ�������һ��
		emptyModel=new AreaComboBoxModel(temp);//�����ݼ�
		provinceModel=new AreaComboBoxModel(root1.getAllSubArea());//��һ����ַ�̶�
		String title="���������ѡ�����ѡ���ַ��Ϣ�������ı����в���������Ϣ";
		p1=new JPanel(); p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.setBorder(BorderFactory.createTitledBorder(title));//ע�⣺���ô��б���ı߽�
		area1 =new JComboBox(provinceModel);p1.add(area1);p1.add(new JLabel("ʡ/ֱϽ��"));
		area2 =new JComboBox(emptyModel);p1.add(area2);p1.add(new JLabel("��"));
		area3 =new JComboBox(emptyModel);p1.add(area3);p1.add(new JLabel("��/��"));
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
		if(e.getSource()==area1){//ѡ��һ����ַ���罭��ʡ
		//������ȡ�ö�����ַ�б�������������ļ�������У��б�
			s=(String)area1.getSelectedItem();//��ȡ������ѡ������Ϣ
			root2=root1.locateSubAreaByName(s);//��ȡs�����ж�Ӧ�Ľڵ�
			area2.setModel(new AreaComboBoxModel(root2.getAllSubArea()));//�趨������ַ
			area3.setModel(emptyModel);//���������ַ
			if(p1.getComponentCount()==8)//����ǰ�����ϴ��ڵ��ļ���ַ
				{area4.setModel(emptyModel); p1.remove(area4);}
			setVisible(true);   return;
		}
		if(e.getSource()==area2){//ѡ�������ַ���罭��ʡ���Ž���
		//������ȡ��������ַ�б�������ļ�������У��б�
			if(area2.getModel()==emptyModel)return;
			s=(String)area2.getSelectedItem();
			root3=root2.locateSubAreaByName(s);//���������л�ȡ��ѡ����Ľ��
			area3.setModel(new AreaComboBoxModel(root3.getAllSubArea()));

			if(p1.getComponentCount()==8)//����ǰ�����ϴ��ڵ��ļ���ַ
				{area4.setModel(emptyModel); p1.remove(area4);}
			setVisible(true);   return;
		}
		if(e.getSource()==area3){//ѡ��������ַ���罭��ʡ���Ž��С�®ɽ��
			//������ȡ���ļ���ַ�б����������ʾ
			if(area3.getModel()==emptyModel)return;
			s=(String)area3.getSelectedItem();
			root4=root3.locateSubAreaByName(s);//���������л�ȡ��ѡ����Ľ��
			String[] subArea=root4.getAllSubArea();
			if(p1.getComponentCount()==7){//����ǰδ��ʾ���ļ���ַ
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
			if(streetJTF.getText().equals("  �����������ϸ�ĵ�ַ��Ϣ  ")==false)
				s=s+streetJTF.getText();

			address.setText(s);
			setVisible(true);
		}
	}
	public void focusGained(FocusEvent e){
		if(e.getSource()==streetJTF){
			if(streetJTF.getText().equals(hintText))streetJTF.setText("");
			//setVisible(true);//ʹ�ý������ı�����С��һ������
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
		SetDefaultFont.setAll(new Font("΢���ź�",Font.BOLD,26));
		new AddressGUI("area.txt");
	}
}
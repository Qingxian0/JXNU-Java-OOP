import java.util.Scanner;
class Student{//ѧ����Ϣ��ѧ�š��������Ա����䡢�Ƿ�Ա�����ġ���ѧ
	String ID,name;  char sex;  int age;  boolean partyMember;  double math,chinese; 
	static void titleHint(){//���ڶ�ȡ����ʱ�����������ʽ��ʾ��˼����Ϊ����Ϊ��̬��
    	System.out.print("\n������һ��ѧ��������Ctrl+Z��������ʽΪ��");
    	System.out.print("\nѧ�� ���� �Ա� ���� ��Ա ��ѧ ���ģ� ���磺");
		System.out.print("\n001 ���� M 18 true 84.2 93.7\n");
	}
	void read(Scanner sc){//��sc�����ȡ������������ݣ���ȡ������titleHint()��ͬ
		ID=sc.next(); name=sc.next(); //��ȡѧ�š����������ַ���
		sex=sc.next().charAt(0);     //�ȶ�ȡString�����ݣ���"M"����ȡ�����ַ�'M'
		age=sc.nextInt();            //��ȡint������
		partyMember=sc.nextBoolean();        //��ȡboolean������
		math=sc.nextDouble();  
			chinese=sc.nextDouble();  //��ȡdouble������
	}
	public String toString(){//�Զ���Student�Ͷ���������Ϣ
		String xb=(sex=='F'||sex=='f')?"Ů":( (sex=='M'||sex=='m')?"��":"δ֪" );
		String dy=(partyMember==true) ? "�й���Ա":"�ǵ�Ա";
		return ID+" "+name+" "+xb+" "+age+"�� "+dy+" ��ѧ��"+math+" ���ģ�"+chinese; 
	}
}
class BanJi{//�༶���༶����+�洢Student���������+ʵ������
	final int maxN;	  Student [] st;	 int renShu;
	BanJi(int max){ maxN=max; st=new Student[max]; renShu=0; }	
	void add(Student s){st[renShu]=s; renShu++; }//��༶��׷��ѧ��s
	void append( ){	//������β����׷�ӡ�����һ��ѧ������Ctrl+Z����		
		Student.titleHint();  //�����������͸�ʽ��ʾ��
		Scanner sc=new Scanner(System.in); //�ÿհ׷���Ϊ�����
		//Scanner sc =new Scanner(System.in).useDelimiter("[\\n\\r#]+");//��#���س�����Ϊ�����
		Student s;  //����ѭ���������--������������--���������༶
		while(sc.hasNext()==true){ s=new Student(); s.read(sc); add(s); }
	}
	void show(){
		for(int i=0; i<renShu; i++) System.out.println(st[i]);
		System.out.println("�༶�й��� "+renShu+" �ˡ�");
	}
}
class App{
	public static void main (String[] args) {
		BanJi bj=new BanJi(20);   bj.append();
		System.out.println("�༶��Ϣ���£�");	bj.show();
	}
}
/* �Կհ���Ϊ�����
001 ��ӱ F 18 true 73.1 98.6
002 ������ M 19 false 89 76
003 ����   M 20 true 78 99
004 ���� F 18 true 100 20
 */
/* ��#��Ϊ�����
#001###��  ӱ##F#18#true#73.1#98.6
002#������#M#19#false#89#76
003#��  ��#M#20#true#78#99
004#����#F#18#true#100#20
 */
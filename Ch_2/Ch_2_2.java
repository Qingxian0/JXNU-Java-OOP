class Car{
	String  owner,brand,color;  //������Ʒ�ơ���ɫ
	boolean  isActive; //�Ƿ�����
	void start(){
		if(isActive==false){ isActive=true;
			System.out.print(owner+"�ĳ������ˡ�\n");
		}
		else System.out.print(owner+"�������ظ�����������\n");
	}
	void stop(){
		if(isActive==true)
			System.out.print(owner+"�ĳ�ֹͣ�ˡ�\n");
		else System.out.print(owner+"������Ϩ������ֹͣ��\n");
	}
	void go(){
		if(isActive==true)
			System.out.print(owner+"�ĳ���ǰ����������\n");
		else System.out.print(owner+"������Ϩ���޷�ǰ����\n");
	}
	void back(){
		if(isActive==true)
			System.out.print(owner+"�ĳ��ں��ˡ�������\n");
		else System.out.print(owner+"������Ϩ���޷����ˡ�\n");
	}
	void stall(){
		if(isActive==true){  isActive=false;
			System.out.print(owner+"�ĳ�Ϩ���ˡ�������\n");
		}
		else System.out.print(owner+"������Ϩ��������Ϩ��\n");
	}
	Car(String ow, String br, String co){//���캯��
		owner=ow; brand=br; color=co; isActive=false; 
	}
	public String toString(){
		return "����:"+owner+" Ʒ��:"+brand+" ��ɫ:"+color;
	}
}
class App{
	public static void main (String[] args) {
		Car c=new Car("����","����","��ɫ"); //������������
		System.out.println(c);  //ֱ�����������Ϣ����������Car�е�toString()
		c.go(); //������Ϊ��֤
		c.start();c.back(); c.stop(); //������Ϊ��֤
		c.start(); //������Ϊ��֤
		c.stall();
	}	
}
/*����3.2����javaʵ������������
 *���ж�����Ĺ��ܣ��ᵽ��ͷ����ˮ���ᵽ�ϻ��ŵ��ܣ��ᵽ���˺ܸ��ˡ�
 *��������ֵ������Ϊ100�������й�����ҧ�ˣ�����/̩�Ϲ���һ�οɼ���50/1������ֵ��
 *���Ⱥ����˲��ᡢ̩�ϣ�����ͬ�ĳ�����pp��ppҧ���ˡ�
 **/
class BoneSmell{;}//��ͷ��ζ
class MasterSmell{;}//������ζ
class TigerSmell{;}//�ϻ���ζ
class Person{
	private int blood=100;
	public int getBlood(){return blood;}
	public void setBlood(int x){ blood=(x<0||x>100)?blood:x; }
	public String toString(){ return "blood="+blood; }
}
class Animal{//ּ��չ�ִ���Dog���󣬻��Զ�����Animal�Ĺ��캯��
	public Animal(){System.out.println("���ù��캯����Animal()");}
}
class Dog extends Animal{
	public Dog(){System.out.println("���ù��캯����Dog()");}
	void smell(BoneSmell b){ System.out.println("Ŷ����ζ�Ĺ�ͷ��");}
	void smell(TigerSmell t){ System.out.println("�ϻ���̫�����ˣ�Ͷ����");}
	void smell(MasterSmell m){ System.out.println("���ˣ�������ܿ��ģ�"); }
	public void bite(Person p){
		System.out.print("���ձ�׼����ҧ������ʧѪ10�㡣");
		p.setBlood(p.getBlood()-10); //�ܷ񻻳�:p.setBlood(p.blood-10); why?
	}
}
class Teddy extends Dog{//̩��
	public Teddy(){System.out.println("���ù��캯����Teddy()");}
	public void bite(Person p){
		System.out.print("����̩�ϵ�ҧ������ʧѪ1�㡣");
		p.setBlood(p.getBlood()-1);
	}
}
class TibetanMastiff extends Dog{//����
	public TibetanMastiff(){System.out.println("���ù��캯����TibetanMastiff()");}
	public void bite(Person p){
		System.out.print("���ղ����ҧ������ʧѪ50�㡣");
		p.setBlood(p.getBlood()-50);
	}
}

class App{
	public static void main (String[] args) {
		Person p=new Person();
		System.out.println("====��֤�������������ʱ���Զ����ó���Ĺ��캯����====");
		System.out.println("1������Dog����ע����ù��캯���Ĵ��򣩣�");
		Dog d=new Dog();
		System.out.println("2������Teddy����");
		Teddy td=new Teddy();
		System.out.println("3������TibetanMastiff����");
		TibetanMastiff tm=new TibetanMastiff();
		System.out.println("\n====��֤�����ء���̩�϶Բ�ͬ��ζ�в�ͬ��Ӧ====");
		td.smell(new BoneSmell());
		td.smell(new TigerSmell());
		td.smell(new MasterSmell());
		System.out.println("\n====��֤����д��������� pp.bite(); �ļ��弴��====");
		System.out.println("==����pp����װ��ͬ������� pp.bite()��������ͬЧ��==");
		System.out.println("---�˱�ҧǰ��״����"+p);
		System.out.print("1��pp����װ̩�ϣ�pp��ʼҧ�ˣ�");
		Dog pp=td; pp.bite(p); System.out.println(p);
		System.out.print("2��pp����װ���ᣬpp��ʼҧ�ˣ�");
		pp=tm; pp.bite(p); System.out.println(p);
	}
}

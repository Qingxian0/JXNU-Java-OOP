/*
����ĳ��Ϸ�в�ͬ���͵�ʿ����ǹ���Ĺ�����ʽ�ǡ���ǹ�������
������Ĺ�����ʽ�ǡ������������������ױ��Ĺ�����ʽ�ǡ�������ը����
�����ǹ������a�����ױ�����b��
   a.attack(b)���������ǡ�ǹ����ǹ������ױ�����
   b.attack(a)���������ǡ����ױ�������ըǹ������
ģ��ʱ������һ����Ÿ���ʿ����������飬x��ͷ��β��y��β��ͷ��ִ��x.attack(y)��
����ʾ��������Ҫ�õ�toString()

*/
class ShiBing {
	void attack(ShiBing s) {;}
}
class QiangBing extends ShiBing{
	void attack(ShiBing s) {System.out.println("ǹ������ǹ���  "+s);}
	public String toString() { return "ǹ��"; }
}
class ShouLeiBing extends ShiBing{
	void attack(ShiBing s) {System.out.println("���ױ���������ը  "+s);}
	public String toString() { return "���ױ�"; }
}
class HuoJianBing extends ShiBing{
	void attack(ShiBing s) {System.out.println("�����������������  "+s);}
	public String toString() { return "�����"; }
}
class TestAttack{
	static void attack(ShiBing a, ShiBing b) { a.attack(b); }
	public static void main(String[] args) {
		ShiBing[] sb= {new ShouLeiBing(), new HuoJianBing(),new QiangBing()};//һ�鲻ͬʿ������
		for(int i=0,j=sb.length-1; j>=0; i++,j--)
			attack(sb[i],sb[j]); //ģ��ʿ������໥����
	}
}
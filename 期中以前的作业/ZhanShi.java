/*
����ĳ��Ϸ����սʿ���󣬿���ʹ�ã�������use�����������������絶��ǹ������ꪵȡ�
ʹ��ʹ�ò�ͬ��������չʾ����ͬ��Ч����ֱ��������ɣ������磺
�õ����ݹع�������ǹʩչ����ǹ��
�ý�������ׯ�轣����ꪺ�ɨɳ����
�����������ơ�

*/
class WuQi{;}//������
class Dao extends WuQi{	public String toString() { return "��"; } }
class Qiang extends WuQi{	public String toString() { return "ǹ"; } }
class Jian extends WuQi{	public String toString() { return "��"; } }
class Ji extends WuQi{	public String toString() { return "�"; } }
class ZhanShi{
	public void use(Dao x) { System.out.println("��"+x+"�õ����ݹع���");	}
	public void use(Qiang x) { System.out.println("��"+x+"ʩչ����ǹ");	}
	public void use(Jian x) { System.out.println("��"+x+"������ׯ�轣");	}
	public void use(Ji x) { System.out.println("��"+x+"��ɨɳ��");	}
	public void use(WuQi x){ System.out.println("������");	}
}
class TestW{
	public static void main(String[] args) {
		ZhanShi s=new ZhanShi();
		//���������Ϸ�
//		s.use(new Dao());
//		s.use(new Qiang());
//		s.use(new Jian());
//		s.use(new Ji());
		WuQi[] w= {new Dao(), new Qiang(),new Jian(),new Ji() };
		for(WuQi x: w)s.use(x);
	}
}

/**��������5λ��֤��
*    ������֤���ѡ�ּ��ϣ�Random�ཫ���ݺ�ѡ�ַ����Ĵ�С��������ü��ϵ��±�
*/
import java.util.Random;

class App{
	public static void main (String[] args) {
		int len=5;                        //�ٶ�����5λ��֤��
		String s="589abcdfgiTjklW4Vmno1pq0rtu6vwxyhzAB��CeD��EFG��UHI3��7JKsLM2NOPQRSXYZ@#$%&()!+-";
		   //�޹������֤���ѡ�ַ����ַ���
		char[] charSet=s.toCharArray();    //������֤���ѡ�ַ�����
		char[] yzmCh=new char[len];        //���ڴ洢���ɵ���֤��
			
		int pos;
		Random r=new Random(System.currentTimeMillis()); //���ڵ�ǰʱ�䴴�������������
		for(int i=0; i<len; i++){
			pos=r.nextInt(charSet.length); //����һ��int�������pos��0<=pos<charSet.length
			yzmCh[i]=charSet[ pos ];          //��ȡ��Ӧ�ĺ�ѡ�ַ�
		}
		System.out.print("��������֤��Ϊ��"); 
			for(char c: yzmCh)
				System.out.print(" "+c);
	}
}
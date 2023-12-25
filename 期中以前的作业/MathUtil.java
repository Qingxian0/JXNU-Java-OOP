/*
3�������MathUtil�����������Ա int[][] a��Ҫ��
��1����д�޲ι��캯������������Ķ�ά������󣬼���
     ����a��x��Ԫ�أ�0<x<10����
     ÿ��Ԫ��a[i]��y��Ԫ�أ�0<y<10��
     ÿ��Ԫ��a[i][j]��ֵ���������z��0<z<10
��2����дint sum()�����㲢���a���������ݵ��ۼӺ͡�
��3����дvoid showMe()��
��ӡ���a�е�����Ԫ�أ�
�����ʾa�й��ж���Ԫ�أ�
�Լ��ۼӺͽ����Ҫ����ͬa[i]�ų�һ�У���ͬa[i]�ֱ����ڲ�ͬ�С� 
 *//*


import java.util.Random;

public class MathUtil {
	int[][] a;

	MathUtil() {
		int x, y;
		Random r = new Random();
		x = r.nextInt(9) + 1;
		a = new int[x][];
		for (int i = 0; i < x; i++) {
			y = r.nextInt(9) + 1;// ��ǰa[i]��y��Ԫ��
			a[i] = new int[y];
			for (int j = 0; j < y; j++)
				a[i][j] = r.nextInt(9) + 1;
		}
	}

	int sum() {
		int s = 0;
		for (int[] x : a)
			for (int y : x)
				s = s + y;
		return s;
	}

	int sum1() {
		int s = 0;
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				s = s + a[i][j];
		return s;
	}

	void showMe() {
		int i = 0;
		for (int[] x : a) {
			for (int y : x) {
				i++;
				System.out.print(y + "  ");
			}
			System.out.print("\n");
		}
		System.out.print("\n��ǰ���� " + i + " ��Ԫ�ء�");
		System.out.print("\n�ۼӺ��� " + sum());
	}

	public static void main(String[] args) {
		MathUtil m = new MathUtil();
		m.showMe();
	}
}

class App {// ע��s1��s2��������ͬ����ʽ�ָ�
	public static void main(String[] k) {
		String s1, s2;
		String[] w1, w2;
		// �����֡�С��������������Ϊ�ָ���
		String reg = "[^\\d\\.]+";
		s1 = "-1.1 + 2.2* 3.3 /   4.4 -5.5*6.6";
		s2 = "(1.1+2.2*3.3)/4.4-5.5*6.6";
		w1 = s1.split(reg);
		w2 = s2.split(reg);
		for (String x : w1)
			System.out.print(x + "��");
		System.out.println();
		for (String x : w2)
			System.out.print(x + "��");
	}
}
*/

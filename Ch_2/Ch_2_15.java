import java.util.Scanner;
import java.time.LocalDate;//1.8����
import java.time.Period;//1.8��������Ҫ��Period�෽��getYears()��getMonths()��getDays()������
import java.time.temporal.ChronoUnit;//1.8�������������ڵ���ʱ�䵥λ�ڲ���һ��ʱ�䣬�����������롣
import java.time.Duration; //1.8�������ṩ��ʹ�û���ʱ���ֵ�����룬���룩����ʱ�����ķ����� 
import java.time.Instant;  //1.8����
class App{
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("����������(yyyy-mm-dd):");
		String stringDate=sc.nextLine(); //�����ַ����������ڣ���������yyyy-mm-dd
		  //�������룺1998-7-2�����������󣬱����ǣ�1998-07-02
		Instant begin = Instant.now();
		LocalDate brithDate=LocalDate.parse(stringDate);  //���ַ�������ת��������������
		LocalDate today=LocalDate.now();            //��ȡ��ǰ����
		
		//���ڼ���
		int y,m,d;
		//�������ж�ȡ������
		y=brithDate.getYear(); 
		m=brithDate.getMonthValue(); //ע��getMonth()���ص���Ӣ�ı�ʾ����(Month��)����May
		d=brithDate.getDayOfMonth();
		Period p=Period.between(brithDate,today);
		long days=ChronoUnit.DAYS.between(brithDate,today);//ע������ֵ��long��
		System.out.printf("������� %d �� %d �� %d ��",y,m,d);
		System.out.printf("\n����� %d�� %d���� %d�죬���� %d��.",p.getYears(),p.getMonths(),p.getDays(),days);
		System.out.print("\n���Ѿ����� "+days*3+" �ٷ���");
		System.out.print("\n���м�������Ϊ�����£�");
		Instant end = Instant.now();
		System.out.print("\n���������� "+Duration.between(begin, end).toMillis() +"����");
		System.out.print("\n���������� "+Duration.between(begin, end).getSeconds() +"��");
	}
}
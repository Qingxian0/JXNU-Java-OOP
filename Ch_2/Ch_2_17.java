/**����ģ���ַ�Ͷ��ʵ��
*/
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

class TestPF{
	double d,dd,width,high;    //ddΪ��࣬dΪ�볤,width,highΪ����Ŀ�͸�
	//������(0,0)��(100,100)�ĵ�λ�ռ��У���100�������м����1.0���볤����0.5
	TestPF(){width=100; high=100; dd=1; d=0.5; }
	double x,y,x1,y1,jd; //����(x,y)��(x1,y1)��jdΪ����Ƕ�
		//ע�⣺������(x,y)��x��û�����õģ���Ϊ���򲻻�Խ�磨��x=r.nextInt(99)����
		//     �Һ����겻�����ж��������Ƿ��ཻ�����������δ���ɺ�����
	void PF(int n){//n���������
		Random r=new Random();
		int count=0; 
		for(int i=0; i<n; i++){
			jd=r.nextInt(360)+r.nextDouble();
			y=r.nextInt(99)+r.nextDouble();   y1=y+d*Math.sin(jd);
			//����ȡ������Math.floor(2.9)���Ϊ2������ȡ������Math.ceil(1.1)���Ϊ2
			if(Math.floor(y)!=Math.floor(y1)) count++;//������λ��ȣ��ز��ཻ
		}
		System.out.println("Ͷ��"+n+"�Σ��ཻ"+count+"�Σ�PI="+n*1.0/count);
	}
}
class App{
	public static void main (String[] args) {
		Instant begin,end;
		TestPF pf=new TestPF();
		begin = Instant.now(); pf.PF(2122);	end = Instant.now();
		System.out.println("���������� "+Duration.between(begin, end).toMillis() +"����");
		begin = Instant.now(); pf.PF(21220);	end = Instant.now();
		System.out.println("���������� "+Duration.between(begin, end).toMillis() +"����");
		begin = Instant.now(); pf.PF(212200);	end = Instant.now();
		System.out.println("���������� "+Duration.between(begin, end).toMillis() +"����");
		begin = Instant.now(); pf.PF(2122000);	end = Instant.now();
		System.out.println("���������� "+Duration.between(begin, end).toMillis() +"����");
	}
}
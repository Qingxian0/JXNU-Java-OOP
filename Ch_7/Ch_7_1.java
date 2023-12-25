//����չʾ��ͨ��URL�����ȡ�ض�URL��Դ��������ԡ�
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

class Ch_7_1{
	public static void showInfo(String s) throws Exception{//��ȡԶ�̶�����Ϣ
		System.out.print("\n����ַ��"+s);
		URL u=new URL(s);
		System.out.print("\nURL��Ϣ=");
		System.out.print("Э��:"+u.getProtocol());
		System.out.print(",�˿ں�:"+u.getPort());
		System.out.print(",������:"+u.getHost());
		System.out.print(",�ļ���:"+u.getFile());
		System.out.print(",·��:"+u.getPath());

		System.out.print("\nURLConnectio��Ϣ=");
		URLConnection conn = u.openConnection();
		System.out.print("��������:"+conn.getContentType());
		System.out.print(",���ݳ���:"+conn.getContentLength()+"B");
		   //���ļ�����4G���������ȿ���getContentLengthLong()
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  hh:mm");   //�������ڸ�ʽ
		System.out.print(",����޸�ʱ��:"+sdf.format(new Date(conn.getLastModified())));
		//getLastModified()����long�����ݣ���Ҫ����ת��������ʾΪ���ڸ�ʽ

	}
	public static void main (String[] args) throws Exception{
		String[] s=new String[5];
		s[0]="file:///"+System.getProperty("user.dir")+"/test.jpg";
		//ע��System.getProperty("user.dir")����ȡ��ǰĿ¼;
		 //       user.dir��System����Ԥ������뻷����ص�����
		//s[0]="file:/D:/KT/test.jpg"; //ע��file���������1����3��б�ˣ�������2��
		s[1]="http://www.sina.com.cn";
		s[2]="http://www.sina.com.cn:80/index.html";
		s[3]="http://9.gddx.crsky.com/201808/zidanduanxin-v0.8.2.zip";
		s[4]="https://www.baidu.com/baidu?word=java&ie=utf-8&tn=myie2dg&ch=6";
		for(String x:s)showInfo(x);
	  }
}

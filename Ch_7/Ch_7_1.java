//本例展示：通过URL对象获取特定URL资源的相关属性。
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

class Ch_7_1{
	public static void showInfo(String s) throws Exception{//获取远程对象信息
		System.out.print("\n【网址】"+s);
		URL u=new URL(s);
		System.out.print("\nURL信息=");
		System.out.print("协议:"+u.getProtocol());
		System.out.print(",端口号:"+u.getPort());
		System.out.print(",主机名:"+u.getHost());
		System.out.print(",文件名:"+u.getFile());
		System.out.print(",路径:"+u.getPath());

		System.out.print("\nURLConnectio信息=");
		URLConnection conn = u.openConnection();
		System.out.print("内容类型:"+conn.getContentType());
		System.out.print(",内容长度:"+conn.getContentLength()+"B");
		   //若文件超过4G，测量长度可用getContentLengthLong()
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  hh:mm");   //定义日期格式
		System.out.print(",最后修改时间:"+sdf.format(new Date(conn.getLastModified())));
		//getLastModified()返回long型数据，需要经过转换方能显示为日期格式

	}
	public static void main (String[] args) throws Exception{
		String[] s=new String[5];
		s[0]="file:///"+System.getProperty("user.dir")+"/test.jpg";
		//注：System.getProperty("user.dir")：获取当前目录;
		 //       user.dir是System类中预定义的与环境相关的属性
		//s[0]="file:/D:/KT/test.jpg"; //注：file后面可以是1条或3条斜杆，不能是2条
		s[1]="http://www.sina.com.cn";
		s[2]="http://www.sina.com.cn:80/index.html";
		s[3]="http://9.gddx.crsky.com/201808/zidanduanxin-v0.8.2.zip";
		s[4]="https://www.baidu.com/baidu?word=java&ie=utf-8&tn=myie2dg&ch=6";
		for(String x:s)showInfo(x);
	  }
}

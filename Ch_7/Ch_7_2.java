import java.net.URL;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
class Ch_7_2{
	private static String getFileName(String sourceURL){//从地址中提取文件名，若无文件名则返回null
		File f=new File(sourceURL);		String fileName=f.getName();
		char[] a=fileName.toCharArray();
		for(char c: a)//若文件名中包含windows文件名禁用的字符
			if(c=='\\'||c=='/'||c=='|'||c=='*'||c=='?'||c==':'||c=='"'||c=='<'||c=='>') return null;
		return fileName;
	}
	public static void download(String sourceURL, String targetDirName){
		//注意：targetDirName是目标地址的目录名（null则表示存在当前目录下），文件名使用原来的
		String fileName=getFileName(sourceURL);
		if(fileName==null){
			System.out.println("网址不包含文件名，无法下载，程序结束。");
			return;
		}
		//产生目标文件名：若targetDirName==null则文件存于当前目录（与class同一个目录）
		//否则，文件名=目录名\文件名，且若目录不存在就创建目录
		String targetFileName=null;
		if(targetDirName!=null){
			char c=targetDirName.charAt(targetDirName.length()-1);
			if(c!='\\'&&c!='/')//注：在目录尾部追加分隔符/
				targetFileName =targetDirName + '/'+fileName;
			else targetFileName = targetDirName + fileName;
			File tf=new File(targetDirName);
			if (tf.exists()==false) tf.mkdirs();//目标目录不存在则创建
		}
		else targetFileName=fileName; //默认为当前目录
		
		try{//开始下载文件 
			URL u=new URL(sourceURL);
			InputStream in=u.openStream();            //方式一
   			//URLConnection conn = u.openConnection();//方式二
   			//InputStream in = conn.getInputStream(); //方式二

			FileOutputStream fo=new FileOutputStream(targetFileName);
			byte[] buffer = new byte[4028];			int len=0;
			while ((len=in.read(buffer))!=-1)fo.write(buffer, 0, len);
			  //注：不能使用fo.write(b);这种方式每次写入的一定是buffer.length字节，不足则用空字符代替
			in.close(); fo.close();
			System.out.println("文件下载成功：保存到 "+targetFileName);
		}catch(Exception e){e.printStackTrace();}
	}
	public static void main (String[] args) {
			String s1="file:/D:/KT/test.jpg"; 
			String s2="http://9.gddx.crsky.com/201808/zidanduanxin-v0.8.2.zip";
			String s3="https://www.baidu.com/baidu?word=java&ie=utf-8&tn=myie2dg&ch=6";
			download(s1,null);
			download(s2,"d:/java/1/2/3/4");
			download(s3,"d:/java/1");
	}
}

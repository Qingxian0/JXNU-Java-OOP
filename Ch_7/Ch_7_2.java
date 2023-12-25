import java.net.URL;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
class Ch_7_2{
	private static String getFileName(String sourceURL){//�ӵ�ַ����ȡ�ļ����������ļ����򷵻�null
		File f=new File(sourceURL);		String fileName=f.getName();
		char[] a=fileName.toCharArray();
		for(char c: a)//���ļ����а���windows�ļ������õ��ַ�
			if(c=='\\'||c=='/'||c=='|'||c=='*'||c=='?'||c==':'||c=='"'||c=='<'||c=='>') return null;
		return fileName;
	}
	public static void download(String sourceURL, String targetDirName){
		//ע�⣺targetDirName��Ŀ���ַ��Ŀ¼����null���ʾ���ڵ�ǰĿ¼�£����ļ���ʹ��ԭ����
		String fileName=getFileName(sourceURL);
		if(fileName==null){
			System.out.println("��ַ�������ļ������޷����أ����������");
			return;
		}
		//����Ŀ���ļ�������targetDirName==null���ļ����ڵ�ǰĿ¼����classͬһ��Ŀ¼��
		//�����ļ���=Ŀ¼��\�ļ���������Ŀ¼�����ھʹ���Ŀ¼
		String targetFileName=null;
		if(targetDirName!=null){
			char c=targetDirName.charAt(targetDirName.length()-1);
			if(c!='\\'&&c!='/')//ע����Ŀ¼β��׷�ӷָ���/
				targetFileName =targetDirName + '/'+fileName;
			else targetFileName = targetDirName + fileName;
			File tf=new File(targetDirName);
			if (tf.exists()==false) tf.mkdirs();//Ŀ��Ŀ¼�������򴴽�
		}
		else targetFileName=fileName; //Ĭ��Ϊ��ǰĿ¼
		
		try{//��ʼ�����ļ� 
			URL u=new URL(sourceURL);
			InputStream in=u.openStream();            //��ʽһ
   			//URLConnection conn = u.openConnection();//��ʽ��
   			//InputStream in = conn.getInputStream(); //��ʽ��

			FileOutputStream fo=new FileOutputStream(targetFileName);
			byte[] buffer = new byte[4028];			int len=0;
			while ((len=in.read(buffer))!=-1)fo.write(buffer, 0, len);
			  //ע������ʹ��fo.write(b);���ַ�ʽÿ��д���һ����buffer.length�ֽڣ��������ÿ��ַ�����
			in.close(); fo.close();
			System.out.println("�ļ����سɹ������浽 "+targetFileName);
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

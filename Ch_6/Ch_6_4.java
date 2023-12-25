import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ch_6_4 {
	public static void copyFile(String source,String target){//������ͨ�ļ�
		File src = new File(source);
		File tar = new File(target);
		if (src.exists()==false) {
			System.out.println("Դ�ļ������ڣ�");	return;	}
		if (src.isFile()==false) {
			System.out.println("Դ�ļ������ļ���"); return;	}
		if (tar.getParentFile().exists()==false) //Ŀ��Ŀ¼������
			tar.getParentFile().mkdirs();//����Ŀ¼

		//��ʼ���Ʋ���
		try(   FileInputStream in = new FileInputStream(src); // ����ԭ�ļ���������
			   FileOutputStream out = new FileOutputStream(tar);// ����ԭ�ļ��������
			) {
			byte[] buffer = new byte[1024];
			int num = 0; // ��ȡ�ļ����ֽ���
			while ((num = in.read(buffer)) != -1)// ��numΪ-1ʱ��ʾ�ļ��Ѿ�����
				out.write(buffer, 0, num); // ����ȡ���ֽ�д�������
			System.out.println("���Ƴɹ���");
		}catch(IOException e){ System.out.println("File read error��\n"+e);}
	}
	public static void copyDir(String source,String target) {//����Ŀ¼
		File src = new File(source);
		if (src.exists()==false) {
			System.out.println("ԴĿ¼�����ڣ�");	return;}
		if (src.isDirectory()==false) {
			System.out.println("ԴĿ¼����Ŀ¼��");	return;}

		if (target.endsWith(File.separator)==false) {//ע����Ŀ¼β��׷�ӷָ���/
			target = target + File.separator;
		}//ע�⣺��δ׷�ӷָ����������ڴ����ļ�ʱ���Ὣ�ļ�����·�����ϳ�һ�����ļ���
		//  �����������ļ���δ�����ں��ʵ��ļ�����
		File tar = new File(target);
		if (tar.exists()==true) {
			System.out.println("Ŀ��Ŀ¼�Ѵ��ڣ�");	return;}
		if (tar.mkdirs()==false) {//�����ɹ��򷵻�true
				System.out.println("����Ŀ¼ʧ�ܣ�");return;}

		File[] listFiles = src.listFiles();//��ȡsrc�������ļ�
		for (int i = 0; i < listFiles.length; i++){
			if (listFiles[i].isFile())// ������ļ����͸����ļ�
				copyFile(listFiles[i].getAbsolutePath(),target+listFiles[i].getName());
			else //����������Ŀ¼
				copyDir(listFiles[i].getAbsolutePath(),target+listFiles[i].getName());
		}

	}

	public static void main(String args[] ){  int ch;
	   //String source="G:/Java/tt/ht/a.jpg";  //Դ�ļ�
	   //String target1="D:/00/3/1080p/1/2/3/4/b.jpg"; //Ŀ���ļ�
	   //copyFile(source,target1);
	   String sourceDir = "D:/temp";
	   String targetDir = "E:/temp";
		// �����ļ��У����Ŀ����ڣ��򸲸�
	   copyDir(sourceDir, targetDir);
	}
}
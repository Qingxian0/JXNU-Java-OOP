import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ch_6_3 {
	public static void copyFile(String source,String target){//������ͨ�ļ�
		File src = new File(source);
		File tar = new File(target);
		if (src.exists()==false) {
			System.out.println("Դ�ļ������ڣ�");	return;	}
		if (src.isFile()==false) {
			System.out.println("Դ�ļ������ļ���"); return;	}
		if (tar.getParentFile().exists()==false) //Ŀ��Ŀ¼������
			tar.getParentFile().mkdirs();//����Ŀ¼
		
		try( 
			FileInputStream in = new FileInputStream(src); // ����ԭ�ļ���������
			FileOutputStream out = new FileOutputStream(tar);// ����ԭ�ļ��������
			){
			byte[] buffer = new byte[1024];
			int num = 0; // ��ȡ�ļ����ֽ���
			while ((num = in.read(buffer)) != -1)// ��numΪ-1ʱ��ʾ�ļ��Ѿ�����
				out.write(buffer, 0, num); // ����ȡ���ֽ�д�������
			System.out.println("���Ƴɹ���");
		}catch(IOException e){ System.out.println("File read error��\n"+e);}
	}
	public static void main(String args[] ){  int ch;
	   String source="D:/Java/tt/ht/a.jpg";  //Դ�ļ�
	   String target1="D:/00/3/1080p/1/2/3/4/b.jpg"; //Ŀ���ļ�
	   copyFile(source,target1);
	}
}
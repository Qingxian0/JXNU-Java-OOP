import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Vector;
import java.util.Enumeration;
import java.io.SequenceInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

class Ch_6_6{
	//����ʹ������ģʽ��װģʽ������sis���ϲ�=��sis�ж�ȡ����д��Ŀ���ļ�
	//ģʽ1���������ļ����ϲ���һ�������ֽ���
	static SequenceInputStream mode1(String f1,String f2){//��Դ�ļ�f1��f2˳���������������
		SequenceInputStream sis=null;
		try { sis=new SequenceInputStream( new FileInputStream(f1), new FileInputStream(f2));}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		return sis;//ע����Ϊ��Ҫ����sis���ʲ�����Ϊ��Դ��������try��
	}
	//ģʽ2���ö���ļ����ϲ���һ�������ֽ���
	static SequenceInputStream mode2(String[] files){//��files������д��ϲ��ļ�������
        Vector<FileInputStream> v = new Vector<FileInputStream>();//������������
        try{ for(int i=0; i<files.length; i++)  //������������ļ�������
        		v.add( new FileInputStream( files[i] ) );  }
		catch (FileNotFoundException e) { e.printStackTrace(); }
        Enumeration<FileInputStream> en = v.elements();//���������е�����Ԫ��
             //����������ֻ�ܽ���Enumeration�Ͷ���v.elements()����Enumeration������
        return new SequenceInputStream(en);
	}
	static void write(SequenceInputStream sis,String targetName){//��sisд���ļ�
		try( BufferedInputStream in=new BufferedInputStream(sis);
		     BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(targetName));
		){   byte[] buff=new byte[1024]; int len;
             while((len=in.read(buff))!=-1) out.write(buff, 0, len);
         }catch (FileNotFoundException e) {e.printStackTrace(); }
          catch (IOException e) {e.printStackTrace(); }
	}
    public static void main(String[] args) {
    	SequenceInputStream sis1=mode1("ha.txt","hb.txt");
    	String[] f={"ha.txt","hb.txt","hc.txt"};
    		SequenceInputStream sis2=mode2(f);
    	write(sis1,"hab.txt");
    	write(sis2,"habc.txt");
    }
}

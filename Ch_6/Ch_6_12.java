import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class CopyFile{//����ͨ��ʽ���Ƶ����ļ�
	public static void cpByChannel(String source,String target){
		try(FileChannel in=(new FileInputStream(source)).getChannel();
			FileChannel out=(new FileOutputStream(target)).getChannel();){
			long fLen=in.size();  in.transferTo(0,fLen,out);//ʵʩ�ļ�����
		}catch(FileNotFoundException e){ e.printStackTrace(); }
		 catch(IOException e){ e.printStackTrace(); }
	}
	public static void cpByStream(String source,String target){//������ͨ�ļ�
		try(FileInputStream in = new FileInputStream(source); // ����ԭ�ļ���������
			FileOutputStream out = new FileOutputStream(target);// ����ԭ�ļ��������
			){  byte[] buffer = new byte[10240];  int num = 0; // ��ȡ�ļ����ֽ���
			while ((num = in.read(buffer)) != -1)// ��numΪ-1ʱ��ʾ�ļ��Ѿ�����
				out.write(buffer, 0, num); // ����ȡ���ֽ�д�������
		}catch(IOException e){ System.out.println("File read error��\n"+e);}
	}
	public static void cpByThread(String source,String target,int num){
		//num���߳��������ļ������ֳ�num��
		CopyFileThread[] cpt=new CopyFileThread[num];
		long fLen=new File(source).length(); //�ļ��ܳ���
		long bLen=fLen/num;  //ÿ��Ĵ�С��ע�����һ��ҪСЩ
		long pos=0;
		for(int i=0; i<num-1; i++,pos=pos+bLen)
			cpt[i]=new CopyFileThread(source,target,pos,pos+bLen);
		cpt[num-1]=new CopyFileThread(source,target,pos,fLen);
		for(int i=0; i<num; i++) cpt[i].start();
		//try{ for(int i=0; i<num; i++) cpt[i].join();}
		//	catch(InterruptedException e){e.printStackTrace();}
	}
}
class CopyFileThread extends Thread{//�Զ��̷߳�ʽ���Ƶ����ļ�
	private String source,target; //Դ�ļ���Ŀ���ļ����ƣ����Դ�·����
	private long startPos,endPos; //�̶߳�ȡ�������ļ��е�λ��
	public CopyFileThread(String src,String tar,long start, long end){
		source=src; target=tar; startPos=start; endPos=end;
	}
	public void run(){
		try(RandomAccessFile in=new RandomAccessFile(source,"r");//Դ�ļ��ɶ�
			RandomAccessFile out=new RandomAccessFile(target,"rw");//Ŀ���ļ��ɶ�д��ע�������seek()������Ҫ"r"��
			FileChannel inChannel=in.getChannel();
			FileChannel outChannel=out.getChannel();
			){  in.seek(startPos);  out.seek(startPos); //��Դ�ļ���Ŀ���ļ��Ķ�дλ���Ƶ���ʼ��
			//�����ݴ���ǰ����Ҫ��д���ݵ����������flase��ʾ����
			FileLock lock=outChannel.lock(startPos,endPos-startPos,false);
			inChannel.transferTo(startPos,endPos-startPos,outChannel);//ʵʩ���ݴ���
			lock.release();	//�ͷ���
		}catch(Exception e){ e.printStackTrace(); }
	}
}
class App{
	public static void main (String[] args) {
		String src="f:/tt/0.mkv";   String tar1="f:/tt/1.mkv";
		String tar2="f:/tt/2.mkv";	String tar3="f:/tt/3.mkv";
		long bTime=System.currentTimeMillis();
		//CopyFile.cpByStream(src,tar1);
		//CopyFile.cpByChannel(src,tar2);
		CopyFile.cpByThread(src,tar3,4);//�ֳ�4�飬ÿ����һ���߳���ɸ���
		long eTime=System.currentTimeMillis();
		System.out.println("��ʱ��"+(eTime-bTime)+"ms");
	}
}

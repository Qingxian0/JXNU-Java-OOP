import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class CopyFile{//以普通方式复制单个文件
	public static void cpByChannel(String source,String target){
		try(FileChannel in=(new FileInputStream(source)).getChannel();
			FileChannel out=(new FileOutputStream(target)).getChannel();){
			long fLen=in.size();  in.transferTo(0,fLen,out);//实施文件传输
		}catch(FileNotFoundException e){ e.printStackTrace(); }
		 catch(IOException e){ e.printStackTrace(); }
	}
	public static void cpByStream(String source,String target){//复制普通文件
		try(FileInputStream in = new FileInputStream(source); // 创建原文件的输入流
			FileOutputStream out = new FileOutputStream(target);// 创建原文件的输出流
			){  byte[] buffer = new byte[10240];  int num = 0; // 读取文件的字节数
			while ((num = in.read(buffer)) != -1)// 当num为-1时表示文件已经读完
				out.write(buffer, 0, num); // 将读取的字节写入输出流
		}catch(IOException e){ System.out.println("File read error！\n"+e);}
	}
	public static void cpByThread(String source,String target,int num){
		//num是线程数量，文件将被分成num块
		CopyFileThread[] cpt=new CopyFileThread[num];
		long fLen=new File(source).length(); //文件总长度
		long bLen=fLen/num;  //每块的大小，注：最后一块要小些
		long pos=0;
		for(int i=0; i<num-1; i++,pos=pos+bLen)
			cpt[i]=new CopyFileThread(source,target,pos,pos+bLen);
		cpt[num-1]=new CopyFileThread(source,target,pos,fLen);
		for(int i=0; i<num; i++) cpt[i].start();
		//try{ for(int i=0; i<num; i++) cpt[i].join();}
		//	catch(InterruptedException e){e.printStackTrace();}
	}
}
class CopyFileThread extends Thread{//以多线程方式复制单个文件
	private String source,target; //源文件和目标文件名称（可以带路径）
	private long startPos,endPos; //线程读取数据在文件中的位置
	public CopyFileThread(String src,String tar,long start, long end){
		source=src; target=tar; startPos=start; endPos=end;
	}
	public void run(){
		try(RandomAccessFile in=new RandomAccessFile(source,"r");//源文件可读
			RandomAccessFile out=new RandomAccessFile(target,"rw");//目标文件可读写（注：后面的seek()操作需要"r"）
			FileChannel inChannel=in.getChannel();
			FileChannel outChannel=out.getChannel();
			){  in.seek(startPos);  out.seek(startPos); //将源文件、目标文件的读写位置移到起始点
			//在数据传输前，需要对写数据的区域加锁，flase表示加锁
			FileLock lock=outChannel.lock(startPos,endPos-startPos,false);
			inChannel.transferTo(startPos,endPos-startPos,outChannel);//实施数据传输
			lock.release();	//释放锁
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
		CopyFile.cpByThread(src,tar3,4);//分成4块，每块用一个线程完成复制
		long eTime=System.currentTimeMillis();
		System.out.println("耗时："+(eTime-bTime)+"ms");
	}
}

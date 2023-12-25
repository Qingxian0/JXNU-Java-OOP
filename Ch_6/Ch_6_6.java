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
	//下面使用两种模式组装模式输入流sis，合并=从sis中读取数据写入目标文件
	//模式1：用两个文件流合并成一个序列字节流
	static SequenceInputStream mode1(String f1,String f2){//将源文件f1、f2顺次组成序列输入流
		SequenceInputStream sis=null;
		try { sis=new SequenceInputStream( new FileInputStream(f1), new FileInputStream(f2));}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		return sis;//注：因为需要返回sis，故不能作为资源流定义在try中
	}
	//模式2：用多个文件流合并成一个序列字节流
	static SequenceInputStream mode2(String[] files){//将files存放所有待合并文件的名称
        Vector<FileInputStream> v = new Vector<FileInputStream>();//创建向量容器
        try{ for(int i=0; i<files.length; i++)  //向容器中添加文件输入流
        		v.add( new FileInputStream( files[i] ) );  }
		catch (FileNotFoundException e) { e.printStackTrace(); }
        Enumeration<FileInputStream> en = v.elements();//返回向量中的所有元素
             //序列输入流只能接收Enumeration型对象，v.elements()返回Enumeration型引用
        return new SequenceInputStream(en);
	}
	static void write(SequenceInputStream sis,String targetName){//将sis写入文件
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

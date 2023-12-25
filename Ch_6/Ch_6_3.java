import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ch_6_3 {
	public static void copyFile(String source,String target){//复制普通文件
		File src = new File(source);
		File tar = new File(target);
		if (src.exists()==false) {
			System.out.println("源文件不存在！");	return;	}
		if (src.isFile()==false) {
			System.out.println("源文件不是文件！"); return;	}
		if (tar.getParentFile().exists()==false) //目标目录不存在
			tar.getParentFile().mkdirs();//创建目录
		
		try( 
			FileInputStream in = new FileInputStream(src); // 创建原文件的输入流
			FileOutputStream out = new FileOutputStream(tar);// 创建原文件的输出流
			){
			byte[] buffer = new byte[1024];
			int num = 0; // 读取文件的字节数
			while ((num = in.read(buffer)) != -1)// 当num为-1时表示文件已经读完
				out.write(buffer, 0, num); // 将读取的字节写入输出流
			System.out.println("复制成功！");
		}catch(IOException e){ System.out.println("File read error！\n"+e);}
	}
	public static void main(String args[] ){  int ch;
	   String source="D:/Java/tt/ht/a.jpg";  //源文件
	   String target1="D:/00/3/1080p/1/2/3/4/b.jpg"; //目标文件
	   copyFile(source,target1);
	}
}
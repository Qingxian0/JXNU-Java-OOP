import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ch_6_4 {
	public static void copyFile(String source,String target){//复制普通文件
		File src = new File(source);
		File tar = new File(target);
		if (src.exists()==false) {
			System.out.println("源文件不存在！");	return;	}
		if (src.isFile()==false) {
			System.out.println("源文件不是文件！"); return;	}
		if (tar.getParentFile().exists()==false) //目标目录不存在
			tar.getParentFile().mkdirs();//创建目录

		//开始复制操作
		try(   FileInputStream in = new FileInputStream(src); // 创建原文件的输入流
			   FileOutputStream out = new FileOutputStream(tar);// 创建原文件的输出流
			) {
			byte[] buffer = new byte[1024];
			int num = 0; // 读取文件的字节数
			while ((num = in.read(buffer)) != -1)// 当num为-1时表示文件已经读完
				out.write(buffer, 0, num); // 将读取的字节写入输出流
			System.out.println("复制成功！");
		}catch(IOException e){ System.out.println("File read error！\n"+e);}
	}
	public static void copyDir(String source,String target) {//复制目录
		File src = new File(source);
		if (src.exists()==false) {
			System.out.println("源目录不存在！");	return;}
		if (src.isDirectory()==false) {
			System.out.println("源目录不是目录！");	return;}

		if (target.endsWith(File.separator)==false) {//注：在目录尾部追加分隔符/
			target = target + File.separator;
		}//注意：若未追加分隔符，后续在创建文件时，会将文件名和路径名合成一个长文件名
		//  即：将导致文件并未创建在合适的文件夹内
		File tar = new File(target);
		if (tar.exists()==true) {
			System.out.println("目标目录已存在！");	return;}
		if (tar.mkdirs()==false) {//创建成功则返回true
				System.out.println("创建目录失败！");return;}

		File[] listFiles = src.listFiles();//获取src下所有文件
		for (int i = 0; i < listFiles.length; i++){
			if (listFiles[i].isFile())// 如果是文件，就复制文件
				copyFile(listFiles[i].getAbsolutePath(),target+listFiles[i].getName());
			else //否则，则属于目录
				copyDir(listFiles[i].getAbsolutePath(),target+listFiles[i].getName());
		}

	}

	public static void main(String args[] ){  int ch;
	   //String source="G:/Java/tt/ht/a.jpg";  //源文件
	   //String target1="D:/00/3/1080p/1/2/3/4/b.jpg"; //目标文件
	   //copyFile(source,target1);
	   String sourceDir = "D:/temp";
	   String targetDir = "E:/temp";
		// 复制文件夹，如果目标存在，则覆盖
	   copyDir(sourceDir, targetDir);
	}
}
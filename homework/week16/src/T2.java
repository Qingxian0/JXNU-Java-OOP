import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*第二题
    修改示例1，使其将文本文件复制到指定位置的同名文件。注：若目录不存在，则创建相关目录。
 */
public class T2 {
    public static void copyByBuffer(String source, String target) throws IOException {
        try {
            File src = new File(source);
            File tar = new File(target);
            if(!src.exists()){
                System.out.println("源文件不存在");
                return;
            }
            if(!src.isFile()){
                System.out.println("源文件不是文件");
                return;
            }
            if(!tar.getParentFile().exists()){ // 目标目录不存在，创建目录
                System.out.println(tar.getParent());
                tar.getParentFile().mkdirs();
            }
            BufferedReader br = new BufferedReader(new FileReader(src));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tar));
            String s;
            System.out.print("复制策略：基于缓冲区逐行读取，逐行写入。");
            while ((s = br.readLine()) != null) {   //读取一行，并赋值给字符串s
                bw.write(s, 0, s.length());     //将s中从0至s.length()的所有字符写入bw
                bw.newLine();                       //添加行分隔符
            }
            br.close();
            bw.close();
            System.out.print("-->复制结束！\n");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("文件读入错误: \n" + e);
        }
    }

    public static void main(String args[]) throws IOException {
        String source = "a.txt";   //相对路径，当前文件夹下的源文件文件a.txt（必须存在）

        String target = "cc/bb/b.txt";

        copyByBuffer(source, target);
    }
}

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Ch_6_1 {
    public static void copyByBuffer(String source, String target) {
        try {
            String s;
            BufferedReader br = new BufferedReader(new FileReader(source));
            BufferedWriter bw = new BufferedWriter(new FileWriter(target));
            System.out.print("复制策略：基于缓冲区逐行读取，逐行写入。");
            while ((s = br.readLine()) != null) {  //读取一行，并赋值给字符串s
                bw.write(s, 0, s.length());  //将s中从0至s.length()的所有字符写入bw
                bw.newLine();              //添加行分隔符
            }//注意：使用bw.write(s+"\n",0,s.length());方式将会产生大量垃圾内存，why?
            br.close();
            bw.close();
            System.out.print("-->复制结束！\n");
        } catch (FileNotFoundException e) {
            System.out.println("文件没找到！\n" + e);
        } catch (IOException e) {
            System.out.println("File read error！\n" + e);
        }
    }

    public static void main(String args[]) {
        String source, target;
        source = "homework/week16/a.txt";   //相对路径，当前文件夹下的源文件文件a.txt（必须存在）
        //="UTF-8有签名.txt";  ---这个文件不能直接复制，应该指定编码复制
        target = "bb/b.txt"; //相对路径，当前文件夹中的子目录bb（bb必须存在）
        //="u8.txt"; //UTF-8有签名.txt文件复制结果验证：直接复制后存在乱码
        //target="d:\\t\\c.txt";//绝对路径。注：目录d:\t必须存在，否则无法创建文件
        //target="d:/t/d.txt";//注意对比两种斜杠方式的不同，反斜杠是转义符，正斜杠不是
        copyByBuffer(source, target);
    }
}

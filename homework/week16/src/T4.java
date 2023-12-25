/*第四题
    假定有一个1Gb以上的普通文件，请基于①基于文件流复制文件，
    ②基于缓冲字节流复制文件，缓冲区大小为4Kb，并列出各自所用时间（毫秒级）。
 */

/*发现基于缓冲字节流复制的时间远远短于基于文件流复制的时间，原因如下：
    基于文件流复制的方法需要重复地从文件中读一个字节到内存中再写入到输出流，而基于缓冲字节流复制的方法直接
    将文件中的一部分读入到内存缓冲区中，然后逐步从缓冲区中读取数据，避免了每次都要从文件中读取数据的时间开销，提高了性能。

 */
import java.io.*;

public class T4 {
    private static void copyFileUsingFileStreams(String source, String target) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
        } catch (IOException e) {
            System.out.println("file read error! " + e);
        }
    }

    private static void copyFileUsingBufferedStreams(String source, String target) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target))) {
            byte[] buffer = new byte[4 * 1024]; // 4KB 缓冲区大小
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("file read error! " + e);
        }
    }

    public static void main(String[] args) {
        String sourceFilePath = "天气之子.png";
        String targetFilePath = "bb/天气之子 - 副本.png";

        // 基于文件流复制文件
        long startTime = System.currentTimeMillis();
        copyFileUsingFileStreams(sourceFilePath, targetFilePath);
        long endTime = System.currentTimeMillis();
        System.out.println("基于文件流复制时间：" + (endTime - startTime) + " 毫秒");

        // 基于缓冲字节流复制文件
        startTime = System.currentTimeMillis();
        //copyFileUsingBufferedStreams(sourceFilePath, targetFilePath);
        endTime = System.currentTimeMillis();
        System.out.println("基于缓冲字节流复制时间：" + (endTime - startTime) + " 毫秒");
    }
}
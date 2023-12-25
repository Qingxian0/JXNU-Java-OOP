import java.io.*;
import java.util.Arrays;

/*第三题
    请基于字节缓冲流BufferedXxx实现例6.3的功能。
 */

/*BufferedInputStream和InputStream的区别
1. 缓冲：

    FileInputStream 是直接从文件中读取字节，每次调用 read 方法都会导致直接的文件 I/O 操作，而不使用内存缓冲区。
    BufferedInputStream 则在 FileInputStream 的基础上提供了缓冲功能。
    它使用内部缓冲区，每次读取数据时，会先从文件读取一块数据到内存缓冲区，
    然后逐步从缓冲区中读取数据，避免了每次都直接从文件读取数据的开销，从而提高了性能。

2. 性能：

    由于 BufferedInputStream 使用了缓冲机制，通常比直接使用 FileInputStream 具有更好的性能。
    特别是在大量小规模读取的情况下，缓冲可以显著提高读取效率。
3. API 使用：

    BufferedInputStream 提供了一些额外的方法，如 readLine() 等，
    而 FileInputStream 主要提供基本的读取字节的方法。
4. 使用场景：

    FileInputStream 适用于简单的字节文件读取场景，对于大文件或需要频繁读取的情况，
    可以使用 BufferedInputStream 以提高性能。
 */
public class T3 {
    public static void copyFile(String source, String target) {
        File src = new File(source);
        File tar = new File(target);

        if (!src.exists()) {
            System.out.println("源文件不存在");
            return;
        }
        if (!src.isFile()) {
            System.out.println("源文件不是文件");
            return;
        }
        if (!tar.getParentFile().exists()) {
            tar.getParentFile().mkdirs();
        }
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(tar))) {
            byte[] buffer = new byte[1024];
            int num = 0; // 读取文件的字节数
            while ((num = bin.read(buffer)) != -1) { // 当num为-1时表示文件已读完
                // System.out.println(num);        // 查看读取了多少字节
                bout.write(buffer, 0, num);    // 将读取的字节写入输出流
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            System.out.println("File read error! \n" + e);
        }
    }

    public static void main(String[] args) {
        String source = "a.txt";
        String target = "bb/b.txt";
        copyFile(source, target);
    }
}

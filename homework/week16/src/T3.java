import java.io.*;
import java.util.Arrays;

/*������
    ������ֽڻ�����BufferedXxxʵ����6.3�Ĺ��ܡ�
 */

/*BufferedInputStream��InputStream������
1. ���壺

    FileInputStream ��ֱ�Ӵ��ļ��ж�ȡ�ֽڣ�ÿ�ε��� read �������ᵼ��ֱ�ӵ��ļ� I/O ����������ʹ���ڴ滺������
    BufferedInputStream ���� FileInputStream �Ļ������ṩ�˻��幦�ܡ�
    ��ʹ���ڲ���������ÿ�ζ�ȡ����ʱ�����ȴ��ļ���ȡһ�����ݵ��ڴ滺������
    Ȼ���𲽴ӻ������ж�ȡ���ݣ�������ÿ�ζ�ֱ�Ӵ��ļ���ȡ���ݵĿ������Ӷ���������ܡ�

2. ���ܣ�

    ���� BufferedInputStream ʹ���˻�����ƣ�ͨ����ֱ��ʹ�� FileInputStream ���и��õ����ܡ�
    �ر����ڴ���С��ģ��ȡ������£��������������߶�ȡЧ�ʡ�
3. API ʹ�ã�

    BufferedInputStream �ṩ��һЩ����ķ������� readLine() �ȣ�
    �� FileInputStream ��Ҫ�ṩ�����Ķ�ȡ�ֽڵķ�����
4. ʹ�ó�����

    FileInputStream �����ڼ򵥵��ֽ��ļ���ȡ���������ڴ��ļ�����ҪƵ����ȡ�������
    ����ʹ�� BufferedInputStream ��������ܡ�
 */
public class T3 {
    public static void copyFile(String source, String target) {
        File src = new File(source);
        File tar = new File(target);

        if (!src.exists()) {
            System.out.println("Դ�ļ�������");
            return;
        }
        if (!src.isFile()) {
            System.out.println("Դ�ļ������ļ�");
            return;
        }
        if (!tar.getParentFile().exists()) {
            tar.getParentFile().mkdirs();
        }
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(tar))) {
            byte[] buffer = new byte[1024];
            int num = 0; // ��ȡ�ļ����ֽ���
            while ((num = bin.read(buffer)) != -1) { // ��numΪ-1ʱ��ʾ�ļ��Ѷ���
                // System.out.println(num);        // �鿴��ȡ�˶����ֽ�
                bout.write(buffer, 0, num);    // ����ȡ���ֽ�д�������
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

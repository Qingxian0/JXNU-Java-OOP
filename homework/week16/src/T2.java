import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*�ڶ���
    �޸�ʾ��1��ʹ�佫�ı��ļ����Ƶ�ָ��λ�õ�ͬ���ļ���ע����Ŀ¼�����ڣ��򴴽����Ŀ¼��
 */
public class T2 {
    public static void copyByBuffer(String source, String target) throws IOException {
        try {
            File src = new File(source);
            File tar = new File(target);
            if(!src.exists()){
                System.out.println("Դ�ļ�������");
                return;
            }
            if(!src.isFile()){
                System.out.println("Դ�ļ������ļ�");
                return;
            }
            if(!tar.getParentFile().exists()){ // Ŀ��Ŀ¼�����ڣ�����Ŀ¼
                System.out.println(tar.getParent());
                tar.getParentFile().mkdirs();
            }
            BufferedReader br = new BufferedReader(new FileReader(src));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tar));
            String s;
            System.out.print("���Ʋ��ԣ����ڻ��������ж�ȡ������д�롣");
            while ((s = br.readLine()) != null) {   //��ȡһ�У�����ֵ���ַ���s
                bw.write(s, 0, s.length());     //��s�д�0��s.length()�������ַ�д��bw
                bw.newLine();                       //����зָ���
            }
            br.close();
            bw.close();
            System.out.print("-->���ƽ�����\n");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("�ļ��������: \n" + e);
        }
    }

    public static void main(String args[]) throws IOException {
        String source = "a.txt";   //���·������ǰ�ļ����µ�Դ�ļ��ļ�a.txt��������ڣ�

        String target = "cc/bb/b.txt";

        copyByBuffer(source, target);
    }
}

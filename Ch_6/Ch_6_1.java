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
            System.out.print("���Ʋ��ԣ����ڻ��������ж�ȡ������д�롣");
            while ((s = br.readLine()) != null) {  //��ȡһ�У�����ֵ���ַ���s
                bw.write(s, 0, s.length());  //��s�д�0��s.length()�������ַ�д��bw
                bw.newLine();              //����зָ���
            }//ע�⣺ʹ��bw.write(s+"\n",0,s.length());��ʽ����������������ڴ棬why?
            br.close();
            bw.close();
            System.out.print("-->���ƽ�����\n");
        } catch (FileNotFoundException e) {
            System.out.println("�ļ�û�ҵ���\n" + e);
        } catch (IOException e) {
            System.out.println("File read error��\n" + e);
        }
    }

    public static void main(String args[]) {
        String source, target;
        source = "homework/week16/a.txt";   //���·������ǰ�ļ����µ�Դ�ļ��ļ�a.txt��������ڣ�
        //="UTF-8��ǩ��.txt";  ---����ļ�����ֱ�Ӹ��ƣ�Ӧ��ָ�����븴��
        target = "bb/b.txt"; //���·������ǰ�ļ����е���Ŀ¼bb��bb������ڣ�
        //="u8.txt"; //UTF-8��ǩ��.txt�ļ����ƽ����֤��ֱ�Ӹ��ƺ��������
        //target="d:\\t\\c.txt";//����·����ע��Ŀ¼d:\t������ڣ������޷������ļ�
        //target="d:/t/d.txt";//ע��Ա�����б�ܷ�ʽ�Ĳ�ͬ����б����ת�������б�ܲ���
        copyByBuffer(source, target);
    }
}

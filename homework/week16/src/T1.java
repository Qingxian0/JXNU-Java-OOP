import java.io.*;
import java.util.Scanner;
/*��һ��
    ���ı��ļ�ԭ�ظ��ƣ���windows�У�a.txt��ԭ�ظ��ƺ󣬽���ļ���a - ����.txt��
    Ҫ��a)�����з�ʽ���ƣ�ĩβ���ö��1���س��� b ������)����ʽ���ơ�
 */
/*
    Q: Ϊʲô���и���ʱ��ĩβ���һ���س�?
    while((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
    A: ÿ�δ�reader��ȡһ���ı��󣬶���ʹ��writer.write(line)�������ı�д�뵽Ŀ���ļ��У�
    Ȼ��ʹ��writer.newLine()���һ���µ��зָ�����ͨ���ǻس���+���з�����

    �����Ϊʲôÿ���ı�֮����һ������Ļس���ԭ��
    newLine()���������ƽ̨�Ĳ�ͬ������ʵ����зָ�����
    ��Windows�ϣ��зָ���ͨ���ǻس�����\r���ͻ��з���\n������ϣ�
    ����Unix/Linux��ͨ���ǻ��з���\n����

    ������������˵�һ�����⣬��newline()���ٴ�ӡ���ݡ�
 */

public class T1 {
    public static int numOfCopy = 0; // ��ֵ����
    public static void copy1(String source) {
        // ��ȡĿ���ļ��ļ���
        String targetBaseName = source.substring(0, source.lastIndexOf("."));
        if (numOfCopy > 0) {
            targetBaseName += " - ����" + "(" + numOfCopy + ")";
        } else {
            targetBaseName += " - ����";
        }
        String target = targetBaseName + source.substring(source.lastIndexOf("."));

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            String line;
            System.out.println(source + "��ʼ���ƣ�");
            if ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.write(line);
            }
            while ((line = reader.readLine()) != null) {
                System.out.println();
                writer.newLine(); // ��֤���и���ʱ��ĩβ�����һ���س�
                // System.out.print(line);// �ڿ���̨չʾ
                writer.write(line);
            }
            System.out.println("\n" + source + "���Ƴɹ���" + "���ļ�Ϊ��" + target);
            numOfCopy += 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void copy2(String source) {
        // ��ȡĿ���ļ��ļ���
        String targetBaseName = source.substring(0, source.lastIndexOf("."));
        if (numOfCopy > 0) {
            targetBaseName += " - ����" + "(" + numOfCopy + ")";
        } else {
            targetBaseName += " - ����";
        }
        String target = targetBaseName + source.substring(source.lastIndexOf("."));

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            int character;
            System.out.println(source + "��ʼ���ƣ�");
            while ((character = reader.read()) != -1) {
                // System.out.print((char) character); // �ڿ���̨չʾ
                writer.write(character);
            }
            System.out.println("\n" + source + "���Ƴɹ���" + "���ļ�Ϊ��" + target);
            numOfCopy += 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String source = "a.txt";
        System.out.print("������Ҫ���ƵĴ�����");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while ((n--) != 0) {
            copy1(source);
        }
    }
}

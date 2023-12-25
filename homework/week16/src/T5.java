import java.io.*;
import java.util.Scanner;

/*
    ʵ�����²������Ӽ��̶���һ��ѧ����Ϣ��������ѧ�� ���� �Ա� ���� ��Ա ��ѧ ���ģ�������������
    ��������Ϣд���ļ�a.dat��֮���������������a.dat�ж�ȡ���ݡ�
    Ҫ�󣺳������Ҫ��ȷ��д������ݺͶ�����������ȫһ�¡�
 */
public class T5 {
    public static void main(String[] args) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("a.dat"));
             DataInputStream in = new DataInputStream(new FileInputStream("a.dat"))) {
            Scanner sc = new Scanner(System.in);

            System.out.println("������ѧ��");
            int studentID = Integer.parseInt(sc.nextLine());
            out.writeInt(studentID);

            System.out.println("����������");
            String name = sc.nextLine();
            out.writeUTF(name);

            System.out.println("�������Ա�");
            char gender = sc.nextLine().charAt(0);
            out.writeChar(gender);

            System.out.println("����������");
            int age = Integer.parseInt(sc.nextLine());
            out.writeInt(age);

            System.out.println("������������ò");
            String politicalStatus = sc.nextLine();
            out.writeUTF(politicalStatus);

            System.out.println("��������ѧ�ɼ�");
            double mathScore = Double.parseDouble(sc.nextLine());
            out.writeDouble(mathScore);

            System.out.println("���������ĳɼ�");
            double chineseScore = Double.parseDouble(sc.nextLine());
            out.writeDouble(chineseScore);

            System.out.println("ѧ����Ϣ��д���ļ�a.dat");

            // ���ļ���ȡѧ����Ϣ
            System.out.println("���ļ���ȡѧ����Ϣ��");
            int readStudentID = in.readInt();
            String readName = in.readUTF();
            char readGender = in.readChar();
            int readAge = in.readInt();
            String readPoliticalStatus = in.readUTF();
            double readMathScore = in.readDouble();
            double readChineseScore = in.readDouble();

            System.out.println("ѧ��: " + readStudentID);
            System.out.println("����: " + readName);
            System.out.println("�Ա�: " + readGender);
            System.out.println("����: " + readAge);
            System.out.println("������ò: " + readPoliticalStatus);
            System.out.println("��ѧ�ɼ�: " + readMathScore);
            System.out.println("���ĳɼ�: " + readChineseScore);

        } catch (IOException e) {
            System.out.println("File I/O error: " + e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e);
        }
    }
}




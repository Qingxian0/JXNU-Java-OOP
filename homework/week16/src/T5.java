import java.io.*;
import java.util.Scanner;

/*
    实现如下操作：从键盘读入一个学生信息，包括：学号 姓名 性别 年龄 党员 数学 语文，借助数据流，
    将上述信息写入文件a.dat，之后借助数据流，从a.dat中读取数据。
    要求：程序必须要能确保写入的数据和读出的数据完全一致。
 */
public class T5 {
    public static void main(String[] args) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("a.dat"));
             DataInputStream in = new DataInputStream(new FileInputStream("a.dat"))) {
            Scanner sc = new Scanner(System.in);

            System.out.println("请输入学号");
            int studentID = Integer.parseInt(sc.nextLine());
            out.writeInt(studentID);

            System.out.println("请输入姓名");
            String name = sc.nextLine();
            out.writeUTF(name);

            System.out.println("请输入性别");
            char gender = sc.nextLine().charAt(0);
            out.writeChar(gender);

            System.out.println("请输入年龄");
            int age = Integer.parseInt(sc.nextLine());
            out.writeInt(age);

            System.out.println("请输入政治面貌");
            String politicalStatus = sc.nextLine();
            out.writeUTF(politicalStatus);

            System.out.println("请输入数学成绩");
            double mathScore = Double.parseDouble(sc.nextLine());
            out.writeDouble(mathScore);

            System.out.println("请输入语文成绩");
            double chineseScore = Double.parseDouble(sc.nextLine());
            out.writeDouble(chineseScore);

            System.out.println("学生信息已写入文件a.dat");

            // 从文件读取学生信息
            System.out.println("从文件读取学生信息：");
            int readStudentID = in.readInt();
            String readName = in.readUTF();
            char readGender = in.readChar();
            int readAge = in.readInt();
            String readPoliticalStatus = in.readUTF();
            double readMathScore = in.readDouble();
            double readChineseScore = in.readDouble();

            System.out.println("学号: " + readStudentID);
            System.out.println("姓名: " + readName);
            System.out.println("性别: " + readGender);
            System.out.println("年龄: " + readAge);
            System.out.println("政治面貌: " + readPoliticalStatus);
            System.out.println("数学成绩: " + readMathScore);
            System.out.println("语文成绩: " + readChineseScore);

        } catch (IOException e) {
            System.out.println("File I/O error: " + e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e);
        }
    }
}




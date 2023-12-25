import java.io.*;
import java.util.Scanner;
/*第一题
    将文本文件原地复制，如windows中，a.txt，原地复制后，结果文件是a - 副本.txt。
    要求：a)以逐行方式复制，末尾不得多出1个回车； b 以逐字)符方式复制。
 */
/*
    Q: 为什么逐行复制时，末尾多出一个回车?
    while((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
    A: 每次从reader读取一行文本后，都会使用writer.write(line)将该行文本写入到目标文件中，
    然后使用writer.newLine()添加一个新的行分隔符（通常是回车符+换行符）。

    这就是为什么每行文本之后都有一个额外的回车的原因。
    newLine()方法会根据平台的不同，添加适当的行分隔符。
    在Windows上，行分隔符通常是回车符（\r）和换行符（\n）的组合，
    而在Unix/Linux上通常是换行符（\n）。

    解决方法：除了第一行以外，先newline()，再打印内容。
 */

public class T1 {
    public static int numOfCopy = 0; // 赋值次数
    public static void copy1(String source) {
        // 获取目标文件文件名
        String targetBaseName = source.substring(0, source.lastIndexOf("."));
        if (numOfCopy > 0) {
            targetBaseName += " - 副本" + "(" + numOfCopy + ")";
        } else {
            targetBaseName += " - 副本";
        }
        String target = targetBaseName + source.substring(source.lastIndexOf("."));

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            String line;
            System.out.println(source + "开始复制！");
            if ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.write(line);
            }
            while ((line = reader.readLine()) != null) {
                System.out.println();
                writer.newLine(); // 保证逐行复制时，末尾不多出一个回车
                // System.out.print(line);// 在控制台展示
                writer.write(line);
            }
            System.out.println("\n" + source + "复制成功！" + "新文件为：" + target);
            numOfCopy += 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void copy2(String source) {
        // 获取目标文件文件名
        String targetBaseName = source.substring(0, source.lastIndexOf("."));
        if (numOfCopy > 0) {
            targetBaseName += " - 副本" + "(" + numOfCopy + ")";
        } else {
            targetBaseName += " - 副本";
        }
        String target = targetBaseName + source.substring(source.lastIndexOf("."));

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            int character;
            System.out.println(source + "开始复制！");
            while ((character = reader.read()) != -1) {
                // System.out.print((char) character); // 在控制台展示
                writer.write(character);
            }
            System.out.println("\n" + source + "复制成功！" + "新文件为：" + target);
            numOfCopy += 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String source = "a.txt";
        System.out.print("请输入要复制的次数：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while ((n--) != 0) {
            copy1(source);
        }
    }
}

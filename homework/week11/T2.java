package homework.week11;

import java.util.Scanner;

// 第二题
public class T2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            try{
                System.out.print("请输入第一个整数 x: ");
                int x = scanner.nextInt();

                System.out.print("请输入第二个整数 y: ");
                int y = scanner.nextInt();

                System.out.print("请输入操作符 op (+, -, *, /): ");
                char op = scanner.next().charAt(0);

                calculate(x, y, op);
                break;
            } catch (Exception e) {
                System.out.println("输入格式错误，请重新输入整数和操作符。");
                scanner.nextLine();  // 清空输入缓冲区
            }
        }
        scanner.close();
    }

    private static void calculate(int x, int y, char op) throws DivideByZeroException {
        double result;
        switch (op) {
            case '+':
                result = x + y;
                System.out.println("计算结果: " + result);
                break;
            case '-':
                result = x - y;
                System.out.println("计算结果: " + result);
                break;
            case '*':
                result = x * y;
                System.out.println("计算结果: " + result);
                break;
            case '/':
                if (y != 0) {
                    result = (double) x / y;
                    System.out.println("计算结果: " + result);
                } else {
                    System.out.println("除数不能为零，请重新输入。");
                    throw new DivideByZeroException();
                }
                break;
            default:
                System.out.println("不支持的操作符，请重新输入。");
        }
    }
}

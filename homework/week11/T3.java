package homework.week11;
// 第三题
import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException() {
        System.out.println("除数不能为零，请重新输入。");
    }
}

class OverflowException extends Exception {
    public OverflowException() {
        System.out.println("计算结果越界，请重新输入。");
    }
}

class IllegalOperator extends Exception{
    public IllegalOperator(){
        System.out.println("不支持的操作符，请重新输入。");
    }
}

public class T3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("请输入第一个整数 x: ");
                int x = scanner.nextInt();

                System.out.print("请输入第二个整数 y: ");
                int y = scanner.nextInt();

                System.out.print("请输入操作符 op (+, -, *, /): ");
                char op = scanner.next().charAt(0);

                int res = compute(x, y, op);
                System.out.println("正确结果为: " + res);
                break;  // 如果计算成功，跳出循环
            } catch (IllegalOperator | DivideByZeroException | OverflowException e) {
                scanner.nextLine();  // 清空输入缓冲区
            } catch (Exception e){
                System.out.println("输入格式错误，请重新输入整数和操作符。");
                scanner.nextLine();  // 清空输入缓冲区
            }
        }

        scanner.close();
    }


    private static int compute(int x, int y, char z) throws DivideByZeroException, OverflowException, IllegalOperator {
        int result;
        switch (z) {
            case '+':
                result = Math.addExact(x, y);
                break;
            case '-':
                result = Math.subtractExact(x, y);
                break;
            case '*':
                result = Math.multiplyExact(x, y);
                break;
            case '/':
                if (y != 0) {
                    result = x / y;
                } else {
                    throw new DivideByZeroException();
                }
                break;
            default:
                throw new IllegalOperator();
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            throw new OverflowException();
        }

        return result;
    }
}


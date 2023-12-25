/*
考题：
模拟共享打印：在构造函数中输入：作业名、作业的内容（字符串数组），构造多个作业对象。要求：
（1）用伪造start()方式构造和启动线程；
（2）这些作业完整的输出，输出内容不得交叉，但各作业的输出次序不定；
（3）在所有均结束后，输出“所有打印作业执行结束。”
 */

class Printer{
    public void Print(String msg){
        System.out.println(msg);
    }
}

class Homework implements Runnable{
    private int id;
    private String content;
    private Printer printer;
    Thread t;
    public Homework(int id, String content, Printer printer){
        this.id = id;
        this.content = content;
        this.printer = printer;
        t = new Thread(this);
    }

    public void start(){
        t.start();
    }

    public void join() throws InterruptedException {
        t.join();
    }

    @Override
    public void run(){
        synchronized (printer){
            printer.Print("打印作业" + this.id + " 为: " + this.content);
        }
    }
}

public class T5 {
    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();
        String[] homeworks = {"1 2 3", "a b c", "e f g"};
        Homework homework1 = new Homework(1, homeworks[0], printer);
        Homework homework2 = new Homework(2, homeworks[1], printer);
        Homework homework3 = new Homework(3, homeworks[2], printer);
        homework1.start();
        homework2.start();
        homework3.start(); // 132 123 start()执行顺序不代表线程的启动顺序

        homework1.join();
        homework2.join();
        homework3.join();
    }
}

/*第一题
    设计一个线程类，给定x，则输出50以内x的倍数，请给出4种创建线程方式。
 */
/*Java创建线程的五种方式：
    1. 继承于Thread类
    2. 实现Runnable接口
    3. 实现Callable接口
    4. 使用线程池
    5. 使用匿名类
 */

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class Thread1 extends Thread { // 第一种方法：直接继承Thread类
    private String name;
    private int begin, end;

    public Thread1(String name, int begin, int end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.print(this.name + " begin: ");
        for (int i = begin; i <= end; i++) {
            System.out.print(i + " ");
        }
        System.out.print(this.name + " end. ");
    }
}

class Thread2 implements Runnable { // 第二种方法：实现Runnable接口
    private String name;
    private int begin, end;
    Thread t;

    public Thread2(String name, int begin, int end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
        t = new Thread(this);
    }

    @Override
    public void run() {
        System.out.print(this.name + " begin: ");
        for (int i = begin; i <= end; i++) {
            System.out.print(i + " ");
        }
        System.out.print(this.name + " end. ");
    }

    public void start() {
        t.start();
    }
}

class Thread3 implements Callable { // 第三种方法
    private String name;
    private int begin, end;
    Thread t;
    public Thread3(String name, int begin, int end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
        t = new Thread(new FutureTask(this));
    }

    @Override
    public Object call() throws Exception {
        System.out.print(this.name + " begin: ");
        for (int i = begin; i <= end; i++) {
            System.out.print(i + " ");
        }
        System.out.print(this.name + " end. ");
        return null;
    }

    public void start(){
        t.start();
    }
}

public class T1 {
    public static void main(String[] args) {
        /*
        第四种方法：匿名内部类
        Thread t1 = new Thread(() -> {
            System.out.print("A" + " begin: ");
            for (int i = 2; i <= 30; i++) {
                System.out.print(i + " ");
            }
            System.out.print("A" + " end. ");
        });
        Thread t2 = new Thread(() -> {
            System.out.print("B" + " begin: ");
            for (int i = 3; i <= 30; i++) {
                System.out.print(i + " ");
            }
            System.out.print("B" + " end. ");
        });
        Thread t3 = new Thread(() -> {
            System.out.print("C" + " begin: ");
            for (int i = 5; i <= 30; i++) {
                System.out.print(i + " ");
            }
            System.out.print("C" + " end. ");
        });
         */


        Thread3 t1 = new Thread3("A", 2, 30);
        Thread3 t2 = new Thread3("B", 3, 30);
        Thread3 t3 = new Thread3("C", 5, 30);

        t1.start();
        t2.start();
        t3.start();
        // 多线程编程中，各线程的start执行顺序和实际运行顺序并不一致，start()只是代表准备运行状态。
    }
}

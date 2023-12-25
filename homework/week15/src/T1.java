/*第一题
    P224页第1题：甲乙丙线程分别输出等量的字符，
    如甲线程输出：1、3、5、7、9；
    乙线程输出：2、4、6、8、0；
    丙线程数出：a、b、c、d、e。
    main线程输出：线程开始、线程结束。
    借助同步机制、sleep()方法、join()方法，实现每隔一秒输出一个字符。
    且最终结果为：（注：下面是【唯一的】输出结果）
    线程开始：1 a 2 3 b 4 5 c 6 7 d 8 9 e 0 线程结束
 */
class PrinterLine {
    private int state;

    public PrinterLine() {
        this.state = 0;
    }

    public void print(int myState, int nextState, String s){
        while(this.state != myState){
            try{
                wait();
            }catch (InterruptedException e){}
        }
        System.out.print(s + " ");
        this.state = nextState; // 改状态、发通知
        notifyAll();
    }
}

class Printer extends Thread{
    private PrinterLine pl;
    private String[] data;
    private int myState, nextState;

    public Printer(PrinterLine pl, String[] data, int myState, int nextState) {
        this.pl = pl;
        this.data = data;
        this.myState = myState;
        this.nextState = nextState;
    }

    @Override
    public void run(){
        for(int i = 0; i < this.data.length; i++) {
            synchronized (pl){
                pl.print(myState, nextState, this.data[i]);
                // System.out.print(this.data[i] + " ");
//                try{ // 实现每隔一秒输出一个字符
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){}
            }
        }
    }
}

public class T1 {
    public static void main(String[] args) throws InterruptedException {
        String flag = "111";
        System.out.println("线程开始");
        String[] str1 = {"1", "3", "5", "7", "9"};
        String[] str2 = {"2", "4", "6", "8", "0"};
        String[] str3 = {"a", "b", "c", "d", "e"};
        PrinterLine pl = new PrinterLine();
        Printer p1 = new Printer(pl, str1, 0, 2);
        Printer p2 = new Printer(pl, str2, 1, 0);
        Printer p3 = new Printer(pl, str3, 2, 1);
        p1.start();
        p2.start();
        p3.start();
        p1.join();
        p2.join();
        p3.join();
        System.out.println("\n线程结束");
    }
}

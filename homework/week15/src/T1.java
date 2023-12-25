/*��һ��
    P224ҳ��1�⣺���ұ��̷ֱ߳�����������ַ���
    ����߳������1��3��5��7��9��
    ���߳������2��4��6��8��0��
    ���߳�������a��b��c��d��e��
    main�߳�������߳̿�ʼ���߳̽�����
    ����ͬ�����ơ�sleep()������join()������ʵ��ÿ��һ�����һ���ַ���
    �����ս��Ϊ����ע�������ǡ�Ψһ�ġ���������
    �߳̿�ʼ��1 a 2 3 b 4 5 c 6 7 d 8 9 e 0 �߳̽���
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
        this.state = nextState; // ��״̬����֪ͨ
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
//                try{ // ʵ��ÿ��һ�����һ���ַ�
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){}
            }
        }
    }
}

public class T1 {
    public static void main(String[] args) throws InterruptedException {
        String flag = "111";
        System.out.println("�߳̿�ʼ");
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
        System.out.println("\n�߳̽���");
    }
}

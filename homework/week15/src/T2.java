import java.time.Duration;
import java.time.Instant;
/*
 * P225页第3题： 给定容量1234的int型数组，依次存储数据0~99。
 * 采用3个线程计算其累加和，要求每个线程每次计算10个元素
 * （注：最后一次计算量可能不足10个）。在所有线程结束后，输出最终计算结果。
 * */
class WareHouse{
    private final int [] arr;
    private int pos;
    private long sum;

    public WareHouse(int[] arr, int pos){
        this.arr = arr;
        this.pos = pos;
    }

    public int getPos(){
        return this.pos;
    }

    public void setPos(int step){
        this.pos += step;
    }

    public void setSum(int add){
        this.sum += add;
    }

    public int getLength(){
        return this.arr.length;
    }

    public int getElement(int index){
        return this.arr[index];
    }

    public long getSum() {
        return this.sum;
    }
}

class T extends Thread{
    private final WareHouse w;
    private final int step;

    public T(WareHouse w, int step){
        this.w = w;
        this.step = step;
    }

    @Override
    public void run(){
        while(w.getPos() < w.getLength()){
            synchronized (w){
                int pos = w.getPos();
                int maxLen = w.getLength();
                int right = (pos + step > maxLen) ? maxLen - 1: pos + step - 1;
                for(int i = pos; i <= right; i++){
                    w.setSum(w.getElement(i));
                }
                w.setPos(step);
            }
        }
    }
}


class Worker {
    private final int cnt, len, step;

    // 线程数 cnt
    // 数组规模长度 len
    // 每个线程计算的步长 step
    public Worker(int cnt, int len, int step) {
        this.cnt = cnt;
        this.len = len;
        this.step = step;
    }

    public void work() {
        T[] t = new T[cnt];
        int[] arr = new int[len];
        for (int i = 1; i <= len; i++)
            arr[i - 1] = i;
        // 传入作为待计算的数组arr
        // 起始下标位置为 0
        WareHouse w = new WareHouse(arr, 0);
        for (int i = 0; i < cnt; i++)
            t[i] = new T(w, step);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++)
            t[i].start();
        try {
            for (int i = 0; i < cnt; i++)
                t[i].join();
        } catch (InterruptedException ignored) {
        }
        long end = System.currentTimeMillis();
        System.out.println("总和是" + w.getSum());
        System.out.println("程序运行了" + (end - begin) + "毫秒");
    }
}

class T2 {
    public static void main(String[] args) {
        final int cnt = 3, len = 1234, step = 10;

        Worker X = new Worker(cnt, len, step);
        X.work();

        Worker z = new Worker(4, 199999999, step);
        z.work();

        Worker x = new Worker(3, 199999999, step);
        x.work();

        Worker y = new Worker(2, 199999999, step);
        y.work();

        Worker w = new Worker(1, 199999999, step);
        w.work();

        // 总和是761995
        // 程序运行了1毫秒
        // 总和是19999999900000000
        // 程序运行了1030毫秒
        // 总和是19999999900000000
        // 程序运行了876毫秒
        // 总和是19999999900000000
        // 程序运行了761毫秒
        // 总和是19999999900000000
        // 程序运行了440毫秒

        // *观察发现，即使在运算量较大的情况，并行的线程数越少，所需时间越短，由此猜测
        // *是由于单纯计算和所需要的时间资源较少，相比之下，使用线程所需要的创建、切换和同步等时间资源开销更大；
        // *导致线程数量越多，反而越低效
    }
}

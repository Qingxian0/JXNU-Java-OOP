import java.time.Duration;
import java.time.Instant;
/*
 * P225ҳ��3�⣺ ��������1234��int�����飬���δ洢����0~99��
 * ����3���̼߳������ۼӺͣ�Ҫ��ÿ���߳�ÿ�μ���10��Ԫ��
 * ��ע�����һ�μ��������ܲ���10�������������߳̽�����������ռ�������
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

    // �߳��� cnt
    // �����ģ���� len
    // ÿ���̼߳���Ĳ��� step
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
        // ������Ϊ�����������arr
        // ��ʼ�±�λ��Ϊ 0
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
        System.out.println("�ܺ���" + w.getSum());
        System.out.println("����������" + (end - begin) + "����");
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

        // �ܺ���761995
        // ����������1����
        // �ܺ���19999999900000000
        // ����������1030����
        // �ܺ���19999999900000000
        // ����������876����
        // �ܺ���19999999900000000
        // ����������761����
        // �ܺ���19999999900000000
        // ����������440����

        // *�۲췢�֣���ʹ���������ϴ����������е��߳���Խ�٣�����ʱ��Խ�̣��ɴ˲²�
        // *�����ڵ������������Ҫ��ʱ����Դ���٣����֮�£�ʹ���߳�����Ҫ�Ĵ������л���ͬ����ʱ����Դ��������
        // *�����߳�����Խ�࣬����Խ��Ч
    }
}

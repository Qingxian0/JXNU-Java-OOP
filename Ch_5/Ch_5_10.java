import java.io.File;
import java.io.FileWriter;

class Data {
    private final int size; //数组的最大容量
    private int pos, step, count;//数组的起始位置、有序步长、当前未完成归并的线程数

    public Data(int arraySize, int firstStep) {
        size = arraySize;
        step = firstStep;
    }

    public synchronized int getPos() { //专供冒泡排序用，用于获得一个step段
        int x = pos;
        pos = pos + step;
        return x;
    }

    public void init() {
        pos = 0;
    }//冒泡线程序一趟结束后执行

    public synchronized void sortOver() {
        count--;
        notifyAll();
    }//必须要唤醒其他线程，否则会死锁

    public synchronized void setPosStep(PosStep ps) {
        while (pos >= size && count != 0)//wait()相关判断要最先执行
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        if (pos < size) {//本趟归并未完成，【开启本趟新归并】
            //尚有待分配数据段（注：此句不能放在while之前，why?）
            ps.pos = pos;
            ps.step = step;
            pos = pos + step * 2;
            count++;
            return;
        }
        if (pos >= size && count == 0 && 2 * step < size) {//【开启新趟】
            step = step * 2;
            ps.pos = 0;
            ps.step = step;
            pos = 0 + step * 2;
            count++;
            notifyAll();
            return;
        }  //成功分配后，必然会进行排序，故count++
        if (pos >= size && count == 0 && 2 * step >= size) {//【归并结束】
            ps.pos = 0;
            ps.step = 2 * step;
            return;
        }
    }
}

class BubbleThread extends Thread {//冒泡线程类
    private int[] a;    //a存放待排序数据
    private Data data;   //排序线程需要从data中获取排序段的起始点下标
    private int size, pos, step; //分别为待排序数组的长度、起始位置、冒泡排序段长度

    public BubbleThread(Data d, int[] a1, int step1) {
        data = d;
        a = a1;
        step = step1;
        size = a.length;
    }

    private void bubbleSort() {//对起点为pos，长度为step的数据段实施冒泡排序
        int i, j, t, flag, endPos;
        endPos = pos + step - 1;
        if (endPos >= size) endPos = size - 1;
        for (i = endPos; i > pos; i--) {
            flag = 0;
            for (j = pos; j < i; j++)
                if (a[j] > a[j + 1]) {
                    flag = 1;
                    t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            if (flag == 0) break;
        }
    }

    public void run() {
        pos = data.getPos();
        while (pos < size) {//排完一次/段 -- 再取下一个pos
            bubbleSort();
            pos = data.getPos();
        }
    }

    public static void sort(Data data, int[] a1, int step1, int threadNum) {
        if (threadNum <= 0) return;
        BubbleThread[] bt = new BubbleThread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            bt[i] = new BubbleThread(data, a1, step1);
            bt[i].start();
        }
        for (int i = 0; i < threadNum; i++)
            try {
                bt[i].join();
            } catch (Exception e) {
                ;
            }
        System.out.print("\n冒泡排序线程结束。。。。");
    }
}

class PosStep {
    int step, pos;
} //每个归并线程都有自己的 PosStep 对象

class MeregeThread extends Thread {//归并线程类
    private int[] a, b; //a存放待排序数据，b是辅助数组
    private Data data;
    private final int size;
    private PosStep ps = new PosStep();//存储线程待排序段起始点、步长

    public MeregeThread(Data d, int[] a1, int[] b1) {
        a = a1;
        b = b1;
        size = a.length;
        data = d;
    }

    private void meregeOne() {//实施一次归并，并将结果回写到a
        int i, j, k, endi, endj; //首先计算两个归并段的起始/末尾下标
        i = ps.pos;
        j = i + ps.step; //i、j分别是两个归并段的起始下标
        if (j >= size) return; //只有一个归并段：不归并、不回写，直接返回
        k = i; //下面定有两个归并段，设置endi、endj的值
        endi = j - 1;
        endj = j + ps.step - 1;
        if (endj >= size) endj = size - 1;
        while (i <= endi && j <= endj)
            if (a[i] <= a[j]) {
                b[k] = a[i];
                k++;
                i++;
            } else {
                b[k] = a[j];
                k++;
                j++;
            }
        while (i <= endi) {
            b[k] = a[i];
            k++;
            i++;
        }
        while (j <= endj) {
            b[k] = a[j];
            k++;
            j++;
        }
        for (i = ps.pos; i <= endj; i++) a[i] = b[i]; //将结果回写到a
    }

    public void run() {
        data.setPosStep(ps); //获取归并的起始点和步长
        while (ps.step < size) {// 实施归并 -- 告知归并结束 -- 再次获取数据
            meregeOne();
            data.sortOver();
            data.setPosStep(ps);
        }
    }

    public static void sort(Data data, int[] a1, int[] b1, int threadNum) {
        if (threadNum <= 0) return;
        MeregeThread[] mt = new MeregeThread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            mt[i] = new MeregeThread(data, a1, b1);
            mt[i].start();
        }
        for (int i = 0; i < threadNum; i++)
            try {
                mt[i].join();
            } catch (Exception e) {
                ;
            }
        System.out.print("\n归并排序结束。。。");
    }
}

class Ch_5_10 {
    public static void main(String[] args) {
        //final int max=100000;//100000000;
        final int max = 100000000;
        int[] a = new int[max];
        int[] b = new int[max];  //a存储待排序元素，b是辅助空间
        for (int i = 0; i < a.length; i++) a[i] = max - i;//产生测试数据
        //int mergeThreadNum=0,bubbleThreadNum=1,firstStep=100000;  //线程数量、初始步长
        //int mergeThreadNum=1,bubbleThreadNum=0,firstStep=1;  //只用单线程归并完成排序
        int mergeThreadNum = 3, bubbleThreadNum = 3, firstStep = 50;  //冒泡3线程、归并3线程、初始步长50
        Data data = new Data(a.length, firstStep);
        long startTime = System.currentTimeMillis(); //计时
        BubbleThread.sort(data, a, firstStep, bubbleThreadNum); //用冒泡排序初始化有序段
        data.init(); //将data中的起始点重置为0。至于步长，在创建data时已设为firstStep
        MeregeThread.sort(data, a, b, mergeThreadNum);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("\n排序花费时间为：" + time + "毫秒");
        for (int i = 1; i < a.length; i++) //对结果的有序性实施验证
            if (a[i - 1] > a[i]) {
                System.out.printf("\n失败：a[%d]=%d，a[%d]=%d", i - 1, a[i - 1], i, a[i]);
                return;
            }
        System.out.printf("\n成功通过验证。");
    }
}

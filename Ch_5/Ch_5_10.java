import java.io.File;
import java.io.FileWriter;

class Data {
    private final int size; //������������
    private int pos, step, count;//�������ʼλ�á����򲽳�����ǰδ��ɹ鲢���߳���

    public Data(int arraySize, int firstStep) {
        size = arraySize;
        step = firstStep;
    }

    public synchronized int getPos() { //ר��ð�������ã����ڻ��һ��step��
        int x = pos;
        pos = pos + step;
        return x;
    }

    public void init() {
        pos = 0;
    }//ð���߳���һ�˽�����ִ��

    public synchronized void sortOver() {
        count--;
        notifyAll();
    }//����Ҫ���������̣߳����������

    public synchronized void setPosStep(PosStep ps) {
        while (pos >= size && count != 0)//wait()����ж�Ҫ����ִ��
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        if (pos < size) {//���˹鲢δ��ɣ������������¹鲢��
            //���д��������ݶΣ�ע���˾䲻�ܷ���while֮ǰ��why?��
            ps.pos = pos;
            ps.step = step;
            pos = pos + step * 2;
            count++;
            return;
        }
        if (pos >= size && count == 0 && 2 * step < size) {//���������ˡ�
            step = step * 2;
            ps.pos = 0;
            ps.step = step;
            pos = 0 + step * 2;
            count++;
            notifyAll();
            return;
        }  //�ɹ�����󣬱�Ȼ��������򣬹�count++
        if (pos >= size && count == 0 && 2 * step >= size) {//���鲢������
            ps.pos = 0;
            ps.step = 2 * step;
            return;
        }
    }
}

class BubbleThread extends Thread {//ð���߳���
    private int[] a;    //a��Ŵ���������
    private Data data;   //�����߳���Ҫ��data�л�ȡ����ε���ʼ���±�
    private int size, pos, step; //�ֱ�Ϊ����������ĳ��ȡ���ʼλ�á�ð������γ���

    public BubbleThread(Data d, int[] a1, int step1) {
        data = d;
        a = a1;
        step = step1;
        size = a.length;
    }

    private void bubbleSort() {//�����Ϊpos������Ϊstep�����ݶ�ʵʩð������
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
        while (pos < size) {//����һ��/�� -- ��ȡ��һ��pos
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
        System.out.print("\nð�������߳̽�����������");
    }
}

class PosStep {
    int step, pos;
} //ÿ���鲢�̶߳����Լ��� PosStep ����

class MeregeThread extends Thread {//�鲢�߳���
    private int[] a, b; //a��Ŵ��������ݣ�b�Ǹ�������
    private Data data;
    private final int size;
    private PosStep ps = new PosStep();//�洢�̴߳��������ʼ�㡢����

    public MeregeThread(Data d, int[] a1, int[] b1) {
        a = a1;
        b = b1;
        size = a.length;
        data = d;
    }

    private void meregeOne() {//ʵʩһ�ι鲢�����������д��a
        int i, j, k, endi, endj; //���ȼ��������鲢�ε���ʼ/ĩβ�±�
        i = ps.pos;
        j = i + ps.step; //i��j�ֱ��������鲢�ε���ʼ�±�
        if (j >= size) return; //ֻ��һ���鲢�Σ����鲢������д��ֱ�ӷ���
        k = i; //���涨�������鲢�Σ�����endi��endj��ֵ
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
        for (i = ps.pos; i <= endj; i++) a[i] = b[i]; //�������д��a
    }

    public void run() {
        data.setPosStep(ps); //��ȡ�鲢����ʼ��Ͳ���
        while (ps.step < size) {// ʵʩ�鲢 -- ��֪�鲢���� -- �ٴλ�ȡ����
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
        System.out.print("\n�鲢�������������");
    }
}

class Ch_5_10 {
    public static void main(String[] args) {
        //final int max=100000;//100000000;
        final int max = 100000000;
        int[] a = new int[max];
        int[] b = new int[max];  //a�洢������Ԫ�أ�b�Ǹ����ռ�
        for (int i = 0; i < a.length; i++) a[i] = max - i;//������������
        //int mergeThreadNum=0,bubbleThreadNum=1,firstStep=100000;  //�߳���������ʼ����
        //int mergeThreadNum=1,bubbleThreadNum=0,firstStep=1;  //ֻ�õ��̹߳鲢�������
        int mergeThreadNum = 3, bubbleThreadNum = 3, firstStep = 50;  //ð��3�̡߳��鲢3�̡߳���ʼ����50
        Data data = new Data(a.length, firstStep);
        long startTime = System.currentTimeMillis(); //��ʱ
        BubbleThread.sort(data, a, firstStep, bubbleThreadNum); //��ð�������ʼ�������
        data.init(); //��data�е���ʼ������Ϊ0�����ڲ������ڴ���dataʱ����ΪfirstStep
        MeregeThread.sort(data, a, b, mergeThreadNum);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("\n���򻨷�ʱ��Ϊ��" + time + "����");
        for (int i = 1; i < a.length; i++) //�Խ����������ʵʩ��֤
            if (a[i - 1] > a[i]) {
                System.out.printf("\nʧ�ܣ�a[%d]=%d��a[%d]=%d", i - 1, a[i - 1], i, a[i]);
                return;
            }
        System.out.printf("\n�ɹ�ͨ����֤��");
    }
}

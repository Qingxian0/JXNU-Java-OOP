import java.util.Random;
/*第二题
    使用顺序型程序生成100万个随机数，并计算时间，使用4个线程生成100万个随机数，
    并计算总时间。比较二者时间上的区别，并分析原因。
    注意：处理方法需要用到join()--start()时作为开始时间，
    只有当所有线程均结束时，才能作为结束时间
 */

/*答：

 */
class RandomNumber extends Thread{
    private String name;
    private Random r;
    private int num;
    public RandomNumber(String name, int num){
        this.name = name;
        this.r = new Random();
        this.num = num;
    }
    @Override
    public void run(){
        for (int i = 0; i < num; i++) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            int randomNum = r.nextInt();
        }
    }
}

public class T2 {
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();

        Thread t1 = new RandomNumber("第一条线程", 2500000);
        Thread t2 = new RandomNumber("第二条线程", 2500000);
        Thread t3 = new RandomNumber("第三条线程", 2500000);
        Thread t4 = new RandomNumber("第四条线程", 2500000);

        // 计算总时间
        long totalStartTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        long totalEndTime = System.currentTimeMillis();

        // 顺序执行
        long seqStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
//            Thread.sleep(1);
            int randomNum = r.nextInt();
        }
        long seqEndTime = System.currentTimeMillis();

        System.out.println("四条线程的总时间: " + (totalEndTime - totalStartTime) + " milliseconds");
        System.out.println("顺序执行的总时间: " + (seqEndTime - seqStartTime) + " milliseconds");
    }
}

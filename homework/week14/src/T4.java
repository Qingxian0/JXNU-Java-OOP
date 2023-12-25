/*第四题
    模拟秒杀购物：假设有如下商品类 Goods{ private int num=5; //表示商品数量为5
    public int getNum(){return num; }
    public void setNum(int x){ num=x; } }
    有甲、乙、丙、丁4个购物车线程，分别装入1-3件商品（随机数产生）。
    若商品数量-购买数量为正数，则秒杀成功，否则为秒杀失败，并在所有秒杀结束后输出结果。
    如： 商品数量共计：5件。 秒杀开始：…… 秒杀结束，
    结果如下： 甲：购物车有 3 件商品，购买 成功！ 乙：购物车有 1 件商品，购买 成功！
    丙：购物车有 1 件商品，购买 成功！ 丁：购物车有 2 件商品，购买 失败！
 */

import java.util.Random;

class Goods{
    private int num;
    public Goods(int num){
        this.num = num;
    }
    public int getNum(){
        return this.num;
    }
    public void setNum(int x){
        this.num = x;
    }
}

class ShoppingCart extends Thread{
    private String name;
    private Goods goods;
    private int buyNum;
    public ShoppingCart(String name, Goods goods, int buyNum){
        this.name = name;
        this.goods = goods;
        this.buyNum = buyNum;
    }
    @Override
    public void run(){
        synchronized (goods) {
            System.out.print(this.name + "购物车有" + this.buyNum + "件商品 ");
            if (goods.getNum() - buyNum >= 0) {
                goods.setNum(goods.getNum() - this.buyNum);
                System.out.println("购买成功，购物车有 " + buyNum + " 件商品");
            } else {
                System.out.println("购买失败");
            }
        }
    }
}

public class T4 {
    public static void main(String[] args) throws InterruptedException {
        Goods goods = new Goods(5);
        Random r = new Random(System.currentTimeMillis());
        int buyNum1 = r.nextInt(1, 4);
        int buyNum2 = r.nextInt(1, 4);
        int buyNum3 = r.nextInt(1, 4);
        ShoppingCart sc1 = new ShoppingCart("甲", goods, buyNum1);
        ShoppingCart sc2 = new ShoppingCart("乙", goods, buyNum2);
        ShoppingCart sc3 = new ShoppingCart("丙", goods, buyNum3);

        System.out.println("商品数量共计" + goods.getNum() + " ");
        System.out.println("秒杀开始");
        sc1.start();
        sc2.start();
        sc3.start();

        // 等待所有线程完成
        sc1.join();
        sc2.join();
        sc3.join();
        System.out.println("秒杀结束");
    }
}

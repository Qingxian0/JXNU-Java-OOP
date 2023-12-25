/*第三题
    模拟实现ATM存取款示例
 */

class Account{
    private String name;
    private int balance; // 余额
    public Account(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int read(){ // 查余额
        return this.balance;
    }

    public void write(int x){ // 改余额
        this.balance = x;
    }
}

class ATM extends Thread{
    private Account a;
    private int value; // 存/取金额，正数表示存入，负数则表示取出

    public ATM(Account a, int value){
        this.a = a;
        this.value = value;
    }

    @Override
    public void run() { //查-改-查
        synchronized (a) { //临界区：以原子方式访问临界资源a
            String opStr = value > 0 ? "存入" + value + " 元" : "取出: " + -1 * value + " 元";
            System.out.print(a.getName() + "现有: " + a.read() + "元，" + opStr);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                ;
            }
            a.write(a.read() + value);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                ;
            }
            System.out.println("，现有余额: " + a.read() + " 元。");
        }
    }
}


public class T3 {
    public static void main(String[] args) {
        Account a = new Account("用户", 600);
        ATM atm1 = new ATM(a, -300);
        ATM atm2 = new ATM(a, 200);
        atm1.start();
        atm2.start();
    }
}

/*
3、功能：设计一组类和接口，满足如下要求：（编译成功即可）
a.游戏模拟：兵种包括轰炸机、直升机、重型坦克、轻型坦克、音波坦克、步兵、飞行兵
b.轰炸机、直升机、飞行兵属于空军；步兵、轻型坦克、重型坦克属于陆军，音波坦克属于水陆两栖；
（提示：设计时，音波坦克内有标记inWater，在构造时填入，若为true，则表示目前音波坦克在水中，否则就是在陆地上）
c.轻型坦克、步兵只能攻击陆军，音波坦克只能攻击空军，轰炸机可攻击陆军、海军；重型坦克可攻击陆军、空军，直升机、飞行兵可攻击海军、陆军、空军；
并验证设计效果。（即向兵种变量填入海/陆/空军兵种，检测a.attack(b)的输出）
 */
package homework.week10.Answers;

//【【【注意：加入海陆两栖后，变化：步兵攻击陆军，陆军可能是水中的音波坦克 == 不能攻击
//解决策略：先单独判断两栖类，再判断单类，详见轰炸机类
interface 陆军 {
} // 陆军标签

interface 空军 {
} // 空军标签

interface 海军 {
} // 空军标签

interface 海陆两栖 extends 海军, 陆军 {// 由于可以在陆地或海洋，故需要能获得该兵种的位置标记
    boolean isInWater();
}

interface 可攻击陆军 { // 可以攻击陆军属性标签
    default void attack(陆军 x) {
        System.out.println("攻击陆军");
    }
}

interface 可攻击空军 { // 可以攻击空军属性标签
    default void attack(空军 x) {
        System.out.println("攻击空军");
    }
}

interface 可攻击海军 { // 可以攻击空军属性标签
    default void attack(海军 x) {
        System.out.println("攻击海军");
    }
}

abstract class 兵种 { // 兵种总基类
    private String type;

    public 兵种(String n) {
        type = n;
    }

    public abstract void attack(兵种 x);

    public String attackInfo(兵种 x) {
        return type + " 遇见 " + x;
    }

    public String toString() {
        return type;
    }
}

abstract class 飞行器 extends 兵种 implements 空军 { // 飞行器基类(都是空军)
    public 飞行器(String n) {
        super(n);
    }
}

abstract class 坦克 extends 兵种 { // 坦克基类
    public 坦克(String n) {
        super(n);
    }
}

abstract class 士兵 extends 兵种 { // 士兵基类
    public 士兵(String n) {
        super(n);
    }
}

// 飞行器子类
class 轰炸机 extends 飞行器 implements 可攻击陆军, 可攻击海军 { // 轰炸机类
    public 轰炸机() {
        super("轰炸机");
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 海陆两栖) {// 先单独判断两栖类，
            海陆两栖 y = (海陆两栖) x;
            if (y.isInWater() == true)
                attack((海军) x);
            else
                attack((陆军) x);
        } // 再判断单型类
        else if (x instanceof 陆军)
            attack((陆军) x); // 此处是单型的陆军
        else if (x instanceof 海军)
            attack((海军) x); // 此处是单型的海军
        else
            System.out.print("不能攻击");
    }
}

class 直升机 extends 飞行器 implements 可攻击陆军, 可攻击空军, 可攻击海军 { // 直升机类
    public 直升机() {
        super("直升机");
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 海陆两栖) {// 先单独判断两栖类，
            海陆两栖 y = (海陆两栖) x;
            if (y.isInWater() == true)
                attack((海军) x);
            else
                attack((陆军) x);
        } // 再判断单型类
        else if (x instanceof 陆军)
            attack((陆军) x);
        else if (x instanceof 空军)
            attack((空军) x);
        else if (x instanceof 海军)
            attack((海军) x);
        else
            System.out.print("不能攻击");
    }
}

// 坦克子类
class 重型坦克 extends 坦克 implements 陆军, 可攻击陆军, 可攻击空军 { // 重型坦克类
    public 重型坦克() {
        super("重型坦克");
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 海陆两栖) {// 先单独判断两栖类，
            海陆两栖 y = (海陆两栖) x;
            if (y.isInWater() == true)
                System.out.print("不能攻击");
            else
                attack((陆军) x);
        } // 再判断单型类
        else if (x instanceof 陆军)
            attack((陆军) x);
        else if (x instanceof 空军)
            attack((空军) x);
        else
            System.out.print("不能攻击");
    }
}

class 轻型坦克 extends 坦克 implements 陆军, 可攻击陆军 { // 轻型坦克类
    public 轻型坦克() {
        super("轻型坦克");
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 海陆两栖) {// 先单独判断两栖类，
            海陆两栖 y = (海陆两栖) x;
            if (y.isInWater() == true)
                System.out.print("不能攻击");
            else
                attack((陆军) x);
        } // 再判断单型类
        else if (x instanceof 陆军)
            attack((陆军) x);
        else
            System.out.print("不能攻击");
    }
}

class 音波坦克 extends 坦克 implements 海陆两栖, 可攻击空军 { // 音波坦克类
    private boolean inWater;

    public 音波坦克(boolean flag) {
        super("音波坦克");
        inWater = flag;
    }

    public boolean isInWater() {
        return inWater;
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 空军)
            attack((空军) x);
        else
            System.out.print("不能攻击");
    }

    public String toString() {
        String s = "在陆地";
        if (inWater == true)
            s = "在水中";
        return "音波坦克-" + s;
    }
}

// 士兵子类
class 步兵 extends 士兵 implements 陆军, 可攻击陆军 { // 步兵类
    public 步兵() {
        super("步兵");
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 海陆两栖) {// 先单独判断两栖类，
            海陆两栖 y = (海陆两栖) x;
            if (y.isInWater() == true)
                System.out.print("不能攻击");
            else
                attack((陆军) x);
        } // 再判断单型类
        else if (x instanceof 陆军)
            attack((陆军) x);
        else
            System.out.print("不能攻击");
    }
}

class 飞行兵 extends 士兵 implements 空军, 可攻击陆军, 可攻击海军, 可攻击空军 { // 飞行兵类
    public 飞行兵() {
        super("飞行兵");
    }

    public void attack(兵种 x) {
        System.out.print("\n" + attackInfo(x) + ": ");
        if (x instanceof 海陆两栖) {// 先单独判断两栖类，
            海陆两栖 y = (海陆两栖) x;
            if (y.isInWater() == true)
                attack((海军) x);
            else
                attack((陆军) x);
        } // 再判断单型类
        else if (x instanceof 陆军)
            attack((陆军) x);
        else if (x instanceof 空军)
            attack((空军) x);
        else
            System.out.print("不能攻击");
    }
}

class App {
    public static void main(String[] args) {
        兵种[] a = { new 轰炸机(), new 直升机(), new 重型坦克(), new 轻型坦克(), new 步兵(), new 飞行兵() };
        兵种[] b = { new 音波坦克(false), new 音波坦克(true), new 音波坦克(true), new 音波坦克(false),
                new 直升机(), new 轰炸机() };
        for (int i = 0; i < a.length; i++)
            a[i].attack(b[i]);
    }
}
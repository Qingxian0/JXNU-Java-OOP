【第10周】课后作业
1、用接口方式实现智能形状识别器：能识别类型、面积即可

2、构造超级英雄类SuperHero，该类有String型常量属性name，其值固定为“谭雅”、“伊万”。要求：
在程序运行期间，至多只能造出两个对象，其中一个是谭雅，另一个是伊万。

3、微信程序中可以插装青桔等小程序。某公司编写了一款名为“菜场”的软件，计划支持插装小程序，插装标准为CaiChangApplet，符合该标准的小程序都可插装到菜场中。该标准内有start()、run()、stop()三项功能。main中演示了插装“白菜”、“萝卜”等小程序，以及插装后的运行结果。要求，菜场中至多能插装10个小程序。为简化处理，添加小程序时，可不考虑小程序插满的情形。请完成CaiChangApplet标准的设计，以及菜场（CaiChang）、白菜（BaiCai）、萝卜（LuoBo）等类的设计。
```java
class homework.week10.Answers.App1{
public static void main (String[] args) {
        CaiChang c=new CaiChang();
        BaiCai bc=new BaiCai();
        LuoBo lb=new LuoBo();
        c.add(bc); c.add(lb);
        c.run();
    }
}
```
【运行结果为：】
白菜启动    白菜运行    白菜停止
萝卜启动    萝卜运行    萝卜停止

4、功能：设计一组类和接口，满足如下要求：（编译成功即可）
a.游戏模拟：兵种包括轰炸机、直升机、重型坦克、轻型坦克、音波坦克、步兵、飞行兵
b.轰炸机、直升机、飞行兵属于空军；步兵、轻型坦克、重型坦克属于陆军，音波坦克属于水陆两栖；
（提示：设计时，音波坦克内有标记inWater，在构造时填入，若为true，则表示目前音波坦克在水中，否则就是在陆地上）
例如：步兵遇到音波坦克，若后者在陆地，则可当成陆军，即步兵可以攻击陆地上的音波坦克；
若音波坦克在水中，则步兵无法攻击水中的音波坦克。
c.轻型坦克、步兵只能攻击陆军，音波坦克只能攻击空军，轰炸机可攻击陆军、海军；重型坦克可攻击陆军、空军，直升机、飞行兵可攻击海军、陆军、空军；
并验证设计效果。（即向兵种变量填入海/陆/空军兵种，检测a.attack(b)的输出）
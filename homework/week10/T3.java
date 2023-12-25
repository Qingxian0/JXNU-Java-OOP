package homework.week10;

interface CaiChangApplet{
    public abstract void start();
    public abstract void run();
    public abstract void stop();
}

class BaiCai implements CaiChangApplet{

    @Override
    public void start() {
        System.out.println("白菜启动");
    }

    @Override
    public void run() {
        System.out.println("白菜运行");
    }

    @Override
    public void stop() {
        System.out.println("白菜停止");
    }
}


class LuoBo implements CaiChangApplet {// 萝卜小程序
    // 实现接口，就是对接口中定义的所有抽象方法提供方法体
    // 注意：由于接口中的方法均为public，故下面重写的方法必须加上public
    @Override
    public void start() {
        System.out.println("萝卜启动");
    };
    @Override
    public void run() {
        System.out.println("萝卜运行");
    };
    @Override
    public void stop() {
        System.out.println("萝卜停止");
    };
}

class CaiChang{
    CaiChangApplet[] CC = new CaiChangApplet[10];
    void add(CaiChangApplet c){
        for(int i = 0; i < CC.length; i++){
            if(CC[i] == null){
                CC[i] = c;
                return;
            }
        }
    }

    void run(){
        for (int i = 0; i < CC.length; i++) {
            if(CC[i] != null){
                CC[i].start();
                CC[i].run();
                CC[i].stop();
            }
        }
    }
}

class T3{
    public static void main(String[] args) {
        CaiChang c = new CaiChang();
        BaiCai bc = new BaiCai();
        LuoBo lb = new LuoBo();
        c.add(bc);
        c.add(lb);
        c.run();
    }
}



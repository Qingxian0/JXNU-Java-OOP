package homework.week10;

class superHero1{
    private String name;
    private static int count = 0, total = 1;
    private superHero1(String s){
        this.name = s;
        count++;
    }

    private static boolean limit(String s){
        return (s.equals("谭雅") || s.equals("伊万")) && count < total;
    }

    public static superHero1 create(String s){
        if(!limit(s)){
            return null;
        }
        return new superHero1(s);
    }

    @Override
    public String toString() {
        return "superHero1{" +
                "name='" + name + '\'' +
                '}';
    }
}

class superHero2{
    private String name;
    private static int ty = 0, yw = 0, tyTotal = 1, ywTotal = 1; // 谭雅、伊万的数量
    private superHero2(String s){
        this.name = s;
        if(s.equals("谭雅")){
            ty++;
        }else if(s.equals("伊万")){
            yw++;
        }
    }

    private static boolean limit(String s){
        return (s.equals("谭雅") || s.equals("伊万")) && ty < tyTotal && yw < ywTotal;
    }

    public static superHero2 create(String s){
        if(!limit(s)){
            return null;
        }
        return new superHero2(s);
    }

    @Override
    public String toString() {
        return "superHero2{" +
                "name='" + name + '\'' +
                '}';
    }
}

class App1 {
    public static void main(String[] args) {
        // 验证1：在程序运行期间，至多只能造出一个对象，要么是谭雅，要么是伊万。
        superHero1[] s1 = new superHero1[4];
        s1[0] = superHero1.create("谭雅");
        s1[1] = superHero1.create("伊万");
        s1[2] = superHero1.create("谭雅");
        s1[3] = superHero1.create("伊万");
        System.out.print("测试  至多只能造出一个对象，要么是谭雅，要么是伊万：\n");
        for (superHero1 x : s1)
            System.out.print(x + "、");

        superHero2[] s2 = new superHero2[4];
        s2[0] = superHero2.create("谭雅");
        s2[1] = superHero2.create("伊万");
        s2[2] = superHero2.create("谭雅");
        s2[3] = superHero2.create("伊万");
        System.out.print("\n\n测试  至多只能造2个，且2个时只能是1个谭雅、1个伊万：\n");
        for (superHero2 x : s2)
            System.out.print(x + "、");
    }
}


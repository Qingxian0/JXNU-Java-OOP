
// （4）（4）练习2.3第4题：对例2.8中的学生，用带头结点的单链表作为班级的存储结构，实现向班级中批量增加学生信息，以及打印输出班级中所有学生的信息。
import java.util.Scanner;

class Student {
    int age;
    String name, ID;
    char xingbie;
    boolean PartyMember;
    double math, chinese;

    public static void showHint() {// 读取数据时给出的输入格式提示。
        System.out.printf("请输入一组学生信息,以ctrl+Z结束输入:");
        System.out.printf("\n   年龄 姓名 学号 性别 是否党员 数学 语文");
        System.out.printf("\n例如18  张三 001 M true 93.5 94.3\n");
    }

    void read(Scanner sc) {// 读取数据，并赋值
        age = sc.nextInt();
        name = sc.next();
        ID = sc.next();
        xingbie = sc.next().charAt(0);// 读取字符串再读取字符串的第一个首字母
        PartyMember = sc.nextBoolean();
        math = sc.nextDouble();
        chinese = sc.nextDouble();
    }

    public String toString() {
        String xb = (xingbie == 'F' || xingbie == 'f') ? "女" : ((xingbie == 'M' || xingbie == 'm') ? "男" : "未知");
        String party = "";
        if (PartyMember == true)
            party = "党员";
        else
            party = "非党员";
        return age + " " + name + " " + ID + " " + xb + " " + party + " " + math + " " + chinese;
    }
}

class Banji {
    StudentNode bj = new StudentNode();// 相当于班级的表头结点
    int renShu;

    class StudentNode {// 创建类
        Student data;// 数据为Student型
        StudentNode next;

        StudentNode() {
            data = new Student();
        }// 注意：每新造一个链表结点，结点内的data都要造出一个学生对象，以便后面用read对其赋值。否则，将产生空指针引用错

        int append() {// 有返回值，以便为班级类中的人数赋值
            Scanner sc = new Scanner(System.in);
            Student.showHint();// 提示
            StudentNode tail, s;
            tail = this;
            int i = 0;
            while (sc.hasNextInt() == true) {
                s = new StudentNode(); // 造结点
                s.data.read(sc); // 赋值
                tail.next = s; // 插在表尾
                tail = s; // 修改尾部
                i++;
            }
            return i;
        }

        void show() {
            for (StudentNode p = this.next; p != null; p = p.next)
                System.out.println(p.data); // 注意：p.data才是Student型，才有对应的toString()
        }
    }

    // 下面是班级对外提供的服务
    void append() {
        renShu = bj.append();
    }

    void show() {
        bj.show();
        System.out.print("班级共有 " + renShu + " 人.");
    }
}

class App {
    public static void main(String[] arge) {
        Banji bj = new Banji();
        bj.append();
        System.out.printf("班级信息如下：\n");
        bj.show();
    }
}
// 年龄 姓名 学号 性别 是否党员 数学 语文
/*
 * 以空白作为间隔符
 * 18 赵颖 001 F true 73.1 98.6
 * 19 李晓明 002 M false 89 76
 * 20 罗亮 003 M true 78 99
 * 18 王大川 004 F true 100 20
 */


// ��4����4����ϰ2.3��4�⣺����2.8�е�ѧ�����ô�ͷ���ĵ�������Ϊ�༶�Ĵ洢�ṹ��ʵ����༶����������ѧ����Ϣ���Լ���ӡ����༶������ѧ������Ϣ��
import java.util.Scanner;

class Student {
    int age;
    String name, ID;
    char xingbie;
    boolean PartyMember;
    double math, chinese;

    public static void showHint() {// ��ȡ����ʱ�����������ʽ��ʾ��
        System.out.printf("������һ��ѧ����Ϣ,��ctrl+Z��������:");
        System.out.printf("\n   ���� ���� ѧ�� �Ա� �Ƿ�Ա ��ѧ ����");
        System.out.printf("\n����18  ���� 001 M true 93.5 94.3\n");
    }

    void read(Scanner sc) {// ��ȡ���ݣ�����ֵ
        age = sc.nextInt();
        name = sc.next();
        ID = sc.next();
        xingbie = sc.next().charAt(0);// ��ȡ�ַ����ٶ�ȡ�ַ����ĵ�һ������ĸ
        PartyMember = sc.nextBoolean();
        math = sc.nextDouble();
        chinese = sc.nextDouble();
    }

    public String toString() {
        String xb = (xingbie == 'F' || xingbie == 'f') ? "Ů" : ((xingbie == 'M' || xingbie == 'm') ? "��" : "δ֪");
        String party = "";
        if (PartyMember == true)
            party = "��Ա";
        else
            party = "�ǵ�Ա";
        return age + " " + name + " " + ID + " " + xb + " " + party + " " + math + " " + chinese;
    }
}

class Banji {
    StudentNode bj = new StudentNode();// �൱�ڰ༶�ı�ͷ���
    int renShu;

    class StudentNode {// ������
        Student data;// ����ΪStudent��
        StudentNode next;

        StudentNode() {
            data = new Student();
        }// ע�⣺ÿ����һ�������㣬����ڵ�data��Ҫ���һ��ѧ�������Ա������read���丳ֵ�����򣬽�������ָ�����ô�

        int append() {// �з���ֵ���Ա�Ϊ�༶���е�������ֵ
            Scanner sc = new Scanner(System.in);
            Student.showHint();// ��ʾ
            StudentNode tail, s;
            tail = this;
            int i = 0;
            while (sc.hasNextInt() == true) {
                s = new StudentNode(); // ����
                s.data.read(sc); // ��ֵ
                tail.next = s; // ���ڱ�β
                tail = s; // �޸�β��
                i++;
            }
            return i;
        }

        void show() {
            for (StudentNode p = this.next; p != null; p = p.next)
                System.out.println(p.data); // ע�⣺p.data����Student�ͣ����ж�Ӧ��toString()
        }
    }

    // �����ǰ༶�����ṩ�ķ���
    void append() {
        renShu = bj.append();
    }

    void show() {
        bj.show();
        System.out.print("�༶���� " + renShu + " ��.");
    }
}

class App {
    public static void main(String[] arge) {
        Banji bj = new Banji();
        bj.append();
        System.out.printf("�༶��Ϣ���£�\n");
        bj.show();
    }
}
// ���� ���� ѧ�� �Ա� �Ƿ�Ա ��ѧ ����
/*
 * �Կհ���Ϊ�����
 * 18 ��ӱ 001 F true 73.1 98.6
 * 19 ������ 002 M false 89 76
 * 20 ���� 003 M true 78 99
 * 18 ���� 004 F true 100 20
 */

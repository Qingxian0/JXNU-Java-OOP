class TestBlock{
    TestBlock (int x){System.out.print("1== ");}
    TestBlock (){System.out.print("2== ");}
    static {System.out.print("Static 3 == ");}   //��̬��ʼ����
    {System.out.print("4== ");}                     //ʵ����ʼ����
    {System.out.print("5== ");}                     //ʵ����ʼ����
    static {System.out.print("Static 6== ");}    //��̬��ʼ����
}
public class Ch_3_22{
    public static void main(String[] args) {
		System.out.print("ppppp== ");
    		new TestBlock();     new TestBlock(99);
    }
}

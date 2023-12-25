class TestBlock{
    TestBlock (int x){System.out.print("1== ");}
    TestBlock (){System.out.print("2== ");}
    static {System.out.print("Static 3 == ");}   //静态初始化块
    {System.out.print("4== ");}                     //实例初始化块
    {System.out.print("5== ");}                     //实例初始化块
    static {System.out.print("Static 6== ");}    //静态初始化块
}
public class Ch_3_22{
    public static void main(String[] args) {
		System.out.print("ppppp== ");
    		new TestBlock();     new TestBlock(99);
    }
}

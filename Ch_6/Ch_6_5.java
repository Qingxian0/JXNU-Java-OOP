import java.io.File;
public class Ch_6_5 {
    public static void main(String[] args) {
        File f = new File("D:/Temp");
        System.out.println(f.getName());
        preOrder(f, 0);
    }
    public static void preOrder(File f, int h) {//hΪ�����������߶�
        File[] child = f.listFiles();//Ŀ¼�����ӽڵ㼯��
        for (int i = 0; i < child.length; i++) {
        	//�Ե�h�㣬��Ҫ�ȴ�ӡh��" ��"
            for (int j = 0; j < h; j++)  System.out.print(" ��"); 
            if (i == child.length - 1)  //�����ʱ��ǰ�ļ��е�����ļ�
                System.out.println(" ��" + child[i].getName());
            else  System.out.println(" ��" + child[i].getName());
            if (child[i].isDirectory()) //�������ĵݹ����
                preOrder(child[i], h + 1);
        }
    }
}
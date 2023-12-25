import java.io.File;
public class Ch_6_5 {
    public static void main(String[] args) {
        File f = new File("D:/Temp");
        System.out.println(f.getName());
        preOrder(f, 0);
    }
    public static void preOrder(File f, int h) {//h为层数，即结点高度
        File[] child = f.listFiles();//目录树的子节点集合
        for (int i = 0; i < child.length; i++) {
        	//对第h层，需要先打印h个" ┃"
            for (int j = 0; j < h; j++)  System.out.print(" ┃"); 
            if (i == child.length - 1)  //若结点时当前文件夹的最后文件
                System.out.println(" ┗" + child[i].getName());
            else  System.out.println(" ┣" + child[i].getName());
            if (child[i].isDirectory()) //对子树的递归调用
                preOrder(child[i], h + 1);
        }
    }
}
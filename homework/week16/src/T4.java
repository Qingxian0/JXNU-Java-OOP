/*������
    �ٶ���һ��1Gb���ϵ���ͨ�ļ�������ڢٻ����ļ��������ļ���
    �ڻ��ڻ����ֽ��������ļ�����������СΪ4Kb�����г���������ʱ�䣨���뼶����
 */

/*���ֻ��ڻ����ֽ������Ƶ�ʱ��ԶԶ���ڻ����ļ������Ƶ�ʱ�䣬ԭ�����£�
    �����ļ������Ƶķ�����Ҫ�ظ��ش��ļ��ж�һ���ֽڵ��ڴ�����д�뵽������������ڻ����ֽ������Ƶķ���ֱ��
    ���ļ��е�һ���ֶ��뵽�ڴ滺�����У�Ȼ���𲽴ӻ������ж�ȡ���ݣ�������ÿ�ζ�Ҫ���ļ��ж�ȡ���ݵ�ʱ�俪������������ܡ�

 */
import java.io.*;

public class T4 {
    private static void copyFileUsingFileStreams(String source, String target) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
        } catch (IOException e) {
            System.out.println("file read error! " + e);
        }
    }

    private static void copyFileUsingBufferedStreams(String source, String target) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target))) {
            byte[] buffer = new byte[4 * 1024]; // 4KB ��������С
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("file read error! " + e);
        }
    }

    public static void main(String[] args) {
        String sourceFilePath = "����֮��.png";
        String targetFilePath = "bb/����֮�� - ����.png";

        // �����ļ��������ļ�
        long startTime = System.currentTimeMillis();
        copyFileUsingFileStreams(sourceFilePath, targetFilePath);
        long endTime = System.currentTimeMillis();
        System.out.println("�����ļ�������ʱ�䣺" + (endTime - startTime) + " ����");

        // ���ڻ����ֽ��������ļ�
        startTime = System.currentTimeMillis();
        //copyFileUsingBufferedStreams(sourceFilePath, targetFilePath);
        endTime = System.currentTimeMillis();
        System.out.println("���ڻ����ֽ�������ʱ�䣺" + (endTime - startTime) + " ����");
    }
}
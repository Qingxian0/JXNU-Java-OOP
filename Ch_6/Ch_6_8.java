import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

class Ch_6_8{
	public static void copyByBuffer(String src,String tar){
		BufferedReader br=null; BufferedWriter bw=null;
		try{  String s;
			br=new BufferedReader(new InputStreamReader(new FileInputStream(src), "UTF-8")); //��UTF-8��ʽ��
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tar), "GBK")); //��GBK��ʽд
			while((s=br.readLine())!=null){ bw.write(s,0,s.length()); 	bw.newLine(); }
			br.close();  bw.close();
	   }
	   catch(UnsupportedEncodingException e){ System.out.println("��֧�ֵı����ʽ��\n"+e); }
	   catch(FileNotFoundException e){ System.out.println("�ļ�û�ҵ���\n"+e); }
	   catch(IOException e){ System.out.println("File read error��\n"+e); }
	}
	public static void main(String args[] ){
		String source="UTF-8.txt";
		String target="bb/UTF_8toGBK.txt";
	   copyByBuffer(source,target);
	}
}

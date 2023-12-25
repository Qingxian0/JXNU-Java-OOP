import java.awt.Font;
import java.util.Enumeration;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
public class SetDefaultFont{//��������ȫ���������
    public static void setAll(Font newFont) {//��������ȫ�����壺ȫ����ʽ�趨����
        FontUIResource fr = new FontUIResource(newFont);   //�趨ȫ������
        Enumeration<Object> keys = UIManager.getDefaults().keys(); //����������
        Object key,value;
        while(keys.hasMoreElements()) {
            key = keys.nextElement();    //�ȴӹ�ϣ���л��key
            value = UIManager.get(key);  //����UIManager��ø�key��Ӧ��ֵvalue
            if (value instanceof FontUIResource) //���value����������
            	UIManager.put(key, fr);}          //����fr�滻��key��Ӧ��value

    }
    //�ڶ����趨ȫ������ķ�ʽ������ָ�����������Ĭ������
    public static void setPart(String[] comNames, Font defaultFont){//comNames���������趨ȱʡ���������
    	//ע��������swing��ֻ��ָ��awt�е���������ڼ̳й�ϵ�����Զ�Ӱ��swing��������塣
		for (String item : comNames)
			 UIManager.put(item+ ".font",defaultFont);
    }
}

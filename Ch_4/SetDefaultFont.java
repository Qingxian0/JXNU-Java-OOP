import java.awt.Font;
import java.util.Enumeration;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
public class SetDefaultFont{//用于设置全局字体的类
    public static void setAll(Font newFont) {//用于设置全局字体：全覆盖式设定字体
        FontUIResource fr = new FontUIResource(newFont);   //设定全局字体
        Enumeration<Object> keys = UIManager.getDefaults().keys(); //获得所有组件
        Object key,value;
        while(keys.hasMoreElements()) {
            key = keys.nextElement();    //先从哈希表中获得key
            value = UIManager.get(key);  //再用UIManager获得该key对应的值value
            if (value instanceof FontUIResource) //如果value隶属于字体
            	UIManager.put(key, fr);}          //就用fr替换对key对应的value

    }
    //第二种设定全局字体的方式：自行指定若干组件的默认字体
    public static void setPart(String[] comNames, Font defaultFont){//comNames中是所需设定缺省字体的类名
    	//注：不能是swing，只能指定awt中的组件，由于继承关系，会自动影响swing组件的字体。
		for (String item : comNames)
			 UIManager.put(item+ ".font",defaultFont);
    }
}

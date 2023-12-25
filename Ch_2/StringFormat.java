public class StringFormat{//自定义格式化字符串，用于格式化包含汉字的字符串
    public static boolean isChinese(char c) {//识别是否为汉字
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) 
            return true;
        return false;
    }
	public static int length(String s){//s的实际占用宽度：一个汉字占两个字符位置
		char[] chAr = s.toCharArray();
		int c=0;
		for(char x: chAr)
			c=(isChinese(x))?c+2:c+1;
		return c;
	}
	public static String stringHead(String s, int len){//取s中从头开始的len个字符宽度（注：中文占2个字符）
		char[] chAr = s.toCharArray();
		int c=0,i=0;
		for( ; i<chAr.length&&c<len; i++)//当c满足宽度要求时，i就是所取字符的实际数量
			c=(isChinese(chAr[i]))?c+2:c+1;//c用于计算实际宽度
		return s.substring(0,i);//substring(0,i)源自String，从头取i个字符
	}
	public static String repeat(char c, int n){//将字符c重复n次
		if(n<=0) return "";
		String s="";
		for(int i=0; i<n; i++)s=s+c;
		return s;
	}
	public static String formatR(String s, int n, char c ){//右对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在左部
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return repeat(c,n-len)+s;
	} 
	public static String formatL(String s, int n, char c ){//左对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在右部
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return s+repeat(c,n-len);
	} 
	public static String formatC(String s, int n, char c ){//居中对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在右部
		int left,right,len=length(s);
		if(len>=n) return stringHead(s,n);
		left=(n-len)/2; right=n-len-left;
		return repeat(c,left)+s+repeat(c,right);
	}
}

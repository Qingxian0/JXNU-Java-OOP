public class StringFormat{//�Զ����ʽ���ַ��������ڸ�ʽ���������ֵ��ַ���
    public static boolean isChinese(char c) {//ʶ���Ƿ�Ϊ����
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) 
            return true;
        return false;
    }
	public static int length(String s){//s��ʵ��ռ�ÿ�ȣ�һ������ռ�����ַ�λ��
		char[] chAr = s.toCharArray();
		int c=0;
		for(char x: chAr)
			c=(isChinese(x))?c+2:c+1;
		return c;
	}
	public static String stringHead(String s, int len){//ȡs�д�ͷ��ʼ��len���ַ���ȣ�ע������ռ2���ַ���
		char[] chAr = s.toCharArray();
		int c=0,i=0;
		for( ; i<chAr.length&&c<len; i++)//��c������Ҫ��ʱ��i������ȡ�ַ���ʵ������
			c=(isChinese(chAr[i]))?c+2:c+1;//c���ڼ���ʵ�ʿ��
		return s.substring(0,i);//substring(0,i)Դ��String����ͷȡi���ַ�
	}
	public static String repeat(char c, int n){//���ַ�c�ظ�n��
		if(n<=0) return "";
		String s="";
		for(int i=0; i<n; i++)s=s+c;
		return s;
	}
	public static String formatR(String s, int n, char c ){//�Ҷ��룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ�����������
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return repeat(c,n-len)+s;
	} 
	public static String formatL(String s, int n, char c ){//����룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ����������Ҳ�
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return s+repeat(c,n-len);
	} 
	public static String formatC(String s, int n, char c ){//���ж��룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ����������Ҳ�
		int left,right,len=length(s);
		if(len>=n) return stringHead(s,n);
		left=(n-len)/2; right=n-len-left;
		return repeat(c,left)+s+repeat(c,right);
	}
}

/*����8.1�ķ���˳������һ�������������Ԫ�ص����ֵ��
 **/
class GenList<T extends Comparable<T>>{
	private int len; //��
 	private Object[] data;//ע�⣺�������κζ�������飬������Object[ ]��
 	public GenList(int min){ data=new Object[min]; }
 		//ע�⣺�����������飬����ʹ��new T[ min ];
 	public int length(){ return len; }
 	public T get(int i){ return (i>=0&&i<len)?(T)data[i]:null; }
 	private void addCapacity(){//���ݲ���
 		Object[] temp=new Object[data.length+data.length/3];
 		//��data�е��������ݸ��Ƶ�temp��
 		System.arraycopy(data,0,temp,0,data.length);
 		data=temp;
 	}
 	public void add(T x){
 		if(len==data.length) addCapacity();//����������
 		data[len]=x; len++;
 	}
 	public void insert(T x, int i){//���±�i������Ԫ��x
 		if(i<0||i>len)return; //�Ƿ�λ�ã�ֱ�ӷ���
 		if(len==data.length) addCapacity();//����������
 		System.arraycopy(data,i,data,i+1,len-i);
 		  //��data[i..β]���Ƶ�data[i+1..β]
 		data[i]=x; len++;
 	}
 	public void remove(int i){//ɾ���±�iλ�õ�Ԫ��
 		if(i<0||i>=len)return;//�Ƿ�λ�ã�ֱ�ӷ���
 		System.arraycopy(data,i+1,data,i,len-i-1);
 		  //��data[i+1..β]���Ƶ�data[i..β]
 		len--;
 	}
 	public T max(){//���������ֵ����
 		if(len==0)return null;
		if(len==1)return (T)data[0];
		int m=0;  
		T a,b;
		for(int i=1; i<len; i++){
			//if(data[i].compareTo(data[m])>0) m=i; //�����������:
			//data[i]��Object�ͣ�δʵ��Comparable�ӿ�
			a=(T)data[i];   b=(T)data[m];
			if(a.compareTo(b)>0)m=i;
		}
		return (T)data[m];
	}

 	public void show(){
 		for(int i=0; i<len; i++)
 			System.out.print(data[i]+" ");
 	}
 }
 class Ch_8_2{
	public static void main (String[] args) {
		GenList<String> s=new GenList<String>(100);
		s.add("aa");s.add("bb");s.add("cc");
		System.out.print("String�����ݣ�");s.show();
		System.out.print(",���ֵΪ��"+s.max());
		GenList<Double> d=new GenList(100);
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("\nDouble�����ݣ�");d.show();
		System.out.print(",���ֵΪ��"+d.max());
		//GenList<Number> n=new GenList(100); //�����Numberδʵ��Comparable�ӿ�
		//n.add(5);n.add(6.0f);n.add(7.1);n.show();
			//Number�ɼ���Integer��Double����ֵ�Ͱ�װ��
	}
}
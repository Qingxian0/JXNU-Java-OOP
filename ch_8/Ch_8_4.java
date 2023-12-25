/*�����8.1�ķ���˳������һ�������࣬����������̬������
 *��1���������Ԫ�ؿɱȽϴ�С�����������Ԫ�ص����ֵ��
 *��2���ϲ�ͬ���͵�˳���
 **/
class GenList<T>{
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
 	public void show(){
 		for(int i=0; i<len; i++)
 			System.out.print(data[i]+" ");
 	}
}
class GenLists{
	public static <T extends Comparable<T>> T max(GenList<T> g){
 		if(g.length()==0)return null;
		if(g.length()==1)return g.get(0); 
		int m=0;
		T a,b;
		for(int i=1; i<g.length(); i++){
			a=g.get(i); b=g.get(m);
			if(a.compareTo(b)>0)m=i;
		}
		return g.get(m);
	}
	public static <T>void copyTo(GenList<? extends T> src,GenList<? super T> tar){
 		for(int i=0; i<src.length(); i++)
 			tar.add( src.get(i) );
 	}
}
class Ch_8_4{
	public static void main (String[] args) {
		GenList<Double> d=new GenList(100);
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("Double�����ݣ�");d.show();
		System.out.print(",���ֵΪ��"+GenLists.max(d));
		GenList<Integer> i=new GenList(100);
		i.add(4);i.add(5);i.add(6);
		System.out.print("\nInteger�����ݣ�");i.show();
		System.out.print(",���ֵΪ��"+GenLists.max(i));
		GenList<Number> n=new GenList(100);
		GenLists.copyTo(d,n); GenLists.copyTo(i,n);
		System.out.print("\nNumber�����ݣ�");n.show();
		//int max=GenLists.max(n);//�����Numberδʵ��Comparable�ӿ�
	}
}
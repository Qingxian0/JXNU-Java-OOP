/*����8.1�ķ���˳������һ���������ϲ�ͬ����˳���
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
 	public void copyTo(GenList<? extends T> src,GenList<? super T> tar){
 		//��src�ж�ȡ���ݣ�д��tar
 		for(int i=0; i<src.length(); i++)
 			tar.add( src.get(i) );
 	}
}
class Ch_8_3{
	public static void main (String[] args) {
		GenList<Double> d=new GenList(100);//����Double��˳���
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("Double�����ݣ�");d.show();
		GenList<Integer> i=new GenList(100);//����Integer��˳���
		i.add(4);i.add(5);i.add(6);
		System.out.print("\nInteger�����ݣ�");i.show();
		GenList<Number> n=new GenList(100);//����Number��˳���
		n.copyTo(d,n);    n.copyTo(i,n);     //ʵʩ�ϲ�
		System.out.print("\nNumber�����ݣ�");n.show();

		GenList<String> s=new GenList<String>(100);//����String��˳���
		s.add("aa");s.add("bb");s.add("cc");
		System.out.print("\nString�����ݣ�");s.show();
		//n.copyTo(s,n);  //���������
	}
}
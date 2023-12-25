/*���һ���Զ����ݵķ���˳����������ڹ���ʱָ��˳������С������
 *��������ʱ���Զ����ݵ�ǰ������1/3�������в�����
 *���ص�ǰ�洢���ݵ���������ȡָ��λ��Ԫ�ء�
 *��β��׷��Ԫ�ء�ɾ��ָ��λ��Ԫ�ء�
 *��String��Double�ȶ������������Ƿ���������
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
 class Ch_8_1{
	public static void main (String[] args) {
		GenList<String> s=new GenList<String>(100);
			//JDK1.7֮��Ҳ��ʡ�Ժ����String������д��������ʽ
			//GenList<String> s=new GenList<>(100);  ����
			//GenList<String> s=new GenList(100);
		s.add("aa");s.add("bb");s.add("cc"); s.remove(1);
		System.out.print("String�����ݣ�");s.show();
		GenList<Double> d=new GenList(100);
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("\nDouble�����ݣ�");d.show();
		GenList<Number> n=new GenList(100);
		n.add(5);n.add(6.0f);n.add(7.1);
		System.out.print("\nNumber�����ݣ�");n.show();
			//Number�ɼ���Integer��Double����ֵ�Ͱ�װ��
	}
}
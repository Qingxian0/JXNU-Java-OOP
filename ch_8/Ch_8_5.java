/*����8.4�����ϣ�����
 *  <T extends Comparable<T>>��<T extends Comparable<? super T>>������
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
class Animal implements Comparable<Animal>{
	private int age;
	public Animal(int x){age=x;}
	public int compareTo(Animal a){ return this.age-a.age;}
	public int getAge(){return age;}
	public String toString(){ return "A:"+age;}
}
class Dog extends Animal{//ע�⣺Dog�̳���Comparable<Animal>�ӿ�ʵ��
	Dog(int x){ super(x);}
	public String toString(){ return "D:"+getAge();}
}
class Cat extends Animal{//ע�⣺Cat�̳���Comparable<Animal>�ӿ�ʵ��
	Cat(int x){ super(x);}
	public String toString(){ return "C:"+getAge();}
}
class GenLists{
	public static <T extends Comparable<T>> T max(GenList<T> g){//����8.4��ͬ
	  //Ҫ��Tֻ���Լ�ʵ��Comparable�ӿڣ�������T�ĳ���ʵ�ָýӿ�
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
	public static <T extends Comparable<? super T>> T max1(GenList<T> g){//T�����Լ�����ʵ��Comparable�ӿ�
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
class Ch_8_5{
	public static void main (String[] args) {
		//�ȴ���Animal��Dog��Cat������Ȼ��Dog��Cat�����ݸ��Ƶ�Animal����
		GenList<Animal> a=new GenList<>(100);
		a.add(new Animal(1));a.add(new Animal(2));a.add(new Animal(3));
		System.out.print("Animal�����ݣ�");a.show();
		GenList<Dog> d=new GenList<>(100);
		d.add(new Dog(5));d.add(new Dog(6));d.add(new Dog(7));
		System.out.print("\nDog�����ݣ�");d.show();
		GenList<Cat> c=new GenList<>(100);
		c.add(new Cat(8));c.add(new Cat(9));c.add(new Cat(10));
		System.out.print("\nCat�����ݣ�");c.show();
		GenLists.copyTo(d,a); GenLists.copyTo(c,a);
		System.out.print("\n�ϲ���Animal�����ݣ�");a.show();
		//��ʾ����������ֵ
		System.out.print("\n����������ֵΪ��\n");
		//String maxV="Animal��"+GenLists.max(a)+" Dog��"+GenLists.max(d)+" Cat��"+GenLists.max(c);
		String maxV1="Animal��"+GenLists.max1(a)+" Dog��"+GenLists.max1(d)+" Cat��"+GenLists.max1(c);

		//System.out.println(maxV);
		System.out.println(maxV1);
	}
}
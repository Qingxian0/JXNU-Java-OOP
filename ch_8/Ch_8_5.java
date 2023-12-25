/*在例8.4基础上，测试
 *  <T extends Comparable<T>>和<T extends Comparable<? super T>>的区别
 **/
class GenList<T>{
	private int len; //表长
 	private Object[] data;//注意：能容纳任何对象的数组，必须是Object[ ]型
 	public GenList(int min){ data=new Object[min]; }
 		//注意：创建泛型数组，不能使用new T[ min ];
 	public int length(){ return len; }
 	public T get(int i){ return (i>=0&&i<len)?(T)data[i]:null; }
 	private void addCapacity(){//扩容操作
 		Object[] temp=new Object[data.length+data.length/3];
 		//将data中的所有数据复制到temp中
 		System.arraycopy(data,0,temp,0,data.length);
 		data=temp;
 	}
 	public void add(T x){
 		if(len==data.length) addCapacity();//表满则扩容
 		data[len]=x; len++;
 	}
 	public void insert(T x, int i){//在下标i处插入元素x
 		if(i<0||i>len)return; //非法位置，直接返回
 		if(len==data.length) addCapacity();//表满则扩容
 		System.arraycopy(data,i,data,i+1,len-i);
 		  //将data[i..尾]复制到data[i+1..尾]
 		data[i]=x; len++;
 	}
 	public void remove(int i){//删除下标i位置的元素
 		if(i<0||i>=len)return;//非法位置，直接返回
 		System.arraycopy(data,i+1,data,i,len-i-1);
 		  //将data[i+1..尾]复制到data[i..尾]
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
class Dog extends Animal{//注意：Dog继承了Comparable<Animal>接口实现
	Dog(int x){ super(x);}
	public String toString(){ return "D:"+getAge();}
}
class Cat extends Animal{//注意：Cat继承了Comparable<Animal>接口实现
	Cat(int x){ super(x);}
	public String toString(){ return "C:"+getAge();}
}
class GenLists{
	public static <T extends Comparable<T>> T max(GenList<T> g){//与例8.4相同
	  //要求：T只能自己实现Comparable接口，不能是T的超类实现该接口
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
	public static <T extends Comparable<? super T>> T max1(GenList<T> g){//T可以自己或超类实现Comparable接口
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
		//先创建Animal、Dog、Cat三个表，然后将Dog、Cat的内容复制到Animal表中
		GenList<Animal> a=new GenList<>(100);
		a.add(new Animal(1));a.add(new Animal(2));a.add(new Animal(3));
		System.out.print("Animal表内容：");a.show();
		GenList<Dog> d=new GenList<>(100);
		d.add(new Dog(5));d.add(new Dog(6));d.add(new Dog(7));
		System.out.print("\nDog表内容：");d.show();
		GenList<Cat> c=new GenList<>(100);
		c.add(new Cat(8));c.add(new Cat(9));c.add(new Cat(10));
		System.out.print("\nCat表内容：");c.show();
		GenLists.copyTo(d,a); GenLists.copyTo(c,a);
		System.out.print("\n合并后Animal表内容：");a.show();
		//显示各个表的最大值
		System.out.print("\n各个表的最大值为：\n");
		//String maxV="Animal表："+GenLists.max(a)+" Dog表："+GenLists.max(d)+" Cat表："+GenLists.max(c);
		String maxV1="Animal表："+GenLists.max1(a)+" Dog表："+GenLists.max1(d)+" Cat表："+GenLists.max1(c);

		//System.out.println(maxV);
		System.out.println(maxV1);
	}
}
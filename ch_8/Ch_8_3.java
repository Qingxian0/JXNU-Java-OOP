/*给例8.1的泛型顺序表添加一个方法：合并同类型顺序表。
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
 	public void copyTo(GenList<? extends T> src,GenList<? super T> tar){
 		//从src中读取数据，写入tar
 		for(int i=0; i<src.length(); i++)
 			tar.add( src.get(i) );
 	}
}
class Ch_8_3{
	public static void main (String[] args) {
		GenList<Double> d=new GenList(100);//创建Double型顺序表
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("Double表内容：");d.show();
		GenList<Integer> i=new GenList(100);//创建Integer型顺序表
		i.add(4);i.add(5);i.add(6);
		System.out.print("\nInteger表内容：");i.show();
		GenList<Number> n=new GenList(100);//创建Number型顺序表
		n.copyTo(d,n);    n.copyTo(i,n);     //实施合并
		System.out.print("\nNumber表内容：");n.show();

		GenList<String> s=new GenList<String>(100);//创建String型顺序表
		s.add("aa");s.add("bb");s.add("cc");
		System.out.print("\nString表内容：");s.show();
		//n.copyTo(s,n);  //产生编译错
	}
}
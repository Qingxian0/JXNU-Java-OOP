/*针对例8.1的泛型顺序表，设计一个工具类，包含两个静态方法：
 *（1）如果表中元素可比较大小，就求出表中元素的最大值；
 *（2）合并同类型的顺序表；
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
		System.out.print("Double表内容：");d.show();
		System.out.print(",最大值为："+GenLists.max(d));
		GenList<Integer> i=new GenList(100);
		i.add(4);i.add(5);i.add(6);
		System.out.print("\nInteger表内容：");i.show();
		System.out.print(",最大值为："+GenLists.max(i));
		GenList<Number> n=new GenList(100);
		GenLists.copyTo(d,n); GenLists.copyTo(i,n);
		System.out.print("\nNumber表内容：");n.show();
		//int max=GenLists.max(n);//编译错：Number未实现Comparable接口
	}
}
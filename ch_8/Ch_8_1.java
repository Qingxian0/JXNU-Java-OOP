/*设计一个自动扩容的泛型顺序表容器。在构造时指明顺序表的最小容量。
 *当容器满时，自动扩容当前容量的1/3。并配有操作：
 *返回当前存储数据的数量、获取指定位置元素、
 *在尾部追加元素、删除指定位置元素。
 *用String、Double等对象来检测设计是否满足需求。
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
 class Ch_8_1{
	public static void main (String[] args) {
		GenList<String> s=new GenList<String>(100);
			//JDK1.7之后，也可省略后面的String，即可写成如下形式
			//GenList<String> s=new GenList<>(100);  或是
			//GenList<String> s=new GenList(100);
		s.add("aa");s.add("bb");s.add("cc"); s.remove(1);
		System.out.print("String表内容：");s.show();
		GenList<Double> d=new GenList(100);
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("\nDouble表内容：");d.show();
		GenList<Number> n=new GenList(100);
		n.add(5);n.add(6.0f);n.add(7.1);
		System.out.print("\nNumber表内容：");n.show();
			//Number可兼容Integer、Double等数值型包装器
	}
}
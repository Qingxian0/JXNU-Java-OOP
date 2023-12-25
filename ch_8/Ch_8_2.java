/*给例8.1的泛型顺序表添加一个方法：求表中元素的最大值。
 **/
class GenList<T extends Comparable<T>>{
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
 	public T max(){//新增求最大值方法
 		if(len==0)return null;
		if(len==1)return (T)data[0];
		int m=0;  
		T a,b;
		for(int i=1; i<len; i++){
			//if(data[i].compareTo(data[m])>0) m=i; //产生编译错误:
			//data[i]是Object型，未实现Comparable接口
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
		System.out.print("String表内容：");s.show();
		System.out.print(",最大值为："+s.max());
		GenList<Double> d=new GenList(100);
		d.add(1.1);d.add(2.2);d.add(3.3);
		System.out.print("\nDouble表内容：");d.show();
		System.out.print(",最大值为："+d.max());
		//GenList<Number> n=new GenList(100); //编译错：Number未实现Comparable接口
		//n.add(5);n.add(6.0f);n.add(7.1);n.show();
			//Number可兼容Integer、Double等数值型包装器
	}
}
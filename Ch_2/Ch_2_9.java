class T{
	String s="abc";  int i=5; int[] k={1,2,3};
	void a(){ int i=2;     T s=new T();  b(); }
	void b(){ char j='p';  T s=new T(); }  
}
class App{
    public static void main(String[] args) 
       {   T s=new T();  s.a();  }
}

class Animal{	public void eat(){;}  }
class Dog extends Animal{	public void bark(){;} }
class App{
	public static void main(String[] args) {
		Animal[] a=new Dog[2];
		a[0]=new Dog();      a[0].eat();  //a[0].bark();
		a[1]=new Animal();   a[1].eat();  //a[1].bark();
	}
}
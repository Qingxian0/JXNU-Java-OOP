package homework.week10.T1;

public class Recognizer {
    public static void type(Shape s){
        System.out.println(s.getType());
    }
    public static void area(Shape s){
        System.out.println(s.getArea());
    }

    public static void main(String[] args) {
        Triangle t = new Triangle(1, 2);
        Recognizer.area(t);
    }
}

package homework.week10.T1;

public class Triangle implements Shape{
    private String type;
    private int bottom, height;

    public Triangle(){
        this.type = "三角形";
        this.bottom = 0;
        this.height = 0;
    }
    public Triangle(int bottom, int height){
        this.type = "三角形";
        this.bottom = bottom;
        this.height = height;
    }
    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getArea() {
        return 1.0 / 2 * bottom * height;
    }
}

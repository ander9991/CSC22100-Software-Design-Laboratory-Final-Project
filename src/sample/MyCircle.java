package sample;
import javafx.scene.canvas.GraphicsContext;
public class MyCircle extends MyOval {
    private double radius;

    public MyCircle(MyPoint p, double radius1, MyColor color){
        super(p, 2 * radius1, 2 * radius1, color);
        radius = radius1;
    }

    // Accessor methods
    public double getRadius(){ return radius; }
    @Override
    public double area(){ return Math.PI * Math.pow(radius, 2); }
    @Override
    public double perimeter(){ return 2 * Math.PI * getRadius(); }


    @Override
    public String toString(){
        String description = "MyCircle: \nCenter: (" + super.getX() + ", " + super.getY() + ")\nRadius: "
                + getRadius() + "\nArea: " + this.area() + "\nPerimeter: " + this.perimeter() + "\nColor: "
                + super.getColor();
        return description;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(super.getColor().getJavaFXColor());
        gc.setStroke(super.getColor().getJavaFXColor());
        gc.strokeOval(super.getX() - radius, super.getY() - radius, 2 * radius, 2 * radius);
        gc.fillOval(super.getX() - radius, super.getY() - radius, 2 * radius, 2 * radius);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        // Gather Top Left Cord
        MyPoint p = new MyPoint(super.getX() - radius, super.getY() - radius);
        // Use top left cord, and 2 * radius to achieve the height and width of rectangle
        return new MyRectangle(p, 2 * radius, 2 * radius, MyColor.PINK);
    }

}
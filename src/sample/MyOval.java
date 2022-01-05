package sample;
import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape {
    private MyPoint point;
    private double height, width; // Height and width of the oval (Or inscribed rectangle)

    public MyOval(MyPoint p, double height, double width, MyColor color){
        super(p, color);
        point = p;
        this.height = height;
        this.width = width;
    }

    // Accessor Methods
    public double getX(){ return point.getX(); }
    public double getY(){ return point.getY(); }
    public double getA(){ return (width / 2); } // X abscissa
    public double getB(){ return (height / 2); } // Y abscissa
    public double height(){ return height; } // Semi Major
    public double width(){ return width; } // Semi Minor
    public MyPoint getPoint(){ return point; }
    @Override
    public double area(){ return Math.PI * getA() * getB(); }
    @Override
    public double perimeter(){
        double a = height / 2;
        double b = width / 2;
        return 2 * Math.PI * Math.sqrt((Math.pow(a,2)) + (Math.pow(b, 2)) / 2);
    }

    @Override
    public String toString(){
        String description = "MyOval: \nReference Point: (" + point.getX() + ", " + point.getY() +
                ")\nArea: " + area() + "\nPerimeter: " + perimeter() + "\nHeight: " + height + "\nWidth: "
                + width + "\nX Abscissa: " + getA() + "\nY Abscissa: " + getB() + "\nColor: " + getColor() + "\n";
        return description;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(super.getColor().getJavaFXColor());
        gc.setStroke(super.getColor().getJavaFXColor());
        gc.strokeOval(super.getX(), super.getY(), width, height);
        gc.fillOval(super.getX(), super.getY(), width, height);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        // Using top left point, height, width, create a rectangle of that size.
        return new MyRectangle(point, height, width, MyColor.GREY);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        // Approximation using a circle. If a point is inside a circle,
        // it has a high probability that it is in the polygon.
        // Using formula (x-h)^2 /a^2 + (y-k)^2 /b2 <= 1
        double firstPartOfEquation = Math.pow(getPoint().getX() - p.getX(), 2) / Math.pow(getA() + point.getX(), 2);
        // (x-h)^2 / a^2
        double secondPartOfEquation = Math.pow(getPoint().getY() - p.getY(), 2) / Math.pow(getB() + point.getY(), 2);
        // (y-k)^2 / b^2
        if(firstPartOfEquation + secondPartOfEquation <= 1){
            // <1 meaning it is inside the oval, =1 meaning it is on the circumference
            return true;
        }
        return false;
    }

}
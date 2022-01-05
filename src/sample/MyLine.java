package sample;
import javafx.scene.canvas.GraphicsContext;
public class MyLine extends MyShape {

    private MyPoint point1, point2;

    // Constructor
    public MyLine(MyPoint p1, MyPoint p2, MyColor color){
        super(new MyPoint(0, 0), color);
        point1 = p1;
        point2 = p2;
    }

    // Methods
    public double xAngle(){
        return Math.toDegrees(Math.atan((point2.getY() - point1.getY()) / (point2.getX() - point1.getX())));
    }
    public double length(){
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY() ,2));
    }

    @Override
    public double area(){ return 0; }

    @Override
    public double perimeter(){ return this.length(); }

    @Override
    public String toString(){
        String description = "MyLine: \nPoint 1: (" + point1.getX() + ", " + point1.getY() + ")\nPoint 2: ("
                + point2.getX() + ", " + point2.getY() + ")\nLine Length: " + length() + "\nArea: "
                + area() + "\nAngle relative to the X-axis: " + xAngle() + "\nColor: "+ super.getColor() + "\n";
        return description;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setStroke(super.getColor().getJavaFXColor());
        gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        return new MyRectangle(point1, Math.abs(point2.getX()-point1.getX()),
                Math.abs(point2.getY()-point1.getY()), super.getColor());
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        boolean b = false;
        double d = calculateDistance(point1, p) + calculateDistance(p, point2);
        double d2 = calculateDistance(point1, point2);
        if(d == d2){
            return true;
        }
        return b;
    }

    // Calculate distance between 2 points using distance formula
    public double calculateDistance(MyPoint p1, MyPoint p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}
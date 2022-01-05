package sample;
import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape extends Object implements MyShapeInterface {

    private MyPoint center;
    private MyColor color; // Color of the object

    // Constructor
    public MyShape(MyPoint p, MyColor c){
        center = p;
        color = c;
    }

    // Accessor Methods
    public double getX(){ return center.getX(); }
    public double getY(){ return center.getY(); }
    public MyColor getColor(){ return color; }


    public abstract double area();
    public abstract double perimeter();

    @Override
    public String toString(){
        String description = "MyShape: \nReference Point: (" + center.getX() + ", "
                + center.getY() + ")\nColor: " + getColor() + "\n";
        return description;
    }

    public abstract void draw(GraphicsContext gc);
}
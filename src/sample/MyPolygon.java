package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyPolygon extends MyShape{
    private MyPoint center;
    private int sides;
    private double radius;
    //private ArrayList<MyPoint> points;
    double x[];
    double y[];

    public MyPolygon(MyPoint p, double r, int s, MyColor c){
        super(p, c);
        sides = s;
        radius = r;
        center = p;
        x = new double[sides];
        y = new double[sides];

        double ang = Math.PI * 2 / sides;
        for(int i = 0; i < sides; i++){
            x[i] = p.getX() + (radius*(-1 * Math.sin(i * ang)));
            y[i] = p.getY() + (radius*(-1 * Math.cos(i * ang)));
        }
    }

    // Accessor Methods:
    public double getRadius(){ return radius; }
    public double getX(){ return center.getX();}
    public double getY(){ return center.getY();}
    public double getAngle(){ return (180 * (sides - 2)) / sides;} // Interior angle in degrees
    public int getSides(){ return  sides; }
    @Override
    public double area(){ return perimeter() * radius * 0.5; }
    @Override
    public double perimeter(){return sides * 2 * radius * Math.sin(Math.PI/sides);}
    public MyPoint getCenter(){ return center;}

    @Override
    public String toString(){
        return "MyPolygon: \nPoint: (" + center.getY() + ", " + center.getY() +
                ")\nSides: " + getSides() + "\nRadius: " + getRadius()
                + "\nAngle: " + getAngle() + "\nColor: " + super.getColor();
    }
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(super.getColor().getJavaFXColor());
        gc.setStroke(super.getColor().getJavaFXColor());
        gc.setLineWidth(2);
        gc.strokePolygon(x, y, sides);
        gc.fillPolygon(x, y, sides);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        MyPoint topLeftCorner = new MyPoint(center.getX() - radius, center.getY() - radius);
        return new MyRectangle(topLeftCorner, 2 * radius, 2 * radius, MyColor.BEIGE);
    }

    @Override
    public boolean pointInMyShape(MyPoint p){ ;
        // Gather equation of circle: (x-h)^2 + (y-h)^2 = r^2
        if(Math.pow(p.getX() - center.getX(), 2) + Math.pow(p.getY() - center.getY(), 2) <= Math.pow(radius, 2)){
            return true;
        }
        return false;
    }
}

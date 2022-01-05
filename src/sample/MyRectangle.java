package sample;

import javafx.scene.canvas.GraphicsContext;
public class MyRectangle extends MyShape {

    private MyPoint topLeftCorner;
    private double width, height; // Width and Height of rectangle

    // Constructor
    public MyRectangle(MyPoint p, double h, double w, MyColor c){
        super(p, c);
        topLeftCorner = p;
        height = h;
        width = w;
    }

    // Accessor Methods
    public double getX(){ return topLeftCorner.getX(); }
    public double getY(){ return topLeftCorner.getY(); }
    public double getWidth(){ return width; }
    public double getHeight(){ return  height; }
    @Override
    public double area(){ return width * height; }
    @Override
    public double perimeter(){ return  2 * width + 2 * height; }

    public void setWidth(double w) {
        width = w;
    }
    public void setHeight(double h) {
        height = h;
    }

    @Override
    public String toString(){
        String description = "MyRectangle: \nTop Left Point: (" + topLeftCorner.getX() + ", " + topLeftCorner.getY()
                + ")\nArea: " + area() + "\nPerimeter: " + perimeter() + "\nHeight: " + getHeight()
                + "\nWidth: " + getWidth() + "\nColor: " + getColor() + "\n";
        return description;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(super.getColor().getJavaFXColor());
        gc.fillRect(topLeftCorner.getX(), topLeftCorner.getY(), width, height);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){ return this; }
    // Rectangle of equal attributes

    @Override
    public boolean pointInMyShape(MyPoint p){
        // Bottom right corner
        MyPoint BRC = new MyPoint(topLeftCorner.getX() + width, topLeftCorner.getY() + height);
        if((p.getX() >= topLeftCorner.getX() && p.getX() <= BRC.getX()) && (p.getY() >= topLeftCorner.getY()
                && p.getY() <= BRC.getY())){
            return true;
        }
        return false;
    }
}
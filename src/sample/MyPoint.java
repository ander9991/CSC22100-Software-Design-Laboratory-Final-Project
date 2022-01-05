package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyPoint {
    private double x, y;

    public MyPoint(double x1, double y1){
        x = x1;
        y = y1;
    }

    // Accessor methods
    public double getX(){ return x; }
    public double getY(){ return y; }

    @Override
    public String toString(){
        String description = "MyPoint: \nPoint: (" + getX() + ", " + getY() + ")\n";
        return description;
    }

    public double angleX(MyPoint p) {
        double angle = Math.toDegrees(Math.atan2((double)(p.getY()-y), (double)(p.getX() -x)));
        if(angle < 0) //in case angle is negative
            angle += 360.0;
        return angle;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(MyColor.BLACK.getJavaFXColor());
        gc.fillOval(x, y, 5, 5);
    }
}

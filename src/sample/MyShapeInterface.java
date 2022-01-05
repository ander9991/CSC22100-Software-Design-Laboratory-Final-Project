package sample;

public interface MyShapeInterface {
    public MyRectangle getMyBoundingRectangle();
    // Returns the bounding rectangle of an object

    public boolean pointInMyShape(MyPoint p);
    // Returns true if point P is located inside or on the perimeter of an object

    public static MyRectangle intersectMyShapes(MyShape shape1, MyShape shape2){
        if(shape1 instanceof MyLine || shape2 instanceof MyLine){
            return null;
        }
        MyRectangle rect1 = shape1.getMyBoundingRectangle();
        MyRectangle rect2 = shape2.getMyBoundingRectangle();

        double x1 = rect1.getX();
        double y1 = rect1.getY();
        double w1 = rect1.getWidth();
        double h1 = rect1.getHeight();

        double x2 = rect2.getX();
        double y2 = rect2.getY();
        double w2 = rect2.getWidth();
        double h2 = rect2.getHeight();

        if(x1 + w1 < x2 || x2 + w2 < x1){
            return null;
        }
        if(y1 + h1 < y2 || y2 + h2 < y1){
            return null;
        }

        double maxX = Math.max(x1, x2);
        double maxY = Math.max(y1, y2);
        double minX = Math.min(x1 + w1, x2 + w2);
        double minY = Math.min(y1 + h1, y2 + h2);

        double x = Math.abs(minX - maxX);
        double y = Math.abs(minY - maxY);
        MyRectangle rect = new MyRectangle(new MyPoint(maxX, maxY), x, y, MyColor.GREY);
        return rect;
    }
}

package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape {
    private MyPoint point1; // Start point
    private MyPoint point2; // End point
    private MyPoint center; // Center point of arc
    private double startingAngle, angle; // Starting angle and the angle of the arc
    private double arcStartingAngle, arcCenterAngle, arcEndingAngle;
    private MyOval base;
    // Oval which the arc is based on
    double width, height; // Width and height of the oval

    public MyArc(MyPoint cp, double w, double h, double sa, double a, MyColor c) {
        super(new MyPoint(0,0), c);
        center = cp;
        width = w;
        height = h;
        startingAngle = sa;
        angle = a;
        arcStartingAngle = Math.toRadians(startingAngle);
        arcCenterAngle = Math.toRadians(angle);
        arcEndingAngle = Math.toRadians(startingAngle + angle);

        double x = center.getX();
        double y = center.getY();

        double x1 = (x + (width * height) / Math.sqrt(Math.pow(height,2)
                + Math.pow(width * Math.tan(arcStartingAngle),2)));
        double y1 = (y + (width * height * Math.tan(arcStartingAngle))
                / Math.sqrt(Math.pow(height,2) + Math.pow(width * Math.tan(arcStartingAngle),2)));
        double x2 = (x + (width * height) / Math.sqrt(Math.pow(height,2)
                + Math.pow(width * Math.tan(arcEndingAngle),2)));
        double y2 = (y + (width * height * Math.tan(arcEndingAngle))
                / Math.sqrt(Math.pow(height,2) + Math.pow(width * Math.tan(arcEndingAngle),2)));

        point1 = new MyPoint(x1, y1);
        point2 = new MyPoint(x2, y2);
        this.base = new MyOval(center, height, width, c);
    }

    public MyArc(MyOval base, MyPoint p1, MyPoint p2, MyColor c) {
        super(new MyPoint(0,0), c);
        center = base.getPoint();
        point1 = p1;
        point2 = p2;
        this.startingAngle = center.angleX(p1);
        this.angle = center.angleX(p2) - startingAngle;
        arcStartingAngle = Math.toRadians(startingAngle);
        arcCenterAngle = Math.toRadians(angle);
        arcEndingAngle = Math.toRadians(startingAngle + angle);
        this.base = base;
        this.width = base.height()*2;
        this.height = base.width()*2;
    }


    @Override
    public String toString() {
        return "MyArc \n" + "Center: (" + center.getX() + ", " + center.getY() + ")\n"
                + "Endpoints: [(" + point1.getX() + ", " + point1.getY() + ")," + " (" + point2.getX()
                + ", " + point2.getY() + ")]\n" + " Angle1: " + startingAngle + "\nAngle2: " + angle;
    }

    @Override
    public void draw(GraphicsContext gc) {
        final double centerX = center.getX() - width;
        final double centerY = center.getY() - height;
        gc.setFill(super.getColor().getJavaFXColor());
        gc.setStroke(super.getColor().getJavaFXColor());
        gc.strokeArc(centerX, centerY, width * 2, height * 2, startingAngle, angle, ArcType.ROUND);
        gc.fillArc(centerX, centerY, width * 2, height * 2, startingAngle, angle, ArcType.ROUND);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return base.getMyBoundingRectangle();
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        double pointAngle = center.angleX(p);
        double dx = center.getX() - p.getX();
        double dy = center.getY() - p.getY();
        return Math.pow(height * dx, 2) + Math.pow(width * dy, 2) <= Math.pow(width * height, 2)
                && pointAngle >= startingAngle && pointAngle <= startingAngle + angle;
    }

    @Override
    public double area() {
        // Sector Area
        // A = (0.5) * central angle (in radians) * r^2
        double radius = getRadius();
        return (0.5) * arcCenterAngle * Math.pow(radius, 2);
    }

    @Override
    public double perimeter() {
        double distanceBetweenPoints;
        double dx = Math.pow(point1.getX() - point2.getX(), 2);
        double dy = Math.pow(point1.getY() - point2.getY(), 2);
        distanceBetweenPoints = Math.sqrt(dx + dy);
        return 0.5 * Math.PI / Math.sqrt(2) * distanceBetweenPoints;
    }

    public double getRadius(){
        return (width * height) / Math.sqrt(Math.pow(height,2) * Math.pow(Math.sin(arcCenterAngle),2)
                + Math.pow(width,2) * Math.pow(Math.cos(arcCenterAngle),2));
    }
}
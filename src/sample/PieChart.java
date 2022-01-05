package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class PieChart {
    private HashMap<Character, Integer> data;
    private static char[] GPAs = new char[]{'A', 'B', 'C', 'D', 'F', 'W'};
    private static MyColor[] colors = new MyColor[]{MyColor.LIGHTYELLOW, MyColor.RED, MyColor.LIME, MyColor.ORANGE,
            MyColor.CYAN, MyColor.PINK};

    public PieChart() {
        data = Database.studentsAndGrades();
    }
    public int getTotalFrequency() {
        int freq = 0;
        for (Map.Entry<Character, Integer> it : data.entrySet()) {
            freq += it.getValue();
        }
        return freq;
    }
    public void draw(GraphicsContext gc) {
        double x = gc.getCanvas().getWidth()/2; // x-coordinate of center of canvas
        double y = gc.getCanvas().getHeight()/2; // y-coordinate of center of canvas
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        double r = y/2; // radius of pie chart
        int currentColor = 0, totalFreq = getTotalFrequency();
        float currentAngle = 0;
        for (char i = 0; i < 6; i++) {
            gc.setFill(colors[currentColor++].getJavaFXColor());
            if (data.containsKey(GPAs[i])) {
                int currFreq = data.get(GPAs[i]); // # of students
                double probabilityOfEvent = (double)currFreq/totalFreq; // probability of event
                gc.fillArc(x - r,y - r, r * 2, r * 2, currentAngle,probabilityOfEvent * 360, ArcType.ROUND);
                gc.setFill(MyColor.BLACK.getJavaFXColor()); // change fill for text
                double midOfArcAngle = (currentAngle + (probabilityOfEvent * 180)) * Math.PI / 180;
                gc.fillText("GPA: " + GPAs[i] + ", " + currFreq + " students ("+ df.format(probabilityOfEvent) +")",
                        x + (r * Math.cos(midOfArcAngle)) * 1.6,
                        y - (r * Math.sin(midOfArcAngle)) * 1.6); // 1.6 is a scaling factor for no overlap
                currentAngle += probabilityOfEvent * 360;
            }
        }
    }
}
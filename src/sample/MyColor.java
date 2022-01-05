package sample;
import javafx.scene.paint.Color;
public enum MyColor {
    RED(255,0,0,255),
    BLUE(0,0,255,255),
    BEIGE(245, 245, 220, 255),
    LIME(0,255,0,255),
    ORANGE(255, 165, 0, 255),
    CYAN(0,255,255,255),
    GREEN(0,128,0,255),
    TURQUOISE(62, 224, 208, 255),
    GREY(128,128,128,255),
    MAGENTA(255,0,255,255),
    PURPLE(128,0,128,255),
    DARKRED(255, 99, 71, 255),
    VIOLET(148,0,211,255),
    BROWN(165, 42, 42, 255),
    CORAL(255,127,80,255),
    CHOCOLATE(210,105,30, 255),
    DARKGRAY(96,96,96,255),
    LIGHTYELLOW(255,255,102,255),
    LIGHTPINK(255,153,153,255),
    SANDYBROWN(244,164,96,255),
    ORCHID(218,112,214,255),
    SKYBLUE(135,206,235,255),
    PALEGREEN(152,251,152,255),
    SALMON(250,128,114,255),
    NAVY(0, 0, 128, 255),
    YELLOW(255,255,0,255),
    WHITE(255,255,255,255),
    BLACK(0,0,0,255),
    PINK(255,192,203,255),
    DARKGREEN(0, 100, 0, 255);

    private int r, g, b, opacity;

    MyColor(int r, int g, int b, int opacity){
        this.r = r;
        this.g = g;
        this.b = b;
        this.opacity = opacity;
    }

    public Color getJavaFXColor(){
        return Color.rgb(r, g, b, (double) (opacity / 255));
    }

    public static MyColor [] getMyColors(){
        return MyColor.values();
        //returns array of colors
    }
}

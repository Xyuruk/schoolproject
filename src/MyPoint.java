import java.awt.*;

public class MyPoint {
    double x;
    double y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public MyPoint(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
}

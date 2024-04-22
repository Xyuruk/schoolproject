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
    public MyPoint(double x, double y, int w, int h) {
        this.x = x-w/2;
        this.y = h/2-y;
    }
}

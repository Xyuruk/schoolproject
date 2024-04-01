import java.awt.*;
import java.awt.event.MouseEvent;

public class MyPoint {
    private int x;

    private int y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public MyPoint() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public static double distanceToPoint(Point p, Line line){
        double d =  Math.abs((line.A * p.x + line.B * p.y + line.C)/ Math.sqrt(line.A*line.A + line.B*line.B));
        return d;
    }
}

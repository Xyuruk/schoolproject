import java.awt.*;
import java.util.ArrayList;

public class Line {

    double A;

    double B;

    double C;

    Line(double x1, double y1, double x2, double y2) {
        double k = (y2 - y1) / (x2 - x1);
        this.A = k;
        this.B = -1;
        this.C = y1 - k * x1;
    }

    Line(double A, double B, double C){
        this.A = A;
        this.B = B;
        this.C = C;
    }

    Line(Point p1, Point p2) {
        this(p1.x, p1.y, p2.x, p2.y);
    }
//    Line(){}
    Line(Line l){}

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    public ArrayList<Point> get2Points() {
        final int x = 0;
        final int x1 = 1000;
        Point p1 = new Point(x, ((int) Math.round((-A * x - C) / B)));
        Point p2 = new Point(x1, ((int) Math.round((-A * (x1) - C) / B)));
        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        return points;
    }



    public static Line perpendicularLine(Point p, Line line){ //right
        return new Line((-1)*line.getB(), line.getA(), (p.getX()*line.getB())-(p.getY()*line.getA()));
    }

    public static Line parallelLine(Point p, Line line){//right
        Line l = new Line(line.getA(),line.getB(),(-1)*(line.getA()*p.getX()+line.getB()*p.getY()));
        return l;
    }
//    public static Line rotatedLine(Point p1){
//        Line line = new Line();
//        double x11 = ( (line.x1 - p1.x) * Math.cos(Math.PI/2) + (line.y1 - p1.y) * Math.sin(Math.PI/2) ) + p1.x;
//        double y11 = (-(line.x1 - p1.x) * Math.sin(Math.PI/2) + (line.y1 - p1.y) * Math.cos(Math.PI/2) ) + p1.y;
//        double x22 = ( (line.x2 - p1.x) * Math.cos(Math.PI/2) + (line.y2 - p1.y) * Math.sin(Math.PI/2) ) + p1.x;
//        double y22 = (-(line.x2 - p1.x) * Math.sin(Math.PI/2) + (line.y2 - p1.y) * Math.cos(Math.PI/2) ) + p1.y;
//        Line l = new Line(x11,y11,x22,y22);
//        return l;
//    }
//    public static MyPoint intersection(Line l1, Line l2){
//        MyPoint p =  new MyPoint();
//        p.setX((int)((l1.getB()*l2.getC()-l2.getB()*l1.getC())/(l1.getA()*l2.getB() - l1.getB()*l2.getA()))) ;
//        p.setY((int)((l1.getC()*l2.getA()-l2.getC()*l1.getA())/(l1.getA()*l2.getB() - l1.getB()*l2.getA()))) ;
//        return p;
//    }

    public static boolean isDownSide(Point a, Line l) {//для рисования первой прямой в случае огда ниже нее еще остаются точки
        if (l.getA() * a.getX() + l.getB() * a.getY() + l.getC() >= 0 ) {// если точка ниже есть
            return true;
        } else {
            return false;
        }
    }


}

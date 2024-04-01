import java.awt.*;

public class Line {
    double A, B, C;
    int x1;

    int y1;
    int x2;
    int y2;

    Line(double x1, double y1, double x2, double y2){
        this.A = y1 - y2;
        this.B = x2 - x1;
        this.C = x1*y2 - x2*y1;
    }
    Line(double A, double B, double C){
        this.A = A;
        this.B = B;
        this.C = C;
    }
    Line(){}
    Line(Line l){}

    public double getA() {
        return A;
    }

    public void setA(double A) {
        this.A = A;
    }
    public double getB() {
        return B;
    }

    public void setB(double B) {
        this.B = B;
    }
    public double getC() {
        return C;
    }

    public void setC(double C) {
        this.C = C;
    }



    public static Line perpendicularLine(Point p, Line line){//right
        Line l = new Line();
        l.setA((-1)*line.getB());
        l.setB(line.getA());
        l.setC((p.getX()*line.getB())-(p.getY()*line.getA()));
        return l;
    }
    public static Line parallelLine(Point p, Line line){//right
        Line l = new Line(line.getA(),line.getB(),(-1)*(line.getA()*p.getX()+line.getB()*p.getY()));
        return l;
    }
    public static Line rotatedLine(Point p1){
        Line line = new Line();
        double x11 = ( (line.x1 - p1.x) * Math.cos(Math.PI/2) + (line.y1 - p1.y) * Math.sin(Math.PI/2) ) + p1.x;
        double y11 = (-(line.x1 - p1.x) * Math.sin(Math.PI/2) + (line.y1 - p1.y) * Math.cos(Math.PI/2) ) + p1.y;
        double x22 = ( (line.x2 - p1.x) * Math.cos(Math.PI/2) + (line.y2 - p1.y) * Math.sin(Math.PI/2) ) + p1.x;
        double y22 = (-(line.x2 - p1.x) * Math.sin(Math.PI/2) + (line.y2 - p1.y) * Math.cos(Math.PI/2) ) + p1.y;
        Line l = new Line(x11,y11,x22,y22);
        return l;
    }
    public static MyPoint intersection(Line l1, Line l2){//right
        MyPoint p =  new MyPoint();
        p.setX((int)((l1.getB()*l2.getC()-l2.getB()*l1.getC())/(l1.getA()*l2.getB() - l1.getB()*l1.getA()))) ;
        p.setY((int)((l1.getC()*l2.getA()-l2.getC()*l1.getA())/(l1.getA()*l2.getB() - l1.getB()*l2.getA()))) ;
        return p;
    }
    public static boolean whichSide(Point a,Line l) {//для рисования первой прямой в случае огда выше нее еще остаются точки
        if (l.getA() * a.getX() + l.getB() * a.getY() + l.getC() < 0 ) {// если точка выше есть
            return true;
        } else {
            return false;
        }
    }


}

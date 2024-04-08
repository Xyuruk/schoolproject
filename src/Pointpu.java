import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Comparator;


public class Pointpu extends JPanel implements MouseListener, MouseMotionListener  {
    ArrayList<Point> mas_points = new ArrayList<>();
    int h = 1000;
    int w = 1000;
    String mod = "MOUSE_MOD";
    public Pointpu() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void mousePressed(MouseEvent e) {
        if(mod.equals("MOUSE_MOD")) {
            mas_points.add(new Point(e.getPoint()));
            repaint();
            System.out.println(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paint (Graphics graphics) {
        super.paint(graphics);
        int x = -w/100;
        int y = h/100;
        graphics.setColor(Color.BLACK);
        graphics.drawLine((w / 2), 0, (w / 2), h);
        for (int i = 0; i < 1000; i += 50) {
            graphics.drawRect((int) ((h / 2) - 7.5), i , 15, 2);
            graphics.fillRect((int) ((h / 2) - 7.5), i ,  15 ,2);
            String oy = Integer.toString(y);
            graphics.drawString(oy, ((w / 2) + 1), i+1);
            y -= 1;
        }
        graphics.drawLine(0, (h / 2) + 1, w, (h / 2) + 1);
        for (int j = -1;j < 1000; j += 50) {
            graphics.drawRect(j, (int) ((h / 2) - 7.5), 2, 15);
            graphics.fillRect(j, (int) ((h / 2) - 7.5), 2, 15);
            String ox = Integer.toString(x);
            graphics.drawString(ox, j + 2, w / 2);
            x += 1;
        }


        for (int i = 0; i < mas_points.size(); i++) {
            graphics.setColor(Color.RED);
            Point pt = mas_points.get(i);
            graphics.fillOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
            graphics.drawOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
        }


        if (mas_points.size() % 10 == 0 && mas_points.size() > 0) { // условный цикл для рисования прямоугольника
            Line line =  new Line ( findMin2Y().getX(), findMin2Y().getY(), findMinY().getX(), findMinY().getY() );//самая первая
            Point point = getPointForRightLine(mas_points, line, graphics, (int) Math.round(findMin2Y().getX()), (int) Math.round(findMin2Y().getY()));// right point
            Line firstLine = getRightLine(mas_points, line, (int) Math.round(findMin2Y().getX()), (int) Math.round(findMin2Y().getY()), (int) Math.round(findMinY().getX()), (int) Math.round(findMinY().getY())); // right line


            graphics.drawLine(findMinY().x, findMinY().y, point.x, point.y);//рисует первую уже правильную

            Line downLine = getMostRemoteParallelLine(mas_points, firstLine);
            ArrayList<Point> p =  downLine.get2Points();
            graphics.drawLine(p.get(0).x, p.get(0).y, p.get(1).x, p.get(1).y );


           /* Line perpendicuolarTo = Line.perpendicularLine(findMinX(), firstLine);//ей пeрпендикулярная


            Line perpendicuolarToPerpendicular = Line.perpendicularLine(findMaxY(),perpendicuolarTo );
            Line perpendicuolarToPerpendicularRight = Line.parallelLine(findMaxY(),perpendicuolarTo );

            Line line4 = Line.perpendicularLine(findMaxX(),firstLine );


            MyPoint downleft = Line.intersection( perpendicuolarToPerpendicular, perpendicuolarTo);//странная
            MyPoint upleft = Line.intersection(firstLine, perpendicuolarTo);

            MyPoint downright = Line.intersection(line4,  perpendicuolarToPerpendicular);
            MyPoint upright = Line.intersection(firstLine, line4);

            graphics.setColor(Color.cyan);
            graphics.fillOval((int) (downleft.getX() - 2.5), (int) (downleft.getY() - 2.5), 5, 5);
            graphics.drawOval((int) (downleft.getX() - 2.5), (int) (downleft.getY() - 2.5), 5, 5);
            graphics.setColor(Color.ORANGE);
            graphics.fillOval((int) (upleft.getX() - 2.5), (int) (upleft.getY() - 2.5), 5, 5);
            graphics.drawOval((int) (upleft.getX() - 2.5), (int) (upleft.getY() - 2.5), 5, 5);
            graphics.setColor(Color.MAGENTA);
            graphics.fillOval((int) (downright.getX() - 2.5), (int) (downright.getY() - 2.5), 5, 5);
            graphics.drawOval((int) (downright.getX() - 2.5), (int) (downright.getY() - 2.5), 5, 5);
            graphics.setColor(Color.green);
            graphics.fillOval((int) (upright.getX() - 2.5), (int) (upright.getY() - 2.5), 5, 5);
            graphics.drawOval((int) (upright.getX() - 2.5), (int) (upright.getY() - 2.5), 5, 5);


            graphics.drawLine(downleft.getX(), downleft.getY(), upleft.getX(), upleft.getY());//левая
            graphics.drawLine(upleft.getX(), upleft.getY(), upright.getX(), upright.getY());//повторяет первую
            graphics.drawLine(downright.getX(), downright.getY(), upright.getX(), upright.getY());
            graphics.drawLine(downleft.getX(), downleft.getY(), downright.getX(), downright.getY());*/





            mas_points.clear();
        }
    }
    Point findMaxX(){
        return mas_points.stream()
                .sorted(Comparator.comparing(Point::getX).reversed())
                .findFirst()
                .get();
    }

    Point findMaxY(){
        return mas_points.stream()
                .sorted(Comparator.comparing(Point::getY).reversed())
                .findFirst()
                .get();
    }
    Point findMinX(){
        return mas_points.stream()
                .sorted(Comparator.comparing(Point::getX))
                .findFirst()
                .get();
    }
    Point findMinY(){
        return mas_points.stream()
                .sorted(Comparator.comparing(Point::getY))
                .findFirst()
                .get();
    }
    Point findMin2Y(){
        return mas_points.stream()
                .sorted(Comparator.comparing(Point::getY))
                .toList()
                .get(1);
    }
    Point getPointForRightLine(ArrayList<Point> mas_points, Line line, Graphics g, int xNorm, int yNorm){// для рисования первой линии
        ArrayList<Point> mas_pointsTrue = new ArrayList<>() ;
        Point point = null;
        for(int i = 0; i < mas_points.size();  i++) {
            if (Line.isUpSide(mas_points.get(i), line)) {
                mas_pointsTrue.add(mas_points.get(i));
            } else {
                point = new Point(xNorm,yNorm);//самая первая
            }
        }

//TESTING
           /*  for (Point pointr : mas_pointsTrue) {
            g.setColor(Color.PINK);;
            g.fillOval((int) (pointr.getX() - 2.5), (int) (pointr.getY() - 2.5), 5, 5);
            g.drawOval((int) (pointr.getX() - 2.5), (int) (pointr.getY() - 2.5), 5, 5);
        }*/
        double maxDistance = 0 ;
        Point maxDistancePoint = null;
        for(int j = 0; j < mas_pointsTrue.size();  j++) {
            if( maxDistance < MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
                maxDistance = MyPoint.distanceToPoint(mas_pointsTrue.get(j), line);
                maxDistancePoint = mas_pointsTrue.get(j);
            }

        }
        if (maxDistancePoint != null) {
            point = new Point((int) Math.round(maxDistancePoint.getX()), (int) Math.round(maxDistancePoint.getY()));
        }

        return point;
    }
    Line getRightLine(ArrayList<Point> mas_points, Line line, int xBefore, int yBefore, int xRight, int yRight){// для рисования первой линии
        ArrayList<Point> mas_pointsTrue = new ArrayList<>() ;
        Point point = null;
        Line firstLine = null;
        for(int i = 0; i < mas_points.size();  i++) {
            if ((Line.isUpSide(mas_points.get(i), line)&&line.A > 0) || (Line.isDownSide(mas_points.get(i), line)&&line.A < 0)  ) {
                mas_pointsTrue.add(mas_points.get(i));
            } else {
                point = new Point(xBefore, yBefore);//самая первая
                firstLine = new Line(point.x, point.y, xRight, yRight);
            }
        }
        double maxDistance = 0 ;
        Point maxDistancePoint = null;
        for(int j = 0; j < mas_pointsTrue.size();  j++) {
            if( maxDistance < MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
                maxDistance = MyPoint.distanceToPoint(mas_pointsTrue.get(j), line);
                maxDistancePoint = mas_pointsTrue.get(j);
            }

        }
        if (maxDistancePoint != null) {
            point = new Point((int) Math.round(maxDistancePoint.getX()), (int) Math.round(maxDistancePoint.getY()));
            firstLine = new Line (point.x, point.y, xRight, yRight);
        }


        return firstLine;
    }
    Line getMostRemoteParallelLine(ArrayList<Point> mas_points, Line line) {// для рисования первой линии
        Line lineParall = null;
        double maxDistance = 0;
        Point maxDistancePoint = null;
        for (Point point: mas_points) {
            if (maxDistance < MyPoint.distanceToPoint(point, line)) {
                maxDistance = MyPoint.distanceToPoint(point, line);
                maxDistancePoint = point;
            }
        }
        lineParall = Line.parallelLine(maxDistancePoint, line);
        return lineParall;
    }

    public static void  getSquare(){
        double square = 0;
        JOptionPane.showMessageDialog(null, square);

    }
}








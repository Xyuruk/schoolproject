import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Comparator;
import java.util.Optional;


public class RectanglePaint extends JPanel implements MouseListener, MouseMotionListener  {
    ArrayList<Point> points = new ArrayList<>();
    final int h = 1000;
    final int w = 1000;

    InputMode mode = InputMode.MOUSE_MODE;
    public RectanglePaint() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void mousePressed(MouseEvent e) {
        switch (mode) {
            case MOUSE_MODE -> {
                points.add(new Point(e.getPoint()));
                repaint();
                System.out.println(e.getPoint());
            }
            //TODO
            case FILE_MODE -> throw new RuntimeException("FILE_MODE not completed.");
            default -> throw new RuntimeException("Mode is not supported.");
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
    public void paint(Graphics graphics) {
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


        for (int i = 0; i < points.size(); i++) {
            graphics.setColor(Color.RED);
            Point pt = points.get(i);
            graphics.fillOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
            graphics.drawOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
        }

        // Рисуем прямоугольник
        if (points.size() % 10 == 0 && points.size() > 0) {
            Point pointMinY = findMinY();
            Point pointPreMinY = findMin2Y();

            // Получение прямой через две верхние точки
            Line line =  new Line (pointMinY.x, pointMinY.y, pointPreMinY.x,  pointPreMinY.y);

            // Получение верной верхней прямой
            Line upperLine = getRightLine(points, line, pointMinY); // right line

            ArrayList<Point>  p =  upperLine.get2Points();
            graphics.drawLine(p.get(0).x, p.get(0).y, p.get(1).x, p.get(1).y);


            //Point point = getPointForRightLine(mas_points, line, graphics, (int) Math.round(findMin2Y().getX()), (int) Math.round(findMin2Y().getY()));// right point



//            graphics.drawLine(findMinY().x, findMinY().y, point.x, point.y);//рисует первую уже правильную
//
//            Line downLine = getMostRemoteParallelLine(mas_points, upperLine);


           /* Line perpendicuolarTo = Line.perpendicularLine(findMinX(), upperLine);//ей пeрпендикулярная


            Line perpendicuolarToPerpendicular = Line.perpendicularLine(findMaxY(),perpendicuolarTo );
            Line perpendicuolarToPerpendicularRight = Line.parallelLine(findMaxY(),perpendicuolarTo );

            Line line4 = Line.perpendicularLine(findMaxX(),upperLine );


            MyPoint downleft = Line.intersection( perpendicuolarToPerpendicular, perpendicuolarTo);//странная
            MyPoint upleft = Line.intersection(upperLine, perpendicuolarTo);

            MyPoint downright = Line.intersection(line4,  perpendicuolarToPerpendicular);
            MyPoint upright = Line.intersection(upperLine, line4);

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





            points.clear();
        }
    }
    Point findMaxX(){
        return points.stream().max(Comparator.comparing(Point::getX))
                .get();
    }

    Point findMaxY(){
        return points.stream().max(Comparator.comparing(Point::getY))
                .get();
    }
    Point findMinX(){
        return points.stream().min(Comparator.comparing(Point::getX))
                .get();
    }
    Point findMinY(){
        return points.stream().min(Comparator.comparing(Point::getY))
                .get();
    }
    Point findMin2Y(){
        return points.stream()
                .sorted(Comparator.comparing(Point::getY))
                .toList()
                .get(1);
    }
//    Point getPointForRightLine(ArrayList<Point> mas_points, Line line, Graphics g, int xNorm, int yNorm){// для рисования первой линии
//        ArrayList<Point> mas_pointsTrue = new ArrayList<>() ;
//        Point point = null;
//        for(int i = 0; i < mas_points.size();  i++) {
//            if (Line.isUpSide(mas_points.get(i), line)) {
//                mas_pointsTrue.add(mas_points.get(i));
//            } else {
//                point = new Point(xNorm,yNorm);//самая первая
//            }
//        }
//
////TESTING
//           /*  for (Point pointr : mas_pointsTrue) {
//            g.setColor(Color.PINK);;
//            g.fillOval((int) (pointr.getX() - 2.5), (int) (pointr.getY() - 2.5), 5, 5);
//            g.drawOval((int) (pointr.getX() - 2.5), (int) (pointr.getY() - 2.5), 5, 5);
//        }*/
//        double maxDistance = 0 ;
//        Point maxDistancePoint = null;
//        for(int j = 0; j < mas_pointsTrue.size();  j++) {
//            if( maxDistance < MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
//                maxDistance = MyPoint.distanceToPoint(mas_pointsTrue.get(j), line);
//                maxDistancePoint = mas_pointsTrue.get(j);
//            }
//
//        }
//        if (maxDistancePoint != null) {
//            point = new Point((int) Math.round(maxDistancePoint.getX()), (int) Math.round(maxDistancePoint.getY()));
//        }
//
//        return point;
//    }

    // Получаем верную верхнюю линию
    Line getRightLine(ArrayList<Point> points, Line preliminaryLine, Point pointMinY) {
        double maxDistance = 0;
        Optional<Point> maxDistancePoint = Optional.empty();
        for (Point point : points) {
            // Проверяем выше ли точка начальной линии
            if (GeometryTool.isUpSide(point, preliminaryLine) ) {
                // Если расстояние от изначальной прямой до точки больше, то запоминаем точку и максимальное расстояние
                double distance = GeometryTool.distanceBetweenPointAndLine(point, preliminaryLine);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    maxDistancePoint = Optional.of(point);
                }

            }
        }
        // Если нашлась новая точка, то возвращаем новую прямую
        // Если нет, то возвращаем исходную
        return maxDistancePoint.map(point -> new Line(pointMinY, point)).orElse(preliminaryLine);
    }


    Line getMostRemoteParallelLine(ArrayList<Point> mas_points, Line line) {
        Line lineParall = null;
        double maxDistance = 0;
        Point maxDistancePoint = null;
        for (Point point: mas_points) {
            double distance = GeometryTool.distanceBetweenPointAndLine(point, line);
            if (maxDistance < distance) {
                maxDistance = distance;
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








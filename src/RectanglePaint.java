import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Comparator;
import java.util.Optional;


public class RectanglePaint extends JPanel implements MouseListener, MouseMotionListener  {
    public static ArrayList<Point> points = new ArrayList<>();
    static MyPoint downleft;
    static MyPoint upleft;
    static MyPoint downright;
    static MyPoint upright;
    static boolean isPressed = false;
    public static InputMode mode = InputMode.MOUSE_MODE;
    public RectanglePaint() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        switch (mode) {
            case MOUSE_MODE -> {
                points.add(new Point(e.getPoint()));
                repaint();
                //System.out.println(e.getPoint());
            }
            case FILE_MODE -> {
                points.addAll(FileReader.getPointsFromFile("points.txt"));
                repaint();
            }
            case KEYBOARD_MODE -> {
                points.add(KeyboardReader.getPointsFromKeyboard());
                repaint();
            }
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
        if (mode == InputMode.FILE_MODE && points.isEmpty()) {
            points.addAll(FileReader.getPointsFromFile("points.txt"));
        }
        drawSystemCoordinates(graphics);

        // Рисуем прямоугольник
        if (isPressed && points.size()>2)  {
            Point pointMinY = findMinY();
            Point pointPreMinY = findMin2Y();

            // Получение прямой через две верхние точки
            Line line =  new Line (pointMinY.x, pointMinY.y, pointPreMinY.x,  pointPreMinY.y);

            // Получение верной верхней прямой
            Line upperLine = getRightLine(points, line, pointMinY); // right line

            // Получение нижней прямой
            Line downLine = getMostRemoteParallelLine(points, upperLine);

            // Получение левой прямой
            Line rightLine = Line.perpendicularLine(findMaxX(), upperLine);
            Line mostRemoteRightLine = getParallelForRightLine(points, rightLine);

            // Получение правой прямой
            Point maxD = getMaxDistance(mostRemoteRightLine);
            Line leftLine = Line.parallelLine(maxD,mostRemoteRightLine );

            // получение точек пересечения получившихся прямых для возможности их нарисовать
            downleft = Line.intersection(downLine, leftLine);
            upleft = Line.intersection( upperLine, leftLine);
            downright = Line.intersection(downLine, mostRemoteRightLine);
            upright = Line.intersection(upperLine,mostRemoteRightLine);

            // рисуем 4 линии
            graphics.drawLine((int)upright.x, (int)upright.y , (int)upleft.x, (int)upleft.y);
            graphics.drawLine((int)downleft.x, (int)downleft.y, (int)upleft.x, (int)upleft.y);
            graphics.drawLine((int)downleft.x, (int)downleft.y, (int)downright.x, (int)downright.y);
            graphics.drawLine((int)upright.x, (int)upright.y , (int)downright.x, (int)downright.y);

            //очищаем список точек для возможности следующего ввода
            isPressed = false;
            points.clear();
        } else if (isPressed && points.size()==2){
            JOptionPane.showMessageDialog(null, "Вы задали всего 2 точки, невозможно нарисовать прямоугольник");
            points.clear();
            isPressed = false;
        } else if (isPressed && points.size()==1){
            JOptionPane.showMessageDialog(null, "Вы задали всего 1 точку, невозможно нарисовать прямоугольник");
            points.clear();
            isPressed = false;
        } else if (isPressed && points.size()==0){
            JOptionPane.showMessageDialog(null, "Вы не задали точки, невозможно нарисовать прямоугольник");
        }
    }
    private void drawSystemCoordinates(Graphics g) {
        g.setColor(Color.BLACK);
        int w = getWidth();
        int h = getHeight();
        g.drawLine(0, h / 2, w, h / 2);
        g.drawLine(w / 2, h, w / 2, 0);
        for(int i = 0; i < w / 100; i++) {
            g.drawLine(w / 2 + i * 50, h / 2 - 5, w / 2 + i * 50, h / 2 +5);
            g.drawString("" + i * 50, w / 2 + i * 50, h / 2 + 11);
            g.drawLine(w / 2 - i * 50, h / 2 - 5, w / 2 - i * 50, h / 2 + 5);
            g.drawString("" + - i * 50, w / 2 - i * 50, h / 2 + 11);
        }
        for(int i = 0; i < h / 100; i++) {
            g.drawLine(w / 2 - 5, h / 2 + i * 50, w / 2 + 5, h / 2 + i * 50);
            g.drawString("" + -i * 50, w / 2 - 30, h / 2 + i * 50);
            g.drawLine(w / 2 - 5, h / 2 - i * 50, w / 2 + 5, h / 2 - i * 50);
            g.drawString("" + i * 50, w / 2 - 30, h / 2 - i * 50);
        }
        for (int i = 0; i < points.size(); i++) {
            g.setColor(Color.RED);
            Point pt = points.get(i);
            g.fillOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
            g.drawOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
        }

    }

    Point findMaxX(){
        return points.stream().max(Comparator.comparing(Point::getX))
                .get();
    }
    Point findMinY(){
        return points.stream().min(Comparator.comparing(Point::getY))
                .get();
    }
    Point findMin2Y(){ // нахождение преминимума
        return points.stream()
                .sorted(Comparator.comparing(Point::getY))
                .toList()
                .get(1);
    }
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
    //находим наиболее удаленную от данной прямой прямую
    Line getMostRemoteParallelLine(ArrayList<Point> mas_points, Line line) {
        Line lineParall;
        double maxDistance = 0;
        Point maxDistancePoint = null;
        for (Point point: mas_points) {
            double distance = GeometryTool.distanceBetweenPointAndLine(point, line);
            if (maxDistance < distance) {
                maxDistance = distance;
                maxDistancePoint = point;
            }
        }
        if(maxDistancePoint == null){
            return line;
        }
        lineParall = Line.parallelLine(maxDistancePoint, line);
        return lineParall;
    }
    Line getParallelForRightLine(ArrayList<Point> mas_points, Line line) {
        ArrayList<Point> masPointsTrue = new ArrayList<>();
        for (int i = 0; i < mas_points.size(); i++) {
            if ((GeometryTool.isUpSide(mas_points.get(i), line) && line.A < 0) || (GeometryTool.isDownSide(mas_points.get(i), line) && line.A > 0)) {//корявое
                masPointsTrue.add(mas_points.get(i));
            }
        }
        double maxDistance = 0;
        Point maxDistancePoint = null;
        for (int j = 0; j < masPointsTrue.size(); j++) {
            if (maxDistance < GeometryTool.distanceBetweenPointAndLine(masPointsTrue.get(j), line)) {
                maxDistance = GeometryTool.distanceBetweenPointAndLine(masPointsTrue.get(j), line);
                maxDistancePoint = masPointsTrue.get(j);
            }
        }
        if(maxDistancePoint == null){
            return line;
        }
        Line lineLeft = Line.parallelLine(maxDistancePoint, line);
        return lineLeft;
    }
    Point getMaxDistance (Line line){
    double maxDistance = 0;
        Point maxDistancePoint = null;
        for (int j = 0; j < points.size(); j++) {
            if (maxDistance < GeometryTool.distanceBetweenPointAndLine( points.get(j), line)) {
                maxDistance = GeometryTool.distanceBetweenPointAndLine( points.get(j), line);
                maxDistancePoint =  points.get(j);
            }
        }
        return  maxDistancePoint;
    }

    public static void  getSquare(MyPoint p1, MyPoint p2, MyPoint p3){
        double a = Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
        double b = Math.sqrt(((p2.x - p3.x) * (p2.x - p3.x)) + ((p2.y - p3.y) * (p2.y - p3.y)));
        double square = a*b;
        JOptionPane.showMessageDialog(null, "Площадь данного прямоугольника равна: "+ Math.round(square));

    }
    //Функция переводит экранные координаты в реальные

}








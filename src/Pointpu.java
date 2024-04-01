import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



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

            line = firstLine(mas_points, line, graphics); //корректируем если сверху есть точки

            graphics.drawLine(findMinY().x, findMinY().y, findMin2Y().x, findMin2Y().y );//рисует первую но потом ее удалиь надо


            Line perpendicuolarTo = Line.perpendicularLine(findMinX(), line);//ей пeрпендикулярная


            Line perpendicuolarToPerpendicular = Line.rotatedLine(findMaxY());
            Line line4 = Line.rotatedLine(findMaxX());

            MyPoint downleft = Line.intersection( perpendicuolarToPerpendicular, perpendicuolarTo);//странная
            MyPoint upleft = Line.intersection(line, perpendicuolarTo);

            MyPoint downright = Line.intersection(line4,  perpendicuolarToPerpendicular);
            MyPoint upright = Line.intersection(line, line4); //странная

            graphics.drawLine(downleft.getX(), downleft.getY(), upleft.getX(), upleft.getY());//рисуем 4 линии
//            graphics.drawLine(upleft.getX(), upleft.getY(), upright.getX(), upright.getY());
//            graphics.drawLine(downright.getX(), downright.getY(), upright.getX(), upright.getY());
//            graphics.drawLine(downleft.getX(), downleft.getY(), downright.getX(), downright.getY());

//            System.out.println("max "+findMinX());//проверка
//            System.out.println(downleft.getX()+" "+downleft.getY()+" "+upleft.getX()+" "+upleft.getY()+" "+downright.getX()+" "+ downright.getY()+" "+upright.getX()+" "+upright.getY());

            mas_points.clear();
        }
    }
    Point findMaxX(){
        Point max =mas_points.get(0);
        for(int i = 0; i < mas_points.size(); i ++){
            if(mas_points.get(i).getX() > max.getX()){
                max = mas_points.get(i);
            }
        }
        return max;
    }

    Point findMaxY(){
        Point max = mas_points.get(0);
        for(int i = 0; i < mas_points.size(); i ++){
            if(mas_points.get(i).getY() > max.getY()){
                max = mas_points.get(i);
            }
        }
        return max;
    }
    Point findMinX(){
        Point min = mas_points.get(0);
        for(int i = 0; i < mas_points.size(); i ++){
            if(mas_points.get(i).getX() < min.getX()){
                min = mas_points.get(i);
            }
        }
        return min;
    }
    Point findMinY(){
        Point min = mas_points.get(0);
        for(int i = 0; i < mas_points.size(); i ++){
            if(mas_points.get(i).getY() < min.getY()){
                min = mas_points.get(i);
            }
        }
        return min;
    }
    Point findMin2Y(){
        Point min = mas_points.get(0);
        for(int i = 0; i < mas_points.size(); i ++){
            if(mas_points.get(i).getY() < min.getY() && mas_points.get(i).getY() > findMinY().getY() ){
                min = mas_points.get(i);
            }
        }
        return min;
    }
    Line firstLine(ArrayList<Point> mas_points, Line line, Graphics g){// для рисования первой линии
        ArrayList<Point> mas_pointsTrue = new ArrayList<>() ;
        for(int i = 0; i < mas_points.size();  i++) {
            if (Line.whichSide(mas_points.get(i), line)) {
                mas_pointsTrue.add(mas_points.get(i));
            } else {
                line = new Line( findMin2Y().getX(), findMin2Y().getY(), findMinY().getX(), findMinY().getY());//самая первая
            }
        }

//        //TESTING
//        for (Point point : mas_pointsTrue) {
//            g.setColor(Color.GREEN);;
//            g.fillOval((int) (point.getX() - 2.5), (int) (point.getY() - 2.5), 5, 5);
//            g.drawOval((int) (point.getX() - 2.5), (int) (point.getY() - 2.5), 5, 5);
//        }
        double maxDistance = 0 ;
        for(int j = 0; j < mas_pointsTrue.size();  j++) {
            if( maxDistance < MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
                maxDistance = MyPoint.distanceToPoint(mas_pointsTrue.get(j), line);
            }
            if (maxDistance == MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
                line = new Line(mas_pointsTrue.get(j).getX(), mas_pointsTrue.get(j).getY(),findMinY().getX(), findMinY().getY() );
            }
        }
        return line;
    }
    public static void  getSquare(){
        double square = 0;
        JOptionPane.showMessageDialog(null, square);

    }
}








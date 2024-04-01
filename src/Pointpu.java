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
    public void paint (Graphics g) {

        int x = -w/100;
        int y = h/100;
        g.setColor(Color.BLACK);
        g.drawLine((w / 2), 0, (w / 2), h);
        for (int i = 0; i < 1000; i += 50) {
            g.drawRect((int) ((h / 2) - 7.5), i , 15, 2);
            g.fillRect((int) ((h / 2) - 7.5), i ,  15 ,2);
            String oy = Integer.toString(y);
            g.drawString(oy, ((w / 2) + 1), i+1);
            y -= 1;
        }
        g.drawLine(0, (h / 2) + 1, w, (h / 2) + 1);
        for (int j = -1;j < 1000; j += 50) {
            g.drawRect(j, (int) ((h / 2) - 7.5), 2, 15);
            g.fillRect(j, (int) ((h / 2) - 7.5), 2, 15);
            String ox = Integer.toString(x);
            g.drawString(ox, j + 2, w / 2);
            x += 1;
        }



        for (int i = 0; i < mas_points.size(); i++) {
            g.setColor(Color.RED);
            Point pt = mas_points.get(i);
            g.fillOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
            g.drawOval((int) (pt.getX() - 2.5), (int) (pt.getY() - 2.5), 5, 5);
        }


        if (mas_points.size() % 10 == 0) { // условный цикл для рисования прямоугольника
            Line line =  new Line ( findMin2Y().getX(), findMin2Y().getY(), findMinY().getX(), findMinY().getY() );//самая первая

            line = firstLine(mas_points, line); //корректируем если сверху есть точки

            g.drawLine(findMinY().x, findMinY().y, findMin2Y().x, findMin2Y().y );//рисует первую но потом ее удалиь надо


            Line perpendicuolarTo = Line.rotatedLine(findMinX());//ей пeрпендикулярная



            Line perpendicuolarToPerpendicular = Line.rotatedLine(findMaxY());
            Line line4 = Line.rotatedLine(findMaxX());

            MyPoint downleft = Line.intersection( perpendicuolarToPerpendicular, perpendicuolarTo);//странная
            MyPoint upleft = Line.intersection(line, perpendicuolarTo);

            MyPoint downright = Line.intersection(line4,  perpendicuolarToPerpendicular);
            MyPoint upright = Line.intersection(line, line4); //странная

            g.drawLine(downleft.getX(), downleft.getY(), upleft.getX(), upleft.getY());//рисуем 4 линии
            g.drawLine(upleft.getX(), upleft.getY(), upright.getX(), upright.getY());
            g.drawLine(downright.getX(), downright.getY(), upright.getX(), upright.getY());
            g.drawLine(downleft.getX(), downleft.getY(), downright.getX(), downright.getY());

            System.out.println("max "+findMinX());//проверка
            System.out.println(downleft.getX()+" "+downleft.getY()+" "+upleft.getX()+" "+upleft.getY()+" "+downright.getX()+" "+ downright.getY()+" "+upright.getX()+" "+upright.getY());

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
    Line firstLine(ArrayList<Point> mas_points, Line line){// для рисования первой линии
        ArrayList<Point> mas_pointsTrue = new ArrayList<>() ;
        for(int i = 0; i < mas_points.size();  i++) {
            if (Line.whichSide(mas_points.get(i), line)) {
                mas_pointsTrue.add(mas_points.get(i));
            } else {
                line = new Line( findMin2Y().getX(), findMin2Y().getY(), findMinY().getX(), findMinY().getY());//самая первая
            }
        }
        for(int j = 0; j < mas_pointsTrue.size();  j++) {
            double maxdistance = 0 ;
            if( maxdistance < MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
                maxdistance = MyPoint.distanceToPoint(mas_pointsTrue.get(j), line);
            }
            if (maxdistance == MyPoint.distanceToPoint(mas_pointsTrue.get(j), line)){
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








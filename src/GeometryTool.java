import java.awt.*;

public class GeometryTool {
    private GeometryTool() {}

    // Проверка выше ли точка прямой
    public static boolean isUpSide(Point point, Line line) {
        return line.getA() * point.getX() + line.getB() * point.getY() + line.getC() > 0;
    }
    public static boolean isDownSide(Point a, Line l) {//для рисования первой прямой в случае огда ниже нее еще остаются точки
            return l.getA() * a.getX() + l.getB() * a.getY() + l.getC() >= 0;// если точка ниже есть
    }
    // Получить расстояние между точкой и прямой
    public static double distanceBetweenPointAndLine(Point p, Line line) {
        return Math.abs((line.A * p.x + line.B * p.y + line.C) / Math.sqrt(line.A*line.A + line.B*line.B));
    }
}

import java.awt.*;

public class GeometryTool {
    private GeometryTool() {}

    // Проверка выше ли точка прямой
    public static boolean isUpSide(Point point, Line line) {
        return line.getA() * point.getX() + line.getB() * point.getY() + line.getC() > 0;
    }

    // Получить линию перпендикулярную текущей через точку
    public static Line perpendicularLine(Point p, Line line){ //right
        return new Line((-1)*line.getB(), line.getA(), (p.getX()*line.getB())-(p.getY()*line.getA()));
    }

    // Получить расстояние между точкой и прямой
    public static double distanceBetweenPointAndLine(Point p, Line line) {
        return Math.abs((line.A * p.x + line.B * p.y + line.C) / Math.sqrt(line.A*line.A + line.B*line.B));
    }
}

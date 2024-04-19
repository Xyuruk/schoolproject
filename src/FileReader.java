import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReader {
    public static ArrayList<String> readFileContents(String fileName) {
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом.");
            return new ArrayList<>();
        }
    }

    public static ArrayList<Point> getPointsFromFile(String fileName) {
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<String> lines = readFileContents(fileName);
        for (String line : lines) {
            String[] coordinates = line.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            Point point = new Point(x, y);
            points.add(point);
        }
        return points;
    }

}
/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class KeyboardReader extends JFrame {
    JTextField  jTextField;
    KeyboardReader(){
        setVisible(true);
        setTitle("Ввод с клавиатуры");
        setSize(400, 650);
        setLocationRelativeTo(null);

        jTextField = new JTextField(7);
        jTextField.setFont(new Font("Courier New", Font.BOLD, 24));
        this.add(jTextField);

        JButton  jButton = new JButton("Записать");
        jButton.setFont(new Font("Courier New", Font.BOLD, 24));
        add(jButton);
        jButton.addActionListener(this::pushButton);

    }

    private void pushButton(ActionEvent actionEvent) {
        getPointsFromKeyboard();
    }

        public static ArrayList<Point> getPointsFromKeyboard( ) {
            ArrayList<Point> points = new ArrayList<>();
            ArrayList<String> lines = null;
            for (String line : lines) {
                String[] coordinates = line.split(" ");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                Point point = new Point(x, y);
                points.add(point);
            }
            return points;
        }
}*/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KeyboardReader extends RectanglePaint {
    public static RectanglePaint RectanglePaint;
    public KeyboardReader(){
    }
    public static ArrayList<Point> getPointsFromKeyboard(){
        String string = JOptionPane.showInputDialog(null,"Введите координаты точки через пробел");
        if (string != null){
            String[] coordinates = string.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            points.add(new Point(x,y));
            System.out.println(points);
            System.out.println(string);
        }
        return points;
    }
}

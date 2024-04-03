import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Main extends JFrame {
    int w = 1000, h = 1000;
    Main(){
        ArrayList<MyPoint> points = new ArrayList<>();
        ArrayList<String> lines = FileReader.readFileContents("points");
        for (String line : lines) {
            String[] coordinates = line.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            MyPoint point = new MyPoint(x, y);
            points.add(point);
        }
        for (MyPoint point : points) {
            System.out.println("x = " + point.getX() + " y = " + point.getY());
        }
        setVisible(true);
        setSize(w, h);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       JMenuBar menuBar = new JMenuBar(); // coздание меню
        menuBar.add(chooseMenu());
        this.setJMenuBar(menuBar);
        add(new Pointpu());


    }
    public JMenu chooseMenu(){
        JMenu jmenu = new JMenu("Выбрать");
        JMenuItem keyboard = new JMenuItem("Клавиатура");
        jmenu.add(keyboard);
        jmenu.add(new JSeparator());
        keyboard.addActionListener(this::readKeyboard);
        JMenuItem mouse = new JMenuItem("Мышка");
        jmenu.add(mouse);
        jmenu.add(new JSeparator());
        mouse.addActionListener(this::readMouse);
        JMenuItem file = new JMenuItem("Файл");
        jmenu.add(file);
        file.addActionListener(this::readFile);

        JMenuItem answer = new JMenuItem("Вывести ответ");
        jmenu.add(answer);
        jmenu.add(new JSeparator());
        answer.addActionListener(this::answering);

        jmenu.setLocation(0,0);
        return jmenu;
    }
    void readMouse(ActionEvent activeEvent){

    }
    void answering(ActionEvent activeEvent){
        Pointpu.getSquare();
    }
    void readFile(ActionEvent activeEvent){

    }
    void readKeyboard(ActionEvent activeEvent){

    }
    public static void main (String[] args){
        new Main().setVisible(true);

    }
}
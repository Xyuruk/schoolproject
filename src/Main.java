import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
    int w = 1000, h = 1000;
    Main(){
        setVisible(true);
        setSize(w, h);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar(); // coздание меню
        menuBar.add(chooseMenu());
        this.setJMenuBar(menuBar);
        add(new RectanglePaint());


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

        JMenuItem draw = new JMenuItem("Нарисовать прямоугольник");
        jmenu.add(draw);
        jmenu.add(new JSeparator());
        draw.addActionListener(this::draw);

        jmenu.setLocation(0,0);
        return jmenu;
    }
    void readMouse(ActionEvent activeEvent){

    }
    void draw(ActionEvent activeEvent){
        RectanglePaint.isPressed = true;
        repaint();
    }
    void answering(ActionEvent activeEvent){
        RectanglePaint.getSquare(RectanglePaint.downleft, RectanglePaint.upleft, RectanglePaint.upright);
    }
    void readFile(ActionEvent activeEvent){
        RectanglePaint.mode = InputMode.FILE_MODE;
        repaint();
    }
    void readKeyboard(ActionEvent activeEvent){

    }
    public static void main (String[] args){
        new Main().setVisible(true);

    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
    int w = 800, h = 750;
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
        jmenu.add(new JSeparator());

        JMenuItem answer = new JMenuItem("Вывести ответ");
        jmenu.add(answer);
        jmenu.add(new JSeparator());
        answer.addActionListener(this::answering);

        JMenuItem draw = new JMenuItem("Нарисовать прямоугольник");
        jmenu.add(draw);
        draw.addActionListener(this::draw);

        jmenu.setLocation(0,0);
        return jmenu;
    }
    void readMouse(ActionEvent activeEvent){
        RectanglePaint.mode = InputMode.MOUSE_MODE;
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
        RectanglePaint.mode = InputMode.KEYBOARD_MODE;
        repaint();
    }
    public static void main (String[] args){
        new Main().setVisible(true);

    }
}
package cz.muni.fi.pb162.project.gui;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Alzbeta Strompova
 * https://github.com/teemoo7/bobby/
 * blob/4623214ed01beabb8f10db6254597124ea2570a4/src/main/java/ch/teemoo/bobby/helpers/GuiHelper.java
 * https://stackoverflow.com/questions/2535417/chess-board-in-java
 */
public class ChessLabel extends JLabel {

    public static final Color BASE = new Color(155, 155, 155);
    public static final Color DARK_SQUARE = new Color(100, 100, 255);
    public static final Color LIGHT_SQUARE = new Color(255, 255, 255);
    public static final Font FONT = new Font("Serif", Font.PLAIN, 48);

    public ChessLabel(String s) {
        super(s);
    }

    public ChessLabel() {
        super();
    }

    public void setSquare(int x, int y) {
        setFont(FONT);
        setOpaque(true);
        setBackground((x + y) % 2 == 0 ? DARK_SQUARE : LIGHT_SQUARE);
        setHorizontalAlignment(CENTER);
    }

    public void setBase() {
        setFont(FONT);
        setOpaque(true);
        setBackground(BASE);
        setHorizontalAlignment(CENTER);
    }

}
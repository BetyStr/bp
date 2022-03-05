package cz.muni.fi.pb162.project.gui;

import cz.muni.fi.pb162.project.Game;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseListener;


/**
 * @author Alzbeta Strompova
 */
public class GUIBoard extends JFrame {

    private final static int SIZE_OF_WINDOW = (int) (Math.min(Toolkit.getDefaultToolkit().getScreenSize().height,
                Toolkit.getDefaultToolkit().getScreenSize().width) * 0.8);
    private final MouseListener mouseListener = new MyMouseListener();
    public static ChessLabel[][] LABELS = new ChessLabel[9][9];
    // todo click new screen

    public void display(Game game)
    {
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(9, 9);
        contentPane.setLayout(gridLayout);

        LABELS[0][0] = new ChessLabel(String.valueOf(Game.SPACE));
        LABELS[0][0].setBase();
        contentPane.add(LABELS[0][0]);
        //add numbers
        for (int i = 1; i < 9; i++) {
            LABELS[0][i] = new ChessLabel(String.valueOf(i));
            LABELS[0][i].setBase();
            contentPane.add(LABELS[0][i]);
        }
        for (int i = 1; i < 9; i++)
        {
            //add letters
            LABELS[0][i] = new ChessLabel(String.valueOf((char) (64 + i)));
            LABELS[0][i].setBase();
            contentPane.add(LABELS[0][i]);

            for (int j = 1; j < 9; j++) {
                var piece = game.getPiece(i - 1, j - 1) == null
                        ? String.valueOf(Game.SPACE)
                        : game.getPiece(i - 1, j - 1).toString();

                LABELS[i][j] = new ChessLabel(piece);
                LABELS[i][j].setSquare(i, j);
                contentPane.add(LABELS[i][j]);
            }
        }

//        JButton b=new JButton("click");//creating instance of JButton
//        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
//        add(b);
        contentPane.addMouseListener(mouseListener);
        setSize(SIZE_OF_WINDOW, SIZE_OF_WINDOW);
       // setLocationRelativeTo(null);
        setVisible(true);
    }

}

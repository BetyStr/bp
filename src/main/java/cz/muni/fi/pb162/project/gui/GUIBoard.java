package cz.muni.fi.pb162.project.gui;

import cz.muni.fi.pb162.project.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


/**
 * @author Alzbeta Strompova
 */
public class GUIBoard extends JFrame {

    private final MouseListener mouseListener = new MyMouseListener();
    public static ChessLabel[][] LABELS = new ChessLabel[9][9];
// todo add letter and number
// todo do it automatic
// click new screen

    public void display(Board board)
    {
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(9, 9);
        contentPane.setLayout(gridLayout);

        LABELS[0][0] = new ChessLabel(String.valueOf(Board.SPACE));
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
                var piece = board.getPiece(i - 1, j - 1) == null
                        ? String.valueOf(Board.SPACE)
                        : board.getPiece(i - 1, j - 1).toString();

                LABELS[i][j] = new ChessLabel(piece);
                LABELS[i][j].setSquare(i, j);
                contentPane.add(LABELS[i][j]);
            }
        }

//        JButton b=new JButton("click");//creating instance of JButton
//        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
//        add(b);
        contentPane.addMouseListener(mouseListener);


        var size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (Math.min(size.width, size.height) * 0.8), (int) (Math.min(size.width, size.height)  * 0.8));
       // setLocationRelativeTo(null);
        setVisible(true);
    }

}

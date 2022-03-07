package cz.muni.fi.pb162.project;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Alzbeta Strompova
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");
        System.out.println(dtf.withLocale(Locale.ENGLISH).format(LocalTime.of(15, 0)));

        System.out.println(System.lineSeparator());
        var player1 = new Player("Jozko", Color.White);
        var player2 = new Player("Janko", Color.Black);

        var chess = new Chess(player1, player2);

        chess.setInitialSet();
        System.out.println(chess.printBoardToConsole());
//        var guiBoard = new GUIBoard();
//        guiBoard.display(chess);

//        chess.write(System.out);
        chess.play();


    }

}

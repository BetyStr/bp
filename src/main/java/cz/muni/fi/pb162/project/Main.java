package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.gui.GUIBoard;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Alzbeta Strompova
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");

        System.out.println(dtf.withLocale(Locale.ENGLISH).format(LocalTime.of(15, 0)));

        System.out.println(System.lineSeparator());
        var player1 = new Player("Jozko", Color.White);
        var player2 = new Player("Janko", Color.Black);

        var chess = new Game(player1, player2);

        var board = chess.setInitialSet();
        System.out.println(board);
        var guiBoard = new GUIBoard();
        guiBoard.display(board);

    }

}

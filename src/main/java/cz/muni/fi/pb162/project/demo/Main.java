package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Player;

/**
 * Class for running main method.
 *
 * @author Alzbeta Strompova
 */
public class Main {


    /**
     * Runs the code.
     *
     * @param args command line arguments, will be ignored.
     */
    public static void main(String[] args) {
        var player1 = new Player("Mat", Color.WHITE);
        var player2 = new Player("Pat", Color.BLACK);

        var chess = new Chess(player1, player2);
        chess.setInitialSet();
        chess.hitSave();
        chess.move(new Coordinate(3, 6),  new Coordinate(3, 5));
        System.out.println(chess.getBoard());
        chess.hitUndo();
        System.out.println(chess.getBoard());
    }

}

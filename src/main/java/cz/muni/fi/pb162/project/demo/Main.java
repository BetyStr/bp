package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Game;
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

        var chess = new Game(player1, player2);
        chess.setInitialSet();
        chess.move(new Coordinate(1, 1), new Coordinate(1, 2));
        for (int i = 0; i < Board.SIZE; i++) {
            System.out.println(chess.getBoard().getPiece(1, i));
        }
    }

}

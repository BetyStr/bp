package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Player;
import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

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
    public static void main(String[] args) throws EmptySquareException, NotAllowedMoveException {
        var player1 = new Player("Jozko", Color.WHITE);
        var player2 = new Player("Janko", Color.BLACK);

        var chess = new Chess(player1, player2);
        chess.setInitialSet();
        chess.play();
    }

}

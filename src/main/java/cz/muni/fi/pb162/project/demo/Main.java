package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinates;
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
        var player1 = new Player("Matko", Color.WHITE);
        var player2 = new Player("Janko", Color.BLACK);

        var chess = new Game(player1, player2, new Board());
        chess.setInitialSet();
        chess.move(new Coordinates(1,1), new Coordinates(1,2));
    }

}

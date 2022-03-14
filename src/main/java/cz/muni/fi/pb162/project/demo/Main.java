package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Draughts;
import cz.muni.fi.pb162.project.Player;
import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

/**
 * @author Alzbeta Strompova
 */
public class Main {


    public static void main(String[] args) throws EmptySquareException, NotAllowedMoveException {

        System.out.println("Hello world!");

        var player1 = new Player("Jozko", Color.WHITE);
        var player2 = new Player("Janko", Color.BLACK);

        var chess = new Draughts(player1, player2);

        chess.setInitialSet();
        chess.play();


    }

}

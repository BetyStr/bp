package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Piece;
import cz.muni.fi.pb162.project.PieceType;
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
        var game = new Game(player1, player2);
        game.putPieceOnBoard(1, 5, new Piece(Color.WHITE, PieceType.QUEEN));
        System.out.println(game.getBoard().getPiece(1, 5).getPieceType());
    }

}

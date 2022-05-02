package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.ChessPieceFactory;
import cz.muni.fi.pb162.project.Color;
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
        var game = new Chess.Builder()
                .addPlayer(new Player("Mat", Color.WHITE))
                .addPlayer(new Player("Pat", Color.BLACK))
                .addPieceToBoard(new ChessPieceFactory()
                        .createPiece(PieceType.KING, Color.WHITE), 'e', 1)
                .build();
        System.out.println(game.getBoard());
    }

}

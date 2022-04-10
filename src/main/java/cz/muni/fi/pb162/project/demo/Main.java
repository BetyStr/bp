package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Piece;

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
        Board board = new Board();
        Piece piece = new Piece();
        board.putPieceOnBoard(0, 0, piece);
        System.out.println(new Coordinates(2, 2));
    }

}

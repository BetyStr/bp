package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class BoardTest {

    private final Board board = new Board();

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Board.class, 2);
        BasicRulesTester.methodsAmount(Board.class, 8);
        BasicRulesTester.attributesFinal(Board.class, 1);
    }

    @Test
    void getRound() {
        assertEquals(0, board.getRound());
    }

    @Test
    void setRound() {
        board.setRound(15);
        assertEquals(15, board.getRound());
    }

    @Test
    void getPiece() {
        var piece = new Piece();
        board.putPieceOnBoard(3, 4, piece);
        board.putPieceOnBoard(6, 1, piece);
        var piece2 = new Piece();
        board.putPieceOnBoard(2, 2, piece2);
        board.putPieceOnBoard(5, 7, piece2);

        assertNull(board.getPiece(1, 3));
        assertNull(board.getPiece(7, 6));
        assertNull(board.getPiece(9, 9));
        assertNull(board.getPiece(2, -2));
        assertEquals(piece, board.getPiece(3, 4));
        assertEquals(piece, board.getPiece(6, 1));
        assertEquals(piece2, board.getPiece(2, 2));
        assertEquals(piece2, board.getPiece(5, 7));
    }

    @Test
    void inRange() {
        assertTrue(Board.inRange(new Coordinates(2, 4)));
        assertTrue(Board.inRange(new Coordinates(0, 0)));
        assertTrue(Board.inRange(new Coordinates(7, 7)));
        assertTrue(Board.inRange(new Coordinates(6, 1)));
        assertFalse(Board.inRange(new Coordinates(5, 15)));
        assertFalse(Board.inRange(new Coordinates(0, 9)));
        assertFalse(Board.inRange(new Coordinates(8, 0)));
        assertFalse(Board.inRange(new Coordinates(-4, -7)));
    }

    @Test
    void isEmptyAndPutPieceOnBoard() {
        var piece = new Piece();
        board.putPieceOnBoard(3, 4, piece);
        board.putPieceOnBoard(6, 1, piece);
        board.putPieceOnBoard(2, 2, piece);
        board.putPieceOnBoard(5, 7, piece);

        assertTrue(board.isEmpty(1, 1));
        assertTrue(board.isEmpty(7, 6));
        assertTrue(board.isEmpty(3, -1));
        assertTrue(board.isEmpty(8, 6));
        assertFalse(board.isEmpty(3, 4));
        assertFalse(board.isEmpty(6, 1));
        assertFalse(board.isEmpty(2, 2));
        assertFalse(board.isEmpty(5, 7));
    }

    @Test
    void findCoordinatesOfPieceById() {
        var piece = new Piece();
        board.putPieceOnBoard(5, 5, piece);
        var result = board.findCoordinatesOfPieceById(piece.getId());
        assertEquals(5, result.letterNumber());
        assertEquals(5, result.number());
        var piece2 = new Piece();
        board.putPieceOnBoard(2, 7, piece2);
        var result2 = board.findCoordinatesOfPieceById(piece2.getId());
        assertEquals(2, result2.letterNumber());
        assertEquals(7, result2.number());
        var result3 = board.findCoordinatesOfPieceById(piece.getId() + piece.getId() + 42);
        assertNull(result3);
    }

}
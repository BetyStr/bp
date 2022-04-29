package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
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
        BasicRulesTester.methodsAmount(Board.class, 7);
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
        assertTrue(Board.inRange(new Coordinate(2, 4)));
        assertTrue(Board.inRange(new Coordinate(0, 0)));
        assertTrue(Board.inRange(new Coordinate(7, 7)));
        assertTrue(Board.inRange(new Coordinate(6, 1)));
        assertFalse(Board.inRange(new Coordinate(5, 15)));
        assertFalse(Board.inRange(new Coordinate(0, 9)));
        assertFalse(Board.inRange(new Coordinate(8, 0)));
        assertFalse(Board.inRange(new Coordinate(-4, -7)));
    }

    @Test
    void isEmptyAndPutPieceOnBoard() {
        board.putPieceOnBoard(3, 4, new Piece());
        board.putPieceOnBoard(6, 1, new Piece());
        board.putPieceOnBoard(2, 2, new Piece());
        board.putPieceOnBoard(5, 7, new Piece());

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
    }
}
package cz.muni.fi.pb162.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class BoardTest {

    private Board board = new Board();

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
        assertNull(board.getPiece(1, 3));
        assertNull(board.getPiece(7, 6));
//        assertThrows(IndexOutOfBoundsException.class, board.getPiece(9,9));
    }

    @Test
    void inRange() {
        assertTrue(board.inRange(new Coordinates(2, 4)));
        assertTrue(board.inRange(new Coordinates(0, 0)));
        assertTrue(board.inRange(new Coordinates(7, 7)));
        assertTrue(board.inRange(new Coordinates(6, 1)));
        assertFalse(board.inRange(new Coordinates(5, 15)));
        assertFalse(board.inRange(new Coordinates(0, 9)));
        assertFalse(board.inRange(new Coordinates(8, 0)));
        assertFalse(board.inRange(new Coordinates(-4, -7)));
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
        var result3 = board.findCoordinatesOfPieceById(piece.getId() + piece.getId());
        assertNull(result3);

    }
}
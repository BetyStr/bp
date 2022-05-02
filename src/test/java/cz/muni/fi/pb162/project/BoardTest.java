package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static cz.muni.fi.pb162.project.Color.WHITE;
import static cz.muni.fi.pb162.project.PieceType.ROOK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class BoardTest {

    private static final PieceFactory FACTORY = new ChessPieceFactory();
    private final Board board = new Board();

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Board.class, 2);
        BasicRulesTester.methodsAmount(Board.class, 17);
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
        assertNull(board.getPiece(1, 3));
        assertNull(board.getPiece(7, 6));
        var piece = FACTORY.createPiece(PieceType.KING, WHITE);
        board.putPieceOnBoard(1, 2, piece);
        assertEquals(piece.getId(), board.getPiece(1, 2).getId());
    }

    @Test
    void getPieceCoordinates() {
        assertNull(board.getPiece(new Coordinate(1, 4)));
        assertNull(board.getPiece(new Coordinate(5, 2)));
        var piece = FACTORY.createPiece(PieceType.QUEEN, Color.BLACK);
        board.putPieceOnBoard(6, 6, piece);
        assertEquals(piece.getId(), board.getPiece(6, 6).getId());
    }

    @Test
    void getColor() {
        assertNull(board.getColor(4, 3));
        assertNull(board.getColor(7, 1));
        var piece = FACTORY.createPiece(ROOK, WHITE);
        var piece2 = FACTORY.createPiece(PieceType.ROOK, Color.BLACK);
        board.putPieceOnBoard(5, 5, piece);
        board.putPieceOnBoard(3, 3, piece2);
        assertEquals(piece.getColor(), board.getColor(5, 5));
        assertEquals(piece2.getColor(), board.getColor(3, 3));
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
        var piece = FACTORY.createPiece(PieceType.KING, WHITE);
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
        var piece = FACTORY.createPiece(PieceType.KING, WHITE);
        board.putPieceOnBoard(5, 5, piece);
        var result = board.findCoordinatesOfPieceById(piece.getId());
        assertEquals(5, result.letterNumber());
        assertEquals(5, result.number());
        var piece2 = FACTORY.createPiece(PieceType.KING, WHITE);
        board.putPieceOnBoard(2, 7, piece2);
        var result2 = board.findCoordinatesOfPieceById(piece2.getId());
        assertEquals(2, result2.letterNumber());
        assertEquals(7, result2.number());
        var result3 = board.findCoordinatesOfPieceById(piece.getId() + piece.getId() + 42);
        assertNull(result3);
    }

    @Test
    void getAllPiecesFromBoard() {
        var board = new Board();
        var result = new ArrayList<Piece>();
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 8; j++) {
                var piece = FACTORY.createPiece(PieceType.PAWN, WHITE);
                board.putPieceOnBoard(i, j, piece);
                result.add(piece);
            }
        }
        assertEquals(result.size(), board.getAllPiecesFromBoard().length);

        var board2 = new Board();
        var piece = FACTORY.createPiece(PieceType.KING, WHITE);
        board2.putPieceOnBoard(1, 2, piece);
        var piece2 = FACTORY.createPiece(PieceType.QUEEN, Color.BLACK);
        board2.putPieceOnBoard(5, 5, piece2);
        var piece3 = FACTORY.createPiece(PieceType.BISHOP, Color.BLACK);
        board2.putPieceOnBoard(7, 4, piece3);
        Assertions.assertThat(board2.getAllPiecesFromBoard())
                .containsOnly(piece, piece2, piece3);
    }

    @Test
    void testToString() {
        var expectedOutput = """
                    1   2   3   4   5   6   7   8\s
                  --------------------------------
                A |   |   |   |   |   |   |   |   |
                  --------------------------------
                B |   |   |   |   |   |   |   |   |
                  --------------------------------
                C |   |   |   |   |   |   |   |   |
                  --------------------------------
                D |   |   |   |   |   |   |   |   |
                  --------------------------------
                E |   |   |   |   |   |   |   |   |
                  --------------------------------
                F |   |   |   |   |   |   |   |   |
                  --------------------------------
                G |   |   |   |   |   |   |   |   |
                  --------------------------------
                H |   |   |   |   |   |   |   |   |
                  --------------------------------""".replace("\n", System.lineSeparator());
        assertEquals(expectedOutput, board.toString());
    }

    @Test
    void save() {
        board.putPieceOnBoard(2, 3, FACTORY.createPiece(PieceType.ROOK, Color.WHITE));
        var saveBoard = board.save();
        assertEquals(board, saveBoard);
        assertNotSame(board, saveBoard, "You should create new instance.");
    }


    @Test
    void restore() {
        board.putPieceOnBoard(2, 3, FACTORY.createPiece(PieceType.ROOK, Color.WHITE));
        var emptyBoard = new Board();
        emptyBoard.restore(board);
        assertEquals(board, emptyBoard);
        assertNotSame(board, emptyBoard, "You should update existence instance.");
        emptyBoard = new Board();
        board.restore(emptyBoard);
        assertEquals(emptyBoard, board);
        assertNotSame(emptyBoard, board, "You should update existence instance.");
    }

    @Test
    void testEquals() {
        assertThat(new Board()).isEqualTo(new Board());
        var game = new Chess(null, null);
        game.setInitialSet();
        var game2 = new Chess(null, null);
        game2.setInitialSet();
        assertThat(game.getBoard())
                .withFailMessage("At board are different pieces, because every piece has unique id.")
                .isNotEqualTo(game2.getBoard());
        var board2 = new Board();
        var piece = FACTORY.createPiece(PieceType.ROOK, Color.WHITE);
        board.putPieceOnBoard(7, 1, piece);
        board2.putPieceOnBoard(7, 1, piece);
        assertThat(board).isEqualTo(board2);
        board2.putPieceOnBoard(7, 1, FACTORY.createPiece(PieceType.ROOK, Color.BLACK));
        assertThat(board).isNotEqualTo(board2);
    }

    @Test
    void testHashCode() {
        assertThat(new Board()).hasSameHashCodeAs(new Board());
        var game = new Chess(null, null);
        game.setInitialSet();
        var game2 = new Chess(null, null);
        game2.setInitialSet();
        assertThat(game.getBoard().hashCode())
                .withFailMessage("At board are different pieces, because every piece has unique id.")
                .doesNotHaveSameHashCodeAs(game2.getBoard());
        var board2 = new Board();
        var piece = FACTORY.createPiece(PieceType.ROOK, Color.WHITE);
        board.putPieceOnBoard(7, 1, piece);
        board2.putPieceOnBoard(7, 1, piece);
        assertThat(board).hasSameHashCodeAs(board2);
        board2.putPieceOnBoard(7, 1, FACTORY.createPiece(PieceType.ROOK, Color.BLACK));
        assertThat(board).doesNotHaveSameHashCodeAs(board2);
    }
}
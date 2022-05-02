package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import cz.muni.fi.pb162.project.moves.Diagonal;
import java.util.ArrayList;
import java.util.HashSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class PieceTest {

    private static final PieceFactory FACTORY = new ChessPieceFactory();
    private final Piece piece = FACTORY.createPiece(PieceType.KING, Color.WHITE);
    private final Piece piece2 = FACTORY.createPiece(PieceType.QUEEN, Color.WHITE);
    private final Piece piece3 = FACTORY.createPiece(PieceType.ROOK, Color.BLACK);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Piece.class, 4);
        BasicRulesTester.methodsAmount(Piece.class, 13);
    }

    @Test
    void inheritance() {
        assertTrue(Prototype.class.isAssignableFrom(Piece.class));
    }

    @Test
    void getId() {
        var pieceIds = new HashSet<Long>();
        for (int i = 0; i < 42; i++) {
            pieceIds.add(FACTORY.createPiece(PieceType.KING, Color.WHITE).getId());
        }
        assertEquals(42, pieceIds.size(), "Instances of piece should have different ids.");
    }

    @Test
    void getColor() {
        assertEquals(Color.WHITE, piece.getColor());
        assertEquals(Color.WHITE, piece2.getColor());
        assertEquals(Color.BLACK, piece3.getColor());
    }

    @Test
    void getAndSetTypeOfPiece() {
        assertEquals(PieceType.KING, piece.getPieceType());
        assertEquals(PieceType.QUEEN, piece2.getPieceType());
        assertEquals(PieceType.ROOK, piece3.getPieceType());
        piece.setPieceType(PieceType.KNIGHT);
        piece2.setPieceType(PieceType.BISHOP);
        piece3.setPieceType(PieceType.PAWN);
        assertEquals(PieceType.KNIGHT, piece.getPieceType());
        assertEquals(PieceType.BISHOP, piece2.getPieceType());
        assertEquals(PieceType.PAWN, piece3.getPieceType());
    }

    @Test
    void testToString() {
        assertEquals("\u2654", piece.toString());
        assertEquals("\u2655", piece2.toString());
        assertEquals("\u265C", piece3.toString());
        piece.setPieceType(PieceType.KNIGHT);
        piece2.setPieceType(PieceType.BISHOP);
        piece3.setPieceType(PieceType.PAWN);
        assertEquals("\u2658", piece.toString());
        assertEquals("\u2657", piece2.toString());
        assertEquals("\u265F", piece3.toString());
    }

    @Test
    void makeClone() {
        var pieceClone = piece.makeClone();
        var pieceClone2 = piece.makeClone();
        var piece2Clone = piece2.makeClone();
        var piece3Clone = piece3.makeClone();
        assertEquals(piece.getColor(), pieceClone.getColor());
        assertEquals(piece.getPieceType(), pieceClone.getPieceType());
        assertEquals(piece.getMoves(), pieceClone.getMoves());
        assertNotEquals(piece.getId(), pieceClone.getId());

        assertEquals(piece.getColor(), pieceClone2.getColor());
        assertEquals(piece.getPieceType(), pieceClone2.getPieceType());
        assertEquals(piece.getMoves(), pieceClone2.getMoves());
        assertNotEquals(piece.getId(), pieceClone2.getId());

        assertEquals(piece2.getColor(), piece2Clone.getColor());
        assertEquals(piece2.getPieceType(), piece2Clone.getPieceType());
        assertEquals(piece2.getMoves(), piece2Clone.getMoves());
        assertNotEquals(piece.getId(), piece2Clone.getId());

        assertEquals(piece3.getColor(), piece3Clone.getColor());
        assertEquals(piece3.getPieceType(), piece3Clone.getPieceType());
        assertEquals(piece3.getMoves(), piece3Clone.getMoves());
        assertNotEquals(piece3.getId(), piece3Clone.getId());
    }

    @Test
    void testEquals() {
        assertThat(piece).isNotEqualTo(piece2);
        assertThat(piece).isNotEqualTo(piece3);
        assertThat(piece2).isNotEqualTo(piece3);
        assertThat(piece).isNotEqualTo(FACTORY.createPiece(PieceType.KING, Color.WHITE));
        assertThat(piece).isEqualTo(piece);
        assertThat(piece2).isEqualTo(piece2);
        assertThat(piece3).isEqualTo(piece3);
    }

    @Test
    void testHashCode() {
        assertNotEquals(piece.hashCode(), piece2.hashCode());
        assertNotEquals(piece.hashCode(), piece3.hashCode());
        assertNotEquals(piece2.hashCode(), piece3.hashCode());
        assertNotEquals(piece.hashCode(), FACTORY.createPiece(PieceType.KING, Color.WHITE).hashCode());
        assertEquals(piece.hashCode(), piece.hashCode());
        assertEquals(piece2.hashCode(), piece2.hashCode());
        assertEquals(piece3.hashCode(), piece3.hashCode());
    }

    @Test
    void getMoves() {
        var moves = piece.getMoves();
        moves = new ArrayList<>();
        Assertions.assertThat(piece.getMoves()).isNotEmpty();

        moves = piece2.getMoves();
        var move = new Diagonal(5);
        try {
            moves.add(move);
        } catch (UnsupportedOperationException ex) {
            moves = null;
        }
        Assertions.assertThat(piece2.getMoves()).doesNotContain(move);
    }

    @Test
    void getAllPossibleMoves() {
        var game = new Chess(null, null);
        game.setInitialSet();
        var whiteKing = game.getBoard().getPiece(4, 0);
        Assertions.assertThat(whiteKing.getAllPossibleMoves(game)).isEmpty();
        var blackPawn = game.getBoard().getPiece(3, 6);
        Assertions.assertThat(blackPawn.getAllPossibleMoves(game))
                .containsOnly(new Coordinate(3, 5), new Coordinate(3, 4));
        var whiteQueen = FACTORY.createPiece(PieceType.QUEEN, Color.WHITE);
        game.putPieceOnBoard(7, 1, whiteQueen);
        Assertions.assertThat(whiteQueen.getAllPossibleMoves(game))
                .containsOnly(new Coordinate(7, 2),
                        new Coordinate(7, 3),
                        new Coordinate(7, 4),
                        new Coordinate(7, 5),
                        new Coordinate(7, 6),
                        new Coordinate(6, 2),
                        new Coordinate(5, 3),
                        new Coordinate(4, 4),
                        new Coordinate(3, 5),
                        new Coordinate(2, 6));
        game.move(new Coordinate(3, 1), new Coordinate(3, 3));
        Assertions.assertThat(whiteKing.getAllPossibleMoves(game)).containsOnly(new Coordinate(3, 1));
    }

    @Test
    void testGetAllPossibleMovesWithCastling() {
        var game = new Chess(null, null);
        game.setInitialSet();
        testCastling(game, 0);
        testCastling(game, 7);
    }

    private void testCastling(Chess game, int column) {
        game.putPieceOnBoard(1, column, null);
        game.putPieceOnBoard(2, column, null);
        game.putPieceOnBoard(3, column, null);
        var whiteKing = game.getBoard().getPiece(4, column);
        Assertions.assertThat(whiteKing.getAllPossibleMoves(game)).containsOnly(new Coordinate(3, column));
        game.putPieceOnBoard(5, column, null);
        game.putPieceOnBoard(6, column, null);
        Assertions.assertThat(whiteKing.getAllPossibleMoves(game))
                .containsOnly(new Coordinate(3, column), new Coordinate(5, column));
    }

}
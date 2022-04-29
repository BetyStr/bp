package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class PieceTest {

    private final Piece piece = new Piece(Color.WHITE, PieceType.KING);
    private final Piece piece2 = new Piece(Color.WHITE, PieceType.QUEEN);
    private final Piece piece3 = new Piece(Color.BLACK, PieceType.ROOK);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Piece.class, 3);
        BasicRulesTester.methodsAmount(Piece.class, 6);
    }

    @Test
    void inheritance() {
        assertTrue(Prototype.class.isAssignableFrom(Piece.class));
    }

    @Test
    void getId() {
        var pieceIds = new HashSet<Long>();
        for (int i = 0; i < 42; i++) {
            pieceIds.add(new Piece(Color.WHITE, PieceType.KING).getId());
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
        assertEquals(PieceType.KING, piece.getTypeOfPiece());
        assertEquals(PieceType.QUEEN, piece2.getTypeOfPiece());
        assertEquals(PieceType.ROOK, piece3.getTypeOfPiece());
        piece.setTypeOfPiece(PieceType.KNIGHT);
        piece2.setTypeOfPiece(PieceType.BISHOP);
        piece3.setTypeOfPiece(PieceType.PAWN);
        assertEquals(PieceType.KNIGHT, piece.getTypeOfPiece());
        assertEquals(PieceType.BISHOP, piece2.getTypeOfPiece());
        assertEquals(PieceType.PAWN, piece3.getTypeOfPiece());
    }

    @Test
    void testToString() {
        assertEquals("K", piece.toString());
        assertEquals("Q", piece2.toString());
        assertEquals("R", piece3.toString());
        piece.setTypeOfPiece(PieceType.KNIGHT);
        piece2.setTypeOfPiece(PieceType.BISHOP);
        piece3.setTypeOfPiece(PieceType.PAWN);
        assertEquals("K", piece.toString());
        assertEquals("B", piece2.toString());
        assertEquals("P", piece3.toString());
    }

    @Test
    void makeClone() {
        var pieceClone = piece.makeClone();
        var pieceClone2 = piece.makeClone();
        var piece2Clone = piece2.makeClone();
        var piece3Clone = piece3.makeClone();
        assertEquals(piece.getColor(), pieceClone.getColor());
        assertEquals(piece.getTypeOfPiece(), pieceClone.getTypeOfPiece());
        assertNotEquals(piece.getId(), pieceClone.getId());

        assertEquals(piece.getColor(), pieceClone2.getColor());
        assertEquals(piece.getTypeOfPiece(), pieceClone2.getTypeOfPiece());
        assertNotEquals(piece.getId(), pieceClone2.getId());

        assertEquals(piece2.getColor(), piece2Clone.getColor());
        assertEquals(piece2.getTypeOfPiece(), piece2Clone.getTypeOfPiece());
        assertNotEquals(piece.getId(), piece2Clone.getId());

        assertEquals(piece3.getColor(), piece3Clone.getColor());
        assertEquals(piece3.getTypeOfPiece(), piece3Clone.getTypeOfPiece());
        assertNotEquals(piece.getId(), piece3Clone.getId());
    }
}
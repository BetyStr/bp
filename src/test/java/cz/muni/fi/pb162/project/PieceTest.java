package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        BasicRulesTester.methodsAmount(Piece.class, 8);
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
        assertEquals("K", piece.toString());
        assertEquals("Q", piece2.toString());
        assertEquals("R", piece3.toString());
        piece.setPieceType(PieceType.KNIGHT);
        piece2.setPieceType(PieceType.BISHOP);
        piece3.setPieceType(PieceType.PAWN);
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
        assertEquals(piece.getPieceType(), pieceClone.getPieceType());
        assertNotEquals(piece.getId(), pieceClone.getId());

        assertEquals(piece.getColor(), pieceClone2.getColor());
        assertEquals(piece.getPieceType(), pieceClone2.getPieceType());
        assertNotEquals(piece.getId(), pieceClone2.getId());

        assertEquals(piece2.getColor(), piece2Clone.getColor());
        assertEquals(piece2.getPieceType(), piece2Clone.getPieceType());
        assertNotEquals(piece.getId(), piece2Clone.getId());

        assertEquals(piece3.getColor(), piece3Clone.getColor());
        assertEquals(piece3.getPieceType(), piece3Clone.getPieceType());
        assertNotEquals(piece.getId(), piece3Clone.getId());
    }

    @Test
    void testEquals() {
        assertThat(piece).isNotEqualTo(piece2);
        assertThat(piece).isNotEqualTo(piece3);
        assertThat(piece2).isNotEqualTo(piece3);
        assertThat(piece).isNotEqualTo(new Piece(Color.WHITE, PieceType.KING));
        assertThat(piece).isEqualTo(piece);
        assertThat(piece2).isEqualTo(piece2);
        assertThat(piece3).isEqualTo(piece3);
    }

    @Test
    void testHashCode() {
        assertNotEquals(piece.hashCode(), piece2.hashCode());
        assertNotEquals(piece.hashCode(), piece3.hashCode());
        assertNotEquals(piece2.hashCode(), piece3.hashCode());
        assertNotEquals(piece.hashCode(), new Piece(Color.WHITE, PieceType.KING).hashCode());
        assertEquals(piece.hashCode(), piece.hashCode());
        assertEquals(piece2.hashCode(), piece2.hashCode());
        assertEquals(piece3.hashCode(), piece3.hashCode());
    }
}
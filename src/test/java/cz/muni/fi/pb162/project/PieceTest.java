package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        BasicRulesTester.methodsAmount(Piece.class, 4);
        BasicRulesTester.attributesFinal(Piece.class, 2);
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

}
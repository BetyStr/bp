package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Knight;
import cz.muni.fi.pb162.project.moves.Pawn;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class ChessPieceFactoryTest {

    private final PieceFactory factory = new ChessPieceFactory();

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(ChessPieceFactory.class, 0);
        BasicRulesTester.methodsAmount(ChessPieceFactory.class, 1);
    }

    @Test
    void inheritance() {
        assertTrue(PieceFactory.class.isAssignableFrom(ChessPieceFactory.class));
    }

    @Test
    void createPiece() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE);
        });
        var actualMessage = exception.getMessage().toLowerCase(Locale.ROOT);
        assertTrue(actualMessage.contains("unknown"));
        var piece = factory.createPiece(PieceType.BISHOP, Color.BLACK);
        assertEquals(PieceType.BISHOP, piece.getPieceType());
        assertEquals(Color.BLACK, piece.getColor());
        assertNotEquals(piece, factory.createPiece(PieceType.BISHOP, Color.BLACK));
        var piece2 = factory.createPiece(PieceType.ROOK, Color.WHITE);
        assertEquals(PieceType.ROOK, piece2.getPieceType());
        assertEquals(Color.WHITE, piece2.getColor());
        assertNotEquals(piece, factory.createPiece(PieceType.ROOK, Color.WHITE));
    }

    @Test
    void createSetOfPrototypes() {
        var actual = factory.createSetOfPrototypes(Set.of(PieceType.QUEEN, PieceType.PAWN));
        assertEquals(2, actual.size());
        var input = Set.of(PieceType.QUEEN, PieceType.DRAUGHTS_MAN);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createSetOfPrototypes(input);
        });
        var actualMessage = exception.getMessage().toLowerCase(Locale.ROOT);
        assertTrue(actualMessage.contains("chess"));
    }

    @Test
    void testListOfMoves() {
        assertEquals(1, factory.createPiece(PieceType.BISHOP, Color.WHITE).getMoves().size());
        assertEquals(1, factory.createPiece(PieceType.ROOK, Color.BLACK).getMoves().size());
        assertSame(factory.createPiece(PieceType.PAWN, Color.WHITE).getMoves().get(0).getClass(), Pawn.class);
        assertSame(factory.createPiece(PieceType.BISHOP, Color.BLACK).getMoves().get(0).getClass(), Diagonal.class);
        assertSame(factory.createPiece(PieceType.KNIGHT, Color.WHITE).getMoves().get(0).getClass(), Knight.class);
        assertEquals(2, factory.createPiece(PieceType.QUEEN, Color.BLACK).getMoves().size());
        assertEquals(2, factory.createPiece(PieceType.KING, Color.BLACK).getMoves().size());
    }
}
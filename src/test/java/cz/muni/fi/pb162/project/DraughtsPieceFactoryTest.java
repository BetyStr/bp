package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class DraughtsPieceFactoryTest {

    private final PieceFactory factory = new DraughtsPieceFactory();

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(ChessPieceFactory.class, 0);
        BasicRulesTester.methodsAmount(ChessPieceFactory.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(PieceFactory.class, DraughtsPieceFactory.class);
    }

    @Test
    void createPiece() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createPiece(PieceType.QUEEN, Color.WHITE));
        var actualMessage = exception.getMessage().toLowerCase(Locale.ROOT);
        assertTrue(actualMessage.contains("unknown"));
        var piece = factory.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK);
        assertEquals(PieceType.DRAUGHTS_MAN, piece.getPieceType());
        assertEquals(Color.BLACK, piece.getColor());
        assertNotEquals(piece, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.BLACK));
        var piece2 = factory.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE);
        assertEquals(PieceType.DRAUGHTS_KING, piece2.getPieceType());
        assertEquals(Color.WHITE, piece2.getColor());
        assertNotEquals(piece, factory.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE));
    }

    @Test
    void createSetOfPrototypes() {
        var actual = factory.createSetOfPrototypes(Set.of(PieceType.DRAUGHTS_MAN, PieceType.DRAUGHTS_KING));
        assertEquals(2, actual.size());
        var input = Set.of(PieceType.DRAUGHTS_MAN, PieceType.QUEEN);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createSetOfPrototypes(input));
        var actualMessage = exception.getMessage().toLowerCase(Locale.ROOT);
        assertTrue(actualMessage.contains("draught"));
    }

    @Test
    void testListOfMoves() {
        assertEquals(2, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE).getMoves().size());
        assertEquals(2, factory.createPiece(PieceType.DRAUGHTS_MAN, Color.WHITE).getMoves().size());
        assertEquals(2, factory.createPiece(PieceType.DRAUGHTS_KING, Color.WHITE).getMoves().size());
        assertEquals(2, factory.createPiece(PieceType.DRAUGHTS_KING, Color.BLACK).getMoves().size());
    }
}
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

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Piece.class, 1);
        BasicRulesTester.methodsAmount(Piece.class, 1);
        BasicRulesTester.attributesFinal(Piece.class, 1);
    }

    @Test
    void getId() {
        var pieceIds = new HashSet<Long>();
        for (int i = 0; i < 42; i++) {
            pieceIds.add(new Piece().getId());
        }
        assertEquals(42, pieceIds.size(), "Instances of piece should have different ids.");
    }

}

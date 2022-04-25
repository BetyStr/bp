package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alzbeta Strompova
 */
class PieceTest {

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Piece.class, 1);
        BasicRulesTester.methodsAmount(Piece.class, 1);
    }

    @Test
    void getId() {
        var piece = new Piece(1L);
        var piece2 = new Piece(2L);
        var piece3 = new Piece(3L);
        assertEquals(1L, piece.getId());
        assertEquals(2L, piece2.getId());
        assertEquals(3L, piece3.getId());
    }
}
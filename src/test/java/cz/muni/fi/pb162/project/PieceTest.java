package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        var piece = new Piece();
        var piece2 = new Piece();
        var piece3 = new Piece();
        assertNotEquals(piece.getId(), piece2.getId());
        assertNotEquals(piece.getId(), piece3.getId());
        assertNotEquals(piece2.getId(), piece3.getId());
    }
}
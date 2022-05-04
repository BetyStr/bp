package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class PieceFactoryTest {

    @Test
    void abstractClass() {
        BasicRulesTester.testAbstractClass(PieceFactory.class);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(FactoryMethodOfPiece.class, PieceFactory.class);
    }

}
package cz.muni.fi.pb162.project.exceptions;

import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.InvalidFormatOfInputException;
import cz.muni.fi.pb162.project.excepions.MissingPlayerException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class ExceptionsTest {

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(EmptySquareException.class, 0);
        BasicRulesTester.methodsAmount(EmptySquareException.class, 0);
        BasicRulesTester.attributesAmount(InvalidFormatOfInputException.class, 0);
        BasicRulesTester.methodsAmount(InvalidFormatOfInputException.class, 0);
        BasicRulesTester.attributesAmount(MissingPlayerException.class, 0);
        BasicRulesTester.methodsAmount(MissingPlayerException.class, 0);
        BasicRulesTester.attributesAmount(NotAllowedMoveException.class, 0);
        BasicRulesTester.methodsAmount(NotAllowedMoveException.class, 0);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(RuntimeException.class, MissingPlayerException.class);
        BasicRulesTester.testInheritance(RuntimeException.class, MissingPlayerException.class);
        BasicRulesTester.testInheritance(Exception.class, EmptySquareException.class);
        BasicRulesTester.testInheritance(Exception.class, NotAllowedMoveException.class);
    }

}

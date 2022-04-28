package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class GameTest {

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Game.class, 4);
        BasicRulesTester.methodsAmount(Game.class, 9);
    }
}
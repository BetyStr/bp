package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alzbeta Strompova
 */
class CoordinateTest {

    private final Coordinate one = new Coordinate(1, 3);
    private final Coordinate two = new Coordinate(-2, 0);
    private final Coordinate three = new Coordinate(3, 9);
    private final Coordinate four = new Coordinate(0, -23);
    private final Coordinate five = new Coordinate(15, -4);
    private final Coordinate six = new Coordinate(-7, 7);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Coordinate.class, 2);
        BasicRulesTester.methodsAmount(Coordinate.class, 9);
        BasicRulesTester.attributesFinal(Player.class);
    }

    @Test
    void getLetterNumber() {
        assertEquals(1, one.letterNumber());
        assertEquals(-2, two.letterNumber());
        assertEquals(3, three.letterNumber());
        assertEquals(0, four.letterNumber());
        assertEquals(15, five.letterNumber());
        assertEquals(-7, six.letterNumber());
    }

    @Test
    void getNumber() {
        assertEquals(3, one.number());
        assertEquals(0, two.number());
        assertEquals(9, three.number());
        assertEquals(-23, four.number());
        assertEquals(-4, five.number());
        assertEquals(7, six.number());
    }


    @Test
    void add() {
        var result = four.add(five);
        assertEquals(15, result.letterNumber());
        assertEquals(-27, result.number());
        var result2 = five.add(six);
        assertEquals(8, result2.letterNumber());
        assertEquals(3, result2.number());
        var result3 = six.add(five);
        assertEquals(result2.letterNumber(), result3.letterNumber());
        assertEquals(result2.number(), result3.number());
    }

    @Test
    void averageOfCoordinates() {
        assertEquals(-11.5, four.averageOfCoordinates());
        assertEquals(5.5, five.averageOfCoordinates());
        assertEquals(0, six.averageOfCoordinates());
    }

    @Test
    void testToString() {
        assertEquals("b2", new Coordinate(1, 1).toString());
        assertEquals("h1", new Coordinate(7, 0).toString());
        assertEquals("a7", new Coordinate(0, 6).toString());
        assertEquals("c5", new Coordinate(2, 4).toString());
        assertEquals("e4", new Coordinate(4, 3).toString());
        assertEquals("f6", new Coordinate(5, 5).toString());
        assertEquals("d3", new Coordinate(3, 2).toString());
        assertEquals("g8", new Coordinate(6, 7).toString());
    }

    @Test
    void compareTo() {
        Assertions.assertThat(six)
                .isLessThan(two)
                .isLessThan(four)
                .isLessThan(one)
                .isLessThan(three)
                .isLessThan(five);
        Assertions.assertThat(four).isLessThan(new Coordinate(0, 0));
    }
}
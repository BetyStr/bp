package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alzbeta Strompova
 */
class CoordinatesTest {

    private final Coordinates one = new Coordinates(1, 3);
    private final Coordinates two = new Coordinates(-2, 0);
    private final Coordinates three = new Coordinates(3, 9);
    private final Coordinates four = new Coordinates(0, -23);
    private final Coordinates five = new Coordinates(15, -4);
    private final Coordinates six = new Coordinates(-7, 7);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Coordinates.class, 2);
        BasicRulesTester.methodsAmount(Coordinates.class, 6);
    }

    @Test
    void getLetterNumber() {
        assertEquals(1, one.getLetterNumber());
        assertEquals(-2, two.getLetterNumber());
        assertEquals(3, three.getLetterNumber());
        assertEquals(0, four.getLetterNumber());
        assertEquals(15, five.getLetterNumber());
        assertEquals(-7, six.getLetterNumber());
    }

    @Test
    void setLetterNumber() {
        one.setLetterNumber(33);
        assertEquals(33, one.getLetterNumber());
        two.setLetterNumber(-13);
        assertEquals(-13, two.getLetterNumber());
        three.setLetterNumber(0);
        assertEquals(0, three.getLetterNumber());
    }

    @Test
    void getNumber() {
        assertEquals(3, one.getNumber());
        assertEquals(0, two.getNumber());
        assertEquals(9, three.getNumber());
        assertEquals(-23, four.getNumber());
        assertEquals(-4, five.getNumber());
        assertEquals(7, six.getNumber());
    }

    @Test
    void setNumber() {
        one.setNumber(33);
        assertEquals(33, one.getNumber());
        two.setNumber(-13);
        assertEquals(-13, two.getNumber());
        three.setNumber(0);
        assertEquals(0, three.getNumber());
    }

    @Test
    void add() {
        var result = four.add(five);
        assertEquals(15, result.getLetterNumber());
        assertEquals(-27, result.getNumber());
        var result2 = five.add(six);
        assertEquals(8, result2.getLetterNumber());
        assertEquals(3, result2.getNumber());
        var result3 = six.add(five);
        assertEquals(result2.getLetterNumber(), result3.getLetterNumber());
        assertEquals(result2.getNumber(), result3.getNumber());
    }

    @Test
    void averageOfCoordinates() {
        assertEquals(-11.5, four.averageOfCoordinates());
        assertEquals(5.5, five.averageOfCoordinates());
        assertEquals(0, six.averageOfCoordinates());
    }
}
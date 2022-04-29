package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.utils.BoardNotation;

/**
 * Class for running main method.
 *
 * @author Alzbeta Strompova
 */
public class Main {


    /**
     * Runs the code.
     *
     * @param args command line arguments, will be ignored.
     */
    public static void main(String[] args) {
        System.out.println(BoardNotation.getNotationOfCoordinates(1, 3));

        Coordinate one = new Coordinate(1, 7);
        Coordinate two = new Coordinate(5, 0);
        System.out.println(one.averageOfCoordinates());

        Coordinate result = one.add(two);
        System.out.println(result.letterNumber());
        System.out.println(result.number());
    }

}

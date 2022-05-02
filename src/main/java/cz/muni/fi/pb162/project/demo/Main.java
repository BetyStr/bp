package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Coordinate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Class for running main method.
 *
 * @author Alzbeta Strompova
 */
public class Main {

    private static final Random RANDOM = new Random();


    /**
     * Runs the code.
     *
     * @param args command line arguments, will be ignored.
     */
    public static void main(String[] args) {
        var hashSet = new HashSet<Coordinate>();
        var start = System.currentTimeMillis();
        testSpeed(hashSet);
        var finish = System.currentTimeMillis();
        var setTime = finish - start;

        var arrayList = new ArrayList<Coordinate>();
        start = System.currentTimeMillis();
        testSpeed(arrayList);
        finish = System.currentTimeMillis();
        var listTime = finish - start;

        System.out.printf("%d < %d%n", setTime, listTime);
        System.out.println(setTime < listTime);
    }

    private static void testSpeed(Collection<Coordinate> coordinates) {
        for (int i = 0; i < 100000; i++) {
            coordinates.add(new Coordinate(RANDOM.nextInt(15), RANDOM.nextInt(42)));
        }
        for (int i = 0; i < 10000; i++) {
            coordinates.remove(new Coordinate(RANDOM.nextInt(15), RANDOM.nextInt(42)));
        }
    }
}

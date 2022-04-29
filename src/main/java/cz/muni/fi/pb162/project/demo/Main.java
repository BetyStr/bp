package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Player;

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
        Player player = new Player();
        player.setName("Matko");
        System.out.println(player.getName());

        Coordinate one = new Coordinate(1, 7);
        Coordinate two = new Coordinate(5, 0);
        System.out.println(one.averageOfCoordinate());

        Coordinate result = one.add(two);
        System.out.println(result.getLetterNumber());
        System.out.println(result.getNumber());
    }

}

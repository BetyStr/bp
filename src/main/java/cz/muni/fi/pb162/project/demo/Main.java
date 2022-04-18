package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Coordinates;
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

        Coordinates one = new Coordinates(1, 7);
        Coordinates two = new Coordinates(5, 0);
        System.out.println(one.averageOfCoordinates());

        Coordinates result = one.add(two);
        System.out.println(result.letterNumber());
        System.out.println(result.number());
    }

}

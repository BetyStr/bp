package cz.muni.fi.pb162.project;

import java.util.Objects;

/**
 * Class representing coordinates of board
 *
 * @author Alzbeta Strompova
 */
public class Coordinates {
    private int letterNumber;
    private int number;

    /**
     * Constructor
     *
     * @param letterNumber first coordinate of board
     * @param number second coordinate of board
     */
    public Coordinates(int letterNumber, int number) {
        this.letterNumber = letterNumber;
        this.number = number;
    }

    public int getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(int letterNumber) {
        this.letterNumber = letterNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

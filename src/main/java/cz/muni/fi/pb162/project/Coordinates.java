package cz.muni.fi.pb162.project;

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
     * @param number       second coordinate of board
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

    /**
     * Addition of two coordinates.
     *
     * @param other is second coordinates.
     * @return mean of coordinates.
     */
    public Coordinates add(Coordinates other) {
        return new Coordinates(letterNumber + other.letterNumber, number + other.letterNumber);
    }

    /**
     * Compute mean of coordinates.
     *
     * @return mean of coordinates.
     */
    public double meanOfCoordinates() {
        return (letterNumber + number) / 2.0;
    }

}

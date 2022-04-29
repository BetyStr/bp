package cz.muni.fi.pb162.project;

/**
 * Class representing coordinate of board.
 *
 * @author Alzbeta Strompova
 */
public class Coordinate {

    private int letterNumber;
    private int number;

    /**
     * Constructor
     *
     * @param letterNumber first coordinate of board
     * @param number       second coordinate of board
     */
    public Coordinate(int letterNumber, int number) {
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
     * @param other is second coordinate.
     * @return new coordinate, which is created by adding the {@code other}.
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(letterNumber + other.letterNumber, number + other.number);
    }

    /**
     * Compute average of coordinate.
     *
     * @return average of coordinate.
     */
    public double averageOfCoordinate() {
        return (letterNumber + number) / 2.0;
    }

}

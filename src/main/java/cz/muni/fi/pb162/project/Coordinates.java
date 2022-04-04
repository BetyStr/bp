package cz.muni.fi.pb162.project;


/**
 * Record representing coordinates of board.
 *
 * @author Alzbeta Strompova
 */
public class Coordinates {

    private int letterNumber;
    private int number;

    /**
     * Constructor.
     *
     * @param letterNumber first coordinates of board.
     * @param number       second coordinates of board.
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
     * @return new coordinates, which is created by adding the {@code other}.
     */
    public Coordinates add(Coordinates other) {
        return new Coordinates(letterNumber + other.letterNumber, number + other.number);
    }

    /**
     * Compute average of coordinates.
     *
     * @return average of coordinates.
     */
    public double averageOfCoordinates() {
        return (letterNumber + number) / 2.0;
    }

}

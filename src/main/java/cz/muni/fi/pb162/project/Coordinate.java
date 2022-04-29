package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;

/**
 * Record representing coordinate of board.
 *
 * @param letterNumber first coordinate of board.
 * @param number       second coordinate of board.
 * @author Alzbeta Strompova
 */
public record Coordinate(int letterNumber, int number) {

    /**
     * Addition of two coordinate.
     *
     * @param other is second coordinate.
     * @return new coordinates, which is created by adding the {@code other}.
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(letterNumber + other.letterNumber, number + other.number);
    }

    /**
     * Compute average of coordinate.
     *
     * @return average of coordinate.
     */
    public double averageOfCoordinates() {
        return (letterNumber + number) / 2.0;
    }

    @Override
    public String toString() {
        return BoardNotation.getNotationOfCoordinates(letterNumber, number);
    }

}

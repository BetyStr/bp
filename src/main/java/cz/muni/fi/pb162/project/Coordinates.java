package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.utils.BoardNotation;

/**
 * Record representing coordinates of board.
 *
 * @param letterNumber first coordinates of board.
 * @param number       second coordinates of board.
 * @author Alzbeta Strompova
 */
public record Coordinates(int letterNumber, int number) {


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

    @Override
    public String toString() {
        return BoardNotation.getNotationOfCoordinates(letterNumber, number);
    }

}

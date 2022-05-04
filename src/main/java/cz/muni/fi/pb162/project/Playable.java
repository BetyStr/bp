package cz.muni.fi.pb162.project;

/**
 * Interface serves as a template, so that every board game has certain methods
 * without which it would not be playable.
 *
 * @author Alzbeta Strompova
 */
public interface Playable {

    /**
     * Method that sets initial layout for the game.
     */
    void setInitialSet();

    /**
     * Method that does one move in the game. Get piece from {@code oldPosition} and put it to {@code newPosition}.
     *
     * @param oldPosition coordinates of piece we want to move.
     * @param newPosition coordinates of place we want to put this piece.
     */
    void move(Coordinates oldPosition, Coordinates newPosition);

    /**
     * Method that represents playing the board game.
     */
    void play();

}

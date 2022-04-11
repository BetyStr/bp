package cz.muni.fi.pb162.project;

/**
 * Interface to help implement game
 *
 * @author Alzbeta Strompova
 */
public interface Playable {

    /**
     * Method that set initial layout for game
     */
    void setInitialSet();

    /**
     * Method that do one move in game. Get piece from {@code oldPosition} and put it to {@code newPosition}.
     *
     * @param oldPosition coordinates of piece we want to move
     * @param newPosition coordinates of place we want to put this piece
     */
    void move(Coordinates oldPosition, Coordinates newPosition);

    /**
     * Method that represent playing board game until is finish.
     */
    void play();

}

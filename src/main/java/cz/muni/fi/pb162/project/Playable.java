package cz.muni.fi.pb162.project;

/**
 * Interface to help implement board game.
 *
 * @author Alzbeta Strompova
 */
public interface Playable extends Prototype<Playable>, Caretaker {

    /**
     * Method that set initial layout for game
     */
    void setInitialSet();

    /**
     * Method that do one move in game. Get piece from {@code oldPosition} and put it to {@code newPosition}.
     *
     * @param oldPosition coordinate of piece we want to move.
     * @param newPosition coordinate of place we want to put this piece.
     */
    void move(Coordinate oldPosition, Coordinate newPosition);

    /**
     * Method that represent playing board game until is finish.
     */
    void play();

}

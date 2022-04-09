package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

/**
 * Interface to help implement game
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
     * @param oldPosition coordinates of piece we want to move
     * @param newPosition coordinates of place we want to put this piece
     */
    void move(Coordinates oldPosition, Coordinates newPosition);

    /**
     * Method that represent playing board game until is finish.
     *
     * @throws EmptySquareException    if we want to move piece from empty square on board.
     * @throws NotAllowedMoveException if we want to do illegal move.
     */
    void play() throws EmptySquareException, NotAllowedMoveException;

}

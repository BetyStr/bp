package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.exceptions.EmptySquareException;
import cz.muni.fi.pb162.project.exceptions.NotAllowedMoveException;

/**
 * Interface serves as a template, so that every board game has certain methods
 * without which it would not be playable.
 *
 * @author Alzbeta Strompova
 */
public interface Playable extends Prototype<Playable>, Caretaker {

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
     *
     * @throws EmptySquareException    if we want to move piece from empty square on board.
     * @throws NotAllowedMoveException if we want to do illegal move.
     */
    void play() throws NotAllowedMoveException, EmptySquareException;

}

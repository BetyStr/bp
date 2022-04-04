package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.excepions.EmptySquareException;
import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public interface Playable extends Prototype<Playable>, Caretaker {
 
    void setInitialSet();

    void move(Coordinates oldPosition, Coordinates newPosition);

    void play() throws EmptySquareException, NotAllowedMoveException;

    void updateStatus();

    Set<Coordinates> allPossibleMovesByCurrentPlayer();

}

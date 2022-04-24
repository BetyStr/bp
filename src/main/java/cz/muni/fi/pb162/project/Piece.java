package cz.muni.fi.pb162.project;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Class represent piece of board game
 *
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final long id;

    /**
     * Constructor which set up uniq id.
     */
    public Piece() {
        id = ID_COUNTER.getAndIncrement();
    }

    public long getId() {
        return id;
    }

}

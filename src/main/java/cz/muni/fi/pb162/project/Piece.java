package cz.muni.fi.pb162.project;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Class represent piece of board game
 *
 * @author Alzbeta Strompova
 */
public class Piece {

    private static final AtomicLong ID_COUNTER = new AtomicLong();
    private final long id;

    /**
     * Constructor takes color and type of piece and set up uniq id
     */
    public Piece() {
        id = ID_COUNTER.getAndIncrement();
    }

    public long getId() {
        return id;
    }
}

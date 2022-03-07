package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Color;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Alzbeta Strompova
 */
public abstract class Piece {

    private final long id;
    private final Color color;

    private static final AtomicLong idCounter = new AtomicLong();

    public Piece(Color color) {
        id = createID();
        this.color = color;
    }

    private static long createID() {
        return idCounter.getAndIncrement();
    }

    public Color getColor() {
        return color;
    }


    public abstract Set<Coordinates> getAllPossibleMoves(Game game);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass().isInstance(o.getClass())) {
            return false;
        }
        Piece piece = (Piece) o;
        return id == piece.id && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }

    public long getId() {
        return id;
    }
}

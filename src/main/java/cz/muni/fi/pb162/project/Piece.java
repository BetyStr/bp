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

    private int letterNumber;
    private int number;

    private static final AtomicLong idCounter = new AtomicLong();

    public Piece(Color color, int letterNumber, int number) {
        id = createID();
        this.color = color;
        this.letterNumber = letterNumber;
        this.number = number;
    }

    private static long createID() {
        return idCounter.getAndIncrement();
    }

    public Color getColor() {
        return color;
    }

    public int getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(int letterNumber) {
        this.letterNumber = letterNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract Set<Coordinates> getAllPossibleMoves(Board board);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return id == piece.id && letterNumber == piece.letterNumber &&
                number == piece.number && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, id, letterNumber, number);
    }
}

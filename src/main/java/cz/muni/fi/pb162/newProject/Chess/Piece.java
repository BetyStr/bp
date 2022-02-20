package cz.muni.fi.pb162.newProject.Chess;

import cz.muni.fi.pb162.newProject.Board;
import cz.muni.fi.pb162.newProject.Color;
import cz.muni.fi.pb162.newProject.Coordinates;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Piece {

    private final Color color;
    private final TypeOfPiece type;

    private int x;
    private int y;

    public Piece(Color color, TypeOfPiece type, int x, int y) {
        this.color = color;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public TypeOfPiece getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Set<Coordinates> getAllPossibleMoves(Board board) {
        throw new NotImplementedException("");
    }

    protected boolean checkPosition(Board board, int x, int y, Set<Coordinates> result) {
        if (board.isEmpty(x, y) || board.getColor(x, y).getOppositeColor().equals(getColor())) {
            result.add(new Coordinates(x, y));
            return true;
        }
        return false;
    }

    protected Set<Coordinates> getDiagonalMoves(Board board){
        var result = new HashSet<Coordinates>();
        for (int i = 1; i <= 7 ; i++) {
            if (board.getColor(getX() + i, getY() + i).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX() + i, getY() + i));
                break;
            }
            if (board.getColor(getX() + i, getY() + i).getOppositeColor() == null) {
                result.add(new Coordinates(getX() + i, getY() + i));
            }
        }
        for (int i = 1; i <= 7 ; i++) {
            if (board.getColor(getX() - i, getY() + i).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX() - i, getY() + i));
                break;
            }
            if (board.getColor(getX() - i, getY() + i).getOppositeColor() == null) {
                result.add(new Coordinates(getX() - i, getY() + i));
            }
        }
        for (int i = 1; i <= 7 ; i++) {
            if (board.getColor(getX() - i, getY() - i).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX() - i, getY() - i));
                break;
            }
            if (board.getColor(getX() - i, getY() - i).getOppositeColor() == null) {
                result.add(new Coordinates(getX() - i, getY() - i));
            }
        }
        for (int i = 1; i <= 7 ; i++) {
            if (board.getColor(getX() + i, getY() - i).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX() + i, getY() - i));
                break;
            }
            if (board.getColor(getX() + i, getY() - i).getOppositeColor() == null) {
                result.add(new Coordinates(getX() + i, getY() - i));
            }
        }
        return result;
    }

    protected Set<Coordinates> getStraightMoves(Board board) {
        var result = new HashSet<Coordinates>();
        for (int i = 1; i <= 7 - getX() ; i++) {
            if (board.getColor(getX() + i, getY()).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX() + i, getY()));
                break;
            }
            if (board.getColor(getX() + i, getY()).getOppositeColor() == null) {
                result.add(new Coordinates(getX() + i, getY()));
            }
        }
        for (int i = 1; i <= 7 - getY() ; i++) {
            if (board.getColor(getX(), getY() + i).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX(), getY() + i));
                break;
            }
            if (board.getColor(getX(), getY() + i).getOppositeColor() == null) {
                result.add(new Coordinates(getX(), getY() + i));
            }
        }
        for (int i = 1; i <= 1 + getX() ; i++) {
            if (board.getColor(getX() - i, getY()).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX() - i, getY()));
                break;
            }
            if (board.getColor(getX() - i, getY()).getOppositeColor() == null) {
                result.add(new Coordinates(getX() - i, getY()));
            }
        }
        for (int i = 1; i <= 1 + getY() ; i++) {
            if (board.getColor(getX(), getY() - i).getOppositeColor().equals(getColor())) {
                result.add(new Coordinates(getX(), getY() - i));
                break;
            }
            if (board.getColor(getX(), getY() - i).getOppositeColor() == null) {
                result.add(new Coordinates(getX(), getY() - i));
            }
        }
        return result;
    }

}

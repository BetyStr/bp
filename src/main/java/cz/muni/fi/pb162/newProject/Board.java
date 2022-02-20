package cz.muni.fi.pb162.newProject;


import cz.muni.fi.pb162.newProject.Chess.Piece;
import cz.muni.fi.pb162.newProject.Chess.TypeOfPiece;

/**
 * @author Alzbeta Strompova
 */
public class Board {

    private Piece[][] board = new Piece[8][8];


    public TypeOfPiece getType(int x, int y) {
        if (isEmpty(x,y)){
            return null;
        }
        return board[x][y].getType();
    }

    public Color getColor(int x, int y) {
        if (isEmpty(x,y)){
            return null;
        }
        return board[x][y].getColor();
    }

    public boolean isEmpty(int x, int y) {
        return x <= 7 && y <= 7 && x >= 0 && y >= 0 && board[x][y] == null;
    }

    public void move(Coordinates oldPosition, Coordinates newPosition) {
        board[newPosition.getX()][newPosition.getY()] = board[oldPosition.getX()][oldPosition.getY()];
        board[oldPosition.getX()][oldPosition.getY()] = null;
    }

}

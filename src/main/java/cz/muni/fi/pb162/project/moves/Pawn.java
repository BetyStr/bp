package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Pawn implements AllowedMoves {

    @Override
    public Set<Coordinates> getAllowedMoves(Board board, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = board.getPiece(position).getColor();

        if (color.equals(Color.White)){
            result.add(new Coordinates(position.getLetterNumber(), position.getNumber() + 1));
            if (position.getNumber() == 1){
                result.add(new Coordinates(position.getLetterNumber(), 3));
            }
            if (board.getPiece(position.getLetterNumber() + 1,
                    position.getNumber() + 1).getColor().equals(Color.Black)){
                result.add(new Coordinates(position.getLetterNumber() + 1, position.getNumber() + 1));
            }
            if (board.getPiece(position.getLetterNumber() - 1,
                    position.getNumber() + 1).getColor().equals(Color.Black)){
                result.add(new Coordinates(position.getLetterNumber() - 1, position.getNumber() + 1));
            }
        }
        if (color.equals(Color.Black)){
            result.add(new Coordinates(position.getLetterNumber(), position.getNumber() - 1));
            if (position.getNumber() == 6){
                result.add(new Coordinates(position.getLetterNumber(), 4));
            }
            if (board.getPiece(position.getLetterNumber() + 1,
                    position.getNumber() - 1).getColor().equals(Color.White)){
                result.add(new Coordinates(position.getLetterNumber() + 1, position.getNumber() - 1));
            }
            if (board.getPiece(position.getLetterNumber() - 1,
                    position.getNumber() - 1).getColor().equals(Color.White)){
                result.add(new Coordinates(position.getLetterNumber() - 1, position.getNumber() - 1));
            }
        }
        // todo en passaint
        return result;
    }
}

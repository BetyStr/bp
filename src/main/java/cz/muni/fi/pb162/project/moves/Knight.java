package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Coordinates;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Knight implements Move {

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = game.getPiece(position).getColor();

        if (game.getColor(position.getLetterNumber() + 2, position.getNumber() + 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() + 1));
        } else if (color.equals(game.getColor(position.getLetterNumber() + 2, position.getNumber() + 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() + 1));
        }


        if (game.getColor(position.getLetterNumber() + 2, position.getNumber() - 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() - 1));
        } else if (color.equals(game.getColor(position.getLetterNumber() + 2, position.getNumber() - 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() + 2, position.getNumber() - 1));
        }

        if (game.getColor(position.getLetterNumber() - 2, position.getNumber() + 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() + 1));
        } else if (color.equals(game.getColor(position.getLetterNumber() - 2, position.getNumber() + 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() + 1));
        }
        if (game.getColor(position.getLetterNumber() - 2, position.getNumber() - 1) == null) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() - 1));
        } else if (color.equals(game.getColor(position.getLetterNumber() - 2, position.getNumber() - 1).getOppositeColor())) {
            result.add(new Coordinates(position.getLetterNumber() - 2, position.getNumber() - 1));
        }

        return result;
    }
}

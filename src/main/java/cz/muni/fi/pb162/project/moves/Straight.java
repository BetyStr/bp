package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Coordinates;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Straight implements Move {

    private int step = Game.SIZE;

    public Straight() {
    }

    public Straight(int step) {
        this.step = step;
    }

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {
        var result = new HashSet<Coordinates>();
        var color = game.getPiece(position).getColor();

        for (int i = 1; i <= step ; i++) {
            if (game.getColor(position.letterNumber() + i, position.number()) == null) {
                result.add(new Coordinates(position.letterNumber() + i, position.number()));
            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber() + i, position.number()))) {
                result.add(new Coordinates(position.letterNumber() + i, position.number()));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i <= step ; i++) {
            if (game.getColor(position.letterNumber() - i, position.number()) == null) {
                result.add(new Coordinates(position.letterNumber() - i, position.number()));
            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber() - i, position.number()))) {
                result.add(new Coordinates(position.letterNumber() - i, position.number()));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i <= step ; i++) {
            if (game.getColor(position.letterNumber(), position.number() + i) == null) {
                result.add(new Coordinates(position.letterNumber(), position.number() + i));
            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber(), position.number() + i))) {
                result.add(new Coordinates(position.letterNumber(), position.number() + i));
                break;
            } else {
                break;
            }
        }

        for (int i = 1; i <= step ; i++) {
            if (game.getColor(position.letterNumber(), position.number() - i) == null) {
                result.add(new Coordinates(position.letterNumber(), position.number() - i));
            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber(), position.number() - i))) {
                result.add(new Coordinates(position.letterNumber(), position.number() - i));
                break;
            } else {
                break;
            }
        }
        return result;
    }

}

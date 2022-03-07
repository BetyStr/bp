package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Diagonal implements Move {

    private int step = Game.SIZE;

    /**
     * Constructor
     */
    public Diagonal() {
    }

    /**
     * Constructor
     * @param step max distance to move
     */
    public Diagonal(int step) {
        this.step = step;
    }

    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {

        var result = new HashSet<Coordinates>();
        var color = game.getPiece(position).getColor();
        Color goal;
        for (int i = 1; i <= step ; i++) {
            goal = game.getColor(position.letterNumber() + i, position.number() + i);
            if (goal == null) {
                result.add(new Coordinates(position.letterNumber() + i, position.number() + i));
            } else if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.letterNumber() + i, position.number() + i));
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i <= step ; i++) {
            goal = game.getColor(position.letterNumber() - i, position.number() + i);
            if (goal == null) {
                result.add(new Coordinates(position.letterNumber() - i, position.number() + i));
            } else if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.letterNumber() - i, position.number() + i));
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i <= step ; i++) {
            goal = game.getColor(position.letterNumber() - i, position.number() - i);
            if (goal == null) {
                result.add(new Coordinates(position.letterNumber() - i, position.number() - i));
            } else if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.letterNumber() - i, position.number() - i));
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i <= step ; i++) {
            goal = game.getColor(position.letterNumber() + i, position.number() - i);
            if (goal == null) {
                result.add(new Coordinates(position.letterNumber() + i, position.number() - i));
            } else if (color.getOppositeColor().equals(goal)) {
                result.add(new Coordinates(position.letterNumber() + i, position.number() - i));
                break;
            } else {
                break;
            }
        }
        return result;
    }

}

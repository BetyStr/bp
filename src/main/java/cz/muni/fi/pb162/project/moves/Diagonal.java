package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.Color;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alzbeta Strompova
 */
public class Diagonal implements Move {

    private int step = Game.SIZE;
    private boolean onlyForward = false;

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

    /**
     * Constructor
     */
    public Diagonal(int step, boolean onlyForward) {
        this(step);
        this.onlyForward = onlyForward;
    }


    @Override
    public Set<Coordinates> getAllowedMoves(Game game, Coordinates position) {

        var result = new HashSet<Coordinates>();
        var color = game.getPiece(position).getColor();
        Color goal;

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<> (Arrays.asList(
                Pair.of(1,1),
                Pair.of(-1,1)));
        if (!onlyForward) {
            coordinates.add(Pair.of(-1,-1));
            coordinates.add(Pair.of(1,-1));
        }

        for (Pair<Integer, Integer> movement: coordinates) {
            for (int i = 1; i <= step ; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                goal = game.getColor(left, right);
                if (goal == null) {
                    result.add(new Coordinates(left, right));
                } else if (color.getOppositeColor().equals(goal)) {
                    result.add(new Coordinates(left, right));
                    break;
                } else {
                    break;
                }
            }
        }

//
//        for (int i = 1; i <= step ; i++) {
//            goal = game.getColor(position.letterNumber() + i, position.number() + i);
//            if (goal == null) {
//                result.add(new Coordinates(position.letterNumber() + i, position.number() + i));
//            } else if (color.getOppositeColor().equals(goal)) {
//                result.add(new Coordinates(position.letterNumber() + i, position.number() + i));
//                break;
//            } else {
//                break;
//            }
//        }
//        for (int i = 1; i <= step ; i++) {
//            goal = game.getColor(position.letterNumber() - i, position.number() + i);
//            if (goal == null) {
//                result.add(new Coordinates(position.letterNumber() - i, position.number() + i));
//            } else if (color.getOppositeColor().equals(goal)) {
//                result.add(new Coordinates(position.letterNumber() - i, position.number() + i));
//                break;
//            } else {
//                break;
//            }
//        }
//        for (int i = 1; i <= step ; i++) {
//            goal = game.getColor(position.letterNumber() - i, position.number() - i);
//            if (goal == null) {
//                result.add(new Coordinates(position.letterNumber() - i, position.number() - i));
//            } else if (color.getOppositeColor().equals(goal)) {
//                result.add(new Coordinates(position.letterNumber() - i, position.number() - i));
//                break;
//            } else {
//                break;
//            }
//        }
//        for (int i = 1; i <= step ; i++) {
//            goal = game.getColor(position.letterNumber() + i, position.number() - i);
//            if (goal == null) {
//                result.add(new Coordinates(position.letterNumber() + i, position.number() - i));
//            } else if (color.getOppositeColor().equals(goal)) {
//                result.add(new Coordinates(position.letterNumber() + i, position.number() - i));
//                break;
//            } else {
//                break;
//            }
//        }
        return result;
    }

}

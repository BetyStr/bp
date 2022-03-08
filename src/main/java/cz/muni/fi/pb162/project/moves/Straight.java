package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Coordinates;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
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

        HashSet<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(0, 1),
                Pair.of(0, -1),
                Pair.of(1, 0),
                Pair.of(-1, 0)));

        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= step; i++) {
                var left = position.letterNumber() + i * movement.getLeft();
                var right = position.number() + i * movement.getRight();
                if (game.getColor(left, right) == null) {
                    result.add(new Coordinates(left, right));
                } else if (color.getOppositeColor().equals(game.getColor(left, right))) {
                    result.add(new Coordinates(left, right));
                    break;
                } else {
                    break;
                }
            }
        }

//        for (int i = 1; i <= step ; i++) {
//            if (game.getColor(position.letterNumber() + i, position.number()) == null) {
//                result.add(new Coordinates(position.letterNumber() + i, position.number()));
//            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber() + i, position.number()))) {
//                result.add(new Coordinates(position.letterNumber() + i, position.number()));
//                break;
//            } else {
//                break;
//            }
//        }
//
//        for (int i = 1; i <= step ; i++) {
//            if (game.getColor(position.letterNumber() - i, position.number()) == null) {
//                result.add(new Coordinates(position.letterNumber() - i, position.number()));
//            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber() - i, position.number()))) {
//                result.add(new Coordinates(position.letterNumber() - i, position.number()));
//                break;
//            } else {
//                break;
//            }
//        }
//
//        for (int i = 1; i <= step ; i++) {
//            if (game.getColor(position.letterNumber(), position.number() + i) == null) {
//                result.add(new Coordinates(position.letterNumber(), position.number() + i));
//            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber(), position.number() + i))) {
//                result.add(new Coordinates(position.letterNumber(), position.number() + i));
//                break;
//            } else {
//                break;
//            }
//        }
//
//        for (int i = 1; i <= step ; i++) {
//            if (game.getColor(position.letterNumber(), position.number() - i) == null) {
//                result.add(new Coordinates(position.letterNumber(), position.number() - i));
//            } else if (color.getOppositeColor().equals(game.getColor(position.letterNumber(), position.number() - i))) {
//                result.add(new Coordinates(position.letterNumber(), position.number() - i));
//                break;
//            } else {
//                break;
//            }
//        }
        return result;
    }

}

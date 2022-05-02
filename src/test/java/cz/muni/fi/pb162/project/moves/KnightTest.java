package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.ChessPieceFactory;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.PieceFactory;
import cz.muni.fi.pb162.project.PieceType;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;


import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class KnightTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Knight.class, 0);
        BasicRulesTester.methodsAmount(Knight.class, 1);
    }

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Knight.class));
    }

    @Test
    void getAllowedMoves() {
        var game = new Chess(null, null);
        game.setInitialSet();
        var knight = new Knight();
        Assertions.assertThat(knight.getAllowedMoves(game, new Coordinate(1, 0)))
                .containsOnly(new Coordinate(0,2), new Coordinate(2,2));
        game.putPieceOnBoard(4, 3, new ChessPieceFactory().createPiece(PieceType.KNIGHT, Color.BLACK));
        Assertions.assertThat(knight.getAllowedMoves(game, new Coordinate(4, 3)))
                .containsOnly(new Coordinate(3,1),
                        new Coordinate(5,1),
                        new Coordinate(3,5),
                        new Coordinate(5,5),
                        new Coordinate(2,2),
                        new Coordinate(2,4),
                        new Coordinate(6,2),
                        new Coordinate(6,4));
    }
}
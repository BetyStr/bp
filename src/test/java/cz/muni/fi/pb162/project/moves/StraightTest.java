package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.ChessPieceFactory;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.PieceFactory;
import cz.muni.fi.pb162.project.PieceType;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class StraightTest {

    private static final PieceFactory FACTORY = new ChessPieceFactory();
    private final Game game = new Chess(null, null);

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Straight.class));
    }

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Straight.class, 1);
        BasicRulesTester.methodsAmount(Straight.class, 1);
    }

    @Test
    void getAllowedMovesStepOne() {
        game.setInitialSet();
        var straight = new Straight(1);
        Assertions.assertThat(straight.getAllowedMoves(game, new Coordinate(1, 1)))
                .containsOnly(new Coordinate(1, 2));
        Assertions.assertThat(straight.getAllowedMoves(game, new Coordinate(5, 6)))
                .containsOnly(new Coordinate(5, 5));
        game.putPieceOnBoard(4, 4, FACTORY.createPiece(PieceType.ROOK, Color.WHITE));
        Assertions.assertThat(straight.getAllowedMoves(game, new Coordinate(4, 4)))
                .containsOnly(new Coordinate(4, 5), new Coordinate(4, 3),
                        new Coordinate(5, 4), new Coordinate(3, 4));
    }

    @Test
    void getAllowedMovesStepBoardSize() {
        game.putPieceOnBoard(3, 3, FACTORY.createPiece(PieceType.QUEEN, Color.WHITE));
        var straight = new Straight();
        Assertions.assertThat(straight.getAllowedMoves(game, new Coordinate(3, 3)))
                .containsOnly(new Coordinate(3, 0),
                        new Coordinate(3, 1),
                        new Coordinate(3, 2),
                        new Coordinate(3, 4),
                        new Coordinate(3, 5),
                        new Coordinate(3, 6),
                        new Coordinate(3, 7),
                        new Coordinate(0, 3),
                        new Coordinate(1, 3),
                        new Coordinate(2, 3),
                        new Coordinate(4, 3),
                        new Coordinate(5, 3),
                        new Coordinate(6, 3),
                        new Coordinate(7, 3));
        game.setInitialSet();
        Assertions.assertThat(straight.getAllowedMoves(game, new Coordinate(7, 1)))
                .containsOnly(new Coordinate(7, 2),
                        new Coordinate(7, 3),
                        new Coordinate(7, 4),
                        new Coordinate(7, 5),
                        new Coordinate(7, 6));
        Assertions.assertThat(straight.getAllowedMoves(game, new Coordinate(0, 0))).isEmpty();
    }
}
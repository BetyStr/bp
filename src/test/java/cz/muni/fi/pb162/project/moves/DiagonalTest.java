package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
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
class DiagonalTest {

    private static final PieceFactory FACTORY = new ChessPieceFactory();
    private final Game game = new Chess(null, null);

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Diagonal.class, 2);
        BasicRulesTester.methodsAmount(Diagonal.class, 1);
    }

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Diagonal.class));
    }

    @Test
    void getAllowedMovesStepOne() {
        game.setInitialSet();
        var diagonal = new Diagonal(1);
        var diagonal2 = new Diagonal(1, true);
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Coordinate(1, 1)))
                .containsOnly(new Coordinate(0, 2), new Coordinate(2, 2));
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Coordinate(5, 6)))
                .containsOnly(new Coordinate(6, 5), new Coordinate(4, 5));
        game.putPieceOnBoard(5, 5, FACTORY.createPiece(PieceType.BISHOP, Color.WHITE));
        Assertions.assertThat(diagonal2.getAllowedMoves(game, new Coordinate(5, 5)))
                .containsOnly(new Coordinate(4, 6), new Coordinate(6, 6));
    }

    @Test
    void getAllowedMovesStepBoardSize() {
        game.putPieceOnBoard(3, 3, FACTORY.createPiece(PieceType.QUEEN, Color.WHITE));
        var diagonal = new Diagonal();
        var diagonal2 = new Diagonal(Board.SIZE, true);
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Coordinate(3, 3)))
                .containsOnly(new Coordinate(0, 0),
                        new Coordinate(1, 1),
                        new Coordinate(2, 2),
                        new Coordinate(4, 4),
                        new Coordinate(5, 5),
                        new Coordinate(6, 6),
                        new Coordinate(7, 7),
                        new Coordinate(2, 4),
                        new Coordinate(4, 2),
                        new Coordinate(1, 5),
                        new Coordinate(5, 1),
                        new Coordinate(0, 6),
                        new Coordinate(6, 0));
        Assertions.assertThat(diagonal2.getAllowedMoves(game, new Coordinate(3, 3)))
                .containsOnly(new Coordinate(4, 4),
                        new Coordinate(5, 5),
                        new Coordinate(6, 6),
                        new Coordinate(7, 7),
                        new Coordinate(2, 4),
                        new Coordinate(1, 5),
                        new Coordinate(0, 6));
        game.putPieceOnBoard(3, 3, FACTORY.createPiece(PieceType.QUEEN, Color.BLACK));
        Assertions.assertThat(diagonal2.getAllowedMoves(game, new Coordinate(3, 3)))
                .containsOnly(new Coordinate(0, 0),
                        new Coordinate(1, 1),
                        new Coordinate(2, 2),
                        new Coordinate(4, 2),
                        new Coordinate(5, 1),
                        new Coordinate(6, 0));
        game.setInitialSet();
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Coordinate(0, 0))).isEmpty();
    }
}
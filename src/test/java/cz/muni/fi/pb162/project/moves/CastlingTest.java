package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class CastlingTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Knight.class, 0);
        BasicRulesTester.methodsAmount(Knight.class, 1);
    }

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Castling.class));
    }

    @Test
    void getAllowedMovesWhiteSize() {
        testCastling(0);
    }

    @Test
    void getAllowedMovesBlackSize() {
        testCastling(7);
    }

    private void testCastling(int column) {
        var game = new Chess(null, null);
        var castling = new Castling();
        game.setInitialSet();
        game.hitSave();
        game.putPieceOnBoard(1, column, null);
        game.putPieceOnBoard(2, column, null);
        game.putPieceOnBoard(3, column, null);
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinate(4, column)))
                .containsOnly(new Coordinate(2, column));

        game.putPieceOnBoard(5, column, null);
        game.putPieceOnBoard(6, column, null);
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinate(4, column)))
                .containsOnly(new Coordinate(2, column), new Coordinate(6, column));

        game.move(new Coordinate(0, column), new Coordinate(1, column));
        game.hitSave();
        game.move(new Coordinate(1, column), new Coordinate(0, column));
        game.hitSave();
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinate(4, column)))
                .containsOnly(new Coordinate(6, column));

        game.move(new Coordinate(4, column), new Coordinate(5, column));
        game.hitSave();
        game.move(new Coordinate(5, column), new Coordinate(4, column));
        game.hitSave();
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinate(4, column))).isEmpty();
        game.setInitialSet();
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinate(4, column))).isEmpty();
    }
}
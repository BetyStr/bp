package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Coordinates;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class CastlingTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Castling.class, 0);
        BasicRulesTester.methodsAmount(Castling.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Move.class, Castling.class);
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
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinates(4, column)))
                .containsOnly(new Coordinates(2, column));

        game.putPieceOnBoard(5, column, null);
        game.putPieceOnBoard(6, column, null);
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinates(4, column)))
                .containsOnly(new Coordinates(2, column), new Coordinates(6, column));

        game.move(new Coordinates(0, column), new Coordinates(1, column));
        game.hitSave();
        game.move(new Coordinates(1, column), new Coordinates(0, column));
        game.hitSave();
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinates(4, column)))
                .containsOnly(new Coordinates(6, column));

        game.move(new Coordinates(4, column), new Coordinates(5, column));
        game.hitSave();
        game.move(new Coordinates(5, column), new Coordinates(4, column));
        game.hitSave();
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinates(4, column))).isEmpty();
        game.setInitialSet();
        Assertions.assertThat(castling.getAllowedMoves(game, new Coordinates(4, column))).isEmpty();
    }
}
package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.Draughts;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Piece;
import cz.muni.fi.pb162.project.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
class JumpTest {

    private final Game game = new Draughts(null, null);

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Jump.class));
    }

    @Test
    void getAllowedMovesStepOne() {
        var jump = new Jump();
        var jump2 = new Jump(true);
        game.putPieceOnBoard(1, 1, new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
        game.putPieceOnBoard(0, 0, new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        Assertions.assertThat(jump.getAllowedMoves(game, new Coordinate(0, 0)))
                .containsOnly(new Coordinate(2, 2));
        Assertions.assertThat(jump2.getAllowedMoves(game, new Coordinate(0, 0))).isEmpty();
        game.setInitialSet();
        Assertions.assertThat(jump.getAllowedMoves(game, new Coordinate(1, 1))).isEmpty();
        Assertions.assertThat(jump2.getAllowedMoves(game, new Coordinate(2, 2))).isEmpty();
        game.putPieceOnBoard(5, 3, new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        Assertions.assertThat(jump.getAllowedMoves(game, new Coordinate(6, 2)))
                .containsOnly(new Coordinate(4, 4));
        Assertions.assertThat(jump2.getAllowedMoves(game, new Coordinate(6, 2)))
                .containsOnly(new Coordinate(4, 4));
        Assertions.assertThat(jump2.getAllowedMoves(game, new Coordinate(5, 3))).isEmpty();
    }
}
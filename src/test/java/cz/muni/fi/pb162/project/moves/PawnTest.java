package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.ChessPieceFactory;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Coordinate;
import cz.muni.fi.pb162.project.PieceType;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class PawnTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Pawn.class, 0);
        BasicRulesTester.methodsAmount(Pawn.class, 1);
    }

    @Test
    void inheritance() {
        assertTrue(Move.class.isAssignableFrom(Pawn.class));
    }

    @Test
    void getAllowedMoves() {
        var game = new Chess(null, null);
        game.setInitialSet();
        var pawn = new Pawn();
        Assertions.assertThat(pawn.getAllowedMoves(game, new Coordinate(1, 1)))
                .containsOnly(new Coordinate(1,2), new Coordinate(1,3));
        Assertions.assertThat(pawn.getAllowedMoves(game, new Coordinate(6, 6)))
                .containsOnly(new Coordinate(6,4), new Coordinate(6,5));
        game.putPieceOnBoard(5, 5, new ChessPieceFactory().createPiece(PieceType.PAWN, Color.BLACK));
        Assertions.assertThat(pawn.getAllowedMoves(game, new Coordinate(5, 5)))
                .containsOnly(new Coordinate(5,4));
    }
}
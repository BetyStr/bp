package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
class ChessTest {

    private final Player player = new Player("Pat", Color.BLACK);
    private final Player player2 = new Player("Mat", Color.WHITE);
    private final Game game = new Chess(player, player2);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Chess.class, 0);
        BasicRulesTester.methodsAmount(Chess.class, 3);
    }

    @Test
    void inheritance() {
        assertTrue(Playable.class.isAssignableFrom(Game.class));
    }

    @Test
    void getPlayers() {
        assertEquals(player, game.getPlayerOne());
        assertEquals(player2, game.getPlayerTwo());
    }

    @Test
    void getCurrentPlayer() {
        assertEquals(player2, game.getCurrentPlayer());
        game.getBoard().setRound(1);
        assertEquals(player, game.getCurrentPlayer());
        game.getBoard().setRound(15);
        assertEquals(player, game.getCurrentPlayer());

        var game2 = new Chess(player2, player);
        assertEquals(player2, game2.getCurrentPlayer());
        game2.getBoard().setRound(3);
        assertEquals(player, game2.getCurrentPlayer());
        game2.getBoard().setRound(42);
        assertEquals(player2, game2.getCurrentPlayer());
    }

    @Test
    void getAndSetStateOfGame() {
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.PAT);
        assertEquals(StateOfGame.PAT, game.getStateOfGame());
    }

    @Test
    void putPieceOnBoard() {
        var piece = new Piece(Color.WHITE, PieceType.KING);
        game.putPieceOnBoard(3, 4, piece);
        game.putPieceOnBoard(6, 1, piece);
        game.putPieceOnBoard(2, 2, piece);
        game.putPieceOnBoard(5, 7, piece);

        assertTrue(game.getBoard().isEmpty(1, 1));
        assertTrue(game.getBoard().isEmpty(7, 6));
        assertTrue(game.getBoard().isEmpty(3, -1));
        assertTrue(game.getBoard().isEmpty(8, 6));
        assertFalse(game.getBoard().isEmpty(3, 4));
        assertFalse(game.getBoard().isEmpty(6, 1));
        assertFalse(game.getBoard().isEmpty(2, 2));
        assertFalse(game.getBoard().isEmpty(5, 7));
    }

    @Test
    void setInitialSet() {
        game.setInitialSet();
        assertEquals(32, game.getBoard().getAllPiecesFromBoard().length);
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(x ,1))
                        .allMatch(x -> x.getPieceType().equals(PieceType.PAWN) && x.getColor().equals(Color.WHITE)),
                "Wrong order of pieces on the board in the 1st column");
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(x ,6))
                        .allMatch(x -> x.getPieceType().equals(PieceType.PAWN) && x.getColor().equals(Color.BLACK)),
                "Wrong order of pieces on the board in the 6th column");
        List<PieceType> pieces = List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP,
                PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);
        var iter = pieces.iterator();
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(x ,0))
                        .allMatch(x -> x.getPieceType().equals(iter.next()) && x.getColor().equals(Color.WHITE)),
                "Wrong order of pieces on the board in the 0th column");
        var iter2 = pieces.iterator();
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(x ,7))
                        .allMatch(x -> x.getPieceType().equals(iter2.next()) && x.getColor().equals(Color.BLACK)),
                "Wrong order of pieces on the board in the 7th column");
    }

    @Test
    void move() {
        var piece = new Piece(Color.WHITE, PieceType.QUEEN);
        game.putPieceOnBoard(1,0, piece);
        game.move(new Coordinate(1, 0), new Coordinate(3, 2));
        assertEquals(piece.getId(), game.getBoard().getPiece(3, 2).getId());
        var piece2 = new Piece(Color.WHITE, PieceType.QUEEN);
        game.putPieceOnBoard(7,6, piece2);
        game.move(new Coordinate(7, 6), new Coordinate(3, 2));
        assertEquals(piece2.getId(), game.getBoard().getPiece(3, 2).getId());
        assertEquals(1, game.getBoard().getAllPiecesFromBoard().length);
    }

    @Test
    void movePromotionWhiteSize() {
        var piece = new Piece(Color.WHITE, PieceType.PAWN);
        game.putPieceOnBoard(7,6, piece);
        game.move(new Coordinate(7, 6), new Coordinate(7, 7));
        assertEquals(piece.getId(), game.getBoard().getPiece(7, 7).getId());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(7, 7).getPieceType());

        var piece2 = new Piece(Color.WHITE, PieceType.PAWN);
        game.putPieceOnBoard(3,6, piece2);
        game.move(new Coordinate(3, 6), new Coordinate(3, 7));
        assertEquals(piece2.getId(), game.getBoard().getPiece(3, 7).getId());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(3, 7).getPieceType());

        var piece3 = new Piece(Color.WHITE, PieceType.PAWN);
        game.putPieceOnBoard(7,4, piece3);
        game.move(new Coordinate(7, 4), new Coordinate(7, 5));
        assertEquals(piece3.getId(), game.getBoard().getPiece(7, 5).getId());
        assertEquals(PieceType.PAWN, game.getBoard().getPiece(7, 5).getPieceType());
    }

    @Test
    void movePromotionBlackSize() {
        var piece = new Piece(Color.BLACK, PieceType.PAWN);
        game.putPieceOnBoard(7,1, piece);
        game.move(new Coordinate(7, 1), new Coordinate(7, 0));
        assertEquals(piece.getId(), game.getBoard().getPiece(7, 0).getId());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(7, 0).getPieceType());

        var piece2 = new Piece(Color.BLACK, PieceType.PAWN);
        game.putPieceOnBoard(3,1, piece2);
        game.move(new Coordinate(3, 1), new Coordinate(3, 0));
        assertEquals(piece2.getId(), game.getBoard().getPiece(3, 0).getId());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(3, 0).getPieceType());

        var piece3 = new Piece(Color.BLACK, PieceType.PAWN);
        game.putPieceOnBoard(0,5, piece3);
        game.move(new Coordinate(0, 5), new Coordinate(0, 4));
        assertEquals(piece3.getId(), game.getBoard().getPiece(0,4).getId());
        assertEquals(PieceType.PAWN, game.getBoard().getPiece(0,4).getPieceType());
    }

    @Test
    void updateStatus() {
        game.setInitialSet();
        game.updateStatus();
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
        game.putPieceOnBoard(4, 7, null);
        game.updateStatus();
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
        game.putPieceOnBoard(4, 0, new Piece(Color.BLACK, PieceType.KING));
        game.updateStatus();
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
    }
}
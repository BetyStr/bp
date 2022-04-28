package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Knight;
import cz.muni.fi.pb162.project.moves.Move;
import cz.muni.fi.pb162.project.moves.Pawn;
import cz.muni.fi.pb162.project.moves.Straight;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class creating chess pieces.
 *
 * @author Alzbeta Strompova
 */
public class ChessPieceFactory extends PieceFactory {

    /**
     * Constructor who gives a super class a set of chess pieces.
     */
    public ChessPieceFactory() {
        super(Set.of(PieceType.KING,
                PieceType.QUEEN,
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.PAWN));
    }

    @Override
    public Set<Piece> createSetOfPrototypes(Set<PieceType> setOfPieceTypes) {
        return setOfPieceTypes
                .stream()
                .map(this::createPrototypeOfPiece)
                .collect(Collectors.toSet());
    }

    private Piece createPrototypeOfPiece(PieceType pieceType) {
        List<Move> listOfMoves = switch (pieceType) {
            case KING -> List.of(new Straight(1), new Diagonal(1));
            case QUEEN -> List.of(new Straight(), new Diagonal());
            case BISHOP -> List.of(new Diagonal());
            case ROOK -> List.of(new Straight());
            case KNIGHT -> List.of(new Knight());
            case PAWN -> List.of(new Pawn());
            default -> throw new IllegalArgumentException("Unknown type in chess");
        };
        return new Piece(Color.WHITE, pieceType, listOfMoves);
    }

}

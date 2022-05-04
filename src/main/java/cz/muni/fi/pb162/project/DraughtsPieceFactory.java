package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Jump;
import cz.muni.fi.pb162.project.moves.Move;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class creating draughts pieces.
 *
 * @author Alzbeta Strompova
 */
public class DraughtsPieceFactory extends PieceFactory {

    /**
     * Constructor who gives a super class a set of Draughts pieces.
     */
    public DraughtsPieceFactory() {
        super(Set.of(PieceType.DRAUGHTS_MAN, PieceType.DRAUGHTS_KING));
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
            case DRAUGHTS_KING -> List.of(new Diagonal(1), new Jump());
            case DRAUGHTS_MAN -> List.of(new Diagonal(1, true), new Jump(true));
            default -> throw new IllegalArgumentException("Unknown type in draughts.");
        };
        return new Piece(Color.WHITE, pieceType, listOfMoves);
    }

}

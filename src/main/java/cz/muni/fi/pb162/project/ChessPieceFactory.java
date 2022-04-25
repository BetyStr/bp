package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Castling;
import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Knight;
import cz.muni.fi.pb162.project.moves.Pawn;
import cz.muni.fi.pb162.project.moves.Straight;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alzbeta Strompova
 */
public class ChessPieceFactory extends PieceFactory {

    /**
     *
     * @param pieceTypes fgg
     */
    public ChessPieceFactory(Set<TypeOfPiece> pieceTypes) {
        super(pieceTypes
                .stream()
                .map(type ->
                        new Piece(
                                Color.WHITE,
                                type,
                                switch (type) {
                                    case KING -> List.of(new Straight(1), new Diagonal(1), new Castling());
                                    case QUEEN -> List.of(new Straight(), new Diagonal());
                                    case BISHOP -> List.of(new Diagonal());
                                    case ROOK -> List.of(new Straight());
                                    case KNIGHT -> List.of(new Knight());
                                    case PAWN -> List.of(new Pawn());
                                    default -> throw new IllegalArgumentException("Unknown type in chess");
                                }
                        )
                )
                .collect(Collectors.toSet()));
    }
}

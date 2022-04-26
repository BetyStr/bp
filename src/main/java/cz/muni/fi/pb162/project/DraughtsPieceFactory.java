package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Jump;
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
     * Constructor
     *
     * @param pieceTypes set of all {@code TypeOfPieces} in Draughts.
     */
    public DraughtsPieceFactory(Set<TypeOfPiece> pieceTypes) {
        super(pieceTypes
                .stream()
                .map(type ->
                        new Piece(
                                Color.WHITE,
                                type,
                                switch (type) {
                                    case DRAUGHTS_KING -> List.of(new Diagonal(1), new Jump());
                                    case DRAUGHTS_MAN -> List.of(new Diagonal(1, true), new Jump(true));
                                    default -> throw new IllegalArgumentException("Unknown type in chess");
                                }
                        )
                )
                .collect(Collectors.toSet()));
    }
}

package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.ChessPieces;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.moves.AllowedMoves;
import cz.muni.fi.pb162.project.moves.Diagonal;
import cz.muni.fi.pb162.project.moves.Knight;
import cz.muni.fi.pb162.project.moves.Pawn;
import cz.muni.fi.pb162.project.moves.Straight;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * @author Alzbeta Strompova
 */
public class ChessPiece extends Piece {

    private final ChessPieces type;

    private final static Map<ChessPieces, List<AllowedMoves>> allowedMovesMap;
    static {
        allowedMovesMap = new HashMap<>();
        allowedMovesMap.put(ChessPieces.King, List.of(new Straight(1), new Diagonal(1)));
        allowedMovesMap.put(ChessPieces.Queen, List.of(new Straight(), new Diagonal()));
        allowedMovesMap.put(ChessPieces.Bishop, List.of(new Diagonal()));
        allowedMovesMap.put(ChessPieces.Rook, List.of(new Straight()));
        allowedMovesMap.put(ChessPieces.Knight, List.of(new Knight()));
        allowedMovesMap.put(ChessPieces.Pawn, List.of(new Pawn()));
    }

    public ChessPiece(Color color, int letterNumber, int number, ChessPieces type) {
        super(color, letterNumber, number);
        this.type = type;
    }

    @Override
    public String toString() {
        return ChessPieces.figures.get(Pair.of(this.type, getColor()));
    }


    // todo notation
    public String getChessNotation() {
        return type.toString() + Board.getNotationOfCoordinates(getLetterNumber(), getNumber());
    }

    @Override
    public Set<Coordinates> getAllPossibleMoves(Board board) {
        var strategies = allowedMovesMap.get(type);
        var result = new HashSet<Coordinates>();
        for (AllowedMoves strategy: strategies) {
            result.addAll(strategy.getAllowedMoves(board, new Coordinates(getLetterNumber(), getNumber())));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}

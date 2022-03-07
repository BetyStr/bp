package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.ChessNotation;
import cz.muni.fi.pb162.project.enums.and.interfaces.TypeOfChessPieces;
import cz.muni.fi.pb162.project.enums.and.interfaces.Color;
import cz.muni.fi.pb162.project.moves.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.text.TableView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Alzbeta Strompova
 */
public class ChessPiece extends Piece {

    private final static Map<TypeOfChessPieces, List<Move>> allowedMovesMap;
    static {
        allowedMovesMap = new HashMap<>();
        allowedMovesMap.put(TypeOfChessPieces.King, List.of(new Straight(1), new Diagonal(1), new Castling()));
        allowedMovesMap.put(TypeOfChessPieces.Queen, List.of(new Straight(), new Diagonal()));
        allowedMovesMap.put(TypeOfChessPieces.Bishop, List.of(new Diagonal()));
        allowedMovesMap.put(TypeOfChessPieces.Rook, List.of(new Straight()));
        allowedMovesMap.put(TypeOfChessPieces.Knight, List.of(new Knight()));
        allowedMovesMap.put(TypeOfChessPieces.Pawn, List.of(new Pawn()));
    }

    private TypeOfChessPieces type;

    //todo maybe choose type
    public void promotion() {
        if (type.equals(TypeOfChessPieces.Pawn)) {
            type = TypeOfChessPieces.Queen;
        }
    }

    public ChessPiece(Color color, TypeOfChessPieces type) {
        super(color);
        this.type = type;
    }

    // todo notation class
    public String getChessNotation() {
        return type.toString();
    }

    public TypeOfChessPieces getType() {
        return type;
    }

    @Override
    public Set<Coordinates> getAllPossibleMoves(Game game) {
        var strategies = allowedMovesMap.get(type);
        var result = new HashSet<Coordinates>();
        for (Move strategy: strategies) {
            result.addAll(strategy.getAllowedMoves(game, game.findCoordinatesOfPieceById(getId())));
        }
        return result.stream().filter(game::inRange).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return ChessNotation.figures.get(Pair.of(this.type, getColor()));
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

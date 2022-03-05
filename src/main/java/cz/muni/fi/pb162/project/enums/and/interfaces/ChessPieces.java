package cz.muni.fi.pb162.project.enums.and.interfaces;


/**
 * @author Alzbeta Strompova
 */
public enum ChessPieces {
    King, Queen, Bishop, Rook, Knight, Pawn;


    @Override
    public String toString() {
        return switch (this) {
            case Knight -> "N";
            case Pawn -> "";
            default -> String.valueOf(name().charAt(0));
        };

    }
}

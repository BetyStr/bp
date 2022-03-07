package cz.muni.fi.pb162.project.enums.and.interfaces;


/**
 * @author Alzbeta Strompova
 */
public enum TypeOfChessPieces {
    King, Queen, Bishop, Rook, Knight, Pawn;


    // todo put in notation class  and find case when it used
    public String getNotation() {
        return switch (this) {
            case Knight -> "N";
            case Pawn -> " ";
            default -> String.valueOf(name().charAt(0));
        };

    }
}

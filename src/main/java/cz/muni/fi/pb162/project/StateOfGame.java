package cz.muni.fi.pb162.project;

/**
 * Enum represent state of game with 2 players
 *
 * @author Alzbeta Strompova
 */
public enum StateOfGame {

    WHITE_PLAYER_WIN,
    BLACK_PLAYER_WIN,
    PAT,
    PLAYING;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

package cz.muni.fi.pb162.newProject;

import org.apache.commons.lang3.NotImplementedException;

/**
 * @author Alzbeta Strompova
 */
public abstract class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private Board board;
    private Player next;
    private int round;

    public void startNewGame(Player firstPlayer, Player secondPlayer){
        board = setInitialSet();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        assert firstPlayer.getColor().getOppositeColor().equals(secondPlayer.getColor());
        next = firstPlayer.getColor().equals(Color.White) ? firstPlayer : secondPlayer;
    }

    public abstract Board setInitialSet();
}

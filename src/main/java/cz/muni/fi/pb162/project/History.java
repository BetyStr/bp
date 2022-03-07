package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.enums.and.interfaces.Caretaker;

import java.util.Stack;

/**
 * @author Alzbeta Strompova
 */
public class History implements Caretaker {

    private final Game game;
    private final Stack<Game.GameState> savedGameStates = new Stack<>();

    public History(Game game) {
        this.game = game;
    }

    @Override
    public void hitSave() {
        savedGameStates.push(game.save());

    }

    @Override
    public void hitUndo() {
        if (savedGameStates.size() > 0) {
            game.restore(savedGameStates.pop());
        }
    }
}

package cz.muni.fi.pb162.project;


import cz.muni.fi.pb162.project.excepions.NotAllowedMoveException;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Alzbeta Strompova
 */
public abstract class Game implements Prototype<Game>, Caretaker {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final Stack<GameBoard.BoardState> savedBoardState = new Stack<>();

    protected GameBoard gameBoard = new GameBoard();

    private Player playerOne;
    private Player playerTwo;
    private Player next;
    private StateOfGame stateOfGame = StateOfGame.Playing;

    /**
     * Constructor who sets the first player according to the color of the players
     *
     * @param playerOne first of two players needed to play chess
     * @param playerTwo second of two players needed to play chess
     */
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        next = playerOne.color().equals(Color.White) ? playerOne : playerTwo;
    }

    /**
     * Constructor because Prototype
     */
    protected Game(Game target) {
        if (target != null) {
            playerOne = target.playerOne;
            playerTwo = target.playerTwo;
            next = target.next;
            stateOfGame = target.stateOfGame;
            gameBoard = target.gameBoard;
        }
    }

    public void setStateOfGame(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getNext() {
        return next;
    }

    private void nextTurn() {
        next = next.equals(playerOne) ? playerTwo : playerOne;
    }

    public abstract void setInitialSet();

    public abstract void move(Coordinates oldPosition, Coordinates newPosition);

    private Coordinates getInputFromPlayer() {
        var position = SCANNER.next().trim();
        if (position.length() != 2) {
            throw new IllegalArgumentException("");
        }
        var letterNumber = position.charAt(0);
        // todo maybe exception
        var number = Integer.parseInt(String.valueOf(position.charAt(1)));
        return BoardNotation.getCoordinatesOfNotation(letterNumber, number);
    }

    public void play() throws Exception {
        while (stateOfGame.equals(StateOfGame.Playing)) {
            System.out.println(String.format("Next one is %s", getNext()) + System.lineSeparator());
            var fromPosition = getInputFromPlayer();
            var toPosition = getInputFromPlayer();
            var piece = gameBoard.getPiece(fromPosition);

            if (piece == null) {
                throw new RuntimeException("on" + fromPosition + " is not any piece");
            }
            if (!piece.getAllPossibleMoves(gameBoard).contains(toPosition)) {
                throw new NotAllowedMoveException(piece + "can move to " + toPosition);
            }
            gameBoard.setRound(gameBoard.getRound() + 1);
            move(fromPosition, toPosition);
            nextTurn();
            System.out.println(gameBoard.printBoardToConsole());
            hitSave();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Patter Matching jdk17
        if (!(o instanceof Game game)) {
            return false;
        }
        return !Objects.equals(playerOne, game.playerOne) ||
                !Objects.equals(playerTwo, game.playerTwo) ||
                !Objects.equals(next, game.next) ||
                stateOfGame != game.stateOfGame ||
                gameBoard.equals(game.gameBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOne, playerTwo, next, stateOfGame, gameBoard);
    }

    @Override
    public void hitSave() {
        savedBoardState.push(gameBoard.save());

    }

    @Override
    public void hitUndo() {
        if (savedBoardState.size() > 0) {
            gameBoard.restore(savedBoardState.pop());
        }
    }


    @Override
    public abstract Game clone();


}

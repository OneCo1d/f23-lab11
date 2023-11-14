package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;
import edu.cmu.cs.cs214.rec09.games.TicTacToe.Player;

public class TicTacToePlugin implements GamePlugin<Player> {
    private static final String GAME_NAME = "TicTacToe";
    private static final String GAME_START_FOOTER = "You are playing TicTacToe!";
    private static final String GAME_TIED_MSG = "The game ended in a tie.";
    private TicTacToe game;
    private GameFramework framework;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return TicTacToe.SIZE;
    }

    @Override
    public int getGridHeight() {
        return TicTacToe.SIZE;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        framework.setFooterText(GAME_START_FOOTER);
    }

    @Override
    public void onNewMove() {
        // Nothing to do here.
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        framework.setSquare(x, y, framework.getCurrentPlayerName());
        game.play(x, y);
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        Player winner = game.winner();
        if (winner != null) {
            return String.format("%s wins this game!", winner);
        } else {
            return GAME_TIED_MSG;
        }
    }

    @Override
    public void onGameClosed() {
        // Nothing to do here.
    }

    @Override
    public Player currentPlayer() {
        return game.currentPlayer();
    }

}

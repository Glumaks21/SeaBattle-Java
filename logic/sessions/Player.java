package logic.sessions;

import logic.location.*;

public class Player {
    private final String name;
    protected PlayerField playerField;
    protected EnemyField enemyField;
    protected GameSession gameSession;
    private boolean turn;

    public Player(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    public PlayerField getPlayerField() {
        return playerField;
    }

    public void setUpPlayerField() {
        playerField = new PlayerField();
        playerField.setUpShips();
    }

    public EnemyField getEnemyField() {
        return enemyField;
    }

    public void setUpEnemyField(PlayerField playerField) {
        enemyField = new EnemyField(playerField);
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public void makeGuess(Cords cords) {
        if (turn) {
            gameSession.checkGuess(this, cords);
        }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
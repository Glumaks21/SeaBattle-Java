package logic.sessions;

import logic.location.*;

public class Player {
    private PlayerField playerField;
    private EnemyField enemyField;
    protected GameSession gameSession;
    private boolean turn;

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
        gameSession.checkGuess(this, cords);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
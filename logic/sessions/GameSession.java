package logic.sessions;

import logic.location.Cell;
import logic.location.Cords;

public class GameSession {
    private final Player player1;
    private final Player player2;
    private final Loger loger;

    public GameSession(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setGameSession(this);
        player2.setGameSession(this);
        loger = new Loger();
    }

    public void swapFields() {
        player1.setUpEnemyField(player2.getPlayerField());
        player2.setUpEnemyField(player1.getPlayerField());
    }

    public void checkGuess(Player p, Cords cords) {
        if (p == player1 && player1.isTurn()) {
            player1.getEnemyField().shoot(cords);
            if (player1.getEnemyField().getStateAt(cords) == Cell.State.DESTROYED) {
                if (!player2.getPlayerField().areShipsAlive()) {
                    loger.addLog("Player1 победил!!!");
                    player1.setTurn(false);
                    player2.setTurn(false);
                    return;
                }
                loger.addLog("Player 1 - попал, повторный ход\n");
                player1.setTurn(true);
            } else {
                loger.addLog("Player 1 - мимо\n");
                loger.addLog("Ход игрока player2\n");
                loger.addLog("--------------------------------------------------------\n");
                player1.setTurn(false);
                player2.setTurn(true);
            }
        } else if (p == player2 && player2.isTurn()) {
            player2.getEnemyField().shoot(cords);
            if (player2.getEnemyField().getStateAt(cords) == Cell.State.DESTROYED) {
                if (!player1.getPlayerField().areShipsAlive()) {
                    loger.addLog("Player2 победил!!!");
                    player1.setTurn(false);
                    player2.setTurn(false);
                    return;
                }
                loger.addLog("Player 2 - попал, повторный ход\n");
                player2.setTurn(true);
            } else {
                loger.addLog("Player 2 - мимо\n");
                loger.addLog("Ход игрока player1\n");
                loger.addLog("--------------------------------------------------------\n");
                player2.setTurn(false);
                player1.setTurn(true);
            }
        }
    }

    public Loger getLoger() {
        return loger;
    }
}
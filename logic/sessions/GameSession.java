package logic.sessions;

import logic.location.Cell;
import logic.location.Cords;
import java.util.*;


public class GameSession {
    private Player player1;
    private Player player2;

    public GameSession(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setGameSession(this);
        player2.setGameSession(this);
    }

    public void swapFields() {
        player1.setUpEnemyField(player2.getPlayerField());
        player2.setUpEnemyField(player1.getPlayerField());
    }

    public void checkGuess(Player p, Cords cords) {
        if (p == player1 && player1.isTurn()) {
            player1.getEnemyField().shoot(cords);
            if (player1.getEnemyField().getStateAt(cords) == Cell.State.DESTROYED) {
                player1.setTurn(true);
            } else {
                player1.setTurn(false);
                player2.setTurn(true);
            }
        } else if (p == player2 && player2.isTurn()) {
            player2.getEnemyField().shoot(cords);
            if (player2.getEnemyField().getStateAt(cords) == Cell.State.DESTROYED) {
                player2.setTurn(true);
            } else {
                player2.setTurn(false);
                player1.setTurn(true);
            }
        }
    }
}

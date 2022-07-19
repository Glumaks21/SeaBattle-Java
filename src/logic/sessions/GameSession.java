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

    public void checkGuess(Player player, Cords cords) {
        Player otherPlayer = player == player1? player2: player1;
        if (player.isTurn()) {
            if (player.getEnemyField().getStateAt(cords) != Cell.State.FREE) {
                loger.addLog(player + " выбрал открытую клетку\n");
                return;
            }

            player.getEnemyField().shoot(cords);
            if (player.getEnemyField().getStateAt(cords) == Cell.State.DESTROYED) {
                if (!otherPlayer.getPlayerField().areShipsAlive()) {
                    loger.addLog(player + " уничтожил все корабли\n");
                    player.setTurn(false);
                    return;
                }

                loger.addLog(player + " - попал, повторный ход\n");
                player.setTurn(true);
            } else {
                loger.addLog(player + " - мимо\n");
                loger.addLog("Ход игрока " + otherPlayer + "\n");
                loger.addLog("--------------------------------------------------------\n");
                player.setTurn(false);
                otherPlayer.setTurn(true);
            }
        }
    }

    public Loger getLoger() {
        return loger;
    }
}
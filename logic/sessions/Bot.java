package logic.sessions;

import logic.location.Cell;
import logic.location.Cords;

import java.util.concurrent.*;

public class Bot extends Player {
    public Bot(String name) {
        super(name);
    }

    public void startThinking() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleWithFixedDelay(() -> {
            makeGuess(null);
        }, 3, 3, TimeUnit.SECONDS);
    }

    @Override public void makeGuess(Cords cords) {
        if (isTurn()) {
            do {
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);
                cords = new Cords(x, y);
            } while (enemyField.getStateAt(cords) != Cell.State.FREE);
            gameSession.checkGuess(this, cords);
        }
    }
}

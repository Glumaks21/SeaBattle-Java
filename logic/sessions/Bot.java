package logic.sessions;

import logic.location.Cords;

import java.util.concurrent.*;

public class Bot extends Player {
    public void startThinking() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleWithFixedDelay(() -> {
            makeGuess(null);
        }, 3, 3, TimeUnit.SECONDS);
    }

    @Override public void makeGuess(Cords cords) {
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        gameSession.checkGuess(this, new Cords(x, y));
    }
}

package logic.sessions;

import java.util.*;

public class Loger extends Observable {
    private final List<String> queue;

    public Loger() {
        queue = new LinkedList<>();
    }

    public String getLog() {
        String log = queue.get(0);
        queue.remove(0);
        return log;
    }

    public void addLog(String action) {
        queue.add(action);
        setChanged();
        notifyObservers();
    }
}
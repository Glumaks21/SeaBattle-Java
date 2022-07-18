package logic.location;

public final class Cell {
    public enum State {
        FREE, MISS, SHIP, DESTROYED
    }

    private final Cords cords;
    private State state;
    private boolean opened;

    public Cell(Cords cords) {
        this.cords = cords;
        state = State.FREE;
    }

    public Cords getCords() {
        return cords;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isOpened() {
        return opened;
    }

    public void open() {
        opened = true;
    }
}
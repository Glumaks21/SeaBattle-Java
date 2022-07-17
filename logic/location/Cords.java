package logic.location;

public class Cords {
    private final int x;
    private final int y;

    public Cords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof Cords)) return false;
        Cords oCords = (Cords) o;
        return x == oCords.x && y == oCords.y;
    }

    @Override public int hashCode() {
        return Integer.hashCode(x) * Integer.hashCode(y);
    }
}
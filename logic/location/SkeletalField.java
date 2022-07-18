package logic.location;

import java.util.Objects;
import java.util.Observable;

public abstract class SkeletalField extends Observable implements Field {
    private final Cell[][] grid;

    public SkeletalField() {
        grid = new Cell[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                grid[y][x] = new Cell(new Cords(x, y));
            }
        }
    }

    @Override public Cell cellAt(Cords cords) {
        return Objects.requireNonNull(grid[cords.getY()][cords.getX()]);
    }

    @Override public boolean isOpenedAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].isOpened();
    }

    @Override public void openAt(Cords cords) {
        grid[cords.getY()][cords.getX()].open();
    }

    @Override public Cell.State getStateAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].getState();
    }

    @Override public void setStateAt(Cords cords, Cell.State state) {
        grid[cords.getY()][cords.getX()].setState(state);
    }

    @Override public boolean isCordsBelong(Cords cords) {
        int x = cords.getX();
        int y = cords.getY();
        return x >= 0 && x < grid.length && y >= 0 && y < grid.length;
    }

    @Override public void shoot(Cords cords) {
        markShoot(cords);
        setChanged();
        notifyObservers();
    }

    protected abstract void markShoot(Cords cords);

    public void print() {
        for (Cell[] line : grid) {
            for (Cell cell : line) {
                System.out.printf("%4d", cell.getState().ordinal());
            }
            System.out.println();
        }
    }
}
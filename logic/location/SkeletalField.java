package logic.location;

public class SkeletalField implements Field {
    private final Cell[][] grid;

    public SkeletalField() {
        grid = new Cell[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                grid[y][x] = new Cell(new Cords(x, y));
            }
        }
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

    public void print() {
        for (Cell[] line : grid) {
            for (Cell cell : line) {
                System.out.printf("%4d", cell.getState().ordinal());
            }
            System.out.println();
        }
    }
}

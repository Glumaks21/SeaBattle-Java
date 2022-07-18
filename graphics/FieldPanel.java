package graphics;

import logic.location.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public abstract class FieldPanel extends JPanel implements Observer {
    private final Field field;
    protected final CellGui[][] grid;

    public FieldPanel(SkeletalField field) {
        this.field = field;
        field.addObserver(this);

        GridLayout gridLayout = new GridLayout(10, 10);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        this.setLayout(gridLayout);
        grid = new CellGui[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cords cords = new Cords(x, y);
                Cell cell = field.cellAt(cords);
                CellGui cellGui = new CellGui(cell);
                grid[y][x] = cellGui;
                this.add(cellGui);
            }
        }
        update(null, null);
    }

    @Override public void update(Observable o, Object arg) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cords cords = new Cords(x, y);
                if (field.isOpenedAt(cords)) {
                    grid[y][x].setState(field.getStateAt(cords));
                    grid[y][x].open();
                }
            }
        }
    }
}
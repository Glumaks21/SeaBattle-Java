package graphics;

import logic.location.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FieldPanel extends JPanel implements Observer {
    private final Field field;
    protected final CellGui[][] grid;

    public FieldPanel(SkeletalField field) {
        this.field = field;
        field.addObserver(this);

        GridLayout gridLayout = new GridLayout(10, 10);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        setLayout(gridLayout);
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
        setBackground(Color.LIGHT_GRAY);
        setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 5), new LineBorder(Color.DARK_GRAY, 4)));
        update(null, null);
    }

    public void addCellListener(ActionListener actionListener) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                grid[y][x].addActionListener(actionListener);
            }
        }
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
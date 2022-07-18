package graphics;

import logic.location.*;
import javax.swing.*;
import java.awt.*;

public class CellGui extends JButton {
    private final Cell cell;

    public CellGui(Cell cell) {
        this.cell = cell;
        setPreferredSize(new Dimension(60, 60));
        setFont(new Font(Font.DIALOG, Font.BOLD, 30));
    }

    public Cords getCords() {
        return cell.getCords();
    }

    public void setState(Cell.State state) {
        cell.setState(state);
    }

    public void open() {
        cell.open();

        String text = null;
        switch (cell.getState()) {
            case FREE: text = " "; break;
            case MISS: text = "."; break;
            case SHIP: text = "*"; break;
            case DESTROYED: text = "X"; break;
        }
        this.setText(text);
        repaint();
    }
}
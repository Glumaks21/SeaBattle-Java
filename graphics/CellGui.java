package graphics;

import logic.location.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CellGui extends JButton {
    private final Cell cell;

    public CellGui(Cell cell) {
        this.cell = cell;
        setPreferredSize(new Dimension(50, 50));
        setFocusPainted(false);
        setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        setPressedIcon(new ImageIcon(new File("").getAbsolutePath() + "\\resources\\pressed.png"));
        update();
    }

    public Cords getCords() {
        return cell.getCords();
    }

    public void setState(Cell.State state) {
        cell.setState(state);
    }

    public void open() {
        cell.open();
        update();
    }

    public void update() {
        String path = new File("").getAbsolutePath() + "\\resources";
        switch (cell.getState()) {
            case FREE:
                path += "\\free.png";
                break;
            case MISS:
                path += "\\miss.png";
                break;
            case SHIP:
                path += "\\ship.png";
                break;
            case DESTROYED:
                path += "\\destroyed.png";
                break;
        }
        ImageIcon img = new ImageIcon(path);
        setIcon(img);
        repaint();
    }
}
package graphics;

import logic.location.Cords;
import logic.location.EnemyField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyFieldPanel extends FieldPanel implements ActionListener  {
    public EnemyFieldPanel(EnemyField enemyField) {
        super(enemyField);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                grid[y][x].addActionListener(this);
            }
        }
    }

    @Override public void actionPerformed(ActionEvent e) {
        Cords cords = ( (CellGui)e.getSource() ).getCords();

    }
}

package graphics;

import logic.location.Cords;
import logic.location.EnemyField;
import logic.location.PlayerField;
import logic.sessions.*;
import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class MainGui {
    private JFrame frame;
    private Player player1;
    private FieldPanel playerField;
    private FieldPanel enemyField;

    public MainGui() {}

    public void start() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1 = new Player();
        Player player2 = new Bot();

        GameSession gameSession = new GameSession(player1, player2);
        player1.setUpPlayerField();
        player2.setUpPlayerField();
        gameSession.swapFields();

        playerField = new PlayerFieldPanel(player2.getPlayerField());
        enemyField = new EnemyFieldPanel(player1.getEnemyField());

        frame.getContentPane().add(BorderLayout.WEST, playerField);
        frame.getContentPane().add(BorderLayout.EAST, enemyField);
        frame.pack();
        frame.setVisible(true);
    }
}
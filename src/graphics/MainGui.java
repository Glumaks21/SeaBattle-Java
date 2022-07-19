package graphics;

import logic.location.Cords;
import logic.sessions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui implements ActionListener {
    private JFrame frame;
    private Player player1;
    private FieldPanel playerField;
    private LogerPanel logerPanel;
    private FieldPanel enemyField;

    public void start() {
        frame = new JFrame("Sea Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1 = new Player("Maks");
        player1.setTurn(true);
        Bot bot = new Bot("Bot");

        GameSession gameSession = new GameSession(player1, bot);
        player1.setUpPlayerField();
        bot.setUpPlayerField();
        gameSession.swapFields();

        playerField = new FieldPanel(player1.getPlayerField());
        enemyField = new FieldPanel(player1.getEnemyField());
        enemyField.addCellListener(this);

        logerPanel = new LogerPanel(gameSession.getLoger());

        bot.startThinking();

        frame.getContentPane().add(BorderLayout.WEST, playerField);
        frame.getContentPane().add(BorderLayout.CENTER, logerPanel);
        frame.getContentPane().add(BorderLayout.EAST, enemyField);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override public void actionPerformed(ActionEvent e) {
        Cords cords = ((CellGui) e.getSource()).getCords();
        player1.makeGuess(cords);
    }
}
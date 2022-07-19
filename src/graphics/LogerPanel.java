package graphics;

import logic.sessions.Loger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class LogerPanel extends JPanel implements Observer {
    private final Loger loger;
    private final JTextArea textArea;

    public LogerPanel(Loger loger) {
        this.loger = loger;
        loger.addObserver(this);

        textArea = new JTextArea(26, 20);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 3), "LOGER", TitledBorder.CENTER, TitledBorder.TOP));
        this.add(scrollPane);
    }

    @Override public void update(Observable o, Object arg) {
        textArea.append(loger.getLog());
        repaint();
    }
}
package MainMenu;

import javax.swing.*;
import java.net.MalformedURLException;

public class GameWindow extends JFrame {
    private GamePanel gamePanel;
    public GameWindow() throws MalformedURLException {
        setTitle("PLAYING...");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}

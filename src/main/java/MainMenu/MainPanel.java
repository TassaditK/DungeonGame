package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {

    public Image backgroundImage;
    public MainPanel() throws IOException {
        setPreferredSize(new Dimension(600,400));
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage,600,400,this);
    }

}

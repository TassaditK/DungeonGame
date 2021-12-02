package MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainMenu extends JFrame  {

    JButton playButton = new JButton("");
    JButton quitButton = new JButton("");
    public MainMenu() throws IOException {
        setSize(600,400);
        setTitle("Jeux du donjon : Main Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainPanel mainPanel = new MainPanel();
        setContentPane(mainPanel);
        add(playButton);
        add(quitButton);
        setLayout(null);
        setResizable(false);
        startGame();
        quitGame();
        resizePlayButton();
        resizeQuitButton();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void resizePlayButton(){
        playButton.setBounds(200,200,200,50);
    }
    public void resizeQuitButton(){
        quitButton.setBounds(200,300,200,50);
    }

    public void startGame(){
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==playButton)
                    dispose();
                try {
                    new GameWindow();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void quitGame(){
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==quitButton){
                    dispose();
                }
            }
        });
    }
}

package states;

import MainMenu.GamePanel;
import UI.Button;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseState extends GameState {

    private Button bouttonResume;
    private Button bouttonExit;
    private  Font font ;
    public PauseState(GameStateManager gameStateManager) {
        super(gameStateManager);
        BufferedImage imageButton = GameStateManager.button.getSubimage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubimage(0,29,122,28);

        font = new Font("MeatMadness",Font.PLAIN,48);
        bouttonResume =  new Button("Resume",imageButton,font,new Vector2D(GamePanel.m_width/2,GamePanel.m_height/2-48),32,16);
        bouttonExit = new Button("EXIT",imageButton,font,new Vector2D(GamePanel.m_width / 2, GamePanel.m_height / 2 + 48), 32, 16);

        bouttonResume.addHoverImage(bouttonResume.createButton("RESUME",imageHover, font, bouttonResume.getWidth(), bouttonResume.getHeight(), 32, 20));
        bouttonExit.addHoverImage(bouttonExit.createButton("EXIT", imageHover, font, bouttonExit.getWidth(), bouttonExit.getHeight(), 32, 20));

        bouttonResume.addEvent(e->{
            gameStateManager.removeState(GameStateManager.PAUSE);
        });
        bouttonExit.addEvent(e->{
            System.exit(0);
        });

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        bouttonResume.input(mouse,key);
        bouttonExit.input(mouse,key);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        bouttonResume.render(graphics2D);
        bouttonExit.render(graphics2D);
    }
}

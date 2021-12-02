package states;

import MainMenu.GamePanel;
import graphics.Sprite;
import graphics.SpriteSheet;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2D;
import UI.Button;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverState extends GameState {

    private  String gameover= "GAME OVER";
    private BufferedImage imageButton;
    private BufferedImage imageHover;
    private Button boutonReset;
    private Button boutonQuit;
    private Font font;
    public GameOverState(GameStateManager gameStateManager) {

        super(gameStateManager);
        imageButton = GameStateManager.button.getSubimage(0,0,121,26);
        imageHover = GameStateManager.button.getSubimage(0,29,122,28);
        font= new Font("MeatMadness",Font.PLAIN,48);
        boutonReset = new Button("RESTART",imageButton,font,new Vector2D(GamePanel.m_width/2,GamePanel.m_height/2-48),32,16);
        boutonQuit = new Button("QUIT",imageButton,font,new Vector2D(GamePanel.m_width/2,GamePanel.m_height/2+48),32,16);
        boutonReset.addHoverImage(boutonReset.createButton("RESTART", imageHover, font, boutonReset.getWidth(), boutonReset.getHeight(), 32, 20));
        boutonQuit.addHoverImage(boutonQuit.createButton("QUIT", imageHover, font, boutonQuit.getWidth(), boutonQuit.getHeight(), 32, 20));
        System.out.println("before event");
        boutonReset.addEvent(e -> {
            System.out.println("ENTER");
            gameStateManager.addState(GameStateManager.PLAY);
            gameStateManager.removeState(GameStateManager.GAMEOVER);
        });

        boutonQuit.addEvent(e -> {
            System.out.println("QUIT ");
            System.exit(0);
        });

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.keyClicked();
        boutonReset.input(mouse, key);
        boutonQuit.input(mouse, key);
        if(key.escape.clicked){
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(new Sprite("src/main/java/resources/UIimage/entrada-london-dungeon.jpeg").getSPRITESHEET(),0,0,GamePanel.m_width,GamePanel.m_height,null);
        SpriteSheet.drawArray(graphics2D,gameover,new Vector2D((GamePanel.m_width/2)-gameover.length()*(32/2),(GamePanel.m_height/2)-32/2),32,32,32);
        boutonReset.render(graphics2D);
        boutonQuit.render(graphics2D);

    }
}

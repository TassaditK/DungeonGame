package states;

import MainMenu.GamePanel;
import graphics.FontFormat;
import graphics.SpriteSheet;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2D;

import java.awt.*;

public class GameStateManager {
   private  GameState[] states;
    public Vector2D map;
    public static SpriteSheet vitalityButton;
    public static SpriteSheet vitalityButtonIcon;
    public static SpriteSheet powerButton;
    public static SpriteSheet powerButtonIcon;
    public static Rectangle powerRectangle;
    public static Rectangle vitalityRectangle;


    public static  final int PLAY    =  0;
    public static  final int MENU    =  1;
    public static  final int PAUSE   =  2;
    public static final int GAMEOVER =  3;
    public static final int GAMEWON  =  4;

    public static graphics.Font font ;
    public  static FontFormat fontFormat;
    public static SpriteSheet ui;
    public static SpriteSheet button;
    public static Graphics2D graphics2D;
    public GameStateManager(Graphics2D graphics2D){
        GameStateManager.graphics2D=graphics2D;
        map = new Vector2D(GamePanel.m_width,GamePanel.m_height);
        Vector2D.setWorldVar(map.x, map.y);
        states = new GameState[5];
        font = new graphics.Font("src/main/java/resources/Font/font.png",10,10);
        fontFormat = new FontFormat();
        fontFormat.loadFont("src/main/java/resources/Font/Stackedpixel.ttf", "MeatMadness");
        fontFormat.loadFont("src/main/java/resources/Font/GravityBold8.ttf", "GravityBold8");
        SpriteSheet.currentFont = font;
        ui = new SpriteSheet("src/main/java/resources/UIimage/ui.png",64,64);
        button = new SpriteSheet("src/main/java/resources/UIimage/buttons.png",50,57);

        powerButton =new SpriteSheet("src/main/java/resources/ui/pieces.png",122,57);
        powerButtonIcon = new SpriteSheet("src/main/java/resources/ui/power.png",122,57);


        vitalityButton =new SpriteSheet("src/main/java/resources/ui/pieces.png",122,57);
        vitalityButtonIcon = new SpriteSheet("src/main/java/resources/ui/vitality.png",122,57);


        powerRectangle = new Rectangle(GamePanel.m_width-40,GamePanel.m_height/2,40,40);
        vitalityRectangle = new Rectangle(GamePanel.m_width-40,(GamePanel.m_height/2)+44,40,40);

        states[PLAY] =new PlayState(this);

    }

    public boolean isStateActive(int state) {
        return states[state] != null;
    }

    public void removeState(int state){
//        states.remove(state);
        states[state]=null;
    }
    public void addState(int state){
        if (states[state] != null)
            return;

        if (state == PLAY) {
            states[PLAY] = new PlayState(this);
        }
        else if (state == MENU) {
            states[MENU] = new MenuState(this);
        }
        else if (state == PAUSE) {
            states[PAUSE] = new PauseState(this);
        }
        else if (state == GAMEOVER) {
            states[GAMEOVER] = new GameOverState(this);
        }
        else if(state == GAMEWON){
            states[GAMEWON] = new GameWonState(this);
        }
    }


    public void addAndRemoveState(int state){
        addAndRemoveState(state,0);
    }
    public void addAndRemoveState(int state,int remove){
        removeState(state);
        addState(state);

    }
    public void update(double time){

        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].update(time);
            }
        }

    }


    public void input(MouseHandler mouse , KeyHandler key) throws InterruptedException {

        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].input(mouse, key);
            }
        }
    }
    public void render(Graphics2D graphics2D){

        graphics2D.setFont(GameStateManager.fontFormat.getFont("MeatMadness"));
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].render(graphics2D);
            }
        }
        graphics2D.drawImage(powerButton.getSpriteSheet().getSubimage(0,1, 60,40).getSPRITESHEET(),GamePanel.m_width-60,GamePanel.m_height/2,null);
        graphics2D.drawImage(powerButtonIcon.getSpriteSheet().getSubimage(0,0, 35,35).getSPRITESHEET(),GamePanel.m_width-37,(GamePanel.m_height/2),null);

        graphics2D.drawImage(vitalityButton.getSpriteSheet().getSubimage(0,1, 60,40).getSPRITESHEET(),GamePanel.m_width-60,(GamePanel.m_height/2)+44,null);
        graphics2D.drawImage(vitalityButtonIcon.getSpriteSheet().getSubimage(0,0, 35,35).getSPRITESHEET(),GamePanel.m_width-37,(GamePanel.m_height/2)+44,null);
        graphics2D.draw(powerRectangle);
        graphics2D.draw(vitalityRectangle);

    }
}

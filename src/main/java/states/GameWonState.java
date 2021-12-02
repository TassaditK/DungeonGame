package states;

import Entity.Entity;
import MainMenu.GamePanel;
import graphics.Sprite;
import graphics.SpriteSheet;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2D;

import java.awt.*;

public class GameWonState extends GameState {


    private  String gamewon= "GAME WON";
    private  String numberPotion = "Number of potions :"+Entity.getNbPotions();
    private  String numberOfCoins = "Number of coins : "+Entity.getNbCoins();
    private  String numberOfCoffre = "Number of Treasures :"+Entity.getNbTreasures();


    private Font font;
    public GameWonState(GameStateManager gameStateManager) {

        super(gameStateManager);
        font= new Font("MeatMadness",Font.PLAIN,48);

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.keyClicked();
        if(key.escape.clicked){
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(new Sprite("src/main/java/resources/UIimage/360_F_305952154_96qiEvv5kwpdDQ6EVNGSpcNt8IkQWh6W.jpeg").getSPRITESHEET(),0,0,GamePanel.m_width,GamePanel.m_height,null);
        SpriteSheet.drawArray(graphics2D,gamewon,new Vector2D(GamePanel.m_width/2-gamewon.length()*(32/2),GamePanel.m_height/2-32/2),32,32,32);
        SpriteSheet.drawArray(graphics2D,numberPotion,new Vector2D(GamePanel.m_width/2-gamewon.length()*(32/2)-200,GamePanel.m_height/2-32/2+100),32,32,32);
        SpriteSheet.drawArray(graphics2D,numberOfCoins,new Vector2D(GamePanel.m_width/2-gamewon.length()*(32/2)-200,GamePanel.m_height/2-32/2+200),32,32,32);
        SpriteSheet.drawArray(graphics2D,numberOfCoffre,new Vector2D(GamePanel.m_width/2-gamewon.length()*(32/2)-200,GamePanel.m_height/2-32/2+300),32,32,32);

    }
}

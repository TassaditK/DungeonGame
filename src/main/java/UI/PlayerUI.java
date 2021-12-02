package UI;

import Entity.Player;
import MainMenu.GamePanel;
import graphics.SpriteSheet;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerUI {

    private Fillbar healthBar;


    public PlayerUI(Player player){
        SpriteSheet bars = new SpriteSheet("src/main/java/resources/UIimage/fillbars.png");

        BufferedImage[] barSprites = {bars.getSubimage(12,2,7,16),bars.getSubimage(39,0,7,14),bars.getSubimage(0,0,12,20)};

        Vector2D position = new Vector2D(32, GamePanel.m_height-52);
        this.healthBar = new Fillbar(player,barSprites,position,16,16);

    }

    public void update(double time){


    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler){

    }

    public void render(Graphics2D graphics2D){
        healthBar.render(graphics2D);
    }
}

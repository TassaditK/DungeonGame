package states;

import Entity.Enemy;
import Entity.Player;
import Entity.enemy.Hunter;
import Entity.enemy.Kagoul;
import Entity.enemy.RoyalHunter;
import MainMenu.GamePanel;
import UI.PlayerUI;
import graphics.Sprite;
import tiles.TileManager;
import util.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class PlayState extends GameState {

    public static Vector2D map;
    private Camera camera;
    public Player player;
    public ArrayList<Enemy> enemy;
    private TileManager tileManager;
    private PlayerUI playerUI;
    private  Font font;
    public PlayState(GameStateManager gameStateManager){
        super(gameStateManager);
        map = new Vector2D();
        camera = new Camera(new ColisionAABB(new Vector2D(GamePanel.m_width/2-800/2,GamePanel.m_height/2-600/2),800,600));
        Vector2D.setWorldVar(map.x,map.y);
        System.out.println(new File("src/main/java/resources/imageEntity/EntityFaces.png").canRead());
        player= new Player(new Sprite("src/main/java/resources/imageEntity/EntityFaces.png"), new Vector2D(0+(GamePanel.m_width/2)+200,200+(GamePanel.m_height/2)-32),64);
        playerUI = new PlayerUI(player);
        enemy = new ArrayList<>();
        int x1 =300;
        int x2 =-500;
        int y2 =1400;
        int x3=900;
        int y3 =1400;
        for(int i =0 ; i<13;i++){
            if(i<3) {
                enemy.add(new Hunter(new Sprite("src/main/java/resources/imageEntity/tobisas.png", 155, 155), new Vector2D((GamePanel.m_width / 2) + x1, 0 + (GamePanel.m_height / 2) + 100), 64));
            }
            if(i>=3 && i<=6) {
                enemy.add(new Kagoul(new Sprite("src/main/java/resources/imageEntity/mobSalle2.png", 155, 155), new Vector2D((GamePanel.m_width / 2) +x2, 0 + (GamePanel.m_height / 2) + y2), 64));
                x2 -= 20;
                y2 += 100;
            }
            if(i>6){
                enemy.add(new RoyalHunter(new Sprite("src/main/java/resources/imageEntity/mobSalle3.png", 155, 155), new Vector2D((GamePanel.m_width / 2) +x3, 0 + (GamePanel.m_height / 2) + y3), 64));
                if(i<10) {
                    x3 += 50;
                    y3 += 50;
                }
                else{
                    x3+=230;
                }
            }
            x1+=100;
        }
        tileManager =  new TileManager("src/main/java/resources/Map2.xml",camera);

    }

    @Override
    public void update(double time) {
        Vector2D.setWorldVar(map.x,map.y);
        for(int i =0 ; i< enemy.size();i++) {
            if (enemy.get(i).getDeath()) {
                enemy.remove(i);
            } else {
                enemy.get(i).update(player);
            }
        }

        if(player.getDeath()){
            gameStateManager.addState(GameStateManager.GAMEOVER);
            gameStateManager.removeState(GameStateManager.PLAY);
        }
        if(enemy.isEmpty()){
            gameStateManager.addState(GameStateManager.GAMEWON);
            gameStateManager.removeState(GameStateManager.PLAY);
        }
        else {
            player.update(enemy, time);

        }

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws InterruptedException {

        key.escape.keyClicked();
        if(!gameStateManager.isStateActive(GameStateManager.PAUSE)) {
            player.input(mouse, key);
        }
        if(key.escape.clicked){
            if(gameStateManager.isStateActive(GameStateManager.PAUSE)){
                gameStateManager.removeState(GameStateManager.PAUSE);
            }else{
                gameStateManager.addState(GameStateManager.PAUSE);
            }
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        tileManager.render(graphics2D);
        if(player.getDeath()) {

        }else{
            playerUI.render(graphics2D);
            player.render(graphics2D);
        }

        for(int i =0 ; i< enemy.size();i++) {
            enemy.get(i).render(graphics2D);

        }
    }
}

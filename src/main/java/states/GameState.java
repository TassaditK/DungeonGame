package states;

import util.KeyHandler;
import util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }
    public abstract  void update(double time);
    public abstract  void input(MouseHandler mouse , KeyHandler key) throws InterruptedException;
    public abstract void render(Graphics2D graphics2D);

}

package util;

import MainMenu.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {

    public List<Key> keys = new ArrayList<Key>();
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();
    public Key pickitem = new Key();
    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != down) down = pressed;
            if (pressed) presses++;
        }

        public void keyClicked() {
            if (absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }

    }

    public void toogle(KeyEvent event, boolean pressed){
        switch (event.getKeyCode()){
            case KeyEvent.VK_UP: up.toggle(pressed);break;
            case KeyEvent.VK_DOWN:down.toggle(pressed);break;
            case KeyEvent.VK_LEFT:left.toggle(pressed);break;
            case KeyEvent.VK_RIGHT:right.toggle(pressed);break;
            case KeyEvent.VK_SPACE:attack.toggle(pressed);break;
            case KeyEvent.VK_ENTER:enter.toggle(pressed);break;
            case KeyEvent.VK_ESCAPE:escape.toggle(pressed);break;
            case KeyEvent.VK_C:pickitem.toggle(pressed);break;

        }
    }

    public KeyHandler(GamePanel gamePanel) {
         gamePanel.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
         toogle(e,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toogle(e,false);
    }
}

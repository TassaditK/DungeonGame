package util;

import MainMenu.GamePanel;
import states.GameStateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    public MouseHandler(GamePanel gamePanel){
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
    }

    public int mouseX= -1 ;
    public int mouseY= -1;
    public int mouseButton = -1;
    public static int powerClicked = -1;
    public static int vitalityClicked = -1;



    public double getX(){
        return mouseX;
    }
    public double getY(){
        return mouseY;
    }
    public int getButton(){
        return mouseButton;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
       if(GameStateManager.powerRectangle.contains(event.getX(), event.getY())){
           powerClicked= 1;

       }
        if(GameStateManager.vitalityRectangle.contains(event.getX(), event.getY())){
            vitalityClicked = 1;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButton=e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButton=-1;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}

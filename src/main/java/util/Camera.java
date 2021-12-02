package util;

import Entity.Entity;
import states.PlayState;

import java.awt.*;

public class Camera {

    private  ColisionAABB colisionCam;
    private  ColisionAABB bounds;

    private boolean up ;
    private boolean down;
    private boolean left;
    private boolean right;

    private float dx;
    private float dy;
    private  float maxSpeed=4f;
    private  float acceleration = 1f;
    private  float deacc =0.3f;

    private  int widthLimit;
    private  int heightLimit;

    private Entity entity;


    public Camera(ColisionAABB colisionCam) {

        this.colisionCam=colisionCam;
    }


    public ColisionAABB getBounds(){
        return colisionCam;
    }

    public void setLimit(int widthLimit,int heightLimit){
        this.widthLimit=widthLimit;
        this.heightLimit=heightLimit;
    }

    public  void  update(){
        moveCamera();
        PlayState.map.x+=dx;
        PlayState.map.y+=dy;

    }
    private void moveCamera(){
        if(up){
            dy-=acceleration;
            if(dy<-maxSpeed)dy=-maxSpeed;
        }else{
            if(dy>0){
                dy+=deacc;


            }
            if(dy<0){
                dy=0;
            }
        }
        if(down){
            dy+=acceleration;
            if(dy>maxSpeed)dy=maxSpeed;
        }else{
            if(dy>0){
                dy=0;
            }
            if(dy<0){
                dy-=deacc;


            }

        }
        if(left){
            dx-=acceleration;
            if(dx<-maxSpeed)dx=-maxSpeed;
        }else{
            if(dx<0){
                dx=0;
            }
            if(dx>0){
                dx+=deacc;
            }

        }
        if(right){
            dx+=acceleration;
            if(dx>maxSpeed)dx=maxSpeed;
        }else{
            if(dx<0){
                dx-=deacc;

            }
            if(dx>0){
                dx=0;
            }

        }


    }
    public void target(Entity entity){
        this.entity=entity;
        deacc=entity.getDeacc();
        maxSpeed = entity.getMaxSpeed();

    }

    public void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        if(entity==null) {
            if (keyHandler.up.down) {
                up = true;
            } else {
                up = false;
            }
            if (keyHandler.down.down) {
                down = true;

            } else {
                down = false;
            }
            if (keyHandler.left.down) {
                left = true;
            } else {
                left = false;
            }
            if (keyHandler.right.down) {
                right = true;
            } else {
                right = false;
            }
        }else{

        }
    }
    public void render(Graphics g){
        g.drawRect((int)colisionCam.getPosition().x,(int)colisionCam.getPosition().y,(int)colisionCam.getM_width(),(int)colisionCam.getM_height());

    }
}

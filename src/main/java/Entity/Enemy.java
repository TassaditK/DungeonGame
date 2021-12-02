package Entity;

import graphics.Sprite;
import util.ColisionAABB;
import util.Vector2D;

import java.awt.*;

public class Enemy extends Entity{

    protected ColisionAABB colideWithPlayer;
    protected int radiusCircle;

    protected ColisionAABB attackRange;
    protected  int radius_attackRange=100;
    protected int xOffset;
    protected int yOffset;
    protected float nextAttack;

    public Enemy(Sprite sprite , Vector2D origin , int size) {
        super(sprite,origin,size);
        damage=0.0005;
        acc=0.5f;
        maxSpeed=2f;
        radiusCircle=150;
        colideWithPlayer =  new ColisionAABB(new Vector2D(origin.x-size/2,origin.y-size/2),radiusCircle);
        attackRange = new ColisionAABB(new Vector2D(origin.x + bounds.getxOffset() + bounds.getM_width() / 2 - radius_attackRange / 2 , origin.y + bounds.getM_yOffset() + bounds.getM_height() / 2 - radius_attackRange / 2 ), radius_attackRange);
        bounds.setM_width(size / 2);
        bounds.setM_height(size / 2 -yOffset );
        bounds.setXOffset(size / 2 - xOffset);
        bounds.setYOffset(size / 2 + yOffset);

    }

    public void move(Player player){

        if(colideWithPlayer.colCircleBox(player.getBounds())){
//            System.out.println("COLIDE WITH PLAYER !");
            if(position.y>player.position.y+1){
                dy-=acc;
                up=true;
                down=false;
                if(dy<-maxSpeed)dy=-maxSpeed;
            }

            else if(position.y<player.position.y-1){
                dy+=acc;
                down=true;
                up=false;
                if(dy>maxSpeed)dy=maxSpeed;
            }else{
                dy=0;
                up=false;
                down=false;
            }
            if(position.x>player.position.x+1){
                dx-=acc;
                left=true;
                right=false;
                if(dx<-maxSpeed)dx=-maxSpeed;
            }else if(position.x<player.position.x-1){
                dx+=acc;
                right=true;
                left=false;
                if(dx>maxSpeed)dx=maxSpeed;
            }else{
                dx=0;
                right=false;
                left=false;

            }
        }
        else{
            up=false;
            down=false;
            left=false;
            right=false;
            dx=0;
            dy=0;
        }

    }

    private void destroy(){

    }

    public void update(Player player){
        super.update();
        move(player);
        attacktime = 0;
        if(attackRange.colCircleBox(player.getBounds())){
            if(nextAttack<System.nanoTime()) {
                attack = true;
                player.setHealthAndForce((int) (player.getHealth() - damage), 1f * getDirection(), currentDirection == UP || currentDirection == DOWN);
                float fireRate = 100000000f;
                nextAttack=System.nanoTime()+fireRate;
            }

        }
        else{
            attack=false;
        }
        if(!fallen) {
            if (!tileColision.collisionWithTile(dx, 0)) {
                colideWithPlayer.getPosition().x += dx;
                attackRange.getPosition().x += dx;
                position.x += dx;
            }
            if (!tileColision.collisionWithTile(0, dy)) {
                colideWithPlayer.getPosition().y += dy;
                attackRange.getPosition().y += dy;
                position.y += dy;
            }
        }
    }



    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(200,200,200,0));

        graphics2D.drawRect((int)(position.getWorldVar().x+bounds.getxOffset()-10),(int)(position.getWorldVar().y+bounds.getM_yOffset()-30),(int)bounds.getM_width(),(int)bounds.getM_height());
        graphics2D.drawOval((int)(colideWithPlayer.getPosition().getWorldVar().x),(int)(colideWithPlayer.getPosition().getWorldVar().y),radiusCircle,radiusCircle);

        graphics2D.drawImage(animation.getImage(),(int)(position.getWorldVar().x),(int)(position.getWorldVar().y),size,size,null);
        //Health
        graphics2D.setColor(Color.red);
        graphics2D.fillRect((int) (position.getWorldVar().x + bounds.getxOffset()), (int) (position.getWorldVar().y - 5), 24, 5);

        graphics2D.setColor(Color.green);
        graphics2D.fillRect((int) (position.getWorldVar().x + bounds.getxOffset()), (int) (position.getWorldVar().y - 5), (int) (24 * healthpercent), 5);
        graphics2D.setColor(new Color(200,200,200,0));
    }
}
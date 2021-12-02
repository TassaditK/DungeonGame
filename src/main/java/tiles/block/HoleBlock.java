package tiles.block;

import graphics.Sprite;
import util.ColisionAABB;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;


public class HoleBlock extends Block{

    public HoleBlock(BufferedImage image, Vector2D position, int width, int height) {
        super(image, position, width, height);

    }


    @Override
    public boolean update(ColisionAABB position) {
        if(!isInside(position)){
            System.out.println("i'm in a hole");
        }
        return false;
    }

    public boolean isInside(ColisionAABB v_position) {
        /*if(position.getPosition().x+position.getxOffset() < position.getPosition().x)   return false;
        if(position.getPosition().y+position.getM_yOffset() < position.getPosition().y)   return false;
        if(width+ position.getPosition().x < position.getM_width()+( position.getPosition().x + position.getxOffset())) return false;
        if(height+ position.getPosition().y < position.getM_height()+( position.getPosition().y + position.getM_yOffset())) return false;
        return  true;*/
        if(v_position.getPosition().x+v_position.getxOffset()<position.x)
        {
            return false;
        }

        if(v_position.getPosition().y+v_position.getM_yOffset()<position.y){
            return false;

        }
        if(width+position.x<v_position.getM_width()+(v_position.getPosition().x+v_position.getxOffset())){
            return false;
        }
        if(height+position.y<v_position.getM_height()+(v_position.getPosition().y+v_position.getM_yOffset())) {
            return false;
        }
        return true;

    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
        //graphics2D.setColor(Color.GREEN);
        //graphics2D.drawRect((int)position.getWorldVar().x,(int)position.getWorldVar().y,width,height);
        graphics2D.drawImage(image,(int) position.getWorldVar().x, (int) position.getWorldVar().y,width,height,null);

    }

}

package tiles.block;

import graphics.Sprite;
import util.ColisionAABB;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class ObjectBlock extends Block{
    public ObjectBlock(BufferedImage image, Vector2D position , int width, int height) {
            super(image,position, width, height);
    }


    @Override
    public boolean update(ColisionAABB position) {
        return true;
    }

    @Override
    public boolean isInside(ColisionAABB position) {
        return false;
    }

    public void render(Graphics2D graphics2D){
        /*super.render(graphics2D);
        //graphics2D.setColor(Color.white);
        //graphics2D.drawRect((int) position.getWorldVar().x, (int) position.getWorldVar().y, width,height);
        graphics2D.drawImage( image,(int) position.getWorldVar().x, (int) position.getWorldVar().y,width,height,null);super.render(graphics2D);
        //graphics2D.setColor(Color.green);
        //graphics2D.drawRect((int) position.getWorldVar().x, (int) position.getWorldVar().y, width*1000,height*1000);
        graphics2D.drawImage(image,(int) position.getWorldVar().x, (int) position.getWorldVar().y,width,height,null);
*/
        super.render(graphics2D);
        //graphics2D.setColor(Color.white);
        //graphics2D.drawRect((int)position.getWorldVar().x,(int)position.getWorldVar().y,width,height);
        graphics2D.drawImage( image,(int) position.getWorldVar().x, (int) position.getWorldVar().y,width,height,null);


    }
}

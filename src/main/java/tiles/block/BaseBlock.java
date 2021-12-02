package tiles.block;

import graphics.Sprite;
import util.ColisionAABB;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class BaseBlock extends Block{

    public BaseBlock(BufferedImage image, Vector2D position, int width, int height) {
        super(image,position, width, height);
    }


    @Override
    public boolean update(ColisionAABB position) {
        return false;
    }

    @Override
    public boolean isInside(ColisionAABB position) {
        return false;
    }

    public void render(Graphics2D graphics2D){
        super.render(graphics2D);
    }
}

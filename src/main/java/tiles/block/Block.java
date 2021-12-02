package tiles.block;

import graphics.Sprite;
import util.ColisionAABB;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    int width, height;
    Vector2D position;
    BufferedImage image;


    public Block(BufferedImage image, Vector2D position, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.image = image;
    }
    public Vector2D getPosition() {
        return position;
    }


    public abstract boolean update(ColisionAABB position);
    public abstract boolean isInside(ColisionAABB position);
    public void render(Graphics2D graphics2D){
        graphics2D.drawImage(image, (int) position.getWorldVar().x, (int) position.getWorldVar().y,width,height,null);
    }


    @Override
    public String toString() {
        return "Block{" +
                "position=" + position +
                "}\n";
    }
}

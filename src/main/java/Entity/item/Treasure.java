package Entity.item;

import util.ColisionAABB;
import util.Vector2D;

import java.awt.image.BufferedImage;

public class Treasure extends Item{
    public static final String name ="Treasure";
    public Treasure(BufferedImage image, Vector2D position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(ColisionAABB position) {
        return false;
    }

    @Override
    public boolean isInside(ColisionAABB position) {
        return true;
    }
}

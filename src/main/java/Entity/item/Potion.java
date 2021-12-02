package Entity.item;

import util.ColisionAABB;
import util.Vector2D;

import java.awt.image.BufferedImage;

public class Potion extends Item{
    public static final String name = "Potion";
    public Potion( BufferedImage image, Vector2D position, int width, int height) {
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

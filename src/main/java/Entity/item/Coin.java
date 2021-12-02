package Entity.item;

import util.ColisionAABB;
import util.Vector2D;

import java.awt.image.BufferedImage;

public class Coin extends Item{
    public static final String name ="Coin";

    public Coin(BufferedImage image, Vector2D position, int width, int height) {
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

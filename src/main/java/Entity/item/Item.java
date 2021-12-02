package Entity.item;


import Entity.GameObject;
import graphics.Sprite;
import util.ColisionAABB;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item  {
    int width, height;
    Vector2D position;
    BufferedImage image;


    public Item(BufferedImage image, Vector2D position, int width, int height) {
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

/*
    int maxHealth = 100;
    int health= 100;
    int damage = 10;
    int item;

    public Item(Sprite image, Vector2D origin, int size, int item){
        super(image, origin, size);
        this.item =item;
    }
    public void update(){};

    public void render(Graphics2D graphics2D){
        graphics2D.drawImage(getImage().getSPRITESHEET(), (int) (getPos().getWorldVar().x), (int)(getPos().getWorldVar().y), getSize(), getSize(), null);
    }
    */


}

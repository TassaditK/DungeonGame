package Entity;

import graphics.Sprite;
import graphics.SpriteSheet;
import util.ColisionAABB;
import util.TileColision;
import util.Vector2D;

import java.awt.*;

public class GameObject {

    SpriteSheet sprite;
    Sprite image;
    ColisionAABB bounds;
    Vector2D position;
    int size;
    int spriteX;
    int spriteY;

    //used for moving objects
    float dx;
    float dy;

    float maxSpeed = 4f;
    float acc = 2f;
    float deacc = 0.3f;
    float force = 25f;

    boolean teleported = false;
    TileColision tileColision;
    String name ="";

    public  GameObject(Vector2D origin, int size){
        this.bounds = new ColisionAABB(origin, size, size);
        this.position = origin;
        this.size = size;
    }

    public GameObject(Sprite image, Vector2D origin, int size){
        this(origin, size);
        this.image = image;
    }
    public GameObject(SpriteSheet sprite, Vector2D origin, int spriteX, int spriteY, int size){
        this(origin, size);
        this.sprite = sprite;
    }

    public void setPosition(Vector2D position){
        this.position = position;
        this.bounds = new ColisionAABB(position, size, size);
        teleported = true;
    }
    public Sprite getImage() { return image; }

    public void setName(String name) { this.name = name; }
    public void setSprite(SpriteSheet sprite) { this.sprite = sprite; }
    public void setSize(int i) { size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcc(float f) { acc = f; }
    public void setDeacc(float f) { deacc = f; }

    public float getDeacc() { return deacc; }
    public float getAcc() { return acc; }
    public float getMaxSpeed() { return maxSpeed; }
    public float getDx() { return dx; }
    public float getDy() { return dy; }
    public ColisionAABB getBounds() { return bounds; }
    public Vector2D getPos() { return position; }
    public int getSize() { return size; }


    public void addForce(float a, boolean vertical){
        if(!vertical){
            dx -= a;
        }else{
            dy -= a;
        }
    }

    public void update(){}

    public void render(Graphics2D graphics2D){
        graphics2D.drawImage(image.getSPRITESHEET(), (int)(position.getWorldVar().x), (int) (position.getWorldVar().y), size, size, null);
    }




}

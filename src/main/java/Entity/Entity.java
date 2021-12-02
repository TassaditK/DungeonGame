package Entity;

import graphics.Animation;
import graphics.Sprite;
import graphics.SpriteSheet;
import util.ColisionAABB;
import util.TileColision;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected  int IDLE=6;
    protected  int ATTACK=5;
    protected  int FALLEN =4;
    protected  int UP = 3;
    protected  int DOWN = 2;
    protected  int RIGHT = 0;
    protected  int LEFT = 1;
    public final int DEAD = 4;
    public int currentAnimation;
    protected int currentDirection = RIGHT;
    protected boolean hasIdle = false;

    protected Animation animation;
    public Sprite sprite;
    protected Vector2D position;
    protected int size;
    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected  boolean fallen;
    protected TileColision tileColision;

    protected int attackSpeed = 1050; // in milliseconds
    protected int attackDuration = 650; // in milliseconds
    protected double attacktime;
    protected boolean canAttack = true;
    protected boolean attacking = false;

    protected  boolean dead=false;

    protected int tempforce =10;
    protected int maxHealth = 100;
    protected double health = 100;
    protected float healthpercent = 1;
    protected int defense = 100;
    protected double damage = 65;

    protected float dx;
    protected float dy;
    protected float maxSpeed=2f;
    protected float acc=1f;
    protected float deacc=0.01f;
    protected ColisionAABB hitbounds;
    protected ColisionAABB bounds;



    public static int nbPotions = 0;
    public static int nbCoins = 0;
    public static int nbTreasures= 0;

    public static int getNbPotions() {
        return nbPotions;
    }

    public static int getNbCoins() {
        return nbCoins;
    }

    public static int getNbTreasures() {
        return nbTreasures;
    }

    public void setNbPotions(int nbPotions) {
        this.nbPotions = nbPotions;
    }

    public void setNbCoins(int nbCoins) {
        this.nbCoins = nbCoins;
    }

    public void setNbTreasures(int nbTreasure) {
        this.nbTreasures = nbTreasure;
    }
    public void addNbPotion(int potion){setNbPotions(getNbPotions()+potion);}
    public void addNbCoin(int coin){setNbCoins(getNbCoins()+coin);}
    public void addNbTreasure(int treasure){setNbTreasures(getNbTreasures()+treasure);}


    public Entity(Sprite sprite, Vector2D origin, int size) {
        this.sprite = sprite;
        this.size = size;
        this.animation = new Animation();
        bounds = new ColisionAABB(origin, size, size);
        hitbounds = new ColisionAABB(origin, size, size);
        hitbounds.setXOffset(size/2);
        position = origin;
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), -1);
        tileColision = new TileColision(this);
    }

    public Entity(SpriteSheet sprite, Vector2D origin, int size) {
        this.size = size;

        hitbounds = new ColisionAABB(origin, size, size);
        hitbounds.setXOffset(size / 2);

        animation = new Animation();

        tileColision = new TileColision(this);
    }
    public boolean setFallen(boolean isFallen){return fallen=isFallen;}
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(int i) {
        size = i;
    }

    public void setMaxSpeed(float i) {
        maxSpeed = i;
    }

    public void setAcc(float f) {
        acc = f;
    }

    public float getDeacc(){return deacc;}
    public float getMaxSpeed(){return maxSpeed;}

    public void setDeacc(float f) {
        deacc = f;
    }



    public ColisionAABB getBounds() {
        return bounds;
    }

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return animation;
    }


    public void setHealthAndForce(double hp,float force ,boolean bool  ){
        health=hp;
        System.out.println(health);
        if(health<=0){
            System.out.println("entered here");
            setDead(true);
        }
        healthpercent = (float) health / (float) maxHealth;
    }
    public void setDead(boolean dead){this.dead=dead;}
    public boolean getDeath() { return dead; }
    public double getHealth() { return health; }
    public float getHealthPercent() { return healthpercent; }
    public int getDefense() { return defense; }
    public int getDirection() {
        if(currentDirection == UP || currentDirection == LEFT) {
            return 1;
        }
        return -1;
    }
    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(frames);
        animation.setDelay(delay);
    }

    public void animate() {
        if (attacking) {
            if (currentAnimation <5) {
                setAnimation(currentAnimation + ATTACK, sprite.getSpriteArray(currentAnimation + ATTACK), attackDuration / 100);
            }
        }
        if (up) {
            if (currentAnimation != UP || animation.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        } else if (down) {
            if (currentAnimation != DOWN || animation.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (left) {
            if (currentAnimation != LEFT || animation.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || animation.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else if (fallen) {
            if (currentAnimation != FALLEN || animation.getDelay() == -1) {
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }

        }

        else {
            if (!attack && currentAnimation > 4) {
                setAnimation(currentAnimation - ATTACK, sprite.getSpriteArray(currentAnimation - ATTACK), -1);
            } else if (!attacking) {
                if (hasIdle && currentAnimation != IDLE) {
                    setAnimation(IDLE, sprite.getSpriteArray(IDLE), 10);
                } else if (!hasIdle) {
                    setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
                }
            }
        }



    }

    public void setHitBoxDirection() {

        if (up && !attacking) {
            hitbounds.setXOffset((size - hitbounds.getM_width()) / 2);
            hitbounds.setYOffset(-hitbounds.getM_height() / 2 + hitbounds.getxOffset());
        } else if (down && !attacking) {
            hitbounds.setXOffset((size - hitbounds.getM_width()) / 2);
            hitbounds.setYOffset(hitbounds.getM_height() / 2 + hitbounds.getxOffset());
        } else if (left && !attacking) {
            hitbounds.setYOffset((size - hitbounds.getM_height()) / 2);
            hitbounds.setXOffset(-hitbounds.getM_width() / 2 + hitbounds.getM_yOffset());
        } else if (right && !attacking) {
            hitbounds.setYOffset((size - hitbounds.getM_height()) / 2);
            hitbounds.setXOffset(hitbounds.getM_width() / 2 + hitbounds.getM_yOffset());
        }
    }
    protected boolean isAttacking(double time) {

        if((attacktime / 1000000) > ((time / 1000000) - attackSpeed)) {
            canAttack = false;
        } else {
            canAttack = true;
        }

        if((attacktime / 1000000) + attackDuration > (time / 1000000)) {
            return true;
        }

        return false;
    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public abstract void render(Graphics2D graphics2D);
}

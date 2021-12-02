package Entity.enemy;

import Entity.Enemy;
import graphics.Sprite;
import util.Vector2D;

public class Kagoul extends Enemy {

    public Kagoul(Sprite sprite, Vector2D origin, int size) {
        super(sprite, origin, size);
        damage=0.0010;
        acc=0.8f;
        maxSpeed=2f;
        radiusCircle=120;
        radius_attackRange=50;

        RIGHT=0;
        LEFT=1;
        DOWN=2;
        UP=3;
        ATTACK=4;
    }


}

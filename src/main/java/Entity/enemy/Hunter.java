package Entity.enemy;

import Entity.Enemy;
import graphics.Sprite;
import util.Vector2D;

public class Hunter extends Enemy {
    public Hunter(Sprite sprite, Vector2D origin, int size) {
        super(sprite, origin, size);
        damage=0.0005;
        acc=0.5f;
        maxSpeed=2f;
        radiusCircle=235;
        radius_attackRange=235;

        RIGHT=0;
        LEFT=1;
        DOWN=2;
        UP=3;
        ATTACK=4;
    }
}

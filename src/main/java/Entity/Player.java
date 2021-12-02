package Entity;

import MainMenu.GamePanel;
import graphics.Sprite;
import states.PlayState;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {
    private boolean stopAttacking = false;

    public Player(Sprite sprite, Vector2D origin, int size) {
        super(sprite, origin, size);
        acc = 3f;
        maxSpeed = 4f;
        health = 115;
        healthpercent = (float) health / (float) maxHealth;

        animation.setNumFrames(4, UP);
        animation.setNumFrames(4, DOWN);
        animation.setNumFrames(4, ATTACK + RIGHT);
        animation.setNumFrames(4, ATTACK + LEFT);
        animation.setNumFrames(4, ATTACK + UP);
        animation.setNumFrames(4, ATTACK + DOWN);


    }


    public void move() {
        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) dy = -maxSpeed;
        } else {
            if (dy > 0) {
                dy += deacc;


            }
            if (dy < 0) {
                dy = 0;
            }
        }
        if (down) {
            dy += acc;
            if (dy > maxSpeed) dy = maxSpeed;
        } else {
            if (dy > 0) {
                dy = 0;
            }
            if (dy < 0) {
                dy -= deacc;


            }

        }
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) dx = -maxSpeed;
        } else {
            if (dx < 0) {
                dx = 0;
            }
            if (dx > 0) {
                dx += deacc;
            }

        }
        if (right) {
            dx += acc;
            if (dx > maxSpeed) dx = maxSpeed;
        } else {
            if (dx < 0) {
                dx -= deacc;

            }
            if (dx > 0) {
                dx = 0;
            }

        }


    }


    public void resetPosition() {
        System.out.println("Resting position player .....");
        position.x = 200 + GamePanel.m_width / 2 - 32;
        PlayState.map.x = 0;
        position.y = 200 + GamePanel.m_height / 2 - 32;
        PlayState.map.y = 0;
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void update(ArrayList<Enemy> enemy, double time) {
        super.update();
        attacking = isAttacking(time);
        for (int i = 0; i < enemy.size(); i++) {
            if (attack && hitbounds.collides(enemy.get(i).getBounds()) && !stopAttacking) {
                enemy.get(i).setHealthAndForce(enemy.get(i).getHealth() - damage, tempforce * getDirection(), currentDirection == UP || currentDirection == DOWN);
                System.out.println("Health of monster is :" + enemy.get(i).getHealth());

            }
            if (enemy.get(i).health <= 0) {
                enemy.get(i).setDead(true);
            }
        }
        move();
        if (!fallen) {
            if (!tileColision.collisionWithTile(dx, 0)) {
                PlayState.map.x += dx;
                position.x += dx;
            }
            if (!tileColision.collisionWithTile(0, dy)) {
                PlayState.map.y += dy;
                position.y += dy;
            }
            if (tileColision.collisionWithItem(dx, 0) || tileColision.collisionWithItem(0, dy)) {
                System.out.println("Collision with : ");
                System.out.println("Nbc  = " + this.getNbCoins());
                System.out.println("NbP  = " + this.getNbPotions());
                System.out.println("NbT  = " + this.getNbTreasures());

            }
        } else {
            if (animation.hasPlayedOnce()) {
                resetPosition();
                fallen = false;
            }

        }
        if (MouseHandler.vitalityClicked == 1) {
            System.out.println("getting more health ...");
            System.out.println("old health == " + health);
            System.out.println("nb Pot = "+getNbPotions());
            if (getNbPotions() != 0) {
                setNbPotions(getNbPotions() - 1);
                health += 7;
                if((health+7) < maxHealth )
                    setHealthAndForce(health+7,5,false);
                System.out.println("new health == " + health);
            } else {
                getMoreVitalityTreasure();
            }
            MouseHandler.vitalityClicked = -1;
        }
        if (MouseHandler.powerClicked == 1) {
            // here we are using mainly the coins to get more power
            if (getNbCoins() != 0) {
                System.out.println("speeding again ...");
                setNbCoins(getNbCoins() - 1);
                acc = 7f;
                maxSpeed = 10f;
                System.out.println("normal speed in 5 sec....");
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                acc = 3f;
                                maxSpeed = 4f;
                            }
                        },
                        5000
                );
            }
            MouseHandler.powerClicked = -1;
        }
    }


    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawRect((int) (position.getWorldVar().x + bounds.getxOffset() + 20), (int) (position.getWorldVar().y + bounds.getM_yOffset() + 20), (int) bounds.getM_width() - 40, (int) bounds.getM_height() - 30);

        if (attack) {
            graphics2D.drawRect((int) (hitbounds.getPosition().getWorldVar().x + hitbounds.getxOffset()), (int) (hitbounds.getPosition().getWorldVar().y + hitbounds.getM_yOffset()), (int) hitbounds.getM_width(), (int) hitbounds.getM_height());
        }
        graphics2D.drawImage(animation.getImage(), (int) (position.getWorldVar().x), (int) (position.getWorldVar().y), size, size, null);

    }

    public void input(MouseHandler mouse, KeyHandler keyHandler) throws InterruptedException {
        if (mouse.getButton() == 1) {
            System.out.println("Player :" + position.x + "," + position.y);
        }



        if(!fallen) {
            if (keyHandler.up.down) {
                up = true;
            } else {
                up = false;
            }
            if (keyHandler.down.down) {
                down = true;

            } else {
                down = false;
            }
            if (keyHandler.left.down) {
                left = true;
            } else {
                left = false;
            }
            if (keyHandler.right.down) {
                right = true;
            } else {
                right = false;
            }
            if(keyHandler.attack.down && canAttack) {
                attack = true;
                stopAttacking=false;
                attacktime = System.nanoTime();
            } else {
                if(!attacking) {
                    attack = false;
                }
                stopAttacking=true;
            }
        }else{
            up=false;
            down=false;
            right=false;
            left=false;
        }

    }

    private void getMoreVitalityTreasure() {
        if (getNbTreasures() != 0) {
            setNbTreasures(getNbTreasures() - 1);
            if((health+10) < maxHealth )
                setHealthAndForce(health+10,5,false);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            acc = 3f;
                            maxSpeed = 4f;
                        }
                    },
                    5000
            );
        }
    }
}
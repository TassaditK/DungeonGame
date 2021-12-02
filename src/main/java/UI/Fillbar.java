package UI;

import Entity.Entity;
import util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fillbar {

    private BufferedImage[] barImage; // 1: health 2: energy
    private Entity entity;
    private Vector2D position;
    private  int size;
    private  int length;

    private int energyLength;
    private int barWithRatio;
    private  int eneryWithRatio;

    private  int barHeightRatio;

    public Fillbar(Entity entity,BufferedImage[] sprite,Vector2D position,int size ,int length){
        this.entity=entity;
        this.barImage=sprite;
        this.position=position;
        this.size=size;
        this.length=length;

        this.energyLength=(int) ((barImage[0].getWidth()+size)*length/(length*entity.getHealthPercent()));
        this.barWithRatio = ((barImage[0].getHeight()+size)/barImage[0].getWidth());
        this.eneryWithRatio = energyLength/(barImage[0].getWidth());
        this.barHeightRatio = ((barImage[0].getHeight()+size)/barImage[0].getHeight());
    }


    public void render(Graphics2D graphics2D){
        int endsWidth =0 ;
        int centerHeight = (int) (position.y - barHeightRatio - barImage[0].getHeight() / 2);
        this.energyLength = (int) ((barImage[0].getWidth() + size) * (length * entity.getHealthPercent()));
        this.eneryWithRatio = this.energyLength / (barImage[0].getWidth());

        if(barImage[2] != null) {
            endsWidth = barImage[2].getWidth() + size;

            graphics2D.drawImage(barImage[2], (int) (position.x), (int) (position.y), endsWidth, barImage[2].getHeight() + size, null);
            graphics2D.drawImage(barImage[2], (int) (position.x + endsWidth * 2 + (barImage[0].getWidth() + size) * length) - this.barWithRatio, (int) (position.y), -(endsWidth), barImage[2].getHeight() + size, null);
            centerHeight += barImage[2].getHeight() / 2 + (barImage[2].getHeight() - barImage[0].getHeight()) / 2;
        }
        graphics2D.drawImage(barImage[0], (int) (position.x + endsWidth - this.barWithRatio), centerHeight, (barImage[0].getWidth() + size) * length, barImage[0].getHeight() + size, null);
        graphics2D.drawImage(barImage[1], (int) (position.x + endsWidth - this.eneryWithRatio), centerHeight, this.energyLength, (int) (barImage[0].getHeight() + size), null);
    }



}

package util;

import Entity.Entity;
import Entity.item.Coin;
import Entity.item.Item;
import Entity.item.Potion;
import Entity.item.Treasure;
import tiles.TileMapHole;
import tiles.TileMapObjects;
import tiles.block.Block;
import tiles.block.HoleBlock;

public class TileColision {

    private Entity entity;
    private Block block;


    public TileColision( Entity entity) {
        this.entity=entity;
    }

    //this method gets the player collide with the map's walls and not go through them
    public boolean collisionWithTile(float ax, float ay){
        //go through each corner
        for (int corner = 0; corner < 4; corner++) {
            int x_tile = (int) ((entity.getBounds().getPosition().x + ax)+(corner%2)* entity.getBounds().getM_width() + entity.getBounds().getxOffset()) / 50;
            int y_tile = (int)  ((entity.getBounds().getPosition().y + ay) +(corner/2)* entity.getBounds().getM_height() +entity.getBounds().getM_yOffset() ) / 50;
            if( TileMapHole.tileMapHoleBlock.get(x_tile +","+y_tile)!=null){
                Block block = TileMapHole.tileMapHoleBlock.get(x_tile +","+y_tile);
                if(block instanceof HoleBlock){
                    colisionWithHole(ax,ay,x_tile,y_tile, block);
                }
                return block.update(entity.getBounds());

            }
        }
        return false;
    }

    public boolean colisionWithHole(float ax ,float ay,float xt,float yt,Block block){
        int nextX = (int) ((((entity.getBounds().getPosition().x + ax) + entity.getBounds().getxOffset()) / 50)+entity.getBounds().getM_width()/50);
        int nextY = (int) ((((entity.getBounds().getPosition().y + ay) + entity.getBounds().getM_yOffset()) / 50)+entity.getBounds().getM_height()/50);
        if(!block.isInside(entity.getBounds())){
            entity.setFallen(true);
            return false;
        }
        else if((nextX==yt+1)||(nextX==xt+1)){
            if(TileMapHole.tileMapHoleBlock.get(xt +","+yt)!=null){
                if(entity.getBounds().getPosition().x>block.getPosition().x){
                    entity.setFallen(true);
                }
                return false;

            }
        }
        entity.setFallen(false);
        return false;
    }

    public boolean collisionWithItem(float ax, float ay){
        //go through each corner
        for (int corner = 0; corner < 4; corner++) {
            int x_tile = (int) ((entity.getBounds().getPosition().x + ax)+(corner%2)* entity.getBounds().getM_width() + entity.getBounds().getxOffset()) / 50;
            int y_tile = (int)  ((entity.getBounds().getPosition().y + ay) +(corner/2)* entity.getBounds().getM_height() +entity.getBounds().getM_yOffset() ) / 50;
            if( TileMapObjects.items.get(x_tile +","+y_tile)!=null){
                Item item = TileMapObjects.items.get(x_tile +","+y_tile);
                if(item instanceof Potion){
                    entity.addNbPotion(1);
                    TileMapObjects.items.remove(x_tile +","+y_tile);
                    TileMapObjects.tileMapObjectsBlock.remove(x_tile +","+y_tile);
                }
                else if(item instanceof Coin){
                    entity.addNbCoin(1);
                    TileMapObjects.items.remove(x_tile +","+y_tile);
                    TileMapObjects.tileMapObjectsBlock.remove(x_tile +","+y_tile);
                }
                else if(item instanceof Treasure){
                    entity.addNbTreasure(1);
                    TileMapObjects.items.remove(x_tile +","+y_tile);
                    TileMapObjects.tileMapObjectsBlock.remove(x_tile +","+y_tile);
                }
                return item.update(entity.getBounds());

            }
        }
        return false;
    }


}
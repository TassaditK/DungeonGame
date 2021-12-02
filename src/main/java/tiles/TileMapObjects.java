package tiles;

import Entity.item.Coin;
import Entity.item.Item;
import Entity.item.Potion;
import Entity.item.Treasure;
import graphics.Sprite;
import tiles.block.Block;
import tiles.block.ObjectBlock;
import util.Vector2D;

import java.awt.*;
import java.util.HashMap;

public class TileMapObjects extends  TileMap{
    public static HashMap<String,Block> tileMapObjectsBlock;
    public static HashMap<String, Item> items;

    public TileMapObjects(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tileMapObjectsBlock = new HashMap<String, Block>();
        items = new HashMap<String, Item>();
        Item item = null;
        String[] block = data.split(",");
        System.out.println("block = "+block);
        for (int index = 0; index < (width * height); index++) {
            //delete all the white spaces
            int value = Integer.parseInt(block[index].replaceAll("\\s",""));
            if(value != 0){
                    tempBlock = new ObjectBlock(sprite.getSprite(((value - 1) % tileColumns), ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                            (index / height) * tileHeight), tileWidth, tileHeight);
                 if(value == 300){
                     item = new Potion(sprite.getSprite(((value - 1) % tileColumns), ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                             (index / height) * tileHeight), tileWidth, tileHeight);

                 }
                 else if(value == 254){
                     item = new Treasure(sprite.getSprite(((value - 1) % tileColumns), ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                             (index / height) * tileHeight), tileWidth, tileHeight);
                 }
                 else if(value == 369){
                     item = new Coin(sprite.getSprite(((value - 1) % tileColumns), ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                             (index / height) * tileHeight), tileWidth, tileHeight);
                 }
                tileMapObjectsBlock.put((index % width)+","+(index / height), tempBlock);
                 items.put((index % width)+","+(index / height),item);
            }
        }
    }

    public void render(Graphics2D graphics2D){
        for (Block block : tileMapObjectsBlock.values()){
            block.render(graphics2D);
        }

    }
}

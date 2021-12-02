package tiles;

import graphics.Sprite;
import tiles.block.BaseBlock;
import tiles.block.Block;
import util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TileMapBase extends TileMap{
    private ArrayList<Block> blocks;
    public static HashMap<String,Block> tileMapBaseBlock;


    public TileMapBase(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        /*blocks = new ArrayList<Block>();
        String[] block = data.split(",");
        for (int index = 0; index < (width * height); index++) {
            //delete all the white spaces
            int tempBlock = Integer.parseInt(block[index].replaceAll("\\s",""));
            if(tempBlock != 0){
                blocks.add(new BaseBlock(sprite.getSprite(( ((tempBlock -1)% tileColumns)),  ((tempBlock-1)/tileColumns)), new Vector2D((int) (index % width)* tileWidth,
                        ( (index / height)* tileHeight)), tileWidth, tileHeight));

            }
        }*/
        tileMapBaseBlock = new HashMap<String, Block>();
        Block tempBlock;
        String[] block = data.split(",");
        //System.out.println("block = "+data+"\n");
        for (int index = 0; index < (width * height); index++) {
            //delete all the white spaces
            int value = Integer.parseInt(block[index].replaceAll("\\s",""));
            if(value != 0){
                tempBlock = new BaseBlock(sprite.getSprite((int) ((value - 1) % tileColumns), (int) ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                        (int) (index / height) * tileHeight), tileWidth, tileHeight);

                if(value == 169){
                    System.out.println("value of first door : "+(index % width)+","+(index / height));
                }
                tileMapBaseBlock.put((index % width)+","+(index / height), tempBlock);

            }
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        for (Block block : tileMapBaseBlock.values()){
            block.render(graphics2D);
        }
    }
}

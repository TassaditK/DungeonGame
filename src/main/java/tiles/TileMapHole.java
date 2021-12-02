package tiles;

import graphics.Sprite;
import graphics.SpriteSheet;
import tiles.block.*;
import util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TileMapHole extends TileMap{
    private ArrayList<Block> blocks;
    public static HashMap<String,Block> tileMapHoleBlock;

    public TileMapHole(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tileMapHoleBlock = new HashMap<String, Block>();
        String[] block = data.split(",");
        for (int index = 0; index < (width * height); index++) {
            //delete all the white spaces
            int value = Integer.parseInt(block[index].replaceAll("\\s",""));
            if(value != 0){
                if(value == 196){
                    tempBlock = new WallBlock(sprite.getSprite((int) ((value - 1) % tileColumns), (int) ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                            (int) (index / height) * tileHeight), tileWidth, tileHeight);
                }
                else
                    tempBlock = new HoleBlock(sprite.getSprite((int) ((value - 1) % tileColumns), (int) ((value - 1) / tileColumns)), new Vector2D((int) (index % width) * tileWidth,
                        (int) (index / height) * tileHeight), tileWidth, tileHeight);
                tileMapHoleBlock.put((index % width)+","+(index / height), tempBlock);

            }
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        for (Block block : tileMapHoleBlock.values()){
            block.render(graphics2D);
        }
    }
}

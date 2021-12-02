package graphics;

import util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Sprite {
    private  BufferedImage SPRITESHEET = null;
    private  BufferedImage[][] spriteArray;
    private final int TILE_SIZE =32;
    public int m_width;
    public int m_height;
    private int widthSprite;
    private int heightSprite;

    public Sprite(String file){
        m_width =TILE_SIZE;
        m_height =TILE_SIZE;

        System.out.println("Loading : "+file+"....");
        SPRITESHEET=loadSprite(file);

        widthSprite =SPRITESHEET.getWidth()/m_width;
        heightSprite=SPRITESHEET.getHeight()/m_height;

        loadSpriteArray();

    }
    public Sprite(BufferedImage image) {
        this.SPRITESHEET = image;
        this.m_width = image.getWidth();
        this.m_height = image.getHeight();
    }

    public Sprite(String file , int weight , int height){
        this.m_width =weight;
        this.m_height =height;
        System.out.println("Loading: "+file+"....");

        SPRITESHEET=loadSprite(file);

        widthSprite =SPRITESHEET.getWidth()/weight;
        heightSprite=SPRITESHEET.getHeight()/height;

        loadSpriteArray();
    }

    public void setSize(int width,int height){
        setM_width(width);
        setM_height(height);
    }

    private void setM_height(int height) {
        m_height = height;
        heightSprite=SPRITESHEET.getHeight()/ m_height;

    }

    public void setM_width(int width){
        m_width = width;
        widthSprite=SPRITESHEET.getWidth()/ m_width;
    }

    public int getM_width(){return m_width;}
    public int getM_height(){return m_height;}

    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite= ImageIO.read(new FileInputStream(file));
        }catch (Exception e){
            System.out.println("ERROR : could not load the file ");
        }
        return sprite;
    }
    public void loadSpriteArray(){
        spriteArray= new BufferedImage[heightSprite][widthSprite];
        for(int x =0 ; x<widthSprite;x++){
            for(int y = 0 ; y< heightSprite;y++){
                spriteArray[y][x]=getSprite(x,y);
            }
        }
    }

    public BufferedImage getSPRITESHEET(){return  SPRITESHEET;}
    //    public BufferedImage getSubimage(int x, int y, int w, int h) {
//        return SPRITESHEET.getSubimage(x, y, w, h);
//    }
    public Sprite getSubimage(int x, int y, int width, int height) {
        return new Sprite(SPRITESHEET.getSubimage(x, y, width, height));
    }
    public BufferedImage getSprite(int x ,int y)
    {
        return SPRITESHEET.getSubimage(x* m_width,y* m_height, m_width, m_height);
    }

    public BufferedImage[] getSpriteArray(int i ){
        return spriteArray[i];
    }
    public BufferedImage[][] getSpriteArray2D(int i){
        return spriteArray;
    }
    public static void drawArray(Graphics2D graphics2D, ArrayList<BufferedImage> image , Vector2D position, int width , int height, int XOffSet, int YOffSet) {
        float x = position.x;
        float y = position.y;

        for (int i = 0; i < image.size(); i++) {
            if (image.get(i) != null) {
                graphics2D.drawImage(image.get(i), (int) x, (int) y, width, height, null);
            }
            x += XOffSet;
            y += YOffSet;
        }
    }
    public  static void drawArray(Graphics2D graphics2D,Font font,String word,Vector2D position,int width,int height,int XOffSet, int YOffSet){
        float x = position.x;
        float y = position.y;
        for(int i =0 ; i<word.length();i++) {
            if (word.charAt(i) != 32) graphics2D.drawImage(font.getFont(word.charAt(i)),(int)x,(int)y,width,height,null);
            x+=XOffSet;
            y+=YOffSet;
        }

    }
    public Sprite getNewSubimage(int x, int y, int width, int height) {
        BufferedImage temp = SPRITESHEET.getSubimage(x, y, width, height);
        BufferedImage newImage = new BufferedImage(SPRITESHEET.getColorModel(), SPRITESHEET.getRaster().createCompatibleWritableRaster(width,height), SPRITESHEET.isAlphaPremultiplied(), null);
        temp.copyData(newImage.getRaster());
        return new Sprite(newImage);
    }

    public Sprite getNewSubimage() {
        return getNewSubimage(0, 0, this.m_width, this.m_height);
    }


}
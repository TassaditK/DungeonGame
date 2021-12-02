package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Font {

    private BufferedImage FONTESHEET = null;
    private  BufferedImage[][] fontArray;
    private final int TILE_SIZE =32;
    public int width;
    public int height;
    private int widthLetter;
    private int heightLetter;

    public Font(String file) {
        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        FONTESHEET = loadFont(file);

        widthLetter = FONTESHEET.getWidth() / width;
        heightLetter = FONTESHEET.getHeight() / height;
        loadFontArray();
    }

    public Font(String file , int weight , int height){
        this.width =weight;
        this.height=height;
        System.out.println("Loading: "+file+"....");

        FONTESHEET= loadFont(file);

        this.widthLetter =FONTESHEET.getWidth()/weight;
        this.heightLetter =FONTESHEET.getHeight()/height;
        loadFontArray();
    }

    public void setSize(int width,int height){
        setWidth(width);
        setHeight(height);
    }

    private void setHeight(int height) {
        this.height= height;
        heightLetter = FONTESHEET.getHeight()/height;

    }

    public void setWidth(int width){
        this.width=width;
        widthLetter =FONTESHEET.getWidth()/width;
    }

    public int getWidth(){return width;}
    public int getHeight(){return height;}

    private BufferedImage loadFont(String file){
        BufferedImage sprite = null;
        try{
            sprite= ImageIO.read(new FileInputStream(file));
        }catch (Exception e){
            System.out.println("ERROR : could not load the file ");
        }
        return sprite;
    }
    public void loadFontArray(){
        fontArray= new BufferedImage[widthLetter][heightLetter];
        for(int x = 0; x< widthLetter; x++){
            for(int y = 0; y< heightLetter; y++){
                fontArray[x][y]= getLetter(x,y);
            }
        }
    }

    public BufferedImage getFONTESHEET(){return  FONTESHEET;}
    public BufferedImage[] getSpriteArray(int i ){
        return fontArray[i];
    }

    public BufferedImage getLetter(int x, int y) {
        BufferedImage image = FONTESHEET.getSubimage(x * width, y * height, width, height);
        return image;
    }
    public BufferedImage getFont(char letter){
        int value = letter-65;
        int  x = value%widthLetter;
        int  y =value/heightLetter;

        return getLetter(x,y);

    }
    public BufferedImage getLetter(char letter) {
        int value = letter;

        int x = value % widthLetter;
        int y = value / widthLetter;
        return getLetter(x, y);
    }


}

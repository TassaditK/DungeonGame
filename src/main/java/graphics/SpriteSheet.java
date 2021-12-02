package graphics;

import util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class SpriteSheet {
    private Sprite spriteSheet = null;
    private Sprite[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int width;
    public int height;
    private int spriteWidth;
    private int spriteHeight;
    private String file;
    public static Font currentFont;

    public SpriteSheet(String file) {
        this.file = file;
        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        spriteSheet = new Sprite(loadSprite(file));

        spriteWidth = spriteSheet.getSPRITESHEET().getWidth() / width;
        spriteHeight = spriteSheet.getSPRITESHEET().getHeight() / height;
        loadSpriteArray();
    }

    public SpriteSheet(Sprite sprite, String name, int width, int height) {
        this.width = width;
        this.height = height;

        System.out.println("Loading: " + name + "...");
        spriteSheet= sprite;

        spriteWidth = spriteSheet.getSPRITESHEET().getWidth() / width;
        spriteHeight = spriteSheet.getSPRITESHEET().getHeight() / height;
        loadSpriteArray();
    }
    public SpriteSheet( String file, int width, int height) {
        this.width = width;
        this.height = height;
        this.file = file;

        System.out.println("Loading: " + file + "...");
        spriteSheet = new Sprite(loadSprite(file));
        spriteWidth= spriteSheet.getSPRITESHEET().getWidth() / width;
        spriteHeight = spriteSheet.getSPRITESHEET().getHeight() / height;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width) {
        this.width = width;
        spriteWidth = spriteSheet.getSPRITESHEET().getWidth() / width;
    }

    public void setHeight(int height) {
        height = height;
        spriteHeight = spriteSheet.getSPRITESHEET().getHeight() / height;
    }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getRows() { return spriteHeight; }
    public int getCols() { return spriteWidth; }
    public int getTotalTiles() { return spriteWidth * spriteHeight; }
    public String getFilename() { return file; }


    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }
    public void loadSpriteArray() {
        spriteArray = new Sprite[spriteHeight][spriteHeight];
        for (int y = 0; y < spriteHeight; y++) {
            for (int x = 0; x < spriteHeight; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public Sprite getSpriteSheet() {
        return spriteSheet;
    }

    public Sprite getSprite(int x, int y) {
        return spriteSheet.getSubimage(x * width, y * height, width, height);
    }

    public Sprite getNewSprite(int x, int y) {
        return spriteSheet.getNewSubimage(x * width, y * height, width, height);
    }

    public Sprite getSprite(int x, int y, int w, int h) {
        return spriteSheet.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getSubimage(int x, int y, int w, int h) {
        return spriteSheet.getSPRITESHEET().getSubimage(x, y, w, h);
    }

    public Sprite[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public Sprite[][] getSpriteArray2() {
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<Sprite> img, Vector2D pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i).getSPRITESHEET(), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }
    public static void drawArray(Graphics2D g, String word, Vector2D pos, int size) {
        drawArray(g, currentFont, word, pos, size, size, size, 0);
    }

    public static void drawArray(Graphics2D g, String word, Vector2D pos, int size, int xOffset) {
        drawArray(g, currentFont, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, String word, Vector2D pos, int width, int height, int xOffset) {
        drawArray(g, currentFont, word, pos, width, height, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2D pos, int size, int xOffset) {
        drawArray(g, f, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2D pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;
        currentFont = f;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g.drawImage(f.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);

            x += xOffset;
            y += yOffset;
        }

    }


}

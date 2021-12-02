package graphics;

import java.awt.*;
import java.awt.Font;
import java.io.FileInputStream;
import java.util.HashMap;

public class FontFormat {

    private HashMap<String ,Font>fonts;

    public FontFormat() {
        fonts = new HashMap<String, Font>();
    }
    public void loadFont(String path,String name){
        try{
            System.out.println("Loading: "+path+".....");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(path));
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(customFont);

            Font font = new Font(name,Font.PLAIN,32);
            fonts.put(name,font);
        }catch (Exception exception){
            System.out.println("ERROR: font - can't load font"+path+"....");
            exception.printStackTrace();
        }
    }

    public Font getFont(String name){
        return fonts.get(name);
    }
}

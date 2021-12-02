package tiles;

import graphics.Sprite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.Camera;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class TileManager{
   
    public static ArrayList<TileMap> tileMap;



    public TileManager() {
        tileMap = new ArrayList<>();
    }
    public TileManager(String path, Camera camera){
        tileMap = new ArrayList<>();
        addTileMap(path, 50, 50,camera);
    }
    public TileManager(String path, int blockWidth, int blockHeight,Camera camera){
        this();
        addTileMap(path, blockWidth, blockHeight,camera);
    }


    private void addTileMap(String path, int blockWidth, int blockHeight, Camera camera) {
        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileColumns;
        String imagePath;
        int nbLayers = 0;
        Sprite sprite;

        String[] data = new String[10];

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new FileInputStream(path));
            document.getDocumentElement().normalize();


            NodeList dungeonTiles =document.getElementsByTagName("tileset");
            Node nodeTiles = dungeonTiles.item(0);




            Element elementTiles = (Element) nodeTiles;   //get the nodeTiles content as an elementTiles
            imagePath = elementTiles.getAttribute("name");
            tileWidth = Integer.parseInt(elementTiles.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(elementTiles.getAttribute("tileheight"));
            tileColumns =  Integer.parseInt(elementTiles.getAttribute("columns"));
            sprite = new Sprite("src/main/java/resources/images/"+imagePath+".png",tileWidth,tileHeight);
            dungeonTiles = document.getElementsByTagName("layer");
            nbLayers = dungeonTiles.getLength();




            //go through the different layers of the file : here we have 2 layers
            for (int index = 0; index < nbLayers; index++) {
                nodeTiles=dungeonTiles.item(index);
                elementTiles = (Element) nodeTiles;

                //if it's the first layer (base)
                if(index <=0){
                    width = Integer.parseInt(elementTiles.getAttribute("width"));
                    height = Integer.parseInt(elementTiles.getAttribute("height"));
                }
                data[index] = elementTiles.getElementsByTagName("data").item(0).getTextContent();

                //adding the base to the TileMap
                if(index == 0)
                    tileMap.add(new TileMapHole(data[index],sprite,width,height,blockWidth,blockHeight,tileColumns));
                else if(index == 1)
                    tileMap.add(new TileMapBase(data[index],sprite,width,height,blockWidth,blockHeight,tileColumns));
                else
                    tileMap.add(new TileMapObjects(data[index], sprite, width, height, blockWidth, blockHeight, tileColumns));

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    //this method will be used to draw the tileMap
    public void render(Graphics2D graphics2D) {
        for (int index = 0; index < tileMap.size(); index++) {
            tileMap.get(index).render(graphics2D);
        }
    }
}

package MainMenu;

import states.GameStateManager;
import util.KeyHandler;
import util.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GamePanel  extends JPanel implements Runnable {
    public static int m_width;
    public static int m_height ;

    private Thread thread;
    public boolean running = false;

    private BufferedImage image ;
    private Graphics2D graphics;
    private BufferStrategy bufferStrategy;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gameStateManager;
    public GamePanel(int width, int height){
        this.m_width=width;
        this.m_height=height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread = new Thread(this,"GameThread");
            thread.start();
        }
    }


    public void init(){
        running=true;
        image = new BufferedImage(m_width,m_height,BufferedImage.TYPE_INT_ARGB_PRE);
        graphics= (Graphics2D) image.getGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);
        gameStateManager = new GameStateManager(graphics);
    }

    @Override
    public void run() {
        init();
        System.out.println("Init.......");

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ;//Time Before Update

        final int MUBR = 5; //Most update before render
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS;//Total time before render
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while (running) {
            double current = System.nanoTime();
            int updateCount = 0;
            while (((current - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update(current);
                try {
                    input(mouse,key);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lastUpdateTime += TBU;
                updateCount++;
            }
            if (current - lastUpdateTime > TBU) lastUpdateTime = current - TBU;
            try {
                input(mouse,key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            render();
            draw();
            lastRenderTime = current;
            frameCount++;
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
            while (current- lastRenderTime < TTBR && current - lastUpdateTime < TBU) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }

                current = System.nanoTime();
            }

        }

    }

    public void input(MouseHandler mouse , KeyHandler key) throws InterruptedException {
        gameStateManager.input(mouse,key);
    }

    public void update(double time){
        gameStateManager.update(time);
    }

    public void render(){
        if(graphics!=null){
            graphics.fillRect(0,0,m_width,m_height );
            gameStateManager.render(graphics);
        }
    }

    public void draw() {
        Graphics graphics2 = (Graphics) this.getGraphics();
        graphics2.drawImage(image, 0, 0, m_width, m_height, null);
        graphics2.dispose();

    }
}

package ui;

import javax.swing.*;

import object.SuperObject;
import snake.Snake;
import tile.TileManager;
import util.AssetSetter;
import util.CollisionChecker;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
	public KeyHandler keyH = new KeyHandler(this);
    public TileManager tileM = new TileManager(this);

    public  UI ui = new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    public Thread gameThread;
    public Snake snake = new Snake(this, keyH);
    public SuperObject[][] obj = new SuperObject[5][10];
	
    public static final int originalTileSize = 16;
    public static final double scale = 1.5;
    public static final int tileSize = (int)(originalTileSize * scale);
    public static final int maxScreenCol = 32;
    public static final int maxScreenRow = 32;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    public static final int gameunit = (screenWidth*screenHeight)/tileSize;
    
    public int worldWidth = screenWidth;
    public int worldHeight = screenHeight;
    
    public static int FPS = 10;   
    public static double drawInterval = 1000000000/FPS; 
    
    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    
    public int mapPick;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        gameState = titleState;
        aSetter.setObject();
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();

                delta--;
            }
        }
    }

    public void update()
    {
    	if(gameState == playState)
        {
            snake.update();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Title Screen
        if(gameState == titleState)
        {
           ui.draw(g2);
        }
        else if(gameState == playState)
        {
        	tileM.draw(g2);
        	for(int i = 0; i < obj[mapPick].length; i++)
            {
                if(obj[mapPick][i] != null)
                    obj[mapPick][i].draw(g2, this);
            }
        	snake.draw(g2);     	
        	ui.draw(g2);
        	
        }
        else if(gameState == pauseState)
        {
        	ui.draw(g2);
        }
        else if(gameState == gameOverState)
        {
        	ui.draw(g2);
        }
        g2.dispose();
    }
}

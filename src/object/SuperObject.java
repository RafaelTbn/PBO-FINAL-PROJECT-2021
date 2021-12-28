package object;

import ui.GamePanel;
import util.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject
{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    
    public Rectangle solidArea = new Rectangle(0, 0, GamePanel.tileSize, GamePanel.tileSize);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public UtilityTool uTool = new UtilityTool();
    
    GamePanel gp;
    
    public SuperObject(GamePanel gp) {
    	this.gp = gp;
    }

    public void draw(Graphics2D g2, GamePanel gp)
    {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
    
    
}


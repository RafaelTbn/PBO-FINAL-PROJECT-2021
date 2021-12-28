package snake;

import ui.GamePanel;
import util.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity
{
    public int worldX[] = new int[GamePanel.gameunit];
    public int worldY[] = new int[GamePanel.gameunit];

    public String direction;

    public Rectangle solidArea = new Rectangle(0, 0, GamePanel.tileSize, GamePanel.tileSize);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter;

    public GamePanel gp;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = uTool.scaleImage(scaledImage, GamePanel.tileSize, GamePanel.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }
}
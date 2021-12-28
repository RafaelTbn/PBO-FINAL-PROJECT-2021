package object;

import object.SuperObject;
import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Apple extends SuperObject
{
    public Apple(GamePanel gp)
    {
    	super(gp);
 
        name = "Apple";
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/apple.png")));
            image = uTool.scaleImage(image, GamePanel.tileSize, GamePanel.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void newApple()
    {
    	boolean spawn = false;
        int col = 0, row = 0;

        while(!spawn)
        {
            Random random = new Random();

            col = random.nextInt(31) + 1;
            row = random.nextInt(25) + 1;
            int tileNum = gp.tileM.mapTileNum[gp.mapPick][col][row];
            if(!gp.tileM.tile[gp.mapPick][tileNum].collision)
                spawn = true;
        }

        worldX = col * GamePanel.tileSize;
        worldY = row * GamePanel.tileSize;
    }
}

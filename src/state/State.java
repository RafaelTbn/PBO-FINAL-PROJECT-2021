package state;

import ui.GamePanel;
import util.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public abstract class State
{
	public boolean warningOn = false;
    Font maruMonica;
    BufferedImage imageScreen;
    public String[] optionMenu;
    public String text;
    Graphics2D g2;
    public int commandNum = 0;
    GamePanel gp;

    public State(GamePanel gp)
    {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage setup(String imagePath, boolean screen)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            if(screen)
                scaledImage = uTool.scaleImage(scaledImage, scaledImage.getWidth(), scaledImage.getWidth(), GamePanel.screenWidth, GamePanel.screenHeight);
            else
                scaledImage = uTool.scaleImage(scaledImage, GamePanel.tileSize, GamePanel.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D g2) {}

    public int getXCenteredText(String text)
    {
        return ((GamePanel.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2));
    }
}

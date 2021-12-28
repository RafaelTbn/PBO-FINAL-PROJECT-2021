package state;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChooseState extends State
{
	
    BufferedImage pointer;
    int warningCounter = 0;

    public ChooseState(GamePanel gp)
    {
        super(gp);
        setOptionMenu();
        getChooseImage();
    }
    public void setOptionMenu()
    {
        text = "Select your Map !";

        optionMenu = new String[6];
        optionMenu[0] = "MAP 1";
        optionMenu[1] = "MAP 2";
        optionMenu[2] = "MAP 3";
        optionMenu[3] = "MAP 4";
        optionMenu[4] = "MAP 5";
        optionMenu[5] = "BACK";
}
    public void getChooseImage()
    {
        imageScreen = setup("/screen/ScreenMenu", true);
        pointer = setup("/ui/pointer", false);
    }
    @Override
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

        g2.drawImage(imageScreen, 0, 0, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));

        int x = getXCenteredText(text);
        int y = GamePanel.tileSize * 3;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        x = GamePanel.tileSize * 3;
        y += GamePanel.tileSize * 3;
        for (int i = 0; i < optionMenu.length; i++)
        {
            if(i == commandNum)
            {
                g2.setColor(Color.GREEN);
                g2.drawString(optionMenu[i], x, y);
                g2.drawImage(pointer, (int) (g2.getFontMetrics().getStringBounds(optionMenu[i], g2).getWidth() + GamePanel.tileSize * 2.2), y - GamePanel.tileSize / 2, null);
            }
            else
            {
                g2.setColor(Color.white);
                g2.drawString(optionMenu[i], x, y);
            }
            if(i == optionMenu.length - 2)
                y += GamePanel.tileSize * 5;
            else
                y += GamePanel.tileSize * 2;
        }
        
        if(warningOn)
        {
        	g2.setColor(new Color(0, 0, 0, 215));
        	g2.fillRoundRect(GamePanel.tileSize * 8,  GamePanel.tileSize * 7,  GamePanel.tileSize * 13,  GamePanel.tileSize * 3, 35, 35);
            g2.setColor(new Color(255, 255, 255));
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(GamePanel.tileSize * 8 + 5, GamePanel.tileSize * 7 + 5, GamePanel.tileSize * 13 - 10, GamePanel.tileSize * 3 - 10, 25, 25);
            g2.setFont(g2.getFont().deriveFont(35F));
            g2.drawString("MAP NOT UNLOCKED", GamePanel.tileSize * 9, GamePanel.tileSize * 9);    
            
            warningCounter++;
            if (warningCounter > 10)
            {
            	warningOn = false;
                warningCounter = 0;
            }
        }
    }
}

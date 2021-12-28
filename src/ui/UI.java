package ui;

import state.ChooseState;
import state.State;
import state.TitleState;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    public State[] screenState = new State[2];
    public boolean gameFinished = false;
    public int titleScreenState = 0;
    public boolean messageOn = false;
    int messageCounter = 0;
    public String message = "";
    
    public UI(GamePanel gp)
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
        setState();
    }

    public void setState()
    {
        screenState[0] = new TitleState(gp);
        screenState[1] = new ChooseState(gp);
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);


        if(gp.gameState == gp.titleState)
        {
            screenState[titleScreenState].draw(g2);
        }
        else if(gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }
        else if(gp.gameState == gp.playState)
        {
        	drawPlayScreen();
        }
        else if(gp.gameState == gp.gameOverState)
        {
        	drawGameOverScreen();
        }
    }
    
    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "PAUSED ";
        int x = getXCenteredText(text);
        int y = GamePanel.tileSize * 11;

        g2.drawString(text, x, y);
        
        text = "PRESS P TO CONTINUE THE GAME";
        x = getXCenteredText(text);
        y = GamePanel.tileSize * 15;
        
        g2.drawString(text, x, y);

        text = "PRESS ENTER TO RETURN TO MENU";
        x = getXCenteredText(text);
        y = GamePanel.tileSize * 19;
        
        g2.drawString(text, x, y);
    }
    public void drawGameOverScreen()
    {
    	g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        
        String text = "GAME OVER";
        
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = GamePanel.screenWidth / 2 - textLength / 2;
        int y = GamePanel.tileSize * 11;

        g2.drawString(text, x, y);

        text = "PRESS ENTER TO RETURN TO MENU";
        
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = GamePanel.screenWidth / 2 - textLength / 2;
        y = GamePanel.tileSize * 15;
        
        g2.drawString(text, x, y);
    }
    public void drawPlayScreen()
    {
    	if(gameFinished)
        {
    		g2.setFont(maruMonica);
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(40F));
            String text = "You complete the mission";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x,y;

            x = GamePanel.screenWidth / 2 - textLength / 2;
            y = GamePanel.tileSize * 6;

            g2.drawString(text, x, y);
            
            g2.setFont(g2.getFont().deriveFont(80F));
            g2.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = GamePanel.screenWidth / 2 - textLength / 2;
            y = GamePanel.tileSize * 9;

            g2.drawString(text, x, y);
            
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(65F));
            text = "PRESS ENTER TO RETURN TO MENU";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        
            x = GamePanel.screenWidth / 2 - textLength / 2;
            y = GamePanel.tileSize * 17;
            
            g2.drawString(text, x, y);
        }
    	else
    	{
        	g2.setFont(maruMonica);
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(40F));
            g2.drawString("X " + gp.snake.totApple, GamePanel.tileSize * 2, (int)(GamePanel.tileSize * 1.5));
            g2.drawImage(gp.obj[gp.mapPick][0].image, GamePanel.tileSize/2, GamePanel.tileSize/2, GamePanel.tileSize, GamePanel.tileSize, null);
            
            if(messageOn)
            {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, GamePanel.tileSize/2, GamePanel.tileSize * 3);    
                
                messageCounter++;
                if (messageCounter > 20)
                {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
    	}
    }
    
    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }
    
    public int getXCenteredText(String text)
    {
        return ((GamePanel.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2));
    }
}
package snake;

import ui.GamePanel;
import ui.KeyHandler;
import object.Apple;
import snake.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Snake extends Entity
{
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public boolean[] unlock = new boolean[5];
    public int totApple = 0;
    public int speed = 10;


    public Snake(GamePanel gp, KeyHandler keyH)
    {
        super(gp);
        this.keyH = keyH;

        screenX = GamePanel.screenWidth/2 - (GamePanel.tileSize/2);
        screenY = GamePanel.screenHeight/2 - (GamePanel.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        unlock[0] = true;
        setDefaultValues();
    }

    public void setXAndY(int i)
    {
        switch (i)
        {
            case 0 -> {
                worldX[0] = GamePanel.tileSize * 0;
                worldY[0] = GamePanel.tileSize * 0;
				direction = "right";
            }
            case 1 -> {
                worldX[0] = GamePanel.tileSize * 16;
                worldY[0] = GamePanel.tileSize * 16;
				direction = "right";
            }
            case 2 -> {
                worldX[0] = GamePanel.tileSize * 11;
                worldY[0] = GamePanel.tileSize * 25;
				direction = "left";
            }
            case 3 -> {
                worldX[0] = GamePanel.tileSize * 9;
                worldY[0] = GamePanel.tileSize * 3;
				direction = "right";
            }
            case 4 -> {
            	worldX[0] = GamePanel.tileSize * 15;
                worldY[0] = GamePanel.tileSize * 15;                     
				direction = "right";
            }
        }
    }

    public void setDefaultValues()
    {
        setXAndY(gp.mapPick);
        direction = "down";
    }

    public void update()
    {
    	collisionOn = false;
    	gp.cChecker.checkOut(this);
    	gp.cChecker.checkTile(this);	  
    	int objIndex = gp.cChecker.checkObject(this, true);
    	pickUpObject(objIndex);
    	      
    	checkFinishMap();
    	      
    	if(!collisionOn)
    	{
    		switch (direction)
    		{
    			case "up" -> worldY[0] -= GamePanel.tileSize;
    			case "down" -> worldY[0] += GamePanel.tileSize;
    	        case "left" -> worldX[0] -= GamePanel.tileSize;
    	        case "right" -> worldX[0] += GamePanel.tileSize;          	
    	    }   	
    	}
    	else
    		gp.gameState = gp.gameOverState;
    }

    public void pickUpObject(int i)
    {
        if(i != 999)
        {
        	//GamePanel.drawInterval = 1000000000/(speed += 1 );
            String objectName = gp.obj[gp.mapPick][i].name;
            switch (objectName)
            {
                case "Apple" -> {                	
                	totApple++;
                	((Apple) gp.obj[gp.mapPick][i]).newApple();
                	gp.ui.showMessage("You got An Apple!");
                }
            }
        }
    }

    public void draw(Graphics2D g2)
    {    	
    	Random random = new Random();

	    		g2.setColor(new Color(random.nextInt(250), random.nextInt(250),random.nextInt(250)));
				g2.fillRect(worldX[0], worldY[0], GamePanel.tileSize, GamePanel.tileSize); 	
    }
      
    public void checkFinishMap()
    {
    	switch(gp.mapPick)
    	{
    	
    		case 0 -> {
    			if(totApple > 1)
    			{
    				gp.ui.gameFinished = true;
    				totApple = 0;
    				direction = "";
    				speed = 10;
    				GamePanel.drawInterval = 1000000000/(speed += 2);
    				unlock[1] = true;
    			}
    		}
    		case 1 -> {
    			if(totApple > 2)
    			{
    				gp.ui.gameFinished = true;
    				totApple = 0;
    				direction = "";
    				speed = 10;
    				GamePanel.drawInterval = 1000000000/(speed += 3);
    				unlock[2] = true;
    			}
    		}
    		case 2 -> {
    			if(totApple > 0)
    			{
    				gp.ui.gameFinished = true;
    				totApple = 0;
    				direction = "";
    				speed = 10;
    				GamePanel.drawInterval = 1000000000/(speed += 4);
    				unlock[3] = true;
    			}
    		}
    		case 3 -> {
    			if(totApple > 0)
    			{
    				gp.ui.gameFinished = true;
    				totApple = 0;
    				direction = "";
    				speed = 10;
    				GamePanel.drawInterval = 1000000000/(speed += 5);
    				unlock[4] = true;
    			}
    		}
    		case 4 -> {
    			if(totApple > 0)
    			{
    				gp.ui.gameFinished = true;
    				totApple = 0;
    				direction = "";
    				System.exit(0);
    			}
    		}
    	}
    }
}
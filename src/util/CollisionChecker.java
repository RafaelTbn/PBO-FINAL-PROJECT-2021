package util;

import snake.Snake;
import ui.GamePanel;

public class CollisionChecker 
{
	GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }
    
	public int checkObject(Snake snake, boolean player)
    {
        int index = 999;

        for (int i = 0; i < gp.obj[gp.mapPick].length; i++)
        {
            if(gp.obj[gp.mapPick][i] != null)
            {
                snake.solidArea.x = snake.worldX[0];
                snake.solidArea.y = snake.worldY[0];

                gp.obj[gp.mapPick][i].solidArea.x = gp.obj[gp.mapPick][i].worldX;
                gp.obj[gp.mapPick][i].solidArea.y = gp.obj[gp.mapPick][i].worldY;

                switch (snake.direction)
                {
                    case "up" -> {
                        if(snake.solidArea.intersects(gp.obj[gp.mapPick][i].solidArea))
                        {
                            if(gp.obj[gp.mapPick][i].collision)
                                snake.collisionOn = true;
                            if(player)
                                index = i;
                        }
                    }
                    case "down" -> {
                        if(snake.solidArea.intersects(gp.obj[gp.mapPick][i].solidArea))
                        {
                            if(gp.obj[gp.mapPick][i].collision)
                                snake.collisionOn = true;
                            if(player)
                                index = i;
                        }
                    }
                    case "left" -> {
                        if(snake.solidArea.intersects(gp.obj[gp.mapPick][i].solidArea))
                        {
                            if(gp.obj[gp.mapPick][i].collision)
                                snake.collisionOn = true;
                            if(player)
                                index = i;
                        }
                    }
                    case "right" -> {
                        if(snake.solidArea.intersects(gp.obj[gp.mapPick][i].solidArea))
                        {
                            if(gp.obj[gp.mapPick][i].collision)
                                snake.collisionOn = true;
                            if(player)
                                index = i;
                        }
                    }
                }
                snake.solidArea.x = snake.solidAreaDefaultX;
                snake.solidArea.y = snake.solidAreaDefaultY;
                gp.obj[gp.mapPick][i].solidArea.x = gp.obj[gp.mapPick][i].solidAreaDefaultX;
                gp.obj[gp.mapPick][i].solidArea.y = gp.obj[gp.mapPick][i].solidAreaDefaultY;
            }
        }
        return index;
    }
	
	public void checkOut(Snake snake)
	{
		if(snake.worldX[0] > gp.worldWidth)
			snake.collisionOn = true;
		else if(snake.worldX[0] < 0)
			snake.collisionOn = true;
		if(snake.worldY[0] > gp.worldHeight)
			snake.collisionOn = true;
		else if(snake.worldY[0] < 0)
			snake.collisionOn = true;
	}
	
	public void checkTile(Snake snake)
	{
		int entityLeftWorldX = snake.worldX[0];
        int entityRightWorldX = snake.worldX[0];
        int entityTopWorldY = snake.worldY[0];
        int entityBottomWorldY = snake.worldY[0];

        int entityLeftCol = entityLeftWorldX / GamePanel.tileSize;
        int entityRightCol = entityRightWorldX / GamePanel.tileSize;
        int entityTopRow = entityTopWorldY / GamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / GamePanel.tileSize;

        int tileNum;

        switch (snake.direction)
        {
            case "up" -> {
                entityTopRow = (entityTopWorldY - GamePanel.tileSize) / GamePanel.tileSize;
                if(entityTopRow > 0)
                {
                    tileNum = gp.tileM.mapTileNum[gp.mapPick][entityLeftCol][entityTopRow];
                    if(gp.tileM.tile[gp.mapPick][tileNum].collision)
                        snake.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + GamePanel.tileSize) / GamePanel.tileSize;
                if(entityBottomRow < gp.maxScreenRow)
                {
                    tileNum = gp.tileM.mapTileNum[gp.mapPick][entityLeftCol][entityBottomRow];
                    if(gp.tileM.tile[gp.mapPick][tileNum].collision)
                        snake.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - GamePanel.tileSize) / GamePanel.tileSize;
                if(entityLeftCol > 0)
                {
                    tileNum = gp.tileM.mapTileNum[gp.mapPick][entityLeftCol][entityTopRow];
                    if(gp.tileM.tile[gp.mapPick][tileNum].collision)
                        snake.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + GamePanel.tileSize) / GamePanel.tileSize;
                if(entityRightCol < gp.maxScreenCol)
                {
                    tileNum = gp.tileM.mapTileNum[gp.mapPick][entityRightCol][entityTopRow];
                    if(gp.tileM.tile[gp.mapPick][tileNum].collision)
                        snake.collisionOn = true;
                }
            }
        }
	}
	
	public void checkBodyCollision(Snake snake)
	{
		
	}
}

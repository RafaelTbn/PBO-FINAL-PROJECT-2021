package tile;

import ui.GamePanel;
import util.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager
{
    GamePanel gp;
    public Tile[][] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        // 10 kinds of tile
        tile = new Tile[5][60];
        mapTileNum = new int[5][][];
        mapTileNum[0] = new int[32][32];
        mapTileNum[1] = new int[32][32];
        mapTileNum[2] = new int[32][32];
        mapTileNum[3] = new int[32][32];
        mapTileNum[4] = new int[32][32];
        getTileImage();
        loadMap("/map/world01.txt", 0, 32, 32);
        loadMap("/map/world02.txt", 1, 32, 32);
        loadMap("/map/world03.txt", 2, 32, 32);
        loadMap("/map/world04.txt", 3, 32, 32);
        loadMap("/map/world05.txt", 4, 32, 32);
    }

    public void setup(int mapIndex, int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[mapIndex][index] = new Tile();
            tile[mapIndex][index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[mapIndex][index].image = uTool.scaleImage(tile[mapIndex][index].image, GamePanel.tileSize, GamePanel.tileSize);
            tile[mapIndex][index].collision = collision;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int i, int maxWorldCol, int maxWorldRow)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0, row = 0;
            while (col < maxWorldCol && row < maxWorldRow)
            {
                String line = br.readLine();
                while (col < maxWorldCol)
                {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[i][col][row] = num;
                    col++;
                }
                if(col == maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getTileImage()
    {
    	setup(0, 0, "map1/layer " + 1, true);
        for (int i = 1; i <= 7; i++)
            setup(0, i, "map1/layer " + i, false);
        
        for (int i = 0; i <= 9; i++)
            setup(1, i, "map2/layer 10", true);
        for (int i = 10; i <= 21; i++)
            setup(1, i, "map2/layer " + i, false);
        
        setup(1, 17, "map2/layer 17", true);
        setup(1, 18, "map2/layer 18", true);
        setup(1, 19, "map2/layer 19", true);
        
        for (int i = 0; i <= 21; i++)
            setup(2, i, "map3/layer 22", true);
        for (int i = 22; i <= 36; i++)
            setup(2, i, "map3/layer " + i, false);
        
        setup(2, 22, "map3/layer 22", true);
        setup(2, 23, "map3/layer 23", true);
        setup(2, 24, "map3/layer 24", true);
        setup(2, 25, "map3/layer 25", true);
        setup(2, 26, "map3/layer 26", true);
        setup(2, 27, "map3/layer 27", true);
        setup(2, 28, "map3/layer 28", true);
        setup(2, 29, "map3/layer 29", true);
        setup(2, 35, "map3/layer 35", true);
        setup(2, 36, "map3/layer 36", true);
        
        for (int i = 0; i <= 37; i++)
            setup(3, i, "map4/layer 37", true);
        for (int i = 37; i <= 42; i++)
            setup(3, i, "map4/layer " + i, false);
        
        setup(3, 41, "map4/layer 41", true);
        
        for (int i = 0; i <= 42; i++)
            setup(4, i, "map5/layer 43", true);
        for (int i = 43; i <= 44; i++)
            setup(4, i, "map5/layer " + i, false);
        
        setup(4, 44, "map5/layer 44", true);
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0, worldRow = 0;
        while (worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow)
        {
            int tileNum = mapTileNum[gp.mapPick][worldCol][worldRow];
            int worldX = worldCol * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;

            g2.drawImage(tile[gp.mapPick][tileNum].image, worldX, worldY, null);

            worldCol++;
            if(worldCol == gp.maxScreenCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

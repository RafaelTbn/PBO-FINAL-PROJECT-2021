package util;

import object.Apple;
import ui.GamePanel;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.obj[0][0] = new Apple(gp);
        gp.obj[0][0].worldX = GamePanel.tileSize * 15;
        gp.obj[0][0].worldY = GamePanel.tileSize * 15;
        
        gp.obj[1][0] = new Apple(gp);
        gp.obj[1][0].worldX = GamePanel.tileSize * 29;
        gp.obj[1][0].worldY = GamePanel.tileSize * 5;
        
        gp.obj[2][0] = new Apple(gp);
        gp.obj[2][0].worldX = GamePanel.tileSize * 10;
        gp.obj[2][0].worldY = GamePanel.tileSize * 5;
        
        gp.obj[3][0] = new Apple(gp);
        gp.obj[3][0].worldX = GamePanel.tileSize * 29;
        gp.obj[3][0].worldY = GamePanel.tileSize * 5;
        
        gp.obj[4][0] = new Apple(gp);
        gp.obj[4][0].worldX = GamePanel.tileSize * 29;
        gp.obj[4][0].worldY = GamePanel.tileSize * 5;
    }
}
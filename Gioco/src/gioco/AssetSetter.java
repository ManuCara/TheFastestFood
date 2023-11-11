package gioco;

import object.OBJ_Burger;

/*
 * @author manuc
 */
public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Burger();
        gp.obj[0].worldX = 2 * gp.tileSize;
        gp.obj[0].worldY = 2 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Burger();
        gp.obj[1].worldX = 4 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;

    }

}
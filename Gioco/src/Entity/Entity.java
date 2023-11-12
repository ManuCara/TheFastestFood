package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * @author manuc
 */
public class Entity {
    
    public int worldX, worldY;
    public int speed;
    
    //Per rendere dinamiche l'uso delle immagini in base alla direzione necessito di una matrice, ogni riga rappresentera' una direzione e ogni colonna un'immagine della direzione
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea;
    public boolean collision = false;

}

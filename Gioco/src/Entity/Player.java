package Entity;

import gioco.GamePanel;
import gioco.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * @author manuc
 */
public final class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;
        
        solidArea = new Rectangle(8, 16, gp.tileSize * (3/4), gp.tileSize * (3/4));
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() { // imposta la posizione iniziale del giocatore
        worldX = gp.tileSize + 23 - (gp.tileSize)/2;
        worldY = gp.tileSize + 21 - (gp.tileSize)/2;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        
        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

            
        } catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
        }
        
        
        
    }
    
    
    public  void update(){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            
        if(keyH.upPressed == true){
            direction = "up";
        }
        if(keyH.downPressed == true){
            direction = "down";
        }
        if(keyH.leftPressed == true){
            direction = "left";
        }
        if(keyH.rightPressed == true){
            direction = "right";
        }
        
        //Check player collision
        collision = false;
        gp.cChecker.checkTile(this);
        
        //If collision is false, player can move
        if(collision == false){
            
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
            
        }
             
    }
    
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }                
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }               
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);   
        }
}

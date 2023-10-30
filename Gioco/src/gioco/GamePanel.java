package gioco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/*
 * @author manuc
 */
public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    
    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; // value = 32
    final int maxScreenRow = 12; // value = 24
    final int screenWidth = tileSize * maxScreenCol; // 1536px
    final int screenHeight = tileSize * maxScreenRow; // 1152px
    
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
    
    int fps = 60;


    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        System.out.println("Fino a qua arrivo");

        double drawInterval = 1000000000/fps; //refresh rate 0.01666 s
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){

            
            // 1 Update information such a character position 
            update();
            
            // 2 Draw: draw the screen with the update information
            repaint();
            
            
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    
    public void update(){

        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.white);
        
        g2.fillRect(playerX, playerY,tileSize, tileSize);

        g2.dispose();
    }
    
    
}
;
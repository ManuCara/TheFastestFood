package gioco;

import Entity.Player;
import Tiles.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * @author manuc
 */
public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // value = 32
    public final int maxScreenRow = 12; // value = 24
    public final int screenWidth = tileSize * maxScreenCol; // 1536px
    public final int screenHeight = tileSize * maxScreenRow; // 1152px
    
    int fps = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    
        
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
    
   /* @Override
    public void run() { // Create a loop with method Sleep

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
        
    }*/
    
    @Override
    public void run(){ //Create a loop with Delta
        
        double drawInterval = 1000000000 / fps; //refresh rate 0.01666 s with 60fps
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        long timer = 0; //Per mostrare gli FPS del app
        int drawCount = 0;
                
        while (gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval; // Calcola il tempo che è passato dal ciclo precedente
            timer += (currentTime - lastTime); 
            lastTime = currentTime;
            
            
            if(delta >= 1){     //se e' passato almeno un tempo di intervallo ->

                // 1 Update information such a character position 
                update();

                // 2 Draw: draw the screen with the update information
                repaint();
            
                delta--;
                
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: "+ drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }
    
    
    public void update(){
        
        player.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);
        
        player.draw(g2);

        g2.dispose();
    }
    
    
}
;
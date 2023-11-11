package gioco;

import Entity.Player;
import Tiles.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;

/*
 * @author manuc
 */
public class GamePanel extends JPanel implements Runnable{
    
    //TILES SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    
    //SCREEN  SETTINGS
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // value = 32
    public final int maxScreenRow = 12; // value = 24
    public final int screenWidth = tileSize * maxScreenCol; // 1536px
    public final int screenHeight = tileSize * maxScreenRow; // 1152px


    //WORLD SETTINGS
    public final int maxWorldCol = 16;
    public final int maxWorldRow = 16;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int fps = 60;
    
    
    TileManager tileM = new TileManager(this, "res/tiles/ristorante"); //Incaricato di scaricare tutte le informazioni grafiche dal path della directory 
    KeyHandler keyH = new KeyHandler(); //Si occupa di gestire gli input da tastiera dell'utente
    Thread gameThread;
    public CollisionCecker cChecker = new CollisionCecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; // Per ora sopporta solo 10 oggetti
    
    
    //Inizializzazione del Game Panel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    //Richiamo metodo per il posizionamento degli oggetti
   public void setUpGame(){
       aSetter.setObject();
   }

    //Richiamo metodo che permette l'avvio del loop di gioco
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run(){ //Creo il loop con il Delta
        
        double drawInterval = 1000000000 / fps; //Refresh rate 0.01666 s with 60fps
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

                // 1 Update information esempio la posizione di un personaggio
                update();

                // 2 Draw: Disegno lo schermo con le informazioni aggiornate
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
    
    //Aggiorno i movimenti che ha fatto il persoanggio
    public void update(){
        player.update();
    }
    
    
    //Permette di disegnare all'interno del GamePanel le diverse immagini, l'ordine di disegno e' importante per la sovrapposizione
    @Override
    public void paintComponent(Graphics g){// Metodo richiamato con la funzione repaint()
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);
        
        for(int i = 0; i < obj.length; i++){
            if( obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        
        player.draw(g2);

        g2.dispose();
    }
}

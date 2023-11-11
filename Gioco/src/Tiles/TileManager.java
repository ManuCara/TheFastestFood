package Tiles;

import gioco.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * @author manuc
 */
public final class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp, String folderPath) {
        this.gp = gp;
        getTileImage(folderPath);
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // imposta le dimensione massime della mappa di gioco
        
        loadMap("/maps/risto.txt"); // Gli viene passato il path del file .txt nella quale è salvata la mappa. NOTA: rendere più carino, es. parametro passato dal gamePanel
    }
    
    //Passandogli il path della cartella dove sono presenti le immagini grafiche del gioco, popola l'array con esse e verifica se sono parti attraversabili dall'utente o no
    public void getTileImage(String folderPath){
        File path = new File(folderPath);
        File[] allFiles = path.listFiles();
        
        tile = new Tile[allFiles.length];
           
        for(int i = 0; i < allFiles.length; i++){
            try {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(allFiles[i]);
                String s = allFiles[i] + "";
                if(s.contains("collision") || s.contains("Collision")){
                    tile[i].collision = true;
                    //System.out.println("File numero: " + i + " Collisione attiva");
                }                
            } catch (IOException ex) {
                Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }            
        
    }
    
    //In base alla mappa .txt, lui popola una matrice che rappresentano le colonne e le righe di gioco, ne salva il valore grafico di esse 
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int col = 0;
                int row = 0;
                
                while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                    
                    String line = br.readLine();
                    
                    while (col < gp.maxWorldCol){
                        String numbers[] = line.split(" ");
                        
                        int num = Integer.parseInt(numbers[col]);
                        
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    
                    if(col == gp.maxWorldCol){
                        col = 0;
                        row++;
                    }
                }
            }            
        } catch (IOException | NumberFormatException e) {
        }
    }
    
    // Disegna la mappa in base al valore grafico della matrice, la ricercaall'interno dell'array, riga per colonna carica tutto
    // In questa impostazione il gioco si incarica di disegnare solo quelle attorno al player e non tutta la mappa per non sovraccaricare nel caso di mappe immense.
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;

        
        while(worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize  < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY && 
                    worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY ){ //Per migliorare le prestazioni, l'applicazione non deve caricare tutta la mappa, ma solo quella visibile
            
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            }
            worldCol++;
            
            if(worldCol == gp.maxScreenCol){
                worldCol = 0;
                worldRow++;
            }
            
        } 

    }

}

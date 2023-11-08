package Tiles;

import gioco.GamePanel;
import java.awt.Graphics2D;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * @author manuc
 */
public final class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        
        tile = new Tile[12];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        
        getTileImage();
        loadMap("/maps/risto.txt");
    }
    
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int col = 0;
                int row = 0;
                
                while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                    
                    String line = br.readLine();
                    
                    while (col < gp.maxScreenCol){
                        String numbers[] = line.split(" ");
                        
                        int num = Integer.parseInt(numbers[col]);
                        
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    
                    if(col == gp.maxScreenCol){
                        col = 0;
                        row++;
                    }
                }
            }            
        } catch (IOException | NumberFormatException e) {
        }
    }
    
    
        
        /*
        public int numeroImmagini(String path){
    
        // Metodo che dovrebbe contare quanti elementi ci sono in una directory, non riesco a cambiare da percorso assoluto a relativo
        // Se funziona si può creare e poi popolare l'array di tiles con le giuste dimensioni
        // Per ora si deve cambiare in base agli elementi nella directory ogni volta
        // Nel caso di aggiornamenti di tiles grafici bisognerà farlo ogni volta
    
        List<String> fileNames = new ArrayList<>(); 
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("D:\\Manuel\\Scuola\\Università\\2 Anno\\Interfacce grafiche\\TheFastestFood\\Gioco\\res\\tiles\\ristorante"));
            for (Path paths : directoryStream) {
                fileNames.add(paths.toString());
            }
        } catch (IOException ex) {
        }
        System.out.println("File Count:" + fileNames.size());
        
        return fileNames.size();
        }
        */
        
        
    
    public void getTileImage(){
        
        try {
            
            for(int i = 0; i < tile.length; i++ ){
                String s;
                if(i < 10){
                    s = "/tiles/ristorante/sprite_0" + i + ".png";
                }
                else{
                    s = "/tiles/ristorante/sprite_" + i + ".png";

                }
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(s));
            }
            
        } catch (IOException e) {
        }
    }
    
    
    public void draw(Graphics2D g2){
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            
            int tileNum = mapTileNum[col][row];
            
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }

        } 

    }

}

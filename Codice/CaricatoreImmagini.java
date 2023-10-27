import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class CaricatoreImmagini {
  
  BufferedImage image = null;

  public CaricatoreImmagini() {
  }

  public BufferedImage caricaImmagine(String posizione){
    try{
      image = ImageIO.read(getClass().getResourceAsStream((posizione)));
            System.out.println("C'è l'immagine!");

      
    }catch(IOException ex){
      System.out.println("Indirizzo sbagliato!");
     // Logger.getLogger(CaricatoreImmagini.class.getName()).log(Level.SEVERE, ex, "Indirizzo specificato sbagliato!");
    }
    return image;
  }
}

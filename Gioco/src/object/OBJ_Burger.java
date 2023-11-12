package object;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * @author manuc
 */
public class OBJ_Burger extends SuperObject{

    //Assegna all'oggetto la sua imagini in questa classe e' burger
    //Necessito di una classe che crea in maniera dinamica l'oggetto con l'immagini adeguata
    public OBJ_Burger() {
        
        name = "Burger";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/burger.png"));
        } catch (IOException ex) {
            Logger.getLogger(OBJ_Burger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}

package object;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * @author manuc
 */
public class OBJ_Burger extends SuperObject{

    public OBJ_Burger() {
        
        name = "Burger";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/burger.png"));
        } catch (IOException ex) {
            Logger.getLogger(OBJ_Burger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}

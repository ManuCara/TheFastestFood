package gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * @author manuc
 */
public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //Metodo in ascolto se wasd viene premuto
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        

    }

    //metodo in ascolto quando wasd viene rilasciato
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }

    }

    
    
    
}

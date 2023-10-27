/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gioco;

import javax.swing.*;

/*
 * @author manuc
 */
public class Gioco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The Fastest Food");
        
        window.add(gamePanel);
        
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread();

        
        }
    
}

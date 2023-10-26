import java.util.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends Canvas{
  private static final int larghezza = 1980;
  private static final int altezza = 1080;
  private static final String nomeGioco = "The Fastest Food";
  BufferedImage sfondo = null;

  public Main(){
    caricaRisorse();
  }

    public static void main(String[] args){
      Main gioco = new Main();

      JFrame finestraGioco = new JFrame(nomeGioco, null);
      
      Dimension dimensioneFinestra = new Dimension(larghezza,altezza);
      finestraGioco.setPreferredSize(dimensioneFinestra);
      finestraGioco.setMaximumSize(dimensioneFinestra);
      finestraGioco.setResizable(false);

      finestraGioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      finestraGioco.add(gioco);
      
      finestraGioco.pack();
      finestraGioco.setVisible(true);
    }

    private void caricaRisorse(){
      CaricatoreImmagini loader = new CaricatoreImmagini();
      sfondo = loader.caricaImmagine("/Immagini/Sfondi/Prova.jpg");
      System.out.println("Sfondo caricato correttamente!");
    }

}
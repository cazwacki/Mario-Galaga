import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import sun.audio.*;

public class GameOver extends GameScreen {

   private BufferedImage myImage;
   public static Graphics myBuffer;
   protected int msgNum = (int)Math.floor(Math.random()*6+1);
   protected AudioStream gameOverMusic;
   protected String gameOverMusicFile = "RESORCES/Sound/game over.au";


   public GameOver(JFrame p, int w, int h) {
      super(p, w, h);
      myImage =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      
      gameOverMusic = loadSoundFile(gameOverMusicFile);
      
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,NintendoEvolution.resWidth,NintendoEvolution.resHeight);
   }
   
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   
   public void actionPerformed(ActionEvent e) 
   {
		myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,NintendoEvolution.resWidth,NintendoEvolution.resHeight);
      Font stringFont = new Font("SansSerif", Font.PLAIN, 18);
      myBuffer.setFont(stringFont);
      myBuffer.setColor(Color.WHITE);
      myBuffer.drawString("GAME OVER", NintendoEvolution.resWidth/2, NintendoEvolution.resHeight/2);
      myBuffer.drawString("YOUR SCORE", NintendoEvolution.resWidth/2, NintendoEvolution.resHeight/2 + 50);
      myBuffer.drawString("" + Level1Panel.score, NintendoEvolution.resWidth/2, NintendoEvolution.resHeight/2 + 100);
      myBuffer.drawImage(new ImageIcon("RESORCES/prompt.png").getImage(), NintendoEvolution.resWidth/2, NintendoEvolution.resHeight/2, null);
      repaint();
   }
   
   public void keyTyped(KeyEvent e) {
    
   }
  
   public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
      case KeyEvent.VK_ENTER:
         System.exit(0);
         break;
      case KeyEvent.VK_SPACE:
         break;
			

      }
   }
  
   public void keyReleased(KeyEvent e){
   
   }
   
   public boolean draw() {
      return true;
   }
   
   public void startScreen() {
      super.startScreen();
      if(gameOverMusic != null) {
      AudioPlayer.player.start(gameOverMusic);
      }
   }
   
   public void stopScreen() {
      super.stopScreen();
      if(gameOverMusic != null) {
      AudioPlayer.player.stop(gameOverMusic);
      }
   }

}
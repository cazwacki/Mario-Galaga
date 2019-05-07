import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import sun.audio.*;

//MENU PANEL
public class NEPanel extends GameScreen {

   ImageObserver imgobs;
   
   private static final Color sideBars = new Color(61, 3, 119);
	
   NintendoEvolution ne = new NintendoEvolution();
   
   private BufferedImage myImage;
   public static Graphics myBuffer;
   
   ArrayList graphicObjects = new ArrayList(); 
   
   public Image neLogo = new ImageIcon("nelogo.png").getImage();
   public Image prompt = new ImageIcon("prompt.png").getImage();
   
   protected String titleScreenMusicFile = "RESORCES/Sound/pacmansong.au";
   protected String ohBabySoundFile = "RESORCES/Sound/oh baby.au";
   protected AudioStream titleScreenMusic = null;
   
   public NEPanel(JFrame p, int w, int h)
   {
      super(p, w, h);
      myImage =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();

      for(int i = 1; i <= 35; i++) {
         graphicObjects.add(new Star(myBuffer));
      }
		titleScreenMusic = null;
    titleScreenMusic = loadSoundFile(titleScreenMusicFile);
    if(titleScreenMusic != null) {
       // play the audio clip with the audioplayer class
       AudioPlayer.player.start(titleScreenMusic);  
    }
      
    myBuffer.setColor(Color.BLACK);
    myBuffer.fillRect(0,0,NintendoEvolution.resWidth,NintendoEvolution.resHeight);

   }

   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   public void keyTyped(KeyEvent e){
    
   }
  
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_ENTER) {
         NintendoEvolution.gotoLevelOne();
         AudioPlayer.player.stop(titleScreenMusic);         
      }
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
         AudioStream ohBabySound = null;
         ohBabySound = loadSoundFile(ohBabySoundFile);
			AudioPlayer.player.start(ohBabySound);
      }	
   }
	 
  
   public void keyReleased(KeyEvent e){
  
   }
   int loopLength = 100;
   public void actionPerformed(ActionEvent e) 
   {
		myBuffer.setColor(Color.BLACK);
	   myBuffer.fillRect(0,0,NintendoEvolution.resWidth,NintendoEvolution.resHeight);
      makeSidebars();
      Iterator iter = graphicObjects.iterator();
      while(iter.hasNext()) {
         GraphicObject go = (GraphicObject)iter.next();
         go.draw();
      }
      myBuffer.drawImage(neLogo, ne.getResWidth()/2 - 275, 100, imgobs);
      if (loopLength > 50) {
         myBuffer.drawImage(prompt, ne.getResWidth()/2 - 275, ne.getResHeight()-400, imgobs);
         loopLength--;
      } else {
         if(loopLength <= 0) {
            loopLength = 100;
         }
         loopLength--;
      }

      repaint();
      titleScreenMusic = loopSoundFile(titleScreenMusicFile, titleScreenMusic);
   }
   
    private double distance(double x1, double y1, double x2, double y2) 
   {  
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); 
   }
   
   private void makeSidebars() {
      myBuffer.setColor(sideBars);
      myBuffer.fillRect(0,0,100,ne.getResHeight());
      myBuffer.fillRect(ne.getResWidth()-100, 0, ne.getResWidth(), ne.getResHeight()); 
   }
	
   public void stopScreen() {
      super.stopScreen();
      if(titleScreenMusic != null) {
      AudioPlayer.player.stop(titleScreenMusic);
      }
   }
   
   public void startScreen() {
      super.startScreen();
      if(titleScreenMusic != null) {
      AudioPlayer.player.start(titleScreenMusic);
      }
   }  
}
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import sun.audio.*;

public abstract class GameScreen extends JPanel implements KeyListener, ActionListener {

   protected JFrame parent;
   protected int width;
   protected int height;
   protected Timer timer;
   
public GameScreen(JFrame p, int w, int h) {
   parent = p;
   width = w;
   height = h;
   timer = new Timer(15, this);
} 

protected AudioStream loadSoundFile(String filename) {
   AudioStream result = null;
   try {
      FileInputStream in = new FileInputStream(filename);
      result = new AudioStream(in);
   } catch(Throwable t) {
      System.out.println("Exception caught trying to load audio stream: " + filename);
      t.printStackTrace(System.out);
   }
   return result;
}

protected AudioStream loopSoundFile(String filename, AudioStream as) {
   try {
      if(as.available() == 0) {
            as = loadSoundFile(filename);
            AudioPlayer.player.start(as);
      }
   } catch(Throwable t) {
      System.out.println("Exception caught trying to loop audio stream: " + filename);
      t.printStackTrace(System.out);
   }
   return as;
}

public void startScreen() {
      addKeyListener(this);
      parent.addKeyListener(this);
      parent.setVisible(true);
      timer.start();
   }
   
public void stopScreen() {
      timer.stop();
      parent.removeKeyListener(this);
      parent.setVisible(false);
      removeKeyListener(this);
   }


}
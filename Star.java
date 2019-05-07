import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Star extends GraphicObject {

public double dy;

public Star(Graphics g) {
   super(g);
   setStartPosition();
   yPosition = Math.floor(Math.random()*NintendoEvolution.getResHeight());
}

public boolean draw() {
      graphics.setColor(Color.BLACK);
      graphics.fillRect((int)xPosition,(int)yPosition - 1, 2, 2);
      graphics.setColor(Color.WHITE);
      graphics.fillRect((int)xPosition, (int)yPosition, 2, 2);
      yPosition = yPosition + dy; 
      if(yPosition >= NintendoEvolution.getResHeight()) {
         setStartPosition();
         }
      return true;
   }
  
protected void setStartPosition() {
   xPosition = Math.floor(Math.random()*(NintendoEvolution.getResWidth()-200))+100;
   dy = Math.floor(Math.random()*4 + 3);
   yPosition = 0;
}

public Rectangle getBoundingBox() {
   return new Rectangle((int)xPosition, (int)yPosition, 1, 1);
}

}
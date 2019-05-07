import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import sun.audio.*;

public class NEShip extends GraphicObject {

private int shipLevel;
private int shipExp;
public Image ship = new ImageIcon("RESORCES/Sprites/mario.png").getImage();
public ImageObserver imgobs;

public NEShip(Graphics g, int startLevel) {
   super(g);
   shipLevel = startLevel;
   xPosition = (NintendoEvolution.resWidth-75)/2;
   yPosition = NintendoEvolution.resHeight - 110;
}

public int getShipLevel() {
   return shipLevel;
}

public int getShipExp() {
   return shipExp;
}

public void setShipExp(int newExp) {
   shipExp = newExp;
}

public void testForLevelUp() {
   boolean eligibleForLevelUp = false;
   int expCheck = getShipExp();
   int levelCheck = getShipLevel();
   if(expCheck >= Math.pow((15*shipLevel),2)+100) {
      //initiate boss fight
   } 
}

public boolean draw() {
   graphics.drawImage(ship, (int)xPosition, (int)yPosition, 60, 60, imgobs);
   return true;
}

public void moveUp() {
   yPosition = yPosition - 6;
   if(yPosition <= NintendoEvolution.resHeight*0.8) {
      yPosition = NintendoEvolution.resHeight*0.8;
   }
}

public void moveDown() {
   yPosition = yPosition + 6;
   if(yPosition >= NintendoEvolution.resHeight - 75) {
      yPosition = NintendoEvolution.resHeight - 75;
   }
}

public void moveLeft() {
   xPosition = xPosition - 6;
   if(xPosition <= 100) {
      xPosition = 100;
   }
}

public void moveRight() {
   xPosition = xPosition + 6;
   if(xPosition >= NintendoEvolution.resWidth - 160) {
      xPosition = NintendoEvolution.resWidth - 160;
   }
}

public Rectangle getBoundingBox() {
   return new Rectangle((int)xPosition, (int)yPosition, 60, 60);
}

}
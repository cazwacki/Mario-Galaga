import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import sun.audio.*;

public class PlayerBullet extends GraphicObject {

public Image shoot = new ImageIcon("RESORCES/Sprites/particles/fire.png").getImage();

public PlayerBullet(Graphics g, int startX, int startY) {
   super(g);
   xPosition = startX;
   yPosition = startY;
}

public boolean draw() {
   yPosition = yPosition - 12;
   graphics.drawImage(shoot, (int)xPosition, (int)yPosition, 5, 5, null);
   if(yPosition < 0) {
      return false;
   } else {
      return true;
   }
}

public Rectangle getBoundingBox() {
   return new Rectangle((int)xPosition, (int)yPosition, 5, 5);
}

}
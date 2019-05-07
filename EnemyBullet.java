import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import sun.audio.*;

public class EnemyBullet extends GraphicObject {

public Image shoot = new ImageIcon("RESORCES/Sprites/shell.png").getImage();

private int targetX;
private int targetY;
private int stepsToTarget;
private int currentStep = 1;
private int startX;
private int startY;

/*public String toString() {
   java.lang.StringBuilder sb = new java.lang.StringBuilder();
   sb.append("{ type=EnemyBullet, startX=").append(startX);
   sb.append(", startY=").append(startX);
   sb.append(", targetX=").append(targetX);
   sb.append(", targetY=").append(targetY);
   sb.append(", stepsToTarget=").append(stepsToTarget);
   sb.append(", currentStep=").append(currentStep);
   sb.append(", xPosition=").append(xPosition);
   sb.append(", yPosition=").append(yPosition);
   sb.append(" }");
   return sb.toString();
}*/

public EnemyBullet(Graphics g, int startX, int startY, int targetX, int targetY, int stepsToTarget) {
   super(g);
   xPosition = startX;
   yPosition = startY;
   this.startX = startX;
   this.startY = startY;
   this.targetX = targetX;
   this.targetY = targetY;
   this.stepsToTarget = stepsToTarget;
   System.out.println("Creating " + this);
}

public boolean draw() {
   xPosition = startX + ((double)(targetX - startX)/stepsToTarget)*currentStep;
   yPosition = startY + ((double)(targetY - startY)/stepsToTarget)*currentStep;
   //System.out.println("Drawing " + this);
   graphics.drawImage(shoot, (int)xPosition, (int)yPosition, 20, 20, null);
   if(yPosition >= NintendoEvolution.resHeight || xPosition <= 0 || xPosition >= NintendoEvolution.resWidth) {
      return false;
   } else {
      currentStep++;
      return true;
   }
}

public Rectangle getBoundingBox() {
   return new Rectangle((int)xPosition, (int)yPosition, 10, 10);
}

}
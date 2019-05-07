import java.util.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Enemy extends GraphicObject {

protected List flightPlans;
protected FlightPlan currentFlightPlan;
protected Iterator flightPlanIterator;
protected Image enemyImage;
protected Image enemyBullet;
protected int health;
protected int pointValue;
protected double shootingChance;

public Enemy(Graphics g, Image enemyImage, List flightPlans, int health, int pointValue, double shootingChance, Image enemyBullet) {
   super(g);
   this.enemyImage = enemyImage;
   this.flightPlans = flightPlans;
   this.health = health;
   this.pointValue = pointValue;
   this.enemyBullet = enemyBullet;
   this.shootingChance = shootingChance;
   flightPlanIterator = this.flightPlans.iterator();
   currentFlightPlan = (FlightPlan)flightPlanIterator.next();
}

public boolean draw() {
   xPosition = currentFlightPlan.getCurrentX();
   yPosition = currentFlightPlan.getCurrentY();
   graphics.drawImage(enemyImage, (int)xPosition, (int)yPosition, 75, 75, null);
   if(currentFlightPlan.calculateNextStep() == false) {
      currentFlightPlan = (FlightPlan)flightPlanIterator.next();
   }
   return health > 0;
   
}

public EnemyBullet getEnemyShot(NEShip ship) {
   EnemyBullet eb = null;
   if(xPosition > 100 && xPosition < NintendoEvolution.resWidth - 100) { 
      if(Math.random() < shootingChance) {
         System.out.println("Shooting");
         eb = new EnemyBullet(graphics, (int)xPosition, (int)yPosition, (int)ship.xPosition, (int)ship.yPosition, (int)Math.sqrt(Math.pow(xPosition - ship.xPosition, 2) + Math.pow(yPosition - ship.yPosition, 2))/10);   
      }
   }
   return eb;
}

public Rectangle getBoundingBox() {
   return new Rectangle((int)xPosition, (int)yPosition, 75, 75);
}

public int getPointValue() { return pointValue; }
public int getHealth() { return health; }

public void takeDamage(int damageAmount) {
   health -= damageAmount;
}

}
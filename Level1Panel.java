import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.Rectangle;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import sun.audio.*;

//LEVEL 1 PANEL
public class Level1Panel extends GameScreen
{
   private static final Color sideBars = new Color(61, 3, 119);
   private BufferedImage myImage;
   public static Graphics myBuffer;
	private NEShip ship;
   private boolean movingUp = false;
   private boolean movingDown = false;
   private boolean movingLeft = false;
   private boolean movingRight = false;
   private boolean firing = false;
	private long nextShootingTime = System.currentTimeMillis();
   private long msBetweenShotsHoldingTrigger = 500;
   private long msBetweenShotsPressingTrigger = 100;
   private long nextFPS = nextShootingTime + 30000;
   private long frames = 0;
   public static int score;
   private int timesLevelOccur = 0;
   public int loopLength = 30;
   protected int spawnWaitTime = 300;
   
   ArrayList graphicObjects = new ArrayList();
   ArrayList enemies = new ArrayList();
   ArrayList enemyBullets = new ArrayList();
   ArrayList playerBullets = new ArrayList();
   
   public BufferedImage logo = null;
   
   protected AudioStream backgroundMusic;
   protected String backgroundMusicFile = "RESORCES/Sound/pacman dubstep remix.au";
	protected String killEnemySoundFile = "RESORCES/Sound/pacman_eatghost.au";
	protected String movementSoundFile = "RESORCES/Sound/pacman_chomp.au";
   protected String shootingSoundFile = "RESORCES/Sound/shoot.au";	
   public Level1Panel(JFrame p, int w, int h) throws Exception {
      super(p, w, h);
      myImage =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
   }
   
   public void initializeGame() {
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,NintendoEvolution.resWidth,NintendoEvolution.resHeight);

      backgroundMusic = loadSoundFile(backgroundMusicFile);

      graphicObjects.clear();
      enemies.clear();
      enemyBullets.clear();
      playerBullets.clear();
      score = 0;
      ship = new NEShip(myBuffer, 1);
      for(int i = 1; i <= 35; i++) {
         graphicObjects.add(new Star(myBuffer));
      }
      graphicObjects.add(ship);
      
      //enemy list
      
      for(int i = 1; i <= 5; i++) {
         Enemy goomba = makeGoomba(-50.0 - i*50, 500.0, i, 5);
         graphicObjects.add(goomba);
         enemies.add(goomba);
      }
      for(int i = 1; i <= 5; i++) {
         Enemy koopa = makeFlatKoopa(-300.0 - i*200, 400.0, i, 4);
         graphicObjects.add(koopa);
         enemies.add(koopa);
      }
      //for(int i = 1; i <= 10; i++) {
      //  Enemy circleKoopa = makeCircleKoopa(-1000 - i*250, NintendoEvolution.resHeight/2, i, 3);
      //  graphicObjects.add(circleKoopa);
      //  enemies.add(circleKoopa);
      //}
		myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,width,height);
   }

   public void paintComponent(Graphics g) {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   public void keyTyped(KeyEvent e) {
    
   }
  
   public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
      case KeyEvent.VK_ENTER:
         NintendoEvolution.gotoGameOver();
         break;
      case KeyEvent.VK_UP:
         movingUp = true;
         break;
      case KeyEvent.VK_DOWN:
         movingDown = true;
         break;
      case KeyEvent.VK_LEFT:
         movingLeft = true;
         break;
      case KeyEvent.VK_RIGHT:
         movingRight = true;
		   break;
		case KeyEvent.VK_SPACE:
			firing = true;
         break;
      }
   }
  
   public void keyReleased(KeyEvent e){
      switch(e.getKeyCode()) {
      case KeyEvent.VK_UP:
         movingUp = false;
         //AudioPlayer.player.stop(audioStream2);
         break;
      case KeyEvent.VK_DOWN:
         movingDown = false;
         //AudioPlayer.player.stop(audioStream2);
         break;
      case KeyEvent.VK_LEFT:
         movingLeft = false;
         //AudioPlayer.player.stop(audioStream2);
         break;
      case KeyEvent.VK_RIGHT:
         movingRight = false;
         //AudioPlayer.player.stop(audioStream3);
         break;
      case KeyEvent.VK_SPACE:
         firing = false;
         nextShootingTime = System.currentTimeMillis() + msBetweenShotsPressingTrigger;
         break;
      }
   }

   public void actionPerformed(ActionEvent e) 
   {
		spawnWaitTime--;
      if(spawnWaitTime == 0) {
         for(int i = 1; i <= 5; i++) {
            Enemy goomba = makeGoomba(-50.0 - i*50, 500.0, i, 5);
            graphicObjects.add(goomba);
            enemies.add(goomba);
            System.out.println("Spawned goomba");
         }
         for(int i = 1; i <= 5; i++) {
            Enemy koopa = makeFlatKoopa(-300.0 - i*200, 400.0, i, 4);
            graphicObjects.add(koopa);
            enemies.add(koopa);
            System.out.println("Spawned koopa");
         }
         spawnWaitTime = (int)(Math.random()*101 + 100);
         System.out.println("Spawn Wait: " + spawnWaitTime);
      }
      long now = System.currentTimeMillis();
      ++frames;
      if(now >= nextFPS) {
         System.out.println("FPS = " + (frames/30.0));
         nextFPS = now + 30000;
         frames = 0;
      }
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(0,0,NintendoEvolution.resWidth,NintendoEvolution.resHeight);
      handlePlayerActions();
      Iterator iter;
      iter = enemies.iterator();
      while(iter.hasNext()) {
         Enemy enemy = (Enemy)iter.next();
         EnemyBullet eb = enemy.getEnemyShot(ship);
         if(eb != null) {
            graphicObjects.add(eb);
            enemyBullets.add(eb);
         }
      }
      iter = graphicObjects.iterator();
      while(iter.hasNext()) {
         GraphicObject go = (GraphicObject)iter.next();
         if(go.draw() == false) {
         graphicObjects.remove(iter);
         }
      }
      makeSidebars();
      updateScore();
      //drawAllHitBoxes();
      handleCollisions();
      repaint();
      backgroundMusic = loopSoundFile(backgroundMusicFile, backgroundMusic);
      
   }
   
   private void drawAllHitBoxes() {
      drawHitBoxes(playerBullets);
      drawHitBoxes(enemies);
      drawHitBoxes(enemyBullets);
      drawHitBox(ship);
   }

   private void handlePlayerActions() {
      long now = System.currentTimeMillis();
      if(movingUp == true) {
         ship.moveUp();
      }
      if(movingDown == true) {
         ship.moveDown();
      }
      if(movingLeft == true) {
         ship.moveLeft();
      }
      if(movingRight == true) {
         ship.moveRight();
      }
      if(firing == true && nextShootingTime <= now) {
         nextShootingTime = now + msBetweenShotsHoldingTrigger;
         PlayerBullet pb = new PlayerBullet(myBuffer, (int)ship.xPosition + 32, (int)ship.yPosition);
         graphicObjects.add(pb);
         playerBullets.add(pb);
         AudioStream shootSound = loadSoundFile(shootingSoundFile);
         AudioPlayer.player.start(shootSound);
      }
   }
   
   private boolean collided(GraphicObject go1, GraphicObject go2) {
      return go1.getBoundingBox().intersects(go2.getBoundingBox());
   }
   
   private void handlePlayerBulletsHitEnemy() {
      Iterator playerBulletIterator = playerBullets.iterator();
      while(playerBulletIterator.hasNext()) {
         PlayerBullet pb = (PlayerBullet)playerBulletIterator.next();
         Iterator enemyIterator = enemies.iterator();
         while(enemyIterator.hasNext()) {
            Enemy enemy = (Enemy)enemyIterator.next();
            if(collided(pb, enemy) == true) {
               graphicObjects.remove(pb);
               playerBulletIterator.remove();
               enemy.takeDamage(1);
               if(enemy.getHealth() != 0) {
                  score += 50;
               }
               if(enemy.getHealth() == 0) {
                  graphicObjects.remove(enemy);
                  enemyIterator.remove();
                  score+= enemy.getPointValue();
                  break;
               }
            }
         }
      }
   }
   
   private void handleEnemyHitsPlayer() {
   
   }
   
   private void handleEnemyBulletsHitPlayer() {
      Iterator enemyBulletIterator = enemyBullets.iterator();
      while(enemyBulletIterator.hasNext()) {
         EnemyBullet eb = (EnemyBullet)enemyBulletIterator.next();
         if(collided(eb, ship) == true) {
            NintendoEvolution.gotoGameOver();
         }
      }
   }
   
   private void handleCollisions() {
      handlePlayerBulletsHitEnemy();
      handleEnemyHitsPlayer();
      handleEnemyBulletsHitPlayer();
   }

   private void drawHitBoxes(ArrayList al) {
      Iterator iter = al.iterator();
      while(iter.hasNext()) {
         GraphicObject go = (GraphicObject)iter.next();
         drawHitBox(go);
      }
   }
   
   private void drawHitBox(GraphicObject go) {
      Rectangle r = go.getBoundingBox();
      myBuffer.drawRect(r.x, r.y, r.width, r.height);
   }

   private double distance(double x1, double y1, double x2, double y2) 
   {  
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); 
   }
   
   public void startScreen() {
      super.startScreen();
      initializeGame();
      if(backgroundMusic != null) {
         AudioPlayer.player.start(backgroundMusic);
      }
   }
   
   public void stopScreen() {
      super.stopScreen();
      if(backgroundMusic != null) {
         AudioPlayer.player.stop(backgroundMusic);
      }
   }
   
   private void makeSidebars() {
      myBuffer.setColor(sideBars);
      myBuffer.fillRect(0,0,100,height);
      myBuffer.fillRect(width-100, 0, width, height); 
   }
   
   private void updateScore() {
      myBuffer.setColor(new Color(83, 236, 7));
      myBuffer.setFont(new Font("Courier New", Font.BOLD, 20));
      myBuffer.drawString("SCORE", NintendoEvolution.resWidth - 85, 20);
      myBuffer.drawString("" + score, NintendoEvolution.resWidth - 85, 50);
   }
   
   protected Enemy makeGoomba(double startX, double startY, int slotX, int slotY) {
      Image goombaImage = new ImageIcon("RESORCES/Sprites/goomba.png").getImage();
      List flightPlans = new ArrayList();
      double endX = (slotX + 1) * (width / 9.0);
      double endY = (slotY + 1) * (height / 17.0);

      flightPlans.add(new SinWaveFlightPlan(startX, NintendoEvolution.resWidth+75, 240, 150, startY));
      flightPlans.add(new DoNothingFlightPlan(5000, 5000));

      Enemy enemy = new Enemy(myBuffer, goombaImage, flightPlans, 1, 100, 0, null);
      return enemy;
   }
   
   protected Enemy makeFlatKoopa(double startX, double startY, int slotX, int slotY) {
      Image koopaImage = new ImageIcon("RESORCES/Sprites/koopa.png").getImage();
      Image koopaBullet = new ImageIcon("RESORCES/Sprites/shell.png").getImage();
      List flightPlans = new ArrayList();
      double endX = (slotX + 1) * (width / 9.0);
      double endY = (slotY + 1) * (height / 17.0);

      flightPlans.add(new FlatLineFlightPlan(startX, startX + NintendoEvolution.resWidth + 2000, 360, 200));
      flightPlans.add(new DoNothingFlightPlan(5000, 5000));

      Enemy enemy = new Enemy(myBuffer, koopaImage, flightPlans, 2, 200, 0.05, koopaBullet);
      return enemy;
   }
   
   protected Enemy makeCircleKoopa(double startX, double startY, int slotX, int slotY) {
      Image koopaImage = new ImageIcon("RESORCES/Sprites/koopa.png").getImage();
      Image koopaBullet = new ImageIcon("RESORCES/Sprites/shell.png").getImage();
      List flightPlans = new ArrayList();
      double endX = (slotX + 1) * (width / 9.0);
      double endY = (slotY + 1) * (height / 17.0);

      flightPlans.add(new FlatLineFlightPlan(startX, NintendoEvolution.resWidth/2, 360, 200));
      //flightPlans.add(new CircleFlightPlan(NintendoEvolution.resWidth/2, NintendoEvolution.resHeight/2, 120, 50));
      flightPlans.add(new FlatLineFlightPlan(NintendoEvolution.resWidth/2 + startX, NintendoEvolution.resWidth + 100, 360, 200));
      flightPlans.add(new DoNothingFlightPlan(5000, 5000));

      Enemy enemy = new Enemy(myBuffer, koopaImage, flightPlans, 2, 200, 0.05, koopaBullet);
      return enemy;
   }
   
   protected Enemy makeBowser(double startX, double startY, int slotX, int slotY) {
      return null;
   }
   
}
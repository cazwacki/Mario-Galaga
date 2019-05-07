/**
* Made by: Charles Zawacki and Nathan Stone
* Use the arrow keys to move around. Press space to fire. Dodge all 
* the shells the koopas shoot and try to get as many points as you can!
* Hit Enter to quit.
*/
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class NintendoEvolution {

public static int resWidth;
public static int resHeight;
public static JFrame frame;

private static NEPanel menu;
private static Level1Panel l1p;
private static GameOver go;
private static GameScreen currentScreen;

public static void main(String[] args) throws Exception {
   GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
   resWidth = gd.getDisplayMode().getWidth();
   resHeight = gd.getDisplayMode().getHeight();

   frame = new JFrame("Nintendo Evolution (Charles Z, Nathan S)");
   frame.setSize(resWidth, resHeight);
 	frame.setUndecorated(true);
   frame.setResizable(false);  
	frame.setLocation(0, 0);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   menu = new NEPanel(frame, resWidth, resHeight);
   l1p = new Level1Panel(frame, resWidth, resHeight);
   go = new GameOver(frame, resWidth, resHeight);
   frame.setVisible(true);
   changeScreen(menu);
}

protected static void changeScreen(GameScreen newScreen) {
   if (currentScreen != null) {
      currentScreen.stopScreen();
   }
   currentScreen = newScreen;
 	frame.setContentPane(currentScreen);
   currentScreen.startScreen();
}

public static void gotoLevelOne() {
changeScreen(l1p);
}
public static void gotoMainMenu() {
changeScreen(menu);
}
public static void gotoGameOver() {
changeScreen(go);
}

public static int getResWidth() {
   return resWidth;
}

public static int getResHeight() {
   return resHeight;
}

}
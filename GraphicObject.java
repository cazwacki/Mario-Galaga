import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GraphicObject {

protected Graphics graphics;
protected double xPosition;
protected double yPosition;

public GraphicObject(Graphics g) {
graphics = g;
}

public abstract Rectangle getBoundingBox();

public abstract boolean draw();

}
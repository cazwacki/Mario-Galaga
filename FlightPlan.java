public abstract class FlightPlan {

protected double startX;
protected double endX;
protected int numOfSteps;

protected double currentX;
protected double currentY;
protected double rotation;
protected double xStepSize;

/**
 * calculateNextStep updates the currentX, currentY, and rotation member variable to follow
 * the given flight plan.
 * 
 * @return True if the flight plan is not done yet.  False if the endX position has been reached.
 **/
public abstract boolean calculateNextStep();

public FlightPlan(double startX, double endX, int numOfSteps) {
this.startX = startX;
this.endX = endX;
this.numOfSteps = numOfSteps;
xStepSize = ((endX - startX)/numOfSteps);
currentX = startX;
}

public double getCurrentX() { return currentX; }
public double getCurrentY() { return currentY; }
public double getCurrentRotation() { return rotation; }
protected double getXStepSize() { return xStepSize;}
protected double getStartX() { return startX;}
protected double getEndX() { return endX;}

protected double calculateRotation(double startX, double startY, double endX, double endY) {
   return 0.0;
}
protected static boolean almostEqual(double a, double b, double eps){
    return Math.abs(a-b)<eps;
}
}
public class FlatLineFlightPlan extends FlightPlan {
   
   public FlatLineFlightPlan(double startX, double endX, int numOfSteps, double yPosition) {
      super(startX, endX, numOfSteps);
      currentY = yPosition;   
      rotation = calculateRotation(startX, currentY, startX + getXStepSize(), currentY);      
   }
   
   public boolean calculateNextStep() {
      boolean moreSteps = true;
      // Normally, oldX = currentX; oldY = currentY, calculate new x/y position and call rotation=calculateRotation(oldX, oldY, currentX, currentY) to update the rotation.
      currentX = currentX + getXStepSize();
      if(almostEqual(currentX, getEndX(), 0.01) == true) {
         moreSteps = false;
      }
      return moreSteps;
   }

}
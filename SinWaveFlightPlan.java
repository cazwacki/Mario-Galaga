public class SinWaveFlightPlan extends FlightPlan {
   
   protected double magnitude;
   protected double startY;
   
   public SinWaveFlightPlan(double startX, double endX, int numOfSteps, double magnitude, double startY) {
      super(startX, endX, numOfSteps);
      this.magnitude = magnitude;
      this.startY = startY;
   }
   
   public boolean calculateNextStep() {
      boolean moreSteps = true;
      // Normally, oldX = currentX; oldY = currentY, calculate new x/y position and call rotation=calculateRotation(oldX, oldY, currentX, currentY) to update the rotation.
      double oldX = currentX;
      double oldY = currentY;
      currentX = currentX + getXStepSize();
      currentY = (int)(startY - Math.sin(Math.toRadians(currentX)) * magnitude);
      rotation = calculateRotation(startX, currentY, startX + getXStepSize(), currentY);
      if(almostEqual(currentX, getEndX(), 0.01) == true) {
         moreSteps = false;
      }
      return moreSteps;
   }

}
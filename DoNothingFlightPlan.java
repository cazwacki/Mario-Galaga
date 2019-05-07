public class DoNothingFlightPlan extends FlightPlan {
   
   public DoNothingFlightPlan(double startX, double yPosition) {
      super(startX, startX, 1);
      currentY = yPosition;   
      rotation = calculateRotation(startX, currentY, startX + getXStepSize(), currentY);      
   }
   
   public boolean calculateNextStep() {
      boolean moreSteps = true;
      return moreSteps;
   }

}
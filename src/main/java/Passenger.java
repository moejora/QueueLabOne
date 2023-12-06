
import java.util.Random;

public class Passenger {
    

    private int passengerId;
    private int processingTime;
    private int arrivalTime;
    private static int maxProcessingTime;
    private static int idNum = 0;
  
    public Passenger(int arrivalTime) {  
        Random num = new Random();
        this.arrivalTime = arrivalTime;
        processingTime = 1 + num.nextInt(maxProcessingTime);
        passengerId = idNum++;
    }

    
    public int getArrivalTime() {
        return arrivalTime;
    }
   
    public int getProcessingTime() {
        return processingTime;
    }

    public int getId() {
        return passengerId;
    }

    public static void setMaxProcessingTime(int maxProcessTime){
    maxProcessingTime = maxProcessTime;
    }
}


import java.util.Scanner;

public class AirlineCheckinSim {

    private PassengerQueue frequentFlyerQueue = new PassengerQueue("Frequent Flyer");
    private PassengerQueue regularPassengerQueue = new PassengerQueue("Regular Passenger");
    private int frequentFlyerMax;
    private int maxProcessingTime;
    private int totalTime;
    private boolean showAll;
    private int clock = 0;
    private int timeDone;
    private int frequentFlyersSinceRP;

   void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
           frequentFlyerQueue.checkNewArrival(clock, showAll);
           regularPassengerQueue.checkNewArrival(clock, showAll);
          
            if (clock >= timeDone) {
                startServe();
            }
        }
    }
   
    public void enterData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Expected number of frequent flyer arrivals per hour: ");
        frequentFlyerQueue.setArrivalRate(scanner.nextDouble());

        System.out.println("Expected number of regular passenger arrivals per hour: ");
        regularPassengerQueue.setArrivalRate(scanner.nextDouble());

        System.out.println("Max amount of frequent flyers served between regular passemgers: ");
        frequentFlyerMax = scanner.nextInt();
        
        System.out.println("max service time in minutes?");
        maxProcessingTime = scanner.nextInt();
        Passenger.setMaxProcessingTime(maxProcessingTime);

        
        System.out.println("The total time in minutes: ");
        totalTime = scanner.nextInt();

        System.out.println(" True to print outputs or false to not");
        showAll = scanner.nextBoolean();
        scanner.close();
    }

    private void startServe() {
        if (!frequentFlyerQueue.isEmpty() && ((frequentFlyersSinceRP <= frequentFlyerMax) || regularPassengerQueue.isEmpty())) {
            frequentFlyersSinceRP++;
            timeDone = frequentFlyerQueue.update(clock, showAll);
        } else if (!regularPassengerQueue.isEmpty()) {
            frequentFlyersSinceRP = 0;
            timeDone = regularPassengerQueue.update(clock, showAll);
        } else if (showAll) {
            System.out.println("Time is " + clock + ": Server is idle");
        }
    }

    void showStats() {
        System.out.println("\nThe number of regular passengers served was " + regularPassengerQueue.getNumServed());
        double averageWaitingTime = (double) regularPassengerQueue.getTotalWait() / (double) regularPassengerQueue.getNumServed();
        System.out.println(" with an average waiting time of " + averageWaitingTime);

        System.out.println("The number of frequent flyers served was " + frequentFlyerQueue.getNumServed());
        averageWaitingTime = (double) frequentFlyerQueue.getTotalWait() / (double) frequentFlyerQueue.getNumServed();
        System.out.println(" with an average waiting time of " + averageWaitingTime);

        System.out.println("Passengers in frequent flyer queue: " + frequentFlyerQueue.size());
        System.out.println("Passengers in regular passenger queue: " + regularPassengerQueue.size());
    }
}

import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.Scanner;
/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell and azlan
 * @version 1.1
 */
public class Race
{
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    private int tracks;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }

    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        System.out.println("enter track lengths");
        Scanner sc = new Scanner(System.in);
        String trackLength_string = sc.nextLine();
        int trackLengthInt = Integer.parseInt(trackLength_string);
        while(trackLengthInt>150||trackLengthInt<5){
            System.out.println("length wont fit in screen");
            trackLength_string = sc.nextLine();
            trackLengthInt = Integer.parseInt(trackLength_string);
        }
        this.raceLength = trackLengthInt;

        char Symbol_h1;
        String name_h1 = "null";
        char Symbol_h2;
        String name_h2 = "null";
        char Symbol_h3;
        String name_h3 = "null";
        System.out.println("Unicode for symbols? (y/n):");
        String unicode_inp = sc.nextLine();
        while (!unicode_inp.equals("y") && !unicode_inp.equals("n")) {
            System.out.println("Please enter 'y' or 'n':");
            unicode_inp = sc.nextLine();
        }
        if(unicode_inp.equals("y")){
          System.out.println("Enter symbol for your first horse: in unicode");
          try{String char_inp_1 = sc.nextLine();
             Symbol_h1 = (char) Integer.parseInt(char_inp_1, 16);}
          catch(Exception e){
            System.out.println("invalid unicode value please just enter a charecter");
            Symbol_h1 = sc.nextLine().charAt(0);
          }

          System.out.println("Enter name for your first horse:");
          name_h1 = sc.nextLine();
          System.out.println("Enter symbol for your second horse: in unicode");
          try{String char_inp_2 = sc.nextLine();
             Symbol_h2 = (char) Integer.parseInt(char_inp_2 , 16);}
          catch(Exception e){
            System.out.println("invalid unicode value please just enter a charecter");
            Symbol_h2 = sc.nextLine().charAt(0);
          }
          System.out.println("Enter name for your second horse:");
          name_h2 = sc.nextLine();
          System.out.println("Enter symbol for your third horse: in unicode");
          try{String char_inp_3 = sc.nextLine();
             Symbol_h3 = (char) Integer.parseInt(char_inp_3 , 16);}
          catch(Exception e){
            System.out.println("invalid unicode value please just enter a charecter");
            Symbol_h3 = sc.nextLine().charAt(0);
          }
          System.out.println("Enter name for your third horse:");
          name_h3 = sc.nextLine();
          Horse horse1 = new Horse(Symbol_h1, name_h1, 0.8);
          Horse horse2 = new Horse(Symbol_h2, name_h2, 0.7);
          Horse horse3 = new Horse(Symbol_h3, name_h3, 0.7);
          addHorse(horse1, 1);
          addHorse(horse2, 2);
          addHorse(horse3, 3);
        }
        else{
          System.out.println("Enter symbol for your first horse: in unicode");
          Symbol_h1 = sc.nextLine().charAt(0);
          System.out.println("Enter name for your first horse:");
          name_h1 = sc.nextLine();
          System.out.println("Enter symbol for your second horse:");
          Symbol_h2 = sc.nextLine().charAt(0);
          System.out.println("Enter name for your second horse:");
          name_h2 = sc.nextLine();
          System.out.println("Enter symbol for your third horse:");
          Symbol_h3 = sc.nextLine().charAt(0);
          System.out.println("Enter name for your third horse:");
          name_h3 = sc.nextLine();
          Horse horse1 = new Horse(Symbol_h1, name_h1, 0.8);
          Horse horse2 = new Horse(Symbol_h2, name_h2, 0.7);
          Horse horse3 = new Horse(Symbol_h3, name_h3, 0.7);
          addHorse(horse1, 1);
          addHorse(horse2, 2);
          addHorse(horse3, 3);
        }
        //reset all the lanes (all horses not fallen and back to 0). 
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();
        while (!finished)
        {
            //move each horse
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);

            //print the race positions
            printRace();

            //if any of the three horses has won the race is finished
            if ( raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse) )
            {
                finished = true;
            }
            if(lane1Horse.hasFallen()&&lane2Horse.hasFallen()&&lane3Horse.hasFallen()){//this was added by me to check if all the hourses have fallen
                finished = true;
                System.out.println("all horses have fallen");
            }
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){
                System.out.println("exception");
            }
        }
        if (raceWonBy(lane1Horse)){
            System.out.println(lane1Horse.getName());
                }
        else if(raceWonBy(lane2Horse)){
            System.out.println(lane2Horse.getName());
        }
        else if(raceWonBy(lane3Horse)){
            System.out.println(lane3Horse.getName());
        }}

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen

        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }

            //the probability that the horse will fall is very small (max is 0.01)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.01*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }

    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window

        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        printLane(lane1Horse);
        System.out.println(lane1Horse.getName() + "(Current confidence " + lane1Horse.getConfidence() + ")");

        printLane(lane2Horse);
        System.out.println(lane2Horse.getName() + "(Current confidence " + lane2Horse.getConfidence() + ")");

        printLane(lane3Horse);
        System.out.println(lane3Horse.getName() + "(Current confidence " + lane3Horse.getConfidence() + ")");

        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }

    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        //print a | for the beginning of the lane
        System.out.print('|');

        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);

        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }

        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);

        //print the | for the end of the track
        System.out.print('|');
    }


    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

}
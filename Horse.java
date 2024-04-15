
/**
 * template to create a hourse object
 * 
 * @author azlan
 * @version 1
 */
public class Horse
{
    //Fields of class Horse
    String horseName;
    char horseSymbol;
    int distance_travelled;
    boolean fallen;
    double horseConfidence;
    String breed;
    String coat_colour;


    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
      this.horseSymbol = horseSymbol;
      this.horseName = horseName;
      this.horseConfidence = horseConfidence;
    }



    //Other methods of class Horse
    public void fall()
    {
      this.fallen = true;
    }
    public void setbreed(String breed)
    {
      this.breed = breed;
    }
    public void setcoatcolour(String coatcolour)
    {
      this.coat_colour = coatcolour;
    }
    public String getbreed()
    {
      return this.breed;
    }
    public String getcoatcolour()
    {
      return this.coat_colour;
    }

    public double getConfidence()
    {
      return horseConfidence;
    }

    public int getDistanceTravelled()
    {
      return distance_travelled;
    }

    public String getName()
    {
      return horseName;
    }

    public char getSymbol()
    {
      return horseSymbol;
    }

    public void goBackToStart()
    {
      this.distance_travelled = 0;
    }

    public boolean hasFallen()
    {
      return fallen;
    }

    public void moveForward()
    {
      this.distance_travelled += 1;
    }

    public void setConfidence(double newConfidence)
    {
      this.horseConfidence = newConfidence;
    }

    public void setSymbol(char newSymbol)
    {
      this.horseSymbol = newSymbol;
    }

}












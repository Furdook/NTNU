package eksamen.shared;

public class Luggage {

    // Declare private fields here
    private final int weight; // The weight of the luggage in kilograms
  
    /**
     * Constructs a Luggage object with a given passenger and weight.
     * @param weight The weight of the luggage in kilograms as an integer.
     */
    public Luggage(int weight) {
      this.weight = weight;
    }
  
    /**
     * Returns the weight of the luggage in kilograms.
     * @return The weight of the luggage in kilograms as an integer.
     */
    public int getWeight() {
      return weight;
    }
  
    // Write other methods here
  
    /**
     * Returns a string representation of the luggage.
     * @return A string representation of the luggage that contains the passenger and the weight.
     */
    public String toString() {
      return "Luggage{" +
             ", weight=" + weight +
             '}';

    }
}

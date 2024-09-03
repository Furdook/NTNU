package eksamen.shared;

public interface IAircraft  extends Comparable<IAircraft>{
    // Returns the type of the aircraft
    public String getType();
  
    // Returns the number of seats in the aircraft
    public int getSeats();
  
    // Returns the emission of the aircraft in kg of CO2 per mile
    public double getEmission();

    public double getFuelLevel();
  
    // Updates the emission and fuel level of the aircraft after a flight
    public void flightCompleted(double fuelUsed);
  
    // Refills the fuel tank of the aircraft to its maximum capacity
    public void refillAircraft();
  
    // Compares this aircraft with another one based on their emission per seat
    public int compareTo(IAircraft other);
  
    // Returns the emission per seat of the aircraft in kg of CO2 per km per seat
    public double getEmissionPerSeat();
  
    // Returns a string representation of the aircraft
    public String toString();
  }
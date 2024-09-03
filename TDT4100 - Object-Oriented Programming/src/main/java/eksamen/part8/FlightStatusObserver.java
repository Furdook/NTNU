package eksamen.part8;


public interface FlightStatusObserver {
    void updateFlightStatus(String flightNumber, String status);
}
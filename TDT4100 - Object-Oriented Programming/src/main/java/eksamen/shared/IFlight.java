package eksamen.shared;

import java.time.LocalDateTime;
import java.util.List;

public interface IFlight {
    // returns the flight number of this flight
    public String getFlightNumber();

    // returns the origin of this flight
    public String getOrigin();

    // returns the destination of this flight
    public String getDestination();

    // returns the duration of this flight in minutes
    public int getDuration();

    // returns the miles of this flight
    public int getMiles();

    // returns the carbon offset of this flight in kilograms
    public double getCarbonOffset();

    public IAircraft getAircraft();

    // returns the list of bookings for this flight
    public List<IBooking> getBookings();

    // returns the time of departure of this flight
    public LocalDateTime getTimeOfDeparture();

    // returns the maximum number of seats for this flight
    public int getMaxSeats();

    public int getNumberOfBookedSeats();

    // adds a booking to this flight if there are available seats
    public void addBooking(IBooking booking);

    // returns true if this flight has available seats, false otherwise
    public boolean hasAvailableSeats();

    // returns true if this flight has the same origin and destination as another
    // flight, false otherwise
    public boolean hasSameRoute(IFlight other);

    // updates the aircraft for this flight if it is compatible or if forceChange is
    // true
    public void updateAircraft(IAircraft aircraft, boolean forceChange);

    // Checks if the flight is overbooked, i.e. there are more bookings than seats
    public boolean isOverbooked();
}
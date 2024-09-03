package eksamen.shared;

public interface IBooking extends Iterable<IFlight> {
    // Returns the passenger associated with this booking
    public Passenger getPassenger();

    // Returns the booking class associated with this booking
    public String getBookingClass();

    // Sets the booking class associated with this booking
    public void setBookingClass(String bookingClass);

    // Returns the price of this booking
    public int getPrice();

    // Gets the flight at the given index in the booking
    public IFlight getFlight(int index);

    // Gets the number of flights in the booking
    public int getNumberOfFlights();

    // Returns true if the booking is cancelled, false otherwise
    public boolean isCancelled();

    // Cancels the booking and sets the isCancelled field to true
    public void cancelBooking();
}
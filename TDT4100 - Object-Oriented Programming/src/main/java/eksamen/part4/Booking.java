package eksamen.part4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import eksamen.part1.Aircraft;
import eksamen.part3.Flight;
import eksamen.shared.BookingClasses;
import eksamen.shared.IBooking;
import eksamen.shared.IFlight;
import eksamen.shared.Passenger;

public class Booking implements IBooking {

    private Passenger passenger;
    private int price;
    private String bookingClass;
    private List<IFlight> bookedFlights = new ArrayList<>();
    private boolean cancelled = false;

    /**
     * Constructs a Booking object with a passenger, a list of flights, and a
     * booking class. The booking class needs to be a valid booking class.
     * You do not need to validate the flights in any way.
     * 
     * @param passenger    The passenger associated with this booking.
     * @param flights      The list of flights associated with this booking.
     * @param bookingClass The booking class associated with this booking.
     * @param price        The price associated with this booking.
     */
    public Booking(Passenger passenger, List<IFlight> flights, String bookingClass, int price) {
        this.price = price;
        this.passenger = passenger;
        this.setBookingClass(bookingClass);
        this.bookedFlights = flights;
    }

    /**
     * Returns the passenger associated with this booking.
     *
     * @return The passenger associated with this booking.
     */
    public Passenger getPassenger() {
        return this.passenger;
    }

    /**
     * Returns the booking class associated with this booking.
     *
     * @return The booking class associated with this booking.
     */
    public String getBookingClass() {
        return this.bookingClass;
    }

    /**
     * Returns the price associated with this booking.
     *
     * @return The price associated with this booking.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the booking class associated with this booking.See shared/BookingClasses
     * for
     * helper String constants.
     *
     * @param bookingClass The new booking class to set for this booking.
     * @throws IllegalArgumentException if the booking class is invalid
     */
    public void setBookingClass(String bookingClass) {
        if (!BookingClasses.getValidBookingClasses().contains(bookingClass)) throw new IllegalArgumentException();
        this.bookingClass = bookingClass;
    }

    /**
     * Gets the flight at the given index in the booking.
     * 
     * @param index the index of the flight to get
     * @return the flight at the index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public IFlight getFlight(int index) {
        return this.bookedFlights.get(index);
    }

    /**
     * Gets the number of flights in this booking
     * 
     * @return the number of flights in this booking
     */
    public int getNumberOfFlights() {
        return this.bookedFlights.size();
    }

    /**
     * @return true if the booking is cancelled, false otherwise
     *         A newly created booking is never cancelled
     */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Cancels the booking
     */
    public void cancelBooking() {
        this.cancelled = true;

    }

    /**
     * Calculates the EU 261 compensation for the booking based on flight distances
     * in miles. You should use the maximum flight distance in the booking.
     * The method should return 0 if the booking is not cancelled.
     * If the booking is cancelled, calculate the compensation based on the standard
     * EU 261 rates
     * You may ignore the destination and origin of any flights when calculating
     * this.
     *
     * The compensation amounts are determined based on the flight distance in miles
     * 1. 250 for flights up to 930 miles
     * 2. 400 for flights between 930 miles and 2,175 miles (3,500 km)
     * 3. 600 for flights over 2,175 miles.
     *
     * 
     * @return The EU 261 compensation for the given booking in USD.
     */
    public int calculateEu261Compensation() {
        IFlight placeholder = this.bookedFlights.get(0);
        for (IFlight flight : this.bookedFlights) {
            if (placeholder.getMiles() < flight.getMiles()){
                placeholder = flight;
            }
        }
        int miles = placeholder.getMiles();
        if (miles < 930) return 250;
        else if (miles < 2175) return 400;
        else return 600;
    }

    /**
     * Create an iterator that iterates over the {@link IFlight flights} in this
     * booking
     * 
     * @return An iterator to iterate over the flights in this booking
     */
    @Override
    public Iterator<IFlight> iterator() {
        return this.bookedFlights.iterator();
    }

    /**
     * Returns a string representation of the booking object, which includes
     * the passenger, booking class, price, and whether the booking is cancelled or
     * not.
     * 
     * @return A string representation of the booking object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Booking Details:\n");
        sb.append("Passenger: ").append(getPassenger()).append("\n");
        sb.append("Booking Class: ").append(getBookingClass()).append("\n");
        sb.append("Price: $").append(getPrice()).append("\n");
        sb.append("Booking Status: ").append(isCancelled() ? "Cancelled" : "Active");

        return sb.toString();
    }

    public static void main(String[] args) {
        // Create some passenger objects
        Passenger p1 = new Passenger("Alice", "alice@example.com");

        // Create some aircraft objects
        Aircraft a1 = new Aircraft("Boeing 747", 400, 0.05, 200000);

        // Create some flight objects
        Flight f1 = new Flight("London", "New York", "BA001", a1, 420, 5567, LocalDateTime.of(2024, 3, 26, 12, 0));
        Flight f2 = new Flight("New York", "London", "BA002", a1, 400, 5567, LocalDateTime.of(2024, 3, 27, 15, 0));

        // Create some booking objects
        Booking b1 = new Booking(p1, Arrays.asList(f1, f2), BookingClasses.ECONOMY, 1000);

        // Test some methods of the booking class and print out the results
        System.out.println(b1.getPassenger()); // Alice (alice@example.com)
        System.out.println(b1.getBookingClass()); // Economy
        System.out.println(b1.getPrice()); // 1000
        System.out.println(b1.getFlight(0)); // BA001 (London -> New York)
        System.out.println(b1.isCancelled()); // false

        b1.setBookingClass(BookingClasses.BUSINESS);

        System.out.println(b1.getBookingClass());

        b1.cancelBooking();
        System.out.println(b1.calculateEu261Compensation()); // 600;

    }
}
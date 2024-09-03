package eksamen.part3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eksamen.part1.Aircraft;
import eksamen.part4.Booking;
import eksamen.shared.BookingClasses;
import eksamen.shared.IAircraft;
import eksamen.shared.IBooking;
import eksamen.shared.IFlight;
import eksamen.shared.Passenger;

/**
 * Represents a flight with a flight number, origin, destination, duration, and
 * miles.
 */
public class Flight implements IFlight {
    private final String origin;
    private final String destination;
    private final String flightNumber;
    private IAircraft aircraft;
    private final int duration;
    private final int miles;
    private final LocalDateTime timeOfDeparture; // The time of departure for this flight
    private List<IBooking> bookings = new ArrayList<>();

    /**
     * Constructs a Flight object with an origin, a destination, a flight number, an
     * aircraft type,
     * and a maximum number of seats available on the flight.
     *
     * @param origin          The origin airport code for this flight.
     * @param destination     The destination airport code for this flight.
     * @param flightNumber    The flight number for this flight.
     * @param aircraft        The aircraft type for this flight.
     * @param duration        The duration of this flight in minutes.
     * @param miles           The distance of this flight, in miles.
     * @param timeOfDeparture The time of departure for this flight.
     * 
     */
    public Flight(String origin, String destination, String flightNumber, IAircraft aircraft, int duration, int miles,
            LocalDateTime timeOfDeparture) {
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.duration = duration;
        this.miles = miles;
        this.timeOfDeparture = timeOfDeparture;
    }

    /**
     * Returns the flight number of this Flight object.
     *
     * @return the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns the origin airport code of this Flight object.
     *
     * @return the origin airport code
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Returns the destination airport code of this Flight object.
     *
     * @return the destination airport code
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the duration of this Flight object in minutes.
     *
     * @return the duration in minutes
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the distance of this Flight object in miles.
     *
     * @return the distance in miles
     */
    public int getMiles() {
        return miles;
    }

    /**
     * Returns the time of departure for this flight.
     * 
     * @return the time of departure for this flight
     */
    public LocalDateTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    /**
     * Returns the aircraft for this flight
     * 
     * @return the aircraft used for this flight
     */
    public IAircraft getAircraft() {
        return aircraft;
    }

    /**
     * Calculates and returns carbon offset for this Flight object based on its
     * distance and aircraft
     * 
     * @return carbon offset for this Flight object based on its distance
     * 
     */
    public double getCarbonOffset() {
        return this.getMiles()*this.getAircraft().getEmission();
    }

    /**
     * Returns the maximum number of seats available on this flight.
     *
     * @return The maximum number of seats available on this flight.
     */
    public int getMaxSeats() {
        return this.getAircraft().getSeats();
    }

    /**
     * Returns the current number of booked seats on this flight.
     * Cancelled bookings are not counted as used seats , i.e. if there are 24
     * booking and 4 of them are
     * cancelled, only 20 seats are used.
     * 
     * @see Booking#isCancelled()
     */
    public int getNumberOfBookedSeats() {
        return (int) this.getBookings().stream().filter(b -> !b.isCancelled()).count();
    }

    /**
     * Checks if there are available seats on this flight. Cancelled bookings are
     * not counted as used seats, i.e. if there are 24 booking and 4 of them are
     * cancelled, only 20 seats are used.
     * 
     * @return true if the flight has available seats for booking, false otherwise
     * @see Booking#isCancelled()
     */
    public boolean hasAvailableSeats() {
        return this.getMaxSeats() - this.getNumberOfBookedSeats() > 0 ? true : false;
    }

    /**
     * Adds a booking to the list of bookings made to this flight. Throws an
     * IllegalStateException if there are no more seats available.
     * 
     * @param booking The booking to add to the list of bookings made to this plane.
     * 
     * @throws IllegalStateException if there are no more seats
     *                               available
     */
    public void addBooking(IBooking booking) {
        if (!this.hasAvailableSeats()) throw new IllegalStateException();
        this.bookings.add(booking);
    }

    /**
     * Returns the list of bookings made to this flight.
     *
     * @return The list of bookings made to this flight.
     */
    public List<IBooking> getBookings() {
        return this.bookings;
    }

    /**
     * @param other the other flight to compare with
     * @return true if the flight has the same origin and destination as the other
     *         flight, false otherwise
     */
    public boolean hasSameRoute(IFlight other) {
        if (this.getOrigin() == other.getOrigin() && this.getDestination() == other.getDestination()) {
            return true;
        }
        return false;
    }

    /**
     * Change the aircraft of this flight.
     * This throws an IllegalArgumentException if the new number of seats is less
     * than the current number of booked seats on the plane (i.e., if there are more
     * booked seats than there
     * are new seats on the aircraft) and forceChange parameter is false
     *
     * @param aircraft    The new aircraft to use for this flight
     * @param forceChange Whether to force through the change regardless of it if
     *                    means the number of bookings will be higher than the
     *                    number of available seats
     * @throws IllegalArgumentException if the new aircraft seats
     *                                  is less than the number of bookings and
     *                                  parameter forceChange
     *                                  is false
     */
    public void updateAircraft(IAircraft aircraft, boolean forceChange) {
        if (!forceChange && aircraft.getSeats() < this.getNumberOfBookedSeats()) throw new IllegalArgumentException();
        this.aircraft = aircraft;
    }

    /**
     * Returns whether the flight is currently overbooked. Cancelled bookings are
     * not counted as used seats, i.e. if there are 24 booking and 4 of them are
     * cancelled, only 20 seats are used.
     * 
     * @return true if the number of bookings exceeds the capacity of the flight,
     *         false otherwise
     * @see Booking#isCancelled()
     */
    public boolean isOverbooked() {
        return this.getMaxSeats() < this.getNumberOfBookedSeats();
    }

    @Override // for code validation in Booking.java
    public String toString(){
        return this.flightNumber + " (" + this.getOrigin() + " -> " + this.getDestination() +  ")";
    }

    public static void main(String[] args) {
        // create some aircrafts
        Aircraft a1 = new Aircraft("Boeing 747", 400, 0.1, 1000);
        Aircraft a2 = new Aircraft("Airbus A380", 500, 0.08, 1200);
        Aircraft a3 = new Aircraft("Airbus A320", 1, 0.075, 800);

        // create some flights
        Flight f1 = new Flight("New York", "Los Angeles", "AA123", a1, 300, 2500,
                LocalDateTime.of(2023, 3, 26, 12, 30));
        Flight f2 = new Flight("London", "Paris", "BA456", a2, 90, 200, LocalDateTime.of(2023, 3, 26, 14, 00));

        // create some passengers
        Passenger p1 = new Passenger("Alice", "alice@gmail.com");
        Passenger p2 = new Passenger("Bob", "bob@yahoo.com");
        Passenger p3 = new Passenger("Charlie", "charlie@hotmail.com");

        // create some bookings
        Booking b1 = new Booking(p1, Arrays.asList(f1), BookingClasses.ECONOMY, 500);
        Booking b2 = new Booking(p2, Arrays.asList(f1), BookingClasses.BUSINESS, 800);
        Booking b3 = new Booking(p3, Arrays.asList(f2), BookingClasses.ECONOMY, 200);

        // add some bookings to the flights
        f1.addBooking(b1);
        f1.addBooking(b2);
        f2.addBooking(b3);

        // print some information about the flights
        System.out.println("Flight number: " + f1.getFlightNumber());
        System.out.println("Origin: " + f1.getOrigin());
        System.out.println("Destination: " + f1.getDestination());
        System.out.println("Duration: " + f1.getDuration() + " minutes");
        System.out.println("Miles: " + f1.getMiles());
        System.out.println("Carbon offset: " + f1.getCarbonOffset() + " kg");
        System.out.println("Time of departure: " + f1.getTimeOfDeparture());
        System.out.println("Max seats: " + f1.getMaxSeats());
        System.out.println("Bookings: " + f1.getBookings());
        System.out.println("Available seats: " + f1.hasAvailableSeats());

        // Test updateAircraft: should throw IllegalArgumentException
        try {
            System.out.println("Attempting to update aircraft to a4 without forcing change...");
            f1.updateAircraft(a3, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Test updateAircraft: should cause flight to be overbooked
        try {
            System.out.println("Attempting to update aircraft to a4 with forced change...");
            f1.updateAircraft(a3, true);
            System.out.println("Aircraft successfully updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Check if flight 1 is overbooked after updating the aircraft
        System.out.println("Flight 1 is overbooked after updating aircraft: " + f1.isOverbooked());
    }
}
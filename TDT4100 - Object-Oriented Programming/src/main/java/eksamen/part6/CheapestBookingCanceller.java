package eksamen.part6;

import java.util.List;

import eksamen.part1.Aircraft;
import eksamen.part3.Flight;
import eksamen.part4.Booking;
import eksamen.shared.BookingCanceller;
import eksamen.shared.BookingClasses;
import eksamen.shared.IBooking;
import eksamen.shared.Passenger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

// A class that implements the booking canceller interface
// This class cancels the bookings that have the lowest price
public class CheapestBookingCanceller implements BookingCanceller {

  /**
   * Cancel the number of bookings with the lowest price
   * @param bookings the list of bookings to cancel from
   * @param numberToCancel the number of bookings to cancel, can be assumed to be larger than 0
   */
  public void cancelBookings(List<IBooking> bookings, int numberToCancel) {
    bookings.sort((a, b) -> a.getPrice() - b.getPrice());

    for (int i = 0; i < numberToCancel; i++) {
      bookings.get(i).cancelBooking();
    }
  }

  public static void main(String[] args) {
    // Create some passenger objects
    Passenger p1 = new Passenger("Alice", "alice@example.com");

    // Create some aircraft objects
    Aircraft a1 = new Aircraft("Boeing 747", 400, 0.05, 200000);
    Aircraft a2 = new Aircraft("Airbus A320", 200, 0.04, 100000);

    // Create some flight objects
    Flight f1 = new Flight("London", "New York", "BA001", a1, 420, 5567, LocalDateTime.of(2024, 3, 26, 12, 0));
    Flight f2 = new Flight("New York", "London", "BA002", a1, 400, 5567, LocalDateTime.of(2024, 3, 27, 15, 0));
    Flight f3 = new Flight("Paris", "Berlin", "AF001", a2, 90, 878, LocalDateTime.of(2024, 3, 28, 10, 0));

    // Create some booking objects
    Booking b1 = new Booking(p1, Arrays.asList(f1), BookingClasses.ECONOMY, 1000);
    Booking b2 = new Booking(p1, Arrays.asList(f2), BookingClasses.ECONOMY, 500);
    Booking b3 = new Booking(p1, Arrays.asList(f3), BookingClasses.ECONOMY, 750);
    Booking b4 = new Booking(p1, Arrays.asList(f1, f2), BookingClasses.ECONOMY, 2000);

    // Add the sample bookings to a list
    List<IBooking> bookings = new ArrayList<>();
    bookings.add(b1);
    bookings.add(b2);
    bookings.add(b3);
    bookings.add(b4);

    // Print the initial list of bookings
    System.out.println("Initial bookings:");
    for (IBooking b : bookings) {
      System.out.println("Booking with price: " + b.getPrice() + " and cancelled status: " + b.isCancelled());
    }

    // Create a CheapestBookingCanceller object
    CheapestBookingCanceller canceller = new CheapestBookingCanceller();

    // Cancel 2 cheapest bookings
    canceller.cancelBookings(bookings, 2);

    // Print the list of bookings after cancelling the 2 cheapest bookings
    System.out.println("\nBookings after cancelling 2 cheapest:");
    for (IBooking b : bookings) {
      System.out.println("Booking with price: " + b.getPrice() + " and cancelled status: " + b.isCancelled());
    }
  }

}
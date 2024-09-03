package eksamen.part5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Collectors;

import eksamen.part1.Aircraft;
import eksamen.part3.Flight;
import eksamen.part4.Booking;
import eksamen.shared.BookingClasses;
import eksamen.shared.IBooking;
import eksamen.shared.IFlight;
import eksamen.shared.Passenger;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.time.LocalDateTime;

public class BoardingPassService {
    /**
     * Writes the boarding pass for a booking to an OutputStream in the following
     * format:
     * Name of the passenger
     * Booking Class
     * For each flight in the booking:
     * Origin - Destination - Duration
     * 
     * See boarding_pass.txt in this package for an example.
     *
     * @param booking      The booking for which to write the boarding pass.
     * @param outputStream The OutputStream to which to write the boarding pass.
     */
    public static void printBoardingPass(IBooking booking, OutputStream outputStream) throws IOException {
        PrintWriter tmp = new PrintWriter(outputStream);
        Iterator<IFlight> flights = booking.iterator();
        String flightString = "";  
        while (flights.hasNext()) {
            IFlight flight = flights.next();
            flightString += flight.getOrigin() + " - " + flight.getDestination() + " - " + flight.getDuration() + "\n";
        }
        tmp.write(booking.getPassenger().getName() + "\n" + booking.getBookingClass() + "\n" + flightString);
        tmp.flush();
        tmp.close();
    }

    /**
     * Scans a boarding pass for a flight from an InputStream and throws an
     * IllegalArgumentException if there does not exist
     * a passenger with that name and booking class on the given input flight. 
     *
     * @param flight      The flight for which to scan the boarding pass.
     * @param inputStream The InputStream from which to scan the boarding pass.
     * 
     * @throws IllegalArgumentException If there does not exist a passenger with
     *                                  that name and booking class on the given
     *                                  flight.
     */
    public static void scanBoardingPass(IFlight flight, InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);
        String name = "";
        name = scanner.nextLine();
        scanner.close();
        if (!flight.getBookings().stream().map(b -> b.getPassenger().getName()).collect(Collectors.toList()).contains(name)) throw new IllegalArgumentException();

        
    }

    public static void main(String[] args) {
        // Create sample data for testing
        Passenger passenger = new Passenger("John Doe", "john.doe@example.com");
        Aircraft aircraft = new Aircraft("Boeing 747", 400, 0.05, 200000);
        Flight flight = new Flight("London", "New York", "BA001", aircraft, 420, 5567,
                LocalDateTime.of(2024, 3, 26, 12, 0));
        Booking booking = new Booking(passenger, Collections.singletonList(flight), BookingClasses.ECONOMY, 1000);

        // Add the booking to the flight, commentint out this line should yield an
        // IllegalArgumentException below when reading
        flight.addBooking(booking);

        String basePath = "src/main/java";

        /** Comment this next section out if you just want to test loading */
        String packageName = BoardingPassService.class.getPackageName().replace('.', '/');

        // Write the boarding pass to a text file, change the filename here to test with the provided example
        String fileName = basePath + "/" + packageName + "/my_boarding_pass.txt";
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            printBoardingPass(booking, outputStream);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        String fileName2 = basePath + "/" + packageName + "/boarding_pass.txt";
        // Read the boarding pass from the text file and scan it. If you just want to test with the provided example, change the filename to "/boarding_pass.txt" above
        try (FileInputStream inputStream = new FileInputStream(fileName2)) {
            scanBoardingPass(flight, inputStream);
            System.out.println("Boarding pass scanned successfully!");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error scanning boarding pass: " + e.getMessage());
        }

        // Deletes the text file, uncomment this to check how the text file ends up.
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Error deleting the file: " + e.getMessage());
        } 
    }
}
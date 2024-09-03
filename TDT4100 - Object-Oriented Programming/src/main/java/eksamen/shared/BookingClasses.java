package eksamen.shared;

import java.util.Arrays;
import java.util.List;

// DO NOT MODIFY THIS CLASS
public class BookingClasses {
    // Helper String utils to ensure that the booking class is valid. 
    public static final String BUSINESS = "BUSINESS CLASS";
    public static final String FIRST_CLASS = "FIRST CLASS";
    public static final String ECONOMY = "ECONOMY";
    public static final String PREMIUM_ECONOMY = "PREMIUM ECONOMY";

    public static List<String> getValidBookingClasses() {
        return Arrays.asList(BUSINESS, FIRST_CLASS, ECONOMY, PREMIUM_ECONOMY);
    }
}

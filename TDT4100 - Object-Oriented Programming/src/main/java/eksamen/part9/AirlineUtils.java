package eksamen.part9;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import eksamen.part1.Aircraft;
import eksamen.part3.Flight;
import eksamen.shared.IFlight;
import eksamen.shared.TimeUtils;

public class AirlineUtils {

    /**
     * Returns a list of flights that satisfy the given predicate.
     *
     * @param flights   the list of flights to filter
     * @param predicate the predicate to filter flights by
     * @return a list of flights that satisfy the given predicate
     */
    public static List<IFlight> getFlightsByPredicate(List<IFlight> flights, Predicate<IFlight> predicate) {
        return flights.stream().filter(predicate).toList();
    }

    /**
     * Returns the busiest airport (the airport with the highest number of arrivals
     * and departures). Returns an arbitrary airport if there is a tie.
     *
     * @param flights the list of flights to analyze
     * @return the busiest airport
     */
    public static String getBusiestAirport(List<IFlight> flights) {
        HashMap<String, Integer> counter = new HashMap<>();

        for (IFlight flight : flights) {
            String airport = flight.getOrigin();
            counter.put(airport, counter.containsKey(airport) ? counter.get(airport) + 1 : 1);

            airport = flight.getDestination();
            counter.put(airport, counter.containsKey(airport) ? counter.get(airport) + 1 : 1);
        }
        List<String> airports = counter.keySet().stream().toList();
        String busiestAirport = airports.get(0);
        for (String airport : airports) {
            if (counter.get(airport) > counter.get(busiestAirport)) {
                busiestAirport = airport;
            }   
        }
        return busiestAirport;
    }

    /**
     * Checks if a list of flights are all connected, meaning each flight departs
     * less than 24 hours after the one before
     * and from the same origin as the previous flight's destination.
     *
     * @param flights the list of flights to check
     * @return true if all flights are connected, false otherwise
     */
    public static boolean isConnectedFlights(List<IFlight> flights) {

        Iterator<IFlight> compare = flights.iterator();
        IFlight firstFlight = compare.next();
        while(compare.hasNext()) {
            IFlight nextFlight = compare.next();
            if(!TimeUtils.isWithin24Hours(firstFlight.getTimeOfDeparture(), nextFlight.getTimeOfDeparture())
                || firstFlight.getDestination() != nextFlight.getOrigin()) return false;

            firstFlight = nextFlight;
        }
        return true;
    }

    public static void main(String[] args) {
        // create some aircraft
        Aircraft a1 = new Aircraft("Boeing 747", 400, 0.1, 1000);
        Aircraft a2 = new Aircraft("Airbus A380", 500, 0.08, 1200);

        // create some flights
        Flight f1 = new Flight("New York", "Los Angeles", "AA123", a1, 300, 2500,
                LocalDateTime.of(2023, 3, 26, 12, 30));
        Flight f2 = new Flight("London", "Paris", "BA456", a2, 90, 200, LocalDateTime.of(2023, 3, 26, 14, 00));
        Flight f3 = new Flight("Los Angeles", "Chicago", "UA789", a1, 250, 1500,
                LocalDateTime.of(2023, 3, 26, 17, 15));
        Flight f4 = new Flight("Paris", "London", "AF111", a2, 90, 200, LocalDateTime.of(2023, 3, 26, 18, 30));

        List<IFlight> flights = Arrays.asList(f1, f2, f3, f4);

        // test findFlightsByOrigin
        List<IFlight> flightsFromNY = AirlineUtils.getFlightsByPredicate(flights,
                f -> f.getOrigin().equals("New York"));
        System.out.println("Flights from New York: " + flightsFromNY);

        // test getBusiestAirport
        String busiestAirport = AirlineUtils.getBusiestAirport(flights);
        System.out.println("Busiest Airport: " + busiestAirport);

        // test isConnectedFlights
        List<IFlight> connectedFlights = Arrays.asList(f1, f3);
        boolean isConnected = AirlineUtils.isConnectedFlights(connectedFlights);
        System.out.println("Is the list of flights connected? " + isConnected);
    }

}

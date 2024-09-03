package project.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {
    Movie movie;
    Theatre theatre;
    Screening screening;
    Tickets ticket;

    @BeforeEach 
    public void setup() throws IOException {
        movie = new Movie("Test Film", 5);
        theatre = new Theatre("Test Theatre", 50);
        screening = new Screening(movie, 0, theatre);
        ticket = new Tickets(movie, screening, 1, true, "John");
    }

    @Test // Test fails if ran with others ??? OK on it's own.
    public void testConstructor() {
        //checks that duplicate tickets throws
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Tickets(movie, screening, 4, true, "John");
        });
        // checks that screening can't be null
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Tickets(movie, null, 2, true, "John");
        });
        // checks that ticket can't contain more than available seating
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Tickets(movie, screening, 200, true, "John");
        });
    }

    @Test
    public void testMethods() throws IOException {
        // checks that setup ticket exists in program
        Assertions.assertFalse(Tickets.checkTicket(screening.toString(), movie.getTitle(), "John"));
        // checks that getScreening returns correctly
        Assertions.assertEquals(ticket.getScreening(), "12:00 - 13:15");
        // checks that getMovie returns correct title
        Assertions.assertEquals(ticket.getMovie(), "Test Film");
        // checks that the correct seats are returned
        List<String> tmp = new ArrayList<>(Arrays.asList("A-1"));
        Assertions.assertEquals(tmp, ticket.getSeats());
        // checks that method returns all tickets
        List<Tickets> tmp2 = new ArrayList<>(Arrays.asList(ticket, new Tickets(movie, screening, 2, true, "Peter")));
        Assertions.assertEquals(tmp2, Tickets.getTickets());
    }
    
}

package project.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScreeningTest {
    Screening test;
    Screening test2;
    Movie movie;
    Movie movie2;
    Theatre theatre;
    Theatre theatre2;

    @BeforeEach
    public void setup() {
        movie = new Movie("The Imitation Game", 8);
        movie2 = new Movie("Ex Machina", 8);
        theatre = new Theatre("Test", 50);
        theatre2 = new Theatre("Test2", 50);
        test = new Screening(movie, 0, theatre);
        test2 = new Screening(movie2, 10, theatre2);
    }
    
    @Test
    public void testConstructor() {
        // Sjekker at ikke screenings som starter før åpning kan godkjennes
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Screening(movie, -1, theatre);
        });
    }

    @Test
    public void testMethods() {
        // testing av metodene for å finne screenings
        Assertions.assertEquals(test, Screening.findScreening(movie, "12:00 - 14:00"));
        Assertions.assertEquals(test2, Screening.findScreening(movie2, "14:30 - 16:30"));
       
        // Sjekker at screenings 'mister' plasser når de blir 'kjøpt'
        test.setSeats(3);
        Assertions.assertEquals(47, test.getSeats().size());
        // checks that the corrects seats are taken in setTaken
        List<String> tmp = new ArrayList<>(Arrays.asList("A-4", "A-5"));
        Assertions.assertEquals(tmp, test.setSeats(2));
        // checks that getMovie return correctly
        Assertions.assertEquals(movie, test.getMovie());
        // Sjekker at dette ikke endrer på andre screenings
        Assertions.assertEquals(50, test2.getSeats().size());
        // checks getTime
        Assertions.assertEquals("12:00 - 14:00", test.getTime());
        // checks getTheatre returns correctly
        Assertions.assertEquals("Test", test.getTheatre());
    }
}

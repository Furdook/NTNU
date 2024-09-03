package project.modules;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {
    Movie test;
    Movie test2;
    Screening screening;

    @BeforeEach
    public void setup() {
        test = new Movie("Shawshank", 8);
        test2 = new Movie("Ex, Machina", 20);
        screening = new Screening(test, 1, new Theatre("Bah", 50));
    }

    @Test
    public void testConstructor() {
        // Sjekker at throws dersom lengden er null
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Movie("Ex Machina", 0);
        });
        // Sjekker at tittel ikke kan være null
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Movie("", 3);
        });
    }

    @Test
    public void testMethods() {
        // Sjekker at getTitle() returnerer riktig tittel
        Assertions.assertEquals("Shawshank", test.getTitle());
        // Sjekker at lengde stemmer
        Assertions.assertEquals(8, test.getLength());
        // Sjekker at getMovie() klarer å finne en gitt film i programmet
        Assertions.assertEquals(test, Movie.getMovie("Shawshank"));
        // Sjekker at den også klarer å finne andre titler
        Assertions.assertEquals(test2, Movie.getMovie("Ex, Machina"));
        // Sjekker at getter for screenings tilhørende filmen fungerer
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(screening)), test.getScreenings());
    }
}



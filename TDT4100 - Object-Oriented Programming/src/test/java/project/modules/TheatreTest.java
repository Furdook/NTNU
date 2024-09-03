package project.modules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TheatreTest {
    static Theatre test;

    @BeforeEach
    public void setup() {
        test = new Theatre("Sal 1", 100);
    }

    @Test
    public void testConstructor() {
        // checks that theatre name can't be null
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Theatre("", 1);
        });
        // checks that theatre can't have negative seating
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Theatre("Name", -1);
        });
    }

    @Test
    public void testMethods() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            test.setTaken(-1, 12);
        });
        // remove 0-12 timeslot
        test.setTaken(0, 12);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // try setting a screening when theatre is busy, should throw
            test.setTaken(0, 8);
        });
        // checks that the right amount og seats are set
        Assertions.assertEquals(100, test.getSeatList().size());
        // checks that getName return correctly
        Assertions.assertEquals(test.getName(), "Sal 1");
        // checks that it can find the correct theatre object
        Assertions.assertEquals(test, Theatre.getTheatre("Sal 1"));
        // checks that theatre is available after the 0-12 screening is finished
        Assertions.assertTrue(test.getAvailability(13, 14));
    }

}

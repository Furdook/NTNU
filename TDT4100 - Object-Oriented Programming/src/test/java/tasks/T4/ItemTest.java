package tasks.T4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tasks.O4.Item;
import tasks.O4.Merchant;

public class ItemTest {

    Item item1;
    Item item2;
    Merchant merchant;

    @BeforeEach
    public void setUp() {
        item1 = new Item("Sunveil Katana", "Weapon", 500);
        item2 = new Item("Carian greaves", "Armour", 100);
        merchant = new Merchant(100);
    }

    @Test
    @DisplayName("Sjekker at konstruktør setter opp objektet riktig")
    void testConstructor() {
        assertEquals(500, item1.getPrice());
        assertNull(item2.getOwner());
    }
    
    @Test
    @DisplayName("Sjekker at prisen endres på riktig vis")
    void testSetPrice() {
        item1.setPrice(100);
        assertEquals(100, item1.getPrice());
        assertThrows(RuntimeException.class, () -> {
			item2.setPrice(-100);
		}, "Skal ikke kunne sette prisen til å være negativ");
    }

    @Test
    @DisplayName("Sjekker at eier endres på riktig vis")
    void testChangeOwner() {
        item1.changeOwner(merchant);
        assertEquals(merchant, item1.getOwner());
    }
}

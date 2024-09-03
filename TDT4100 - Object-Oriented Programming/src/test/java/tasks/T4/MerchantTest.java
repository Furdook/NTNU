package tasks.T4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tasks.O4.Item;
import tasks.O4.Merchant;

public class MerchantTest {
    
    Item item1;
    Item item2;
    Merchant merchant1;
    Merchant merchant2;

    @BeforeEach
    public void setUp() {
        item1 = new Item("Sunveil Katana", "Weapon", 500);
        item2 = new Item("Carian greaves", "Armour", 100);
        merchant1 = new Merchant(100);
        merchant2 = new Merchant(1000);
    }

    @Test
    @DisplayName("Sjekker at konstruktøren setter objektet opp riktig")
    void testConstructor() {
        assertEquals(100, merchant1.getBalance());
        assertThrows(RuntimeException.class, () -> {
			Merchant poorMerchant = new Merchant(-1);
		}, "Skal ikke kunne lage en merchant med negativ balanse");
    }

    @Test
    @DisplayName("Sjekker at selgeren kan finne et objekt og legge det i inventaret sitt")
    void testObtainItem() {
        merchant1.obtainItem(item1);
        assertEquals(1, merchant1.getInventory().size());
    }

    @Test
    @DisplayName("Sjekker at selgeren kan fjerne et objekt fra inventaret sitt")
    void testRemoveItem() {
        merchant1.obtainItem(item1);
        assertEquals(1, merchant1.getInventory().size());
        merchant1.removeItem(item1);
        assertEquals(0, merchant1.getInventory().size());
    }

    @Test
    @DisplayName("Sjekker at en transaksjon virker som den skal")
    void testMakeTransaction() {
        merchant2.obtainItem(item1);
        assertThrows(RuntimeException.class, () -> {
			merchant2.sellItem(item1, merchant2);
		}, "Skal ikke kunne selge til seg selv");
        assertThrows(RuntimeException.class, () -> {
            merchant2.sellItem(item1, merchant1);
		}, "Skal ikke kunne kjøpe et objekt man ikke har råd til");
        assertThrows(RuntimeException.class, () -> {
			merchant2.sellItem(item2, merchant1);
		}, "Skal ikke kunne selge et objekt man ikke eier");
        merchant2.obtainItem(item2);
        merchant2.sellItem(item2, merchant1);
        assertEquals(1, merchant1.getInventory().size());
        assertEquals(merchant1, item2.getOwner());
        assertEquals(0, merchant1.getBalance());
        assertEquals(1100, merchant2.getBalance());
    }

    @Test
    @DisplayName("Sjekker at et salg virker som det skal")
    void testSale() {
        assertThrows(RuntimeException.class, () -> {
			merchant1.itemSale(0.3, item1);
		}, "Skal ikke kunne starte et salg på et objekt selgeren ikke eier");
        merchant1.obtainItem(item1);
        merchant1.obtainItem(item2);
        assertThrows(RuntimeException.class, () -> {
			merchant1.itemSale(1.5, item1);
		}, "Skal ikke kunne selge et objekt med mer enn 100% avslag");
        assertThrows(RuntimeException.class, () -> {
			merchant1.itemSale(-1, item1);
		}, "Skal ikke kunne selge et objekt med mindre enn 0% avslag");
        merchant1.itemSale(0.5, item1);
        assertEquals(250, item1.getPrice());
        merchant1.inventorySale(0.5);
        assertEquals(125, item1.getPrice());
        assertEquals(50, item2.getPrice());
    }
}

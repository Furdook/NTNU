package eksamen.shared;

// DO NOT MODIFY THIS CLASS. 
public class Passenger {
    private final String name;
    private final String contactEmail;

    /**
     * Constructs a Passenger object with a name and a contact email.
     *
     * @param name         The name of the passenger.
     * @param contactEmail The contact email of the passenger.
     */
    public Passenger(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
    }

    /**
     * Returns the name of the passenger.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the contact email of the passenger.
     *
     * @return The contact email of the passenger.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    public String toString() {
        return this.name;
    }
}
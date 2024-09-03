# Del 7 - Flyselskap

I denne delen skal du kombinere elementer fra de forrige delene for å lage en flyselskapsklasse `Airline`.

*Du kan bruke klassen [`TimeUtils`](../shared/TimeUtils.java) hvis det er nødvendig i visse oppgaver relatert til kontroll av tid.*

# Implementer følgende metoder

1. `addFlight(IFlight flight)`: En metode for å legge til en flygning til systemet.
2. `addBooking(IBooking booking)`: En metode for å legge til en bestilling i systemet. Å legge til en bestilling bør sikre at alle flyreiser i bestillingen er tilsvarende oppdatert.
3. `processFlightBookings(IFlight flight, Consumer<IBooking> action)`. En metode for å utføre en handling (beskrevet i *action*) for hver bestilling for den angitte flyreisen.
4. `findReplacementFlights(Flight flight)`: En metode for å finne erstatningsflyvninger for en kansellert flyvning.
5. `changeAircraft(IFlight flight, IAircraft aircraft, BookingCanceller canceller)`: En metode for å endre flyet til en flyvning, og kansellere noen bestillinger hvis flyvningen nå er overbooket.

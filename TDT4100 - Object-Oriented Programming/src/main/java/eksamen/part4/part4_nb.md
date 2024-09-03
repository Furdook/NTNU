# Del 4, Bookingklassen

I denne oppgaven skal du implementere Booking-klassen som representerer en bestilling i et flyselskapssystem. En *booking* representerer en passasjer og potensielt flere flygninger relatert til bestillingen. Bookingklassen bør implementere [IBooking](../shared/IBooking.java)-grensesnittet, som har følgende metoder:

- `getPassenger()`: returnerer passasjeren knyttet til denne bestillingen som et [`Passenger`](../shared/Passenger.java)-objekt. En passasjer er et objekt som inneholder navnet og e-postadressen til passasjeren.
- `getBookingClass()`: returnerer [bookingklassen](../shared/BookingClasses.java) knyttet til denne bestillingen som en streng.
- `getPrice()`: returnerer prisen på denne bestillingen som et heltall.
- I tillegg til metodene i [IBooking](../shared/IBooking.java) er en *toString()*-metode også implementert.

Metodene ovenfor og obligatoriske felt for disse er allerede implementert og kan trygt antas å fungere. Du skal implementere følgende metoder:

- `setBookingClass(String bookingClass)`: setter bookingklassen knyttet til denne bestillingen til den gitte strengen. Strengen skal være en av konstantene definert i [`BookingClasses`](../shared/BookingClasses.java).
- `getFlight(int index)`: får flygningen til den gitte indeksen i bestillingen som et Flight-objekt. Den tar en heltall som en parameter *index*, og returnerer flygningen ved den indeksen i listen over flyvninger. Hvis indeksen er ugyldig utløses en *IndexOutOfBoundsException*"*.
- `getNumberOfFlights()`: returnerer antall flyreiser i bestillingen som et heltall.
- `isCancelled()`: returnerer *true* hvis bestillingen kanselleres, ellers *false*.
- `cancelBooking()`: kansellerer bestillingen.
- `calculateEu261Compensation()`: beregner EU 261-kompensasjonen for den gitte bestillingen basert på den lengste av flygningene, i miles. JavaDocs inneholder detaljene her.
- `iterator()`: returnerer en iterator for flygningene i bestillingen.




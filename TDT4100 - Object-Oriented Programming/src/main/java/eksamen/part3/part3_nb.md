# Del 3, klassen Flight

## NB: Del 3 er tett knyttet mot del 4 [`Booking`](../part4/Booking.java). Du kan bruke eventuelle metodekall der som om del 4 er korrekt implementert mens du implementerer Flight

I denne oppgaven skal du implementere klassen Flight. Denne representerer en flyvning for et flyselskap. En flyvning har et sett med *bookinger* knyttet til seg, samt litt andre metadata. Klassen Flight skal implementere interfacet IFlight, som har følgende metoder:

- `getFlightNumber()`: returnerer flightnummer for denne flyvningen, som en streng.
- `getOrigin()`: returnerer flyvningens avgangssted, som en streng.
- `getDestination()`: returnerer flyvningens destinasjon, som en streng.
- `getDuration()`: returnerer hvor lang tid flyvningen tar i minutter, som et heltall.
- `getMiles()`: returnerer flyvningens lengde i miles, som et heltall.
- `getTimeOfDeparture()`: returnerer flyvningens avgangstidspunkt, som et LocalDateTime-objekt.
- `getAircraft()`: returnerer flyet som brukes for denne flyvningen.

Metodene over og nødvendige felt for implementasjonen er allerede implementert, og du kan gå ut ifra at de fungerer. Du skal implementere følgede endringer:

- `getCarbonOffset()`: returnerer flyvningens karbonfotavtrykk i kilogram, som et flyttall (double). Karbonfotavtrykket finner du ved å multiplisere flyvningens lengde i miles med flyets utslipp per mile.
- `getMaxSeats()`: returnerer maksimalt antall seter tilgjengelig for denne flyvningen som et heltall. Maksimalt antall seter bestemmes av flyet som er tildelt denne flyvningen.
- `getNumberOfBookedSeats()`: returnerer hvor mange som har booket flyvningen.
- `hasAvailableSeats()`: returnerer *true* hvis denne flyvningen har ledige seter, ellers *false*. Et sete er tilgjengelig hvis det ikke er bestilt av noen passasjer. Vi opererer ikke her med bestilling av bestemte plasser (f.eks. 3c), kun antallet bestillinger.
- `addBooking(IBooking booking)`: legger til en bestilling på denne flyvningen hvis det er ledige seter. Hvis det ikke er ledige seter, utløses et unntak.
- `getBookings()`: returnerer listen over bestillinger for denne flyvningen som et `List<IBooking>`-objekt. Antall bestillinger på flyvningen er det samme som antall solgte seter på flyvningen.
- `hasSameRoute(Flight other)`: returnerer *true* hvis denne flyvningen har samme steder for avgang og destinasjon som en annen flyvning, ellers *false*. Den tar et annet flyobjekt som parameter og sammenligner avgang og destinasjon med denne flyreisens avgang og destinasjon.
- `updateAircraft(Aircraft aircraft, boolean forceChange)`: oppdaterer flyet for denne flyvningen hvis det er kompatibelt, eller hvis forceChange er *true*. Et kompatibelt fly er et fly som har nok seter til å ta imot alle bestillinger for denne flyvningen som skal overføres. Hvis forceChange er *true*, oppdaterer det flyet uavhengig av kompatibilitet. Dette kan føre til at flyet blir overbooket.
- `boolean isOverbooked()`: Returnerer om flyet er overbooket.

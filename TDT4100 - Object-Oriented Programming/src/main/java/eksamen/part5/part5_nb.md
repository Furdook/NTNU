# Del 5 - BoardingPassService

I denne delen skal du implementere klassen `BoardingPassService`. Denne klassen er designet for å administrere opprettelse og skanning av boardingkort for passasjerer som har bestillinger på flyreiser.

## Klassestruktur

`BoardingPassService`-klassen skal ha følgende struktur:

- Offentlige metoder:
  - `printBoardingPass(Booking booking, OutputStream outputStream)`: Skriver boardingkortet for en bestilling til en OutputStream som tekst i følgende format:
    - Navnet på passasjeren
    - Bookingklassen
    - For hver flyvning i bestillingen:
      - Origin - Destination - Duration
  - `scanBoardingPass(Flight flight, InputStream inputStream)`: Skanner et boardingkort for en flytur fra en InputStream og utløser et *IllegalArgumentException* hvis det ikke finnes en passasjer med det navnet og samme bookingklassen på den gitte flygningen.

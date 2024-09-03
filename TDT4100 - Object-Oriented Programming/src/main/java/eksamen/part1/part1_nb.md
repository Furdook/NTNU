# Del 1, Aircraft-klassen

I denne oppgaven skal du implementere Aircraft-klassen som representerer et fly i et flyselskapssystem. Denne skal implementere IAircraft-grensesnittet, som har følgende metoder:

- Konstruktører:
  - `Aircraft(String type, int seats, double emission, double fuelCapacity)`: Initialiserer et fly med de gitte verdiene. Flyet skal være fulltanket ved oppstart, det vil si at drivstoffnivået er lik drivstoffkapasiteten.

- Offentlige metoder:
  - `getType()`: returnerer flytypen som en streng, for eksempel "Boeing 747" eller "Airbus A320".
  - `getSeats()`: returnerer antall seter i flyet som et heltall.
  - `getEmission()`: returnerer utslippet til flyet i kg CO2 per mile som et desimaltall.
  - `getFuelLevel()`: returnerer flyets nåværende drivstoffnivå.
  - `flightCompleted(double fuelUsed)`: oppdaterer drivstoffnivået for flyet etter en flytur.
  - `refillAircraft()`: fyller drivstofftanken til flyet til maksimal kapasitet (som bestemmes av flytypen).
  - `compareTo(IAircraft other)`: sammenligner dette flyet med et annet fly basert på antall seter. Den tar et annet IAircraft-objekt som parameter og returnerer et negativt heltall, null eller et positivt heltall hvis dette flyet har færre, like mange eller flere seter enn det andre flyet (other).
  - `getEmissionPerSeat()`: returnerer utslippet per sete for flyet i kg CO2 per mile per sete som et desimaltall. Det beregnes ved å dividere utslippet med antall seter.
  - `toString()`: returnerer en strengrepresentasjon av flyet. Strengen skal inneholde typen, antall seter, utslipp og drivstoffnivået til flyet.

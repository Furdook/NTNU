# Del 9 - klassen AirlineUtils

`AirlineUtils`-klassen gir ulike verktøymetoder som opererer på en liste over `IFlight`-objekter. Klassen inkluderer tre metoder, som du skal fullføre. Du kan bruke klassen [`TimeUtils`](../shared/TimeUtils.java) hvis det er nødvendig for visse oppgaver relatert til kontroll av tid.

1. `getFlightsByPredicate(List<IFlight> flights, Predicate<IFlight>)`: Returner en liste over flyreiser som tilfredsstiller det gitte predikatet.
2. `getBusiestAirport(List<IFlight> flights)`: Returner den travleste flyplassen (flyplassen med det høyeste antallet ankomster og avganger).
3. `isConnectedFlights(List<IFlight> flights)`: Sjekk om en liste over flygninger er koblet sammen, som betyr:
    - hver flyvning har *avgangtid* mindre enn 24 timer etter forrige flygnings *avgangtid*.
    - hver avgang starter fra samme *avgangssted* som den forrige flygningens *destinasjon*.
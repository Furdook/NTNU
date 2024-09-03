# Del 2, Bagasjehåndterer

I denne delen skal du implementere klassen `LuggageHandler`. Denne klassen er designet for å håndtere bagasje for ulike flyreiser. Hver flyreise identifiseres med et unikt flynummer, og hver bagasje har en angitt vekt. `LuggageHandler` bruker klassen [`Luggage`](../shared/Luggage.java).

## Klassens struktur

`LuggageHandler`-klassen skal ha følgende implementerte metoder:

- Konstruktør:
  - `LuggageHandler(int maxWeight)`: Initialiserer en `LuggageHandler` med maksimal vekt for enhver gitt bagasje.

- Offentlige metoder:
  - `addLuggage(String flightNumber, Luggage luggage)`: Legger til en bagasje på den gitte flyreisen. Hvis bagasjen overstiger den tillatte maksimale vekten, utløses en *IllegalArgumentException*.
  - `removeLuggage(String flightNumber, Luggage luggage)`: Fjerner en bagasje fra den gitte flyreisen. Hvis flyreisen ikke inneholder den spesifikke bagasjen returneres *false*, ellers returneres *true*.
  - `getTotalWeight(String flightNumber)`: Returnerer den totale vekten av all bagasje for en gitt flyreise. Hvis flynummeret ikke har noen bagasje, returneres *tallet null (0)*.

# Øving 07: Arv og abstrakte klasser

**Øvingsmål**
*  Lære hvordan arv-mekansimen brukes i OO
* Lære om instanser, typer, deklarasjoner og tilordninger
* Lære om sub- og superklasser samt om synlighetsmodifikatorer som brukes ved arv
* Lære om abstrakte klasser, deres bruksområder og fordeler

**Øvingskrav**
* Kunne bruke arv til å modellerere enkle(re) objektstrukturer- og relasjoner i Java
* Kunne la flere subklasser bruke funksjonalitet definert i samme superklasse
* Kunne la en subklasse redefinere metoder definert i en superklasse
* Kunne samle felles oppførsel til to eller flere subklasser i en felles abstrakt klasse

## Dette må du gjøre
Oppgavene skal lagres i `ovinger/src/main/java/oving7`.

I del 1 og 2 er antageligvis vanskelighetsgraden stigende. Alle er høyst eksamensrelevante og det anbefales følgelig å ta en titt på samtlige.

For å få 2 poeng på øvingen må det gjennomføres til sammen *4* valgfrie oppgaver fra del 1 og 2, samt del 3.
### Del 1: Arv
Velg og gjennomfør *minst én* av oppgavene om arv:

* [CardContainerImpl](./CardContainerImpl.md)
* [Train](./Train.md)
* [SavingsAccount](./SavingsAccount.md)

### Del 2: Abstrakte klasser og arv
Velg og gjennomfør *minst én* av oppgavene om abstrakte klasser og arv:

* [AbstractAccount](./AbstractAccount.md)
* [ObservableList](./ObservableList.md)

### Del 3: Testing

Skriv kode som tester oppførselen til `CoffeeCup`-klassen, dvs. at du skal teste om metodene i listen under har rett oppførsel og returnerer det de skal, i tillegg til at du skal teste konstruktørene. Det er ikke nødvendig å teste absolutt alle mulige tilfeller, men det kreves at du tester den grunnleggende funksjonaliteten. Basert på resultatene dine, vurder om klassen er implementert på en logisk måte.

- `getCapacity`
- `getCurrentVolume`
- `increaseCupSize`
- `drinkCoffee`
- `fillCoffee`

Du finner `CoffeeCup`-klassen under `ovinger/src/main/java/oving7/testing`.

Her er det anbefalt å bruke [JUnit](https://www.ntnu.no/wiki/display/tdt4100/Enhetstesting+med+JUnit),
men det er lov å teste vha. main-metoden også. Dersom du bruker JUnit må du opprette testen i `oving/src/test/java/oving7/testing`.

### Hjelp / mistanke om bugs

Ved spørsmål eller behov for hjelp konsulter studassen din i saltiden hans / hennes. Du kan også oppsøke andre studasser på sal eller legge ut et innlegg på [Piazza](https://piazza.com/).

### Godkjenning

Last opp kildekode på Blackboard innen den angitte innleveringsfristen. Innlevert kode skal demonstreres for en læringsassistent innen én uke etter innleveringsfrist. Se for øvrig Blackboard-sidene for informasjon rundt organisering av øvingsopplegget og det tilhørende øvingsreglementet.

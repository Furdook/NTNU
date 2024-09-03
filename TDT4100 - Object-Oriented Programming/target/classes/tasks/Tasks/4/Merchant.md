# Objektstrukturer - Merchant-oppgave

Denne oppgaven handler om en `Merchant`-klasse med en [1-n-assosiasjon](https://www.ntnu.no/wiki/display/tdt4100/Koding+av+1-n-assosiasjoner) til `Item`-klassen og det å sikre konsistens slik at objektene i oppgaven er korrekt knyttet sammen.

En viktig del av det å implementere assosiasjoner er å sikre konsistens, dvs. at objekter i hver ende av en kobling refererer korrekt
til hverandre. Et eksempel på dette for [1-n-assosiasjoner](https://www.ntnu.no/wiki/display/tdt4100/Koding+av+1-n-assosiasjoner) er en selger som råder over et inventar med eiendelene sine. I bytte mot penger kan selgeren selge eiendelene sine til andre selgere. For å gjenspeile den virkelige verden må man dermed til enhver tid ha kontroll på hvilke selgere som eier hva. I denne oppgaven skal man implementere `Merchant`- og `Item`-klassen i tillegg til at kjøp og salg av objekter skal håndteres korrekt.

`Item`-klassen skal inneholde informasjon om _navn_, _type_ (Enten "Weapon", "Armour", "Potion" eller "Valuable". _Type_ er en streng), _pris_ og _eier_ (Eieren er et _merchant_-objekt). 

`Item` skal ha en konstruktør:

- `Item(String, String, double)` - Et navn, en av de gyldige typene og en pris. Et passende unntak skal utløses dersom navnet er tomt, typen ikke er gyldig eller prisen ikke er større enn 0.

I tillegg skal `Item` ha følgende metoder:

- `Merchant getOwner()` - Returnerer eieren av objektet
- `void changeOwner(Merchant)` - Endrer eieren av objektet
- `double getPrice()` - Returnerer prisen på objektet
- `void setPrice(double)` - Bestemmer en ny pris på objektet
- En passende `toString()`-metode som informerer om navnet på objektet, hva slags objekt det er og hvor mye det koster

`Merchant`-klassen skal ha et _inventar_ med Item-objekter og en oversikt over _pengene_ til selgeren.

`Merchant` skal ha en konstruktør:

- `Merchant(double)` - Bestemmer hvor mye penger selgeren skal starte med. _IllegalArgumentException_ skal utløses dersom tallet er negativt.

I tillegg skal `Merchant` ha følgende metoder:

- `void obtainItem(Item)` - Lar selgeren finne og få eierskap av et Item-objekt
- `void removeItem(Item)` - Fjerner et Item-objekt fra inventaret til selgeren
- `void sellItem(Item, Merchant)` - Fremstiller en transaksjon mellom to selgere. Vi tar kun for oss situasjoner der en selger kjøper et Item-objekt fra selgeren som kaller på denne metoden. Varen som selges bytter inventar og balansene til selgerne justeres tilsvarende prisen på varen. En _IllegalStateException_ skal utløses dersom kjøperen ikke har råd til varen, dersom selgeren ikke eier objektet som selges og dersom en selger prøver å selge noe til seg selv.
- `void itemSale(double, Item)` - Selgeren skal kunne ha et salg på en av eiendelene sine. Avslaget skal være mellom 0 og 1, og regnes som prosentvis avslag på prisen. For eksempel dersom Item-objektet koster 100 mynter og man kaller på metoden med 0.3 som avslag, skal den nye prisen være 70 gullmynter.
- `void inventorySale(double)` - Selgeren sliter med salgene sine, og vil derfor starte et salg på hele inventaret sitt.

**Java-kode**

Implementer `Item` og `Merchant` som beskrevet over. Test klassene med selvlagde main-metoder og med den utdelte testkoden.

Testkode for denne oppgaven finner du her: [MerchantTest.java](../../src/test/java/oving4/MerchantTest.java)
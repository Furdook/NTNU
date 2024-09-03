# TDT4100-Project
Semesteroppgave i Objekt-Orientert-Programmering

Object-Orientert-Programmering 
Semester Prosjekt


Bookingsystem for kino, med flere saler av varierende størrelse. Her kan brukeren bestille et selvvalgt antall seter for både voksne og barn til valgte filmvisning. Det vil være et bestemt antall filmer å velge mellom hvor hver film har flere ulike saltider man kan velge fra, på ulike saler. Dersom bestillingen er for flere seter vil de plassert ved siden av hverandre, om det ikke er nok ledige plasser i en sal vil naturligvis dette ikke gå gjennom. Det skal være synlig hvor mange ledige plasser det er til hver visning, som oppdateres ved nye bestillinger. Brukeren vil kunne få sine plasser og annen informasjon om visningen (I eget ‘billett’-dokument for ‘Order’-objektet?).

Klasser:

- Order (Bestillinger): Lagrer kunden som et objekt med sin bestilling som variabler, hvor mange billetter, hvilke type, hvilke sete(r), hvilken film og når den vises.
- Theatre (Saler): Saler som objekter med størrelser osv. Sjekk om sal er ledig med forbindelse til ‘Screening’-klassen.
- Movies (Filmer): Tilgjengelige filmer med lengde på film og annen generell informasjon om de objektene, f.eks. aldergrense, direktør, opprinnelsesland osv.
- Screening (extends Movies): Tidsrom hvor filmene vises på hvilken sal, når de starter, slutter, pause o.l. Plassene i salene vil utregnes her ettersom de er spesifikke for hver visning, naturligvis med sjekk i ‘Theatre’-klassen for å sjekke størrelse. (Theatre som ‘parent’?)
- Cinema (Hovedklasse): Den generelle klassen som binder sammen alle de andre og lager selve tidsskjema o.l.
- (Seating klasse dersom denne funksjonen lar seg gjøre)

For enkelhets skyld kan det bestemte antallet saler for eksempel være fire, og antall filmer seks, åtte, eller ni, for brukergrensesnittets estetikks skyld. Ønsker å lage en plassvelger dersom det er mulig å gjøre innenfor de gitte rammene, som prosjektstørrelse.

Alle metodene vil testen ved bruk av ‘JUnit’ ved å sjekke for ‘Exceptions’ og ugyldige verdier. Deriblant setebooking, overbooking, ugyldig film o.l.
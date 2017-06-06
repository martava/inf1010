Oppgaven

Det skal lages et program som sorterer ord ved hjelp av tråder. Programmet skal ta imot tre parametre på kommandolinjen: et heltall som angir antall tråder som skal brukes til sortering, og filnavn til en innfil og en utfil. Eksempel på kommandolinje:

$ java Sort 128 names.txt out.txt

I eksempelet over skal javaprogrammet Sort bruke 128 tråder, lese inn ord fra filen names.txt, og skrive resultatet ut til filen out.txt.

Første parameter i kommandolinjen kaller vi antTraader og angir antall tråder som skal brukes til sorteringen. Første linje i innfilen er et heltall, som vi kaller antOrd og angir hvor mange ord det er totalt i resten av filen. Resten av filen inneholder alle ordene som skal sorteres, adskilt med linjeskift.

Programmet skal gi en passende feilmelding hvis kommandolinjeparameterene ikke stemmer (både manglende og feil type som f.eks tekst istedenfor et tall på antTraader posisjonen). Bruk gjerne try/catch for å få til dette.
Fremgangsmåte

Programmet skal lese ordene inn i en tabell (array). Hver tråd skal sortere n = antOrd/antTraader ord (hvis ikke divisjonen går opp, kan noen tråder ha ett ord ekstra). Pass på at du har laget tabellen før trådene startes. På denne måten vil du kunne se at flere tråder sorterer raskere enn en (eller noen få) tråd(er). Sorteringsalgoritmen som blir valgt skal programmeres og forståes. Lag gjerne en enkel algoritme med en grei invariant. Beskriv invarianten du bruker som en (eller flere) kommentarer i programmet ditt.

Når en tråd er ferdig med å sortere sin del, skal den gi fra seg denne til en Monitor

Hvis monitoren har fått inn to deler skal den aktivisere en tråd for å flettesortere de to delene sammen. Mot slutten av oppgaveteksten er det en forklaring av hva som menes med fletting i denne sammenhengen.

Forrige punkt skal skje hver gang det er to og to tråder som er ferdig, inntil alle ord fra innfilen er ferdig sortert. Legg merke til at det etter hvert er langt færre enn antTraader tråder som jobber med å flette.

Programmet skal i alle fall virke med antTraader mellom 1 og 1000 (og gjerne flere), og skal bl.a. kunne sortere de tre datafilen lenket til under. Hvis antall ord i innfilen ikke stemmer med heltallet på første linje skal programmet stoppe med en fornuftig feilmelding. Før programmet skriver det sorterte resultatet på utfilen skal alle ord ligge i en tabell (array), og programmet skal sjekke at:

  tabellen har riktig lengde (antOrd)
  det ikke ligger en null-peker som siste element
  tabellen er sortert

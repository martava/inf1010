Eksamen i: INF1010 – Objektorientert programmering
Eksamensdag: Torsdag 15. eller fredag 16. august 2013

Innledning oppgave 1.   Dyr i Dyreparken

Eierne av Dyreparken skal lage et program som skal holde orden på  alle dyrene i parken.
Du får beskjed om å lage en første test-versjon i Java.

I denne første testversjonen har alle dyr et navn, og  alle dyrene i parken
klassifiseres som enten pattedyr eller fugler  (krypdyrene må vente til en
senere versjon).  Av pattedyrene skal vi nå bare se på giraffer og primater,
og primatene skal igjen deles inn i mennesker (homo sapiens) og aper.
Av fuglene skal vi her bare ta med papegøyer.

Dyr kan eldes, og du skal derfor sørge for at alle dyr har denne egenskapen.  
Når et dyr opprettes i datasystemet er alder og navn alltid kjent.

Noen dyr (og kanskje andre ting også) kan kle seg ut. Når noe kan kle seg ut
skal det kunne gjøre to ting: Det skal kunne ta imot en ny rollemodell som det
kler seg ut som.  En slik rollemodell kan være hva som helst, og i det
Java-programmet du lager skal du programmere dette slik at rollemodellen er et
hvilket som helst objekt. Det skal også kunne fortelle hva/hvem som er dets
rollemodell i øyeblikket.

I denne test-versjonen skal bare aper og papegøyer kunne kle seg ut.

Oppgave 1a.  Tegn klassehierarkiet med tilhørende grensesnitt slik som beskrevet
over.  Du behøver ikke tegne inn metoder eller andre egenskaper i denne tegningen.

Oppgave 1b.  Programmer alle klassene og tilhørende grensesnitt slik det er
beskrevet over.

Oppgave 1c.  Skriv et fullstendig og kjørbart program som (sammen med svaret på
oppgave 1b) oppretter tre “dyr”, nemlig apen Julius (som er 20 år), mennesket
Kaptein Sabeltann (som er 200 år) og papegøyen Polly (som er 10 år).  La så
Julius kle seg ut som Kaptein Sabeltann.  Til slutt spør programmet Julius
hvem han er kledd ut som, og skriver ut navn og alder på resultatet.


Innledning oppgave 2.   Når dyrene blir syke

Noen ganger blir dyrene i Dyreparken syke, og da må de gå til legen. Det er mye
 å gjøre på legekontoret, så du skal utvide testversjonen av programmet fra
 oppgave 1 med et lite system som administrerer køen på kontoret.  Du skal i
 oppgave 2a lage en generisk klasse for den typen kø de trenger på legekontoret,
 og i oppgave 2b skal du bruke denne generiske klassen til å lage en kø av dyr
 på legekontoret i Dyreparken.  I oppgave 2 skal du ikke bruke noen ferdiglagde
 datastrukturer fra Javas bibliotek, du skal programmere alle datastrukturene selv.

Den type  kø du her skal lage er slik at alle pasienter kommer til legekontoret,
trekker en kølapp, og setter seg til å vente. Vanligvis blir pasientene behandlet
i FIFO-rekkefølge, det vil si at den med lavest kønummer blir ropt opp først
(og skal ut av køen først). Men noen ganger ønsker legene å behandle barn først,  
og da blir den yngste i køen (den med lavest alder) ropt opp (og skal ut av køen).

Den generiske (eller parametriserte) klassen du skal skrive skal derfor ha tre
metoder: settInn, taUtYngst og taUtMinstNr.

Parameteren til settInn er en peker til det objektet som skal settes inn. Alle
objekter som skal settes inn må ha den egenskapen at de kan eldes. Når et objekt
 settes inn trekker det en kølapp (et kønummer i stigende rekkefølge).

Når objekter tas ut skal dette gå raskt. Dette er viktig. Du skal derfor skrive
både taUtYngst og taUtMinstNr slik at de ikke leter gjennom datastrukturen, men
tar ut det første objektet i en dobbelt-lenket liste.  Datastrukturen i klassen
skal derfor bestå av to lister. I den ene listen er objektene sortert på alder,
i den andre er objektene sortert på kø-nummer.  Dette betyr at metoden settInn
må bruke litt tid på å sette et objekt inn i køen.

Metoden taUtYngst returnerer en peker til det yngste objektet i datastrukturen.
Objektet skal samtidig tas ut av begge de to listene.

Metoden taUtMinstNr returnerer en peker til objektet med lavest kønummer.
Objektet tas samtidig ut av begge de to listene.

Hint: De to taUt-metodene tar ut det første objektet i hver sin liste, men i
tillegge må du ha en peker til objektet i den andre listen, slik at programmet
kan ta ut objektet derfra også (uten å måtte lete i den andre listen).

Oppgave 2a. Skriv den generiske klassen slik den er beskrevet over med de tre
metodene settInn, taUtYngst og taUtMinstNr.


I oppgave 2b skal du utvide det fullstendige programmet i oppgave 1c. Du skal
lage en kø for dyrene som skal til legen i Dyreparken. Alle de tre dyrene du
laget i oppgave 1c er blitt syke og må til legen.  Etter dem kommer også giraffen
Stankel på 5 år til legen fordi han har fått så vondt i halsen. La legene først
behandle den som kom først (den med lavest kønummer), deretter den yngste.
Skriv ut navnene på disse to etter at de er tatt ut av køen.

Oppgave 2b. Utvid oppgave 1c slik beskrevet over, og sørg for at du fremdeles
har et fullstendig program.

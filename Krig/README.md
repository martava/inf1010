Oppgave 1.4: Krig

I denne oppgaven skal du lage et program for kortspillet Krig.

a) a) Lag en klasse Kort som representerer et kort. Den skal ha en konstruktør som tar imot 'farge' og tall (seperat). Lag også tilhørende get-metoder og en toString-metode.

b) Lag en klasse Kortstokk som representerer en kortstokk. Den skal ha attributtene

private ArrayList<Kort> kortstokk; // fra java.util.ArrayList

private Random trekker; // fra java.util.Random

som initialiseres i konstruktøren, der kortstokk skal inneholde kortene og trekker skal være en generator for å trekke et tilfeldig kort litt senere.

c) Vi kan representere fargene på kortene slik: String[] farger = {"H", "K", "S", "R"}. Bruk to for-løkker der du går gjennom fargene og tallene, og fyll kortstokkene med add()-metoden til ArrayList (Dette kan gjøres i konstruktøren til kortstokk, eller i en egen metode).

d) Random-generatoren kan vi bruke slik:

int indeks = trekker.nextInt(kortstokk.size()). Vi har nå en indeks som er tilfeldig valgt. Lag en metode trekkKort(). Lag også en metode erTom().

Hint.

e) Lag en klasse Spiller som skal representere en kortspiller. Den skal ha en konstruktør som tar i mot et navn og initialiserer attributtene

private String navn;
private ArrayList<Kort> hand;

Lag også metodene giKort() der spilleren mottar et kort og legger det på handa, leggKort() der spilleren legger et kort på bordet, tomHand() som gir en booleanverdi og antallKort() som gir antall kort spilleren har på hand. Legg også til følgende toString-metode.

public String toString() {
    String string = navn + "\nKort: ";
    for (Kort k : hand) {
        string += k + " ";
    }
    return string;
}

f) Lag en klasse Krig som skal representere selvet spillet vårt. Den skal ha én metode, nemlig en main()-metode. Lag en kortstokk, to spillere og fordel kortene i kortstokken mellom de to spillerne. Skriv ut spillerene til skjerm, for å sjekke at alt fungerer.

g) Vi skal nå lage resten av programmet vårt ved hjelp av diverse if-setninger. Begynn med en while-løkke som fortsetter helt til en av spillerne dine ikke har flere kort igjen. Det kan være lurt å gjøre noen forenklinger underveis, der for eksempel en av spillerene bare får kortene. Husk at det er den med høyeste kort som får alle kortene. Dersom man har det samme tallet, legges det frem 4 kort, og det siste hver av spillerene legger, avgjør hvem som får alle kortene. En forenkling kan være at dersom disse siste kortene også er like, får en av spillerne kortene.

Det kan også være hjelpsomt å skrive ut en del til terminalen underveis. 

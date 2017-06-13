/*
Alleler er alternative utgaver av samme gen. Et allel kan være dominant eller recissivt. For enkelhets skyld skal vi i denne oppgaven kun se på tilfeller der det finnes to alternative alleler hvorav det ene allelet er dominant og det andre er recissivt. Det dominante allelet vil gå i arv hvis minst én av foreldrene har det, mens det recissivt allelet kun vil gå i arv hvis begge foreldrene har det.

Du kan bruke følgende klasse for å representere alleler.

class Allel {
   private boolean dominant;
   private String beskrivelse;

   Allel(boolean dominant, String beskrivelse) {
       this.dominant = dominant;
       this.beskrivelse = beskrivelse;
   }

   public boolean erDominant() {
       return this.dominant;
   }

   @Override
   public String toString() {
       return this.beskrivelse;
   }
}

Det finnes to alleler som bestemmer om øyets iris (ringen rundt pupillen) er brun eller ikke. Det brune allelet er dominant.

a) Skriv en klasse Individ som har en instansvariabel Allel irisfarge. Noen individer kjenner vi irisfargen til, andre individer kjenner vi bare foreldrene til. Klassen skal derfor ha to konstruktører hvorav den ene tar inn irisfargen som parameter og den andre tar inn foreldrene som parametre.

Lag først en ikke-rekursiv løsning. Den andre konstruktøren må da bestemme individets irisfarge ved å sjekke foreldrenes irisfarger (som er kjent).

b) Du skal nå endre Individ slik at irisfargen bestemmes ved rekursjon.

Utvid Individ med instansvariabler for hver forelder og endre den andre konstruktøren slik at den bare lagrer foreldrene i instansvariablene.

Skriv så en metode Allel irisfarge(). Denne skal rekursivt kalle foreldrenes irisfarge() hvis this.irisfarge == null for å avgjøre individets irisfarge. Hvis individets irisfarge er kjent, returneres det bare.
*/


class ArvSimulator {
    public static void main(String[] args) {
        Allel brunIris = new Allel(true, "brun iris");
        Allel ikkeBrunIris = new Allel(false, "ikke brun iris");

        Individ olav = new Individ(ikkeBrunIris);
        Individ martha = new Individ(ikkeBrunIris);
        Individ harald = new Individ(olav, martha);
        Individ sonja = new Individ(brunIris);
        Individ haakon = new Individ(harald, sonja);
        Individ metteMarit = new Individ(ikkeBrunIris);
        Individ ingridAlexandra = new Individ(haakon, metteMarit);

        System.out.println("Harald har " + harald.irisfarge());
        System.out.println("Haakon har " + haakon.irisfarge());
        System.out.println("Ingrid Alexandra har "
                           + ingridAlexandra.irisfarge());
    }
}
class Allel {
    private boolean dominant;
    private String beskrivelse;

    Allel(boolean dominant, String beskrivelse) {
        this.dominant = dominant;
        this.beskrivelse = beskrivelse;
    }

    public boolean erDominant() {
        return this.dominant;
    }

    @Override
    public String toString() {
        return this.beskrivelse;
    }
}
class Individ{
  Allel irisfarge;
  Individ mamma;
  Individ pappa;

  Individ(Allel irisfarge){
    this.irisfarge = irisfarge;
  }
  Individ(Individ mamma, Individ pappa){
    this.mamma = mamma;
    this.pappa = pappa;
  }
  /*
  --- urekursiv versjon:
  Individ(Individ mamma, Individ pappa){
    if(!mamma.irisfarge().erDominant() && !pappa.irisfarge().erDominant()){
      this.irisfarge = mamma.irisfarge();
    }
    else if(mamma.irisfarge().erDominant()){
      this.irisfarge = mamma.irisfarge();
    }
    else{
      this.irisfarge = pappa.irisfarge();
    }
  }
  */
  Allel irisfarge(){
    if (irisfarge == null){
      if(!mamma.irisfarge().erDominant() && !pappa.irisfarge().erDominant()){
        this.irisfarge = mamma.irisfarge();
      }
      else if(mamma.irisfarge().erDominant()){
        this.irisfarge = mamma.irisfarge();
      }
      else{
        this.irisfarge = pappa.irisfarge();
      }
    }
    return irisfarge;
  }
}

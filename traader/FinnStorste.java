/*
I denne oppgaven skal du først skrive en monitor kalt StorstAvTo, som finner det største av to heltall (int).  Monitoren skal ha to metoder: leggInn og hentUt.
Metoden leggInn skal bare kalles to ganger. Hvis metoden kalles en tredje gang skal den kaste et egendefinert unntak.
Hvis metoden hentUt kalles før  leggInn er kalt to ganger,  så må metoden hentUt vente. Metoden hentUt skal returnere den største verdien av de to som ble lagt inn.

Oppgave 3a. Skriv monitoren StorstAvTo

I oppgave 3b skal du skriv en trådklasse, kalt FinnStorst,  som finner det største tallet i en (del av en) endimensjonal array. Klassen skal bl.a. ha arrayen og
 nedre og øvre grense som parametere til konstruktøren.
Når tråden starter skal den først se hvor stor den delen av arrayen som den skal lete i, er.  Hvis delen er mindre enn 1000 elementer skal tråden bare
gå sekvensielt gjennom arrayen og finne det største tallet.
Hvis trådens del av arrayen er større eller lik 10000 elementer skal den opprette en StorstAvTo-monitor, dele arrayen opp i to omlag like store deler,
og deretter starte to tråder (to barn) av samme klasse (FinnStorst) som skal finne det største tallet i hver sin del av arrayen. Når hver av disse to
trådene har funnet det største tallet i sin del, kaller de leggInn i monitoren. Tråden (som startet de to barna) kaller så hentUt i monitoren for å finne
det største av de to tallene som barna har funnet i hver sin del av arrayen.

Oppgave 3b. Skriv trådklassen FinnStorst.

I oppgave 3c skal du skrive et fullstendig og kjørbart program som lager en array av tilfeldige tall og deretter bruker trådklassen FinnStorst
og monitorklassen StorstAvTo til å finne og skrive ut det største tallet i denne tabellen.
Når du lager en array med tilfeldige heltall skal du først lage et objekt av klassen Random (i java.util), og deretter kalle metoden nextInt
i dette objektet så mange ganger du trenger for å fylle hele tabellen. Kall lengden på arrayen LENGDE, og sett verdien til ti millioner (10000000).

Oppgave 3c. Skriv det fullstendige Java-programmet som finner det største tallet i en array slik som beskrevet over. 
*/
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

class FinnStorste{
  public static void main(String[] args){
    Random rand = new Random();
    int[] t = new int[1000000];
    for(int i = 0; i< t.length; i++){
      t[i] = rand.nextInt(10000000)+1;
    }

    StorstAvTo monitor1 = new StorstAvTo();
    monitor1.leggInn(-2); //Må leggje inn eit ekstra tal i monitor1, elles kjem den aldri til å kunne gi ut det høgaste talet
    new Thread(new FinnStorst(t, monitor1)).start();

    new Thread(new HentStorst(monitor1)).start();//traad for å hente ut det største taler

  }

}
class HentStorst implements Runnable{
  StorstAvTo mon;
  public HentStorst(StorstAvTo mon){
    this.mon = mon;
  }
  public void run(){
    //henter og printer ut det største talet frå det øvste "laget" med monitor + traad
    System.out.println("STØRST: " + mon.hentUt());
  }
}

class FinnStorst implements Runnable{
  int[] tabell;
  StorstAvTo mon;

  public FinnStorst(int[] tabell, StorstAvTo mon){
    this.tabell = tabell;
    this.mon = mon;
  }
  public void run(){
    if(tabell.length <= 1000){
      finnStorsteITabell();
    }else{
      int[] tabell1 = new int[(tabell.length / 2)];
      int[] tabell2 = new int[(tabell.length/2) + 1]; //+1 i tilfelle lengda er eit oddetal
      int j = 0;
      for(int i = 0; i<tabell1.length; i++){
        tabell1[i] = tabell[j++];
      }
      for(int i = 0; i<tabell1.length; i++){
        if(tabell.length > j){ //må dobbeltsjekke, i tilfelle tabell2 blei 1 rad for lang
          tabell2[i] = tabell[j++];
        }
      }
      //lager ny monitor, og to barnetraader som får denne monitoren
      StorstAvTo sat = new StorstAvTo();
      new Thread(new FinnStorst(tabell1, sat)).start();
      new Thread(new FinnStorst(tabell2, sat)).start();
      mon.leggInn(sat.hentUt());//legg inn det storste talet som barna fann
    }
  }
  public void finnStorsteITabell(){
    //obs, skreiv minst, men meinte storst! metoden finn den storste iaf.
    int minst = tabell[0];
    for(int i = 1; i<tabell.length; i++){
      if(tabell[i] != 0){
        if(tabell[i] > minst){
          minst = tabell[i];
        }
      }
    }
    mon.leggInn(minst);
  }
}




class StorstAvTo{
  /*Her prøvde eg fyrst å bruke CountDownLatch, men fekk det ikkje til å fungere så bra, så gjekk over til
  å berre bruke ein vanleg int for å halde styr på kor mange tråder som har levert frå seg tal*/

  private ReentrantLock laas = new ReentrantLock();
  private Condition grense = laas.newCondition();
  private int tal1; //lagrar dei to tala som to int, sidan det berre er to av dei
  private int tal2;
  private int antall;



  public void leggInn(int tal){

    laas.lock();
    //System.out.println("grense før: " + antall);
    try{
      if (antall < 2){
        if(tal1 == 0){
          tal1 = tal;
          antall++;
          //System.out.println(tal);
        }
        else{
          tal2 = tal;
          antall++;
          //System.out.println(tal);
        }

        if(antall == 2){
          grense.signalAll(); //signalierer at 2 er lagt inn, i tilfelle det er ventande tråd på å hente ut
        }
        //System.out.println("grense : " + antall);
      }
      else{
        throw new KallaForMangeGangerException();
      }
    }
      catch (Exception e){}
      finally{
        laas.unlock();
      }
    }

  public int hentUt(){
    laas.lock();
    try{
      while(antall<2){
        grense.await();
      }

        if(tal1 > tal2){
          return tal1;
        }
        return tal2;

    }catch (InterruptedException e){

    }finally{
      laas.unlock();
    }
    return -1;
  }
}

//Exception-klassen, OBS: ikkje testa.
class  KallaForMangeGangerException extends RuntimeException{

      KallaForMangeGangerException() {
          super("kalla for mange gonger");
      }

}

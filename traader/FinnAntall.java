/* Denne oppgaven ble gitt som innleveringsoppgave våren 2015

I denne oppgaven skal du vise at du kan løse et problem ved hjelp av enkel tråd-programmering og lage et enkelt felles objekt (en monitor). Kildekoden til det fullstendige kjørbare programmet skal leveres.

Programmet skal lese en fil der første linje inneholder antallet ord i resten av filen, ett ord på hver linje. Ordene skal lagres i en tabell (array), og deretter skal k

tråder finne hvor mange ganger et gitt ord forekommer i tabellen. Hvis programmet ditt heter FinnAntall.java skal det f.eks. startes opp på denne måten:

>java FinnAntall anders minfil.txt
8

Dette betyr at du skal finne hvor mange ganger ordet anders finnes i filen minfil.txt ved hjelp av 8 tråder. Her er altså k=8
. Husk å ta hensyn til at k

kan være 1.

Når filen er lest inn skal main-tråden dele tabellen i ca. k like lange deler, og starte opp k
tråder som skal lete i hver sin del. Når en tråd har funnet antall forekomster av ordet i sin del skal den rapportere dette inn til et felles objekt (en monitor). Main-tråden skal vente til alle trådene er ferdig med å lete, hente det totale antallet forekomster fra monitoren og til slutt skrive dette antallet ut.
*/


import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class FinnAntall{
  static String ord;
  static ArrayList<String> ordtabell = new ArrayList<>();
  static int k;
  static Monitor monitor;
  static boolean[] ferdig;


  public static void main(String[] args){
    Monitor monitor = new Monitor();
    Scanner filLesar;
    try{
      ord = args[0];
      filLesar = new Scanner(new File(args[1]));
      while(filLesar.hasNextLine()){
        ordtabell.add(filLesar.nextLine());
      }
    }catch (FileNotFoundException fne){System.out.println("Fann ikkje fila");}

    Scanner inn = new Scanner(System.in);
    k = Integer.parseInt(inn.nextLine());
    ferdig = new boolean[k];


    for(int i = 0; i<k; i++){
      ArrayList<String> o = new ArrayList<>();
      for(int j = 0; j < (ordtabell.size() / (k - 1)); j++ ){
        if(ordtabell.get(0) != null){
          o.add(ordtabell.remove(0));
        }
      }
      new Thread(new Leitar(o, monitor)).start();
    }

  }
  public static void erFerdig(){
    for(int i =1; i<ferdig.length; i++){
      if(!ferdig[i]){
        ferdig[i] = true;
        return;
      }
    }
    //System.out.println("antall førekomster av " + ord + ": " + monitor.hentAntall() );
  }

  static class Leitar implements Runnable{
    ArrayList<String> mineOrd;
    Monitor monitor;

    Leitar(ArrayList<String> mineOrd, Monitor monitor){
      this.mineOrd = mineOrd;
      this.monitor = monitor;
    }
    public void run(){
      while(!mineOrd.isEmpty()){
        if(mineOrd.remove(0).equals(ord)){
          System.out.println("Fann ordet! ");
          monitor.aukAntall();
        }
      }
      erFerdig();
    }

  }

  static class Monitor{
    private Lock laas = new ReentrantLock();
    private int antall = 0;

    public void aukAntall(){
      laas.lock();
      try{
        antall++;
        System.out.println("Antall førekomster: " + antall);
      }finally{
        laas.unlock();
      }
    }
    public int hentAntall(){
      return antall;
    }
  }
}

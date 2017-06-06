/* a) Skriv en monitor som holder orden på to tall, detMinste og detStorste. En invariant tilstandspåstand i monitoren skal være at detMinste < detStorste. Monitoren skal ha to offentlige metoder settMinste() og settStorste() som gir ny verdi til hhv. det minste og det største tallet. Dersom operasjonen ikke ødelegger invarianten skal det returneres sann (true). Hvis operasjonen ikke kan utføres (altså at det vil bryte med invarianten), skal det returneres usann (false).

b) Skriv to tråder Nedover og Oppover. Nedover skal starte med Integer.MAX_VALUE og telle nedover. For hvert nye tall kalles metoden settStorste() i monitoren. Oppover skal starte med Integer.MIN_VALUE, telle oppover og kalle monitorens sin settMinste(). Trådene skal fortsette så lenge sann returneres. Dersom det returneres usant skal tallet som ble forsøkt lagt inn skrives ut, og tråden skal stoppe
*/


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tallmonitor{
  public static void main(String[] args){
    TallMonitor monitor = new TallMonitor();
    Thread opp = new Thread(new Oppover(monitor));
    Thread ned = new Thread(new Nedover(monitor));


    opp.start();
    ned.start();

  }
}

class Oppover implements Runnable{
  private final TallMonitor monitor;
  private int tall = Integer.MIN_VALUE;

  public Oppover(TallMonitor monitor){
    this.monitor = monitor;
  }

  public void run(){
    boolean ok = true;
    while(ok){
      ok = monitor.settMinste(tall++);
    }
    System.out.println("Opp: " + (tall-1));
  }
}

class Nedover implements Runnable{
  private final TallMonitor monitor;
  private   int tall = Integer.MAX_VALUE;

  public Nedover(TallMonitor monitor){
    this.monitor = monitor;
  }

  public void run(){
    boolean ok = true;
    while(ok){
      ok = monitor.settStorste(tall--);
    }
    System.out.println("Ned: " + (tall+1));

  }
}

class TallMonitor {
    private  final Lock laas = new ReentrantLock();
    private  int detMinste;
    private  int detStorste;

    public boolean settMinste(int i) {
      laas.lock();
      try {
          if (i < detStorste) {
              detMinste = i;
              return true;
          }
          else{
            return false;
          }
      } finally {
          laas.unlock();
      }
      //return false;
   }
   public boolean settStorste(int i) {
     laas.lock();
     try {
         if (i > detMinste) {
             detStorste = i;
             return true;
         }
         else{
           return false;
         }
     } finally {
         laas.unlock();
     }
     //return false;
  }


}

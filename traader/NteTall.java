/* a) Lag en trådklasse som skriver ut hvert 10-ende tall fra 5 opp til 1000.

b) Lag en trådklasse som skriver ut hvert n

-te tall fra start opp til maks. La n, start og maks være parametre til trådens konstruktør.

c) Opprett og start 10 slike tråder der hver tråd skriver ut hvert 10-ende tall (med start lik hhv. 0,1,…,9

) opp til 10000.

d) Skriv en monitor med en metode som tar i mot et tall og skriver det ut. Modifisér trådklassen fra deloppgave b slik at den kaller metoden i denne monitoren med tallet som parameter istedenfor å skrive tallet ut. La metoden i monitoren skrive ut tallet istedet.

Hint.

e) Modifisér monitoren i forrige deloppgave slik at den skriver ut alle tallene den mottar i riktig rekkefølge (dvs. 0,1,2,3,…,10000
). (Altså en svært komplisert måte å skrive ut tallene fra 0 til 10000).
*/

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NteTall{
    Condition ikkjeNeste;
    Condition provIgjen;
    Lock talLaas;

   NteTall(){

     talLaas = new ReentrantLock();
     ikkjeNeste = talLaas.newCondition();
     provIgjen = talLaas.newCondition();
     NteMonitor m = new NteMonitor();
     for(int i = 0; i<10; i++){
       new Thread(new MinRun(i, 0, 1000, m)).start();
     }

   }
  public static void main(String[] args){
    new NteTall();

  }


class NteMonitor{
    int tal = -1;

    public void skrivUt(int i){
      talLaas.lock();
      try{
        while(i-1 != tal){
          ikkjeNeste.await();
        }

          tal = i;
          System.out.println(tal);
          ikkjeNeste.signalAll();

      }
      catch(InterruptedException e){
        System.out.println("feil");
      }
      finally{
        talLaas.unlock();
      }

    }
}
}
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MinRun implements Runnable{
  private final int SLUTT;
  private final int START;
  private final int N;
  private int teller = 0;
  private final NteTall.NteMonitor monitor;

  private final Lock lock = new ReentrantLock();

  public MinRun(int n, int start, int slutt, NteTall.NteMonitor m){
    this.SLUTT = slutt;
    this.START = start;
    this.N = n;
    this.monitor = m;
  }

  public void run(){
    lock.lock();
    try{
      while(teller < SLUTT){
        if(teller % 10 == N){
          monitor.skrivUt(teller);
        }
        teller++;
        }
      }finally{
        lock.unlock();
      }
    }
  }

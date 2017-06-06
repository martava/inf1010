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

  /*  public void giMinste(String liten) {
        System.out.println("Mottok: " + liten);
        laas.lock();
        try {
            if (minste == null || liten.compareTo(minste) < 0) {
                minste = liten;
            }
        } finally {
            laas.unlock();
        }
    }*/
}

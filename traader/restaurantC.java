import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class RestaurantC {
	private Lock bordlas;
	private Condition ikkeTomtBord;
	private Condition ikkeFulltBord;
    RestaurantC(String[] args) {
    	bordlas = new ReentrantLock();
    	ikkeTomtBord = bordlas.newCondition();
    	ikkeFulltBord = bordlas.newCondition();
	   int antall = Integer.parseInt(args[0]);
       FellesBord bord = new FellesBord();
       Kokk  kokk = new Kokk(bord,antall);
	   //kokk.start();
	   new Thread(kokk).start();
	   Servitor  servitor = new Servitor(bord,antall);
       //servitor.start();
       new Thread(servitor).start();
    }

    public static void main(String[] args) {
	       new RestaurantC(args);
    }

    class FellesBord {  // En monitor
	private int antallPaBordet = 0;
        /* Tilstandspastand: 0 <= antallPaBordet <= BORD_KAPASITET */
	private final int BORD_KAPASITET = 5;

	void settTallerken() throws InterruptedException {
	    bordlas.lock();
	    try {
	        while (antallPaBordet >= BORD_KAPASITET) {
		    /* sa lenge det er  BORD_KAPASITET tallerkner
                               pa bordet er det ikke lov a sette pa flere. */
               ikkeFulltBord.await();
            }  // Na er antallPaBordet < BORD_KAPASITET
	        antallPaBordet++;
	        System.out.println("Antall paa bordet: " + antallPaBordet);
	        ikkeTomtBord.signal(); /* Si fra til den som tar tallerkener. */
	    }
	    finally {
	        bordlas.unlock();
	    }
	}

    void hentTallerken() throws InterruptedException  {
    	bordlas.lock();
	    try {
        while (antallPaBordet == 0) {
            /* Sa lenge det ikke er noen talerkener pa
			      bordet er det ikke lov a ta en */
           ikkeTomtBord.await();
        } // Na er antallPaBordet > 0
	    antallPaBordet --;
        ikkeFulltBord.signal(); /* si fra til den som setter tallerkener pa bordet. */
        }
        finally {
	    bordlas.unlock();
	    }
	}
  } // slutt class FellesBord;


 class Kokk implements Runnable {
    private FellesBord bord;
    private final int ANTALL;
    private int laget = 0;
    Kokk(FellesBord bord, int ant) {
		  this.bord = bord;
		  ANTALL = ant;
	  }

	  public void run() {
	      try {
	      while(ANTALL != laget) {
              laget ++;
              System.out.println("Kokken lager tallerken nr: " + laget);
              bord.settTallerken();  // lag og lever tallerken
              Thread.sleep((long) (500 * Math.random()));
           }
           } catch (InterruptedException e) { System.out.println("Uventet stopp 1");}
         // Kokken er ferdig
      }
  }

  class Servitor implements Runnable {
      private FellesBord bord;
      private final int ANTALL;
      private int servert = 0;
      Servitor(FellesBord bord, int ant) {
          this.bord = bord;
	  ANTALL = ant;
      }

      public void run() {
      	 try {
         while (ANTALL != servert)  {
	          bord.hentTallerken(); /* hent tallerken og server */
	          servert++;
              System.out.println("Kelner serverer nr:" +  servert);
              Thread.sleep((long) (1000 * Math.random()));
          }
          } catch (InterruptedException e) { System.out.println("Uventet stopp 2");}
         // Servitoren er ferdig
      }
  }
}

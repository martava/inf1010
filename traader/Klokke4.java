import java.util.Scanner;

class Klokke4 {
   public static void main(String[] args) // throws IOException
    {
      System.out.println("Trykk S <retur> for a starte og stoppe");
      Scanner tastatur= new Scanner (System.in);
      tastatur.next();


      // Her lages stoppeklokke-objektet:
      Stoppeklokke stoppeklokke = new Stoppeklokke();
      Thread mintrad = new Thread(stoppeklokke);

      // og her settes den nye traaden i gang.
      mintrad.start();

      tastatur.next();
      mintrad.interrupt();
   }
}

class Stoppeklokke implements Runnable {
   // private volatile boolean stopp = false;
   // blir kalt opp av superklassens start-metode.
   public void run() {
      int tid = 0;
      try {
        while (! Thread.interrupted()) {
           System.out.println(tid++);
           Thread.sleep(1 * 1000); // ett sekund
        }
      }
	  catch (InterruptedException e) { }
   }
}

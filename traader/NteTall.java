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

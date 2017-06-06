import java.util.concurrent.*;

class CyclicBarrierTest{
  static CyclicBarrier barrier;
  static int teller = 1;
  public static void main(String[] args){
    barrier = new CyclicBarrier(7);
    for(int i= 0; i<10; i++){
      new Thread(new Traad()).start();
    }
    System.out.println(barrier.getNumberWaiting());
  }

  static class Traad implements Runnable{
    public void run(){
      System.out.println("Hallo" + teller++);
      try{
        barrier.await();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}

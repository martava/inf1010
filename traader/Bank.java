import java.util.concurrent.locks.*;
import java.util.*;
import java.util.concurrent.*;

class Bank{
  public static void main(String[] args){
    BankMonitor bm = new BankMonitor(100);
    for(int i = 0; i<10; i++){
      new Thread(new Kunde((i*10), bm)).start();
    }
  }
}

class BankMonitor{
   Lock laas = new ReentrantLock();
  private int saldo;

  BankMonitor(int saldo){
    this.saldo = saldo;
  }

  public synchronized void ta(int i){
    laas.lock();
    try{
      saldo -= i;
    }finally{laas.unlock();}
  }
  public synchronized void gi(int i){
    laas.lock();
    try{
      saldo += i;
    }finally{laas.unlock();}
  }
  public synchronized  void saldo(){
    System.out.println("Saldo: " + saldo);
  }

}

class Kunde implements Runnable{
  int belop;
  BankMonitor m;

  Kunde(int belop, BankMonitor m){
    this.belop = belop;
    this.m = m;
  }

  public void run(){
    m.laas.lock();
    try{
      for(int i = 0; i<10; i++){

          m.ta(belop);
          Thread.sleep(new Random().nextInt(5));
          m.gi(belop);
          m.saldo();
      }
    }catch(InterruptedException e){System.out.println("feil");
    }finally{m.laas.unlock();}
  }
}

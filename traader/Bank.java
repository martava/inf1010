/* Lag en monitor-klasse som du kaller Bank og som inneholder et pengebeløp. Den skal inneholde metodene ta(), gi() og saldo().

Lag en trådklasse som først tar ut et beløp (kaller på ta()), og så setter inn det samme beløpet (kaller gi()). Tråden skal gå i løkke og gjøre dette mange ganger.

Hver gang en tråd er ferdig med å både ta ut penger og sette inn det samme beløpet skal den kalle metoden saldo() for å finne hvor mange penger det er i banken. Skriv ut dette beløpet.

Opprett mange tråder av trådklassen og kjør disse i parallell. Lag gjerne en konstruktør til trådklassen slik at alle trådene tar ut og setter inn forskjellige beløp.

a) Skriv først monitoren uten locks og se hva som da skjer. Er saldoen alltid den samme før og etter uttak?

Legg inn et kall på metoden Thread.sleep() mellom alle (eller noen av) setningene i metodene ta og gi. Hva skjer da? Hva skjer hvis du lar argumentet til sleep være et tilfeldig tall? (Prøv f.eks. Thread.sleep(Math.random()*1000))

b) Legg inn locks og se hva som skjer
*/

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

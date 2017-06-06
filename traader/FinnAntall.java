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

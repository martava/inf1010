import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
import java.io.*;

public class Monitor{
  private int i;
  private int alt;
  private final Lock laas = new ReentrantLock();
  //private final Condition ingenDekrMeldingar = laas.newCondition();

  ArrayList<String[]> klar = new ArrayList<String[]>();
  ArrayList<String[]> nyklar = new ArrayList<String[]>();

  public Monitor(int alt){
    this.alt = alt;

  }
  public void settInnRemse(String[] remse){
    laas.lock();
    try{
      klar.add(remse);
      i++;
      System.out.println(klar.size());
      if(klar.size() >= 2){
        new Thread(new Merger(klar.get(0), klar.get(1), this)).start();
        klar.remove(klar.get(0));
        klar.remove(klar.get(1));
        System.out.println("starter ny traad");
      }
      if(i == alt){
        System.out.println("kallar på skritilfil");
        skrivTilFil();
      }
    }catch (Exception ie){

    }finally{
      laas.unlock();
    }
  }
  public void settInnRemseNy(String[] remse){
    laas.lock();
    try{System.out.println("er her");
      nyklar.add(remse);
      if(nyklar.size() >= 2){
        new Thread(new Merger(nyklar.get(0), nyklar.get(1), this)).start();
        nyklar.remove(nyklar.get(0));
        nyklar.remove(nyklar.get(1));
        System.out.println("ne ne ne traad");
      }

    }catch (Exception ie){

    }finally{
      laas.unlock();
    }
  }
  public void skrivTilFil(){
    try{
      PrintWriter pw = new PrintWriter(new File("sortert.in"), "utf-8");
      String[] ferdigSortert = new String[alt];
      for(int i = 0; i<alt; i++){
        if(ferdigSortert[i] != null){
          pw.println(ferdigSortert[i]);
        }
      }
      pw.close();
    }catch(FileNotFoundException fne){System.out.println("feil");}
      catch(UnsupportedEncodingException uee){}
  }
}

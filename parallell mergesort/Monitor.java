import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
import java.io.*;

public class Monitor{
  private int i;
  private int alt;
  private final Lock laas = new ReentrantLock();


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
        String[] tmp1 = klar.get(0);
        String[] tmp2 = klar.get(1);
        klar.remove(tmp1);
        klar.remove(tmp2);
        System.out.println("starter ny traad");
        new Thread(new Merger(tmp1, tmp2, this)).start();

      }
    }catch (Exception ie){

    }finally{
      laas.unlock();
    }
  }
  public void settInnRemseNy(String[] remse){
    laas.lock();
    try{
      nyklar.add(remse);
      if(nyklar.size() >= 2){
        String[] tmp1 = nyklar.get(0);
        String[] tmp2 = nyklar.get(1);
        nyklar.remove(tmp1);
        nyklar.remove(tmp2);
        new Thread(new Merger(tmp1, tmp2, this)).start();

      }
      if(nyklar.size() == 1){
        System.out.println("kallar på skrivtilfil");
        skrivTilFil();
      }

    }catch (Exception ie){

    }finally{
      laas.unlock();
    }
  }
  public void skrivTilFil(){
    try{
      PrintWriter pw = new PrintWriter(new File("sortert.in"), "utf-8");
      String[] ferdigSortert = nyklar.get(0);
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

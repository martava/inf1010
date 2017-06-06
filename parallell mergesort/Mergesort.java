import java.io.*;
import java.util.*;

public class Mergesort{
  int antTraader;
  int antOrd;
  String[] ordTabell;
  public static void main(String[] args){
    int antTraader;
    int antOrd;
    String[] ordTabell;
    antTraader = Integer.parseInt(args[0]);

    try{
      Scanner filLesar = new Scanner(new File (args[1]));

      antOrd = (1 + Integer.parseInt(filLesar.nextLine()));

      Monitor mon = new Monitor(antOrd);

      ordTabell = new String[antOrd];

      System.out.println(ordTabell.length);

      int i = -1;

      while(filLesar.hasNextLine()){//les alle fila inn i tabellen
        ordTabell[++i] = filLesar.nextLine();
      }
      int ordPrTraad = antOrd / antTraader + 1; //fordi int runder alltid ned


      for(int k = 0; k<antTraader; k++){
          String[] tilTraad = new String[antOrd];
        for(int j = 0; j<antOrd; j++){
          tilTraad[j] = ordTabell[j];
        }
        new Thread(new Sorterer(tilTraad, mon)).start();
      }
    }catch(FileNotFoundException fne){}



  }
}

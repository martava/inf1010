/*
Denne oppgaven er hentet fra prøveeksamen 2004.

Et ordkryss består av et n x n rutenett med bokstaver, der målet er å finne skjulte ord i dette rutenettet. Et eksempel på et 4 x 4 rutenett er

HABD
MVQK
PLUG
ASRD

Et ord finnes i rutenettet hvis det kan formes fra en sekvens av nabo-ruter, der en nabo-rute er definert som en rute med felles kant eller felles hjørne (altså er diagonal-ruter naboer). For eksempel finnes ordet ”KURS” i eksemplet: (de øvrige bokstavene er her erstattet med asterisker for å gjøre det lettere å se)

****
***K
**U*
*SR*

Skriv en metode, med eventuelle hjelpemetoder, som gitt et slikt ordkryss (som en to-dimensjonal character array) sjekker om et gitt ord (en string) finnes i arrayen eller ikke.

Bruk rekursjon.

Deklarasjon og initiering kan gjøres med

char [][] ordene ={{'H','A','B','D'},
                  {'M','V','Q','K'},
                  {'P','L','U','G'},
                  {'A','S','R','D'}}
              */



public class Ordkryss{
  public static void main(String[] args){
    char [][] ordene ={{'H','A','B','D'},
                   {'M','V','Q','K'},
                   {'P','L','U','G'},
                   {'A','S','R','D'}};
    char[][] test = new char[3][4];
    System.out.println(test.length);
    System.out.println(test[0].length);
    System.out.println(finstOrdet("RUVAQLPMH", ordene));
  }
  static boolean finstOrdet(String ordet, char[][] kryss){
    for (int i = 0; i<kryss[0].length; i++){
      for(int j = 0; j<kryss.length; j++){
        if(ordet.charAt(0) == kryss[j][i]){
          return finnResten(ordet.substring(1), kryss, i, j);
        }
      }
    }
    //System.out.print("feil");
    return false;
  }

  static boolean finnResten(String ordet, char[][] kryss, int posx, int posy){
    if(posx+1 < kryss[0].length){
      if(kryss[posy][posx+1] == ordet.charAt(0)){
        if(ordet.length() == 1){

          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx+1, posy);
      }
    }
    if(posy+1 < kryss.length){
      if(kryss[posy+1][posx] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx, posy+1);
      }
    }
    if(posx+1 < kryss[0].length && posy+1 < kryss.length){
      if(kryss[posy+1][posx+1] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx+1, posy+1);
      }
    }
    if(posx-1 >= 0 && posy-1 >= 0){
      if(kryss[posy-1][posx-1] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx-1, posy-1);
      }
    }
    if(posx-1 >= 0 && posy+1 < kryss.length){
      if(kryss[posy+1][posx-1] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx-1, posy+1);
      }
    }
    if(posx-1 >= 0){
      if(kryss[posy][posx-1] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx-1, posy);
      }
    }
    if(posy-1 >= 0){
      if(kryss[posy-1][posx] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
        return finnResten(ordet.substring(1), kryss, posx, posy-1);
      }
    }
    if(posy-1 >= 0 && posx+1 > kryss[0].length){
      if(kryss[posy-1][posx+1] == ordet.charAt(0)){
        if(ordet.length() == 1){
          System.out.println("Fant slutten");
          return true;
        }
      return finnResten(ordet.substring(1), kryss, posx+1, posy-1);
      }
    }
    System.out.print("feil" + ordet.length());
    return false;

  }

}

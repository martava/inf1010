/*Her er 6 små rekursjonsoppgåver slengt inn i ei javafil*/

public  class Fakultet{
  public static void main(String[] args){
    System.out.println(fakultet(12));
    System.out.println(fakultetIterativ(12));
    System.out.println(gcd(48, 98));
    triangel(3, 5);
    System.out.println(teddy(250));
    System.out.println(teddy(42));
    System.out.println(teddy(84));
    System.out.println(teddy(53));
    System.out.println(teddy(41));
    skrivUtBinaer(36);
    int[][] kart = new int[5][5];
    kart[0][0] = 34;
    kart[0][1] = 21;
    kart[0][2] = 32;
    kart[0][3] = 41;
    kart[0][4] = 25;

    kart[1][0] = 14;
    kart[1][1] = 42;
    kart[1][2] = 43;
    kart[1][3] = 14;
    kart[1][4] = 31;

    kart[2][0] = 54;
    kart[2][1] = 45;
    kart[2][2] = 52;
    kart[2][3] = 42;
    kart[2][4] = 23;

    kart[3][0] = 33;
    kart[3][1] = 15;
    kart[3][2] = 51;
    kart[3][3] = 31;
    kart[3][4] = 35;

    kart[4][0] = 21;
    kart[4][1] = 52;
    kart[4][2] = 33;
    kart[4][3] = 13;
    kart[4][4] = 23;

    skattejakt(kart, 11);

    }
/*-------------------------
Skriv en klasse Fakultet med en metode  som beregner n! ved hjelp av ekte rekursjon.
Skriv en metode static int fakultetIterativ(int n) som beregner n! iterativt, altså ved hjelp av en løkkestruktur
*/
  public static int fakultet (int n){
    if(n > 0){
      return n * (fakultet (--n));
    }
    return 1;
  }

  public static int fakultetIterativ (int n){
    int sum = 1;
    while (n > 0){
      sum *= n--;
    }
    return sum;
  }



/*--------
Skriv en metode static int gcd(int a, int b) som beregner GCD ved hjelp av rekursiv implementasjon av Euklids algoritme.
*/
  public static int gcd(int a, int b){

    if(a < b){
        return gcd(b, a);
    }
    int c = a % b;
    if (c != 0){
        return gcd(b, c);
    }
    return b;

  }


  /*------------------------
  Skriv en metode
     static void triangel(int m, int n)
  hvor m≤n
  (dette trenger du ikke å sjekke).
  Metoden skal skrive et mønster med 2⋅(n−m+1)
  linjer til standard output (System.out). Den første linjen inneholder m stjerner, neste linje inneholder m+1 stjerner, og så videre til en linje med n stjerner. Mønsteret skal så repeteres baklengs, fra n ned til m stjerner.
  triangel(3, 5) skal gi output:

  ***
  ****
  *****
  *****
  ****
  ***
  */
  static void triangel(int m, int n){
    if(m <= n){
      for(int i = 0; i<m; i++){
        System.out.print("*");
      }
      System.out.println("");
      triangel(m+1, n);
      for(int i = 0; i<m; i++){
        System.out.print("*");
      }
      System.out.println("");
    }
    if(n == 0){
      System.out.println();
    }
  }



  /*-----------------
  Spillet starter med at du får noen bjørner. Du kan så gi tilbake noen bjørner, men du må følge disse reglene (hvor n er antallet bjørner som du har):

   Hvis n er et partall kan du gi tilbake nøyaktig n/2 bjørner.
   Hvis n er delelig med 3 eller 4, kan du gange de siste to sifrene i n og gi tilbake så mange bjørner. (Det siste sifferet i n er n % 10, og det nest-siste sifferet er (n%100)/10.)
   Hvis n er delelig med 5, kan du gi tilbake nøyaktig 42 bjørner.

Målet med spillet er å ende opp med nøyaktig 42 bjørner.
*/

    static boolean teddy(int n){
        String loysing = hjelp(n, "");
        if(loysing != null){
          System.out.println(loysing);
         return true;
       }
        return false;

    }
    static String hjelp(int n, String path){
      if(n == 42){
        return path;
      }
      if(n <= 0 || path.contains(""+n)){
        return null;
      }

      if(n%5 != 0 && n%2 != 0 && n%3 != 0 && n%4 != 0){
        return null;
      }
      String loypeEin = path;
      String loypeTo = path;
      String loypeTre = path;

      if(n%5 == 0){
        loypeEin = hjelp(n-42, path += " " + (n-42));
      }
      if(n%2 == 0){
        loypeTo = hjelp(n/2, path += " " + n/2);
      }
      if(n%3 == 0 || n%4 == 0){
        loypeTre = hjelp((n%10) + ((n%100)/10), path += " " + (n%10) + ((n%100)/10));

      }
      if(loypeTre != null) return loypeTre;
      else if(loypeTo != null) return loypeTo;
      else if(loypeEin != null) return loypeEin;
      else return null;
    }

/*-------------------------
Skriv en metode

 void skrivUtBinaer(int n)

hvor n er ikke-negativ.

Metoden skal skrive verdien av n som et binært tall. Hvis n er null, skrives en enkelt null, ellers skrives det ikke ut noen ledende nuller. Linjeskift (\n) skrives IKKE ut på slutten av output.

Eksempler: n=0 skal gi output: 0 n=4 skal gi output: 100 n=27 skal gi output: 11011
*/

    static void skrivUtBinaer(int n){

      if(n == 0){
        System.out.print("");
      }
      else {
        skrivUtBinaer(n/2);
        System.out.print(n%2);
      }

    }


/*---------------------
Denne oppgaven er hentet fra KnowIts julekalender fra 2015

Vi skal på skattejakt! Denne skattejakten er litt spesiell i og med at hvordan vi kommer oss dit er minst like viktig som hvor vi til slutt kommer. Kartet ser slik ut:

34 21 32 41 25
14 42 43 14 31
54 45 52 42 23
33 15 51 31 35
21 52 33 13 23

Hvert tall i kartet består av to siffer, MSB og LSB (Most Significant Bit og Least Significant Bit). I det første tallet, 34, er MSB=3
og LSB=4

. I skattejakten vår betyr MSB radnummeret og LSB kolonnenumeret. Når vi da begynner øverst til venstre med tallet 34, går vi til tredje rad og fjerde kolonne, og finner tallet 42. Dette er da vårt nye tall. I neste omgang går vi da til fjerde rad og andre kolonne, og finner tallet 15, osv. Skatten vår ligger på det tallet på kartet som representerer sin egen posisjon.

Lag et Java-program som finner rekken som kommer frem til skatten ved hjelp av rekursjon. Vi begynner med tallet 11, som har posisjon øverst i venstre hjørnet. Rekken begynner slik: 11,34,42,15,…
*/

    static void skattejakt(int[][] kart, int pos){
      int posx = (pos%100/10) - 1;
      int posy = (pos%10) - 1;
      int posisjon = pos - 11;
      if(kart[posx][posy] == pos){
        System.out.println("Fant skatten på posisjon " + pos);
      }
      else{
        int nyposisjon = kart[posx][posy];
        skattejakt(kart, nyposisjon);
      }
    }



}

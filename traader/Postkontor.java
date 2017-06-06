/* I denne oppgaven skal vi modellere en enkel versjon av hvordan et postkontor fungerer. Vi skal ha klassene Post, Postmann, Postkontor og Kunde. I denne versjonen av et postkontor skal postmannen levere pakker til kontoret og kundene skal plukke opp pakker som er til dem.

a) Skriv klassen Post. Klassen skal inneholde en beskrivelse i form av en String og en mottager i form av en String. Legg også til relevante hent- og sett-metoder.

b) Skriv en trådklassen Postmann. I run() skal postmannen lage 100 pakker og levere dem en etter en til postkontoret.

c) Skriv trådklassen Kunde. Kunden skal være identifiserbar med et navn (String) som skal matche mottager-stringen i Post. Kunden skal stille seg i kø på postkontoret og plukke opp post til seg. Når kunden får post skal innholdet skrives ut og kunden stille seg i kø og vente på mer post.

d) Skriv monitoren Postkontor. Postkontoret skal inneholde et Post-array av lengde 10.

Lag en metode public void leverPost(Post p). Putt posten inn i arrayet dersom det er plass til det, eller vent til det er ledig plass.

Lag en metode public Post hentPost() som returnerer post dersom det er en post på postkontoret eller som venter dersom det ikke er det.

e) Utvid hentpost() til å ta imot en en String mottager. Da skal den kun returnere pakker som har personen som kaller den som mottager, eller vente dersom det ikke er noen slike pakker.

f) Utvid klassen Post med to subklasser Brev og Pakke med ulike utskrifter for persone
*/


import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.*;

class Postkontor{

  public static Condition fulltKontor;
  public static Condition tomtKontor;
  public static Lock laas;

  public static void main(String[] args){
    koyr();
  }
  public static void koyr(){
    laas = new ReentrantLock();
    fulltKontor = laas.newCondition();
    tomtKontor = laas.newCondition();
    PostkontorM mon = new PostkontorM(laas, fulltKontor, tomtKontor);
    for(int i = 0; i<10; i++){
      new Thread(new Kunde(Integer.toString(i), mon)).start();
    }
    new Thread(new Postmann(mon)).start();


  }


}
class PostkontorM{

  Condition fulltKontor;
  Condition tomtKontor;
  Lock laas;
  Post[] post = new Post[10];
  int teljar = 0;
  PostkontorM(Lock l, Condition f, Condition t){
    fulltKontor = f;
    laas = l;
    tomtKontor = t;
  }


  public void leverPost(Post p){
    laas.lock();
    try{
      while(teljar == post.length){
          fulltKontor.await();


        }
        for(int i = 0; i< post.length; i++){
          if(post[i] == null){
            post[i] = p;
            teljar++;
            tomtKontor.signalAll();
            break;
          }
        }
      }catch(Exception e){System.out.println("feil i leveringa"); }
      finally{laas.unlock();}
  }
  public Post hentPost(String n){

    laas.lock();
    Post p = null;
    try{
      while(teljar == 0){
        tomtKontor.await();
      }
      for(int i = 0; i<10; i++){
        if(post[i] != null){
        if(post[i].mottakar.equals(n)){
          p = post[i];
          post[i] = null;
          teljar--;
          fulltKontor.signal();
}
        }
      }
    }catch(InterruptedException e){
      System.out.println("feil i hentinga");
    }
    finally{laas.unlock(); } return p;

  }

}

class Post{
  String skildring;
  String mottakar;
  Post(String s, String m){
    skildring = s;
    mottakar = m;
  }

}

class Postmann implements Runnable{

  PostkontorM k;
  Postmann(PostkontorM k){
    this.k = k;
  }

  public void run(){
    int b = 0;

      for(int i = 0; i<100; i++){
        int a = (int) (10.0 * Math.random());
        //System.out.println("Leverer " + "Pakke: " + Integer.toString(i) + " til " +  Integer.toString(a));
        k.leverPost(new Post("Pakke: " + Integer.toString(i), Integer.toString(a)));
        b++;
        System.out.println("Leverte pakker: " + b);

      }
      System.out.println("På kontoret: ");
      for(int i=0; i<k.post.length; i++){
        if(k.post[i] != null)
        System.out.println(k.post[i].skildring + " til traad " + k.post[i].mottakar);
      }
      System.out.println("Antall pakker: " + k.teljar);


  }
}

class Kunde implements Runnable{
  String namn;
  PostkontorM pk;
  static AtomicInteger antall = new AtomicInteger(0);
  static int ant = 0;

  Kunde(String n, PostkontorM pk){
    namn = n;
    this.pk = pk;
  }
  public void run(){

      while(ant < 100){
        Post p = pk.hentPost(namn);
        if(p != null){
          System.out.println(namn + " hentet " + p.skildring);
          //antall.incrementAndGet();
          ant++;
          System.out.println("Henta pakker: " + ant);
        }
      }
      System.out.println("Henta pakker: " + ant);

  }
}

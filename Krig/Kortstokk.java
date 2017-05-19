import java.util.*;

class Kortstokk{
    private ArrayList<Kort> kortstokk = new ArrayList<>();
    private Random trekker = new Random();

    public Kortstokk(){

    }
    public static Kortstokk heilKortstokk(){
      Kortstokk stokk = new Kortstokk();
      //String[] farger = {"H", "K", "S", "R"};
      for(int i = 0; i<4; i++){
        for(int j=1; j<14; j++){
          stokk.leggTil(new Kort(Kort.farger[i], j));
        }
      }
      return stokk;
    }
    public void leggTil(Kort k){
      kortstokk.add(k);
    }
    public Kort trekkKort(){
      int indeks = trekker.nextInt(kortstokk.size());
      Kort k = kortstokk.get(indeks);
      kortstokk.remove(indeks);
      return k;
    }
    public int antall(){
      return kortstokk.size();
    }
    public boolean erTom(){
      kortstokk.isEmpty();
    }
    public Kort hentKort(Kort k){
      return kortstokk.remove(k);
    }
    public void stokk(){
      ArrayList<Kort> gammel = kortstokk;
      ArrayList<Kort> nystokk = new ArrayList<Kort>();
      while(!gammel.erTom()){
        int rest = gammel.size();
        int indeks = random.nextInt(rest);
        Kort k = gammel.remove(indeks);
        nystokk.add(k);
      }
    }
}

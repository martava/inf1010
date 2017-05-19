import java.util.*;
class Spelar{
  private String namn;
  //private ArrayList<Kort> hand = new ArrayList<>();;
  private Kortstokk hand;

  public Spelar(String namn){
    this.namn = namn;
  }
  public void mottaKort(Kort k){
    if(k != null) hand.add(k);
  }
  public Kort leggKort(){
    Kort k = hand.get(0);
    hand.remove(0);
    return k;
  }
  public boolean tomHand(){
    if(hand.size() == 0) return true;
    return false;
  }
  public int antallKort(){
    return hand.size();

  }
  public String toString(){
    String string = namn + "\nKort: ";
    for (Kort k : hand) {
        string += k.toString() + "\n";
    }
    return string;
  }
}

class Kort{
  private char farge;
  private int tal;
  public static final char[] farger = {'S', 'H', 'K', 'R'};

  public Kort(char farge, int tal){
    this.farge = farge;
    this.tal = tal;
  }
  public String toString(){
    return farge + " " + tal;
  }
  public char getFarge(){
    return farge;
  }
  public int getTal(){
    return tal;
  }

}

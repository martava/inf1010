public class Mergesort{
  int antTraader;
  int antOrd;
  String[] ordTabell;
  public static void main(String[] args){
    antTraader = args[0];
    try{
      Scanner filLesar = new Scanner(new File (args[1]));
      antOrd = filLesar.nextLine();
      ordTabell = new String[antOrd];
      int i = 0;
      while(filLesar.hasNextLine()){
        ordTabell[i++] = filLesar.nextLine();
      }
    }catch(FileNotFoundException fne){}



  }

}

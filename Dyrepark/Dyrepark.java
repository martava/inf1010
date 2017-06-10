public class Dyrepark{
  public static void main(String[] args){
    Ape julius = new Ape (20, "Julius");
    Menneske ks = new Menneske(200, "Kaptein Sabeltann");
    Papegoye polly = new Papegoye(10, "Polly");
    julius.kleUt(ks);
    System.out.println(julius.kleddUtSom());


    Sjiraff stankel = new Sjiraff(5, "Stankel");
    Koe k = new Koe();
    k.settInn(julius);
    k.settInn(ks);
    k.settInn(polly);
    k.settInn(stankel);

    System.out.println(k.taUtMinstNr().toString());
    System.out.println(k.taUtYngst().toString());
  }
}

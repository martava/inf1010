public class Dyr{
  int alder;
  String namn;

  public Dyr(int alder, String namn){
    this.alder = alder;
    this.namn = namn;
  }
  public void bliEldre(){
    alder++;
  }
  public String toString(){
    return namn + " på " + alder + " år.";
  }
}
class Pattedyr extends Dyr{

  public Pattedyr(int alder, String namn){
    super(alder, namn);
  }

}
abstract interface KanKleSegUt{
  public void kleUt(Object o);
  public String kleddUtSom();
}

class Primat extends Pattedyr{
  public Primat(int alder, String namn){
    super(alder, namn);
  }
}
class Menneske extends Primat{
  public Menneske(int alder, String namn){
    super(alder, namn);
  }
}
class Ape extends Primat implements KanKleSegUt{
  Object o;

  public Ape(int alder, String namn){
    super(alder, namn);
  }
  public void kleUt(Object o){
    this.o = o;
  }
  public String kleddUtSom(){
    return namn + " er kledd ut som " + o.toString();
  }
}
class Sjiraff extends Pattedyr{
  public Sjiraff(int alder, String namn){
    super(alder, namn);
  }
}

class Fugl extends Dyr{
  public Fugl(int alder, String namn){
    super(alder, namn);
  }
}
class Papegoye extends Fugl implements KanKleSegUt{
  Object o;

  public Papegoye(int alder, String namn){
    super(alder, namn);
  }
  public void kleUt(Object o){
    this.o = o;
  }
  public String kleddUtSom(){
    return namn + " er kledd ut som " + o.toString();
  }

}

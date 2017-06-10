public class Koe{

  class Node{
    Dyr data;
    Node neste;
    Node forrige;
    public Node (Dyr d){
      data = d;
    }
  }

  Node forste;
  Node yngste;

  public void settInn(Dyr d){
    Node n = new Node(d);
    //Viss tom liste:
    if(forste == null){

      forste = n;
      yngste = n;
    }
    else{
      //set fyrst inn bakers i koen
      Node tmp = forste;
      while(tmp.neste != null){
        tmp = tmp.neste;
      }
      tmp.neste = n;
      n.forrige = tmp;


      //set så inn i koen etter alder:
      Node n2 = new Node(d); //Må lage ny node med denne implementasjonen

     if(yngste.data.alder >= n2.data.alder){
        tmp = yngste;
        yngste = n2;
        n2.neste = tmp;
        tmp.forrige = yngste;
      }
      else{
        tmp = yngste;
        while(tmp.neste != null && tmp.data.alder <= n2.data.alder){
          tmp = tmp.neste;
        }
      //set dyret inn i køen mellom tmp og tmp.neste:
      Node tmp2 = tmp.neste;
      tmp.neste = n2;
      n2.forrige = tmp;
      n2.neste = tmp2;
      if(tmp2 != null) tmp2.forrige = n2;

    }//System.out.println("Yngst: " + yngste.data.toString());
  }

  }
  public Dyr taUtYngst(){
    Node tmp = yngste;
    yngste = yngste.neste;
    yngste.forrige = null;
    return tmp.data;
  }
  public Dyr taUtMinstNr(){
    Node tmp = forste;
    forste = forste.neste;
    forste.forrige = null;
    return tmp.data;
  }
}

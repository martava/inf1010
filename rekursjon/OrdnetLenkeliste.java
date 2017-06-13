/*
ikkje ferdig
*/


class OrdnetLenkeliste {
    public static void main(String[] args) {
        Liste<String> ordliste  = new Liste<String>();

        String [] navn = new String []
            { "I", "dag", "er", "det", "eksamen", "i", "INF1010.\n",
              "Jeg", "håper", "du", "liker", "denne", "oppgaven.\n",
              "Lykke", "til!", "hilsen", "oppgaveforfatteren\n"};

        System.out.print("Setter inn: ");
        for (String n: navn) {
            System.out.print(n + " ");
            ordliste.settInn( n );
        }
        System.out.println();
        ordliste.skrivAlle(); System.out.println();
    }
}

class Liste < T extends Comparable<T> > {
    private Node foran;

    private class ListeEnde extends Node {

     }   // oppgave c

    private class Node {
        protected T t;  // peker til objektet som lagres i lista
        protectedddd Node neste;

        Node (T nyttObjekt) {
            t = nyttObjekt;
        }

        int sammenlign(Node k) {
          return t.compareTo(k.t);
        }              // oppgave a

        void settInn(Node ny) {
          Node tmp = foran;
          foran = ny;
          foran.neste = tmp;
        }               // oppgave b TODO: gjere denne rekursvis

        void skriv(){
            System.out.println(t);
            neste.skriv();
        }

    }

    Liste() {   }                                // oppgave d



    public void settInn(T t) {          // du skal ikke endre denne
        Node nyNode = new Node(t);
        foran.settInn(nyNode);
    }

    public void skrivAlle() {
        System.out.println("Alle i listen:\n" + "---");
        foran.skriv();
        System.out.println(" -- SLUTT");
    }
}

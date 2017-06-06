public class Test{
  public static void main(String[] args){
    String[] tab1 = {"a", "b", "b", "bc", "d", "f", "gi"};
    String[] tab2 = {"aa", "ab", "ac", "g", "h"};
    merge(tab1, tab2);
    //a, aa, ab, ac, b, b, bc, d, f, g, g, h


  }
  public static void merge(String[] a, String[] b){
    System.out.println("starter å merge");
    int lengde = a.length + b.length;
    String[] ny = new String[lengde];
    String tmpA = a[0];
    String tmpB = b[0];
    int ateljer = 1;
    int bteljer = 1;
    int i = 0;
    while(i<ny.length){
      if(ateljer < a.length+1 || bteljer < b.length+1){
          if(tmpA == null){
            ny[i++] = tmpB;
            if(bteljer < b.length){
              tmpB = b[bteljer++];
            }else{tmpB = null;}
          }
          else if(tmpB == null){
            ny[i++] = tmpA;
            if(ateljer < a.length){
              tmpA = a[ateljer++];
            }else{tmpA = null;}
          }
          else if (tmpA.compareTo(tmpB) < 0){
            ny[i++] = tmpA;
            System.out.println(tmpA);
            if(ateljer < a.length){
              tmpA = a[ateljer];
              ateljer++;
            }else{
              tmpA = null;
            }
          }


          else{
            ny[i++] = tmpB;
            System.out.println(tmpB);
            if(bteljer < b.length){
              tmpB = b[bteljer++];
            }else{tmpB = null;}

          }
        }


    }
    for(int j = 0; j<ny.length; j++){
      System.out.print(ny[j] + ", ");
    }


  }
}

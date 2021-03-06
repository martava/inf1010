class Merger implements Runnable{
  String[] remse1;
  String[] remse2;
  Monitor monitor;
  boolean ferdig;

  public Merger(String[] remse1, String[] remse2, Monitor monitor){
    this.monitor = monitor;
    this.remse1 = remse1;
    this.remse2 = remse2;
    ferdig = false;
  }
  public void run(){

    merge(remse1, remse2);
    ferdig = true;

  }
  private void merge(String[] a, String[] b){
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
    monitor.settInnRemseNy(ny);
    System.out.println("ferdig med å merge");

  }

}

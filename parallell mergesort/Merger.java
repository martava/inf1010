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
    System.out.println("starter å merge");
    merge(remse1, remse2);
    ferdig = true;

  }
  private void merge(String[] a, String[] b){
    int lengde = a.length + b.length;
    String[] ny = new String[lengde];
    String tmpA = a[0];
    String tmpB = b[0];
    int ateljer = 0;
    int bteljer = 0;
    int i = 0;
    while(i<ny.length){
      if(tmpA != null && tmpB != null){
      if (tmpA.compareTo(tmpB) < 0){
        ny[i++] = tmpA;
        tmpA = a[ateljer++];
      }
      else{
        ny[i++] = tmpB;
        tmpB = b[bteljer++];
      }
    }
    }
    monitor.settInnRemseNy(ny);

  }

}

class Sorterer implements Runnable{
  String[] ord;
  Monitor monitor;
  boolean ferdig;

  public Sorterer(String[] ord, Monitor monitor){
    this.monitor = monitor;
    this.ord = ord;
    ferdig = false;
  }
  public void run(){
    String[] tmp = new String[ord.length];
    mergeSort(ord, tmp, 0, ord.length-1);
    ferdig = true;

    System.out.println("set inn remse");
    /*for (int j = 0; j<ord.length; j++){
      System.out.println(ord[j]);
    }*/
  }
  private void mergeSort(String[] usortert, String[] tmp, int left, int right){
    if(left < right){
      int center = (left+right)/2;
      mergeSort(usortert, tmp, left, center);
      mergeSort(usortert, tmp, center+1, right);
      merge(usortert, tmp, left, center+1, right);
    }
  }
  private void merge(String[] a, String[] tmp, int leftPos, int rightPos, int rightEnd){
    System.out.println("merger");
    int leftEnd = rightPos-1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    //main-loop
    while(leftPos <= leftEnd && rightPos <= rightEnd){
      if(a[leftPos] != null && a[rightPos] != null){
      if(a[leftPos].compareTo(a[rightPos]) <= 0){
        tmp[tmpPos++] = a[leftPos++];
      }}
      else{
        tmp[tmpPos++] = a[rightPos++];
      }
    }
      while(leftPos <= leftEnd){
        tmp[tmpPos++] = a[leftPos++];
      }
      while(rightPos <= rightEnd){
        tmp[tmpPos++] = a[rightPos++];
      }
      for(int i = 0; i<numElements; i++, rightEnd--){
        ord[rightEnd] = tmp[rightEnd];
      }
      monitor.settInnRemse(ord);



  }

}

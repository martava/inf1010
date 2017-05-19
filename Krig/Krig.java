class Krig{

  public static void main (String[] args){
    Spelar madelen = new Spelar("Madelen");
    Spelar marta = new Spelar("Marta");
    Kortstokk stokk = new Kortstokk();
    while(!stokk.erTom()){
      marta.mottaKort(stokk.trekkKort());
      madelen.mottaKort(stokk.trekkKort());
    }
    System.out.println(marta.toString() + madelen.toString());
  /*  for (int i =0; i<53; i++){
      marta.mottaKort(stokk.trekkKort());
    }
    for (int i =0; i<26; i++){
      madelen.mottaKort(stokk.trekkKort());
    }*/

    while(!marta.tomHand() && !madelen.tomHand()){
      Kort mv = marta.leggKort();
      Kort ma = madelen.leggKort();
      if(mv.getTal() > ma.getTal()){
        marta.mottaKort(ma);
        marta.mottaKort(mv);
      }
      if(ma.getTal() > mv.getTal()){
        madelen.mottaKort(mv);
        madelen.mottaKort(ma);
      }
      if(ma.getTal() == mv.getTal()){
        /*if(marta.antallKort() < 4 || madelen.antallKort() < 4){
          marta.mottaKort(ma);
          marta.mottaKort(mv);
        }
        else{
        Kort[] mvs = new Kort[4];
        Kort[] mas = new Kort[4];
        mvs[0] = mv;
        mas[0] = ma;
        for(int i = 1; i<4; i++){
          mvs[i] = marta.leggKort();
          mas[i] = madelen.leggKort();
        }
        mv = marta.leggKort();
        ma = madelen.leggKort();
        if(mv.getTal() > ma.getTal()){
          marta.mottaKort(ma);
          for(int i = 0; i<4; i++){
            marta.mottaKort(mas[i]);
            marta.mottaKort(mvs[i]);
          }
        }
        if(ma.getTal() > mv.getTal()){
          madelen.mottaKort(mv);
          for(int i = 0; i<4; i++){
            madelen.mottaKort(mvs[i]);
            madelen.mottaKort(mas[i]);
          }
        }
        if(ma.getTal() == mv.getTal()){
          marta.mottaKort(ma);
          for(int i = 0; i<4; i++){
            marta.mottaKort(mas[i]);
            marta.mottaKort(mvs[i]);
          }
        }
      }*/
      marta.mottaKort(mv);
      madelen.mottaKort(ma);
      }
    }
    if(marta.tomHand()){
      System.out.println("Madelen vann" + madelen.toString() + marta.toString());
    }
    else{
    System.out.println("Marta vann" + marta.toString() + stokk.erTom());
    }
  //System.out.print(marta.toString());
  }
}

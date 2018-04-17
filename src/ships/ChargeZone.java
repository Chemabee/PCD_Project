package ships;

import java.util.concurrent.Semaphore;

public class ChargeZone{

  private Semaphore water = new Semaphore(1);
  private Semaphore mutex = new Semaphore(1);

  private int contShip = 0;

  private Semaphore[] oil = new Semaphore[5];
  private static ChargeZone instance = null;


  private ChargeZone(){
    for(int i=0;i<5;i++)
      oil[i] = new Semaphore(0);
  }

  public static ChargeZone getChargeZone(){
    if(instance == null)
      instance = new ChargeZone();
    return instance;
  }

  public void shipSignal(OilShip s)throws InterruptedException{
    mutex.acquire();
    contShip++;
    if(contShip==5){
      this.filler();
      contShip = 0;
    }
    mutex.release();
  }

  public void getOil(OilShip s)throws InterruptedException{
    oil[s.id].acquire();
    s.oilCont+=1000;
    shipSignal(s);
  }

  public void getWater(OilShip s)throws InterruptedException{
    water.acquire();
    s.waterCont+=1000;
    water.release();
  }

  public void filler()throws InterruptedException{
    for(int i=0;i<5;i++)
      oil[i].release();
  }



}

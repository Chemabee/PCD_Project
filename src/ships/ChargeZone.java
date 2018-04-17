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

  public void shipSignal(Ship s)throws InterruptedException{
    acquire(mutex);
    contShip++;
    if(contShip==5){
      this.filler();
      contShip = 0;
    }
    release(mutex);
  }

  public void getOil(Ship s)throws InterruptedException{
    acquire(oil[s.id]);
    s.oilCont+=1000;
    shipSignal(s);
  }

  public void getWater(Ship s)throws InterruptedException{
    acquire(water);
    s.waterCont+=1000;
    release(water);
  }

  public void filler()throws InterruptedException{
    for(int i=0;i<5;i++)
      release(oil[i]);
  }



}

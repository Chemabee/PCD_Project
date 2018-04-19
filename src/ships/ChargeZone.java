package ships;

import java.util.concurrent.Semaphore;

public class ChargeZone {

	private Semaphore water = new Semaphore(1);
	private Semaphore mutex = new Semaphore(1);

	private int contShip = 0;

	private Semaphore[] oil = new Semaphore[5];
	private static ChargeZone instance = null;

	private ChargeZone() {
		for (int i = 0; i < 5; i++)
			oil[i] = new Semaphore(0);
	}

	public static ChargeZone getChargeZone() {
		if (instance == null)
			instance = new ChargeZone();
		return instance;
	}

	public void shipSignal(OilShip s) throws InterruptedException {
		System.out.println("ShipSignal " + s.id);
		mutex.acquire();
		contShip++;
		if (contShip == 5) {
			System.out.println("ContShip == 5. Filler working...");
			this.filler();
			contShip = 0;
		} 
//		else
//			System.out.println("ContShip == " + contShip + ". Filler IS NOT WORKING.");
		mutex.release();
	}

	public void getOil(OilShip s) throws InterruptedException {
		shipSignal(s);
		oil[s.id].acquire();
		s.oilCont += 1000;
		System.out.println("Ship " + s.id + " has filled oil. Going to do signal");
	}

	public void getWater(OilShip s) throws InterruptedException {
		water.acquire();
		s.waterCont += 1000;
		water.release();
	}

	public void filler() throws InterruptedException {
		//Esto debería ser una entidad a parte
		for (int i = 0; i < 5; i++)
			oil[i].release();
		System.out.println("filler");
	}

}

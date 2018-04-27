package ships;

import java.util.concurrent.Semaphore;


public class ChargeZone {



	private Semaphore water = new Semaphore(1);
	private Semaphore mutex = new Semaphore(1);

	private int contShip = 0;

	private Semaphore[] oil = new Semaphore[5];
	private static ChargeZone instance = null;

	/**
	 * Default constructor
	 */
	private ChargeZone() {
		for (int i = 0; i < 5; i++)
			oil[i] = new Semaphore(0);
	}

	/**
	 * Singleton method
	 * @return :instance of the Object
	 */
	public static ChargeZone getChargeZone() {
		if (instance == null)
			instance = new ChargeZone();
		return instance;
	}

	/**
	 * Signal done by a ship when it enters in the chargeZone
	 * @param s :Ship Object
	 * @throws InterruptedException
	 */
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

	/**
	 * Mehtod for getting oil to an OilShip
	 * @param s :Ship doing the action
	 * @throws InterruptedException
	 */
	public void getOil(OilShip s) throws InterruptedException {
		shipSignal(s);
		oil[s.id].acquire();
		s.oilCont += 1000;
		System.out.println("Ship " + s.id + " has filled oil. Going to do signal");
	}

	/**
	 * Mehtod for getting water to an OilShip
	 * @param s :Ship doing the action
	 * @throws InterruptedException
	 */
	public void getWater(OilShip s) throws InterruptedException {
		water.acquire();
		s.waterCont += 1000;
		water.release();
	}

	/**
	 * Mehtod that fills the Oil containers
	 * @throws InterruptedException
	 */
	public void filler() throws InterruptedException {
		//TODO Esto deber�a ser una entidad a parte
		for (int i = 0; i < 5; i++)
			oil[i].release();
		System.out.println("filler");
	}

}

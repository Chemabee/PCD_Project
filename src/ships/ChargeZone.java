package ships;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


public class ChargeZone {

	private CyclicBarrier cb = new CyclicBarrier(5);

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
	 * Mehtod for getting oil to an OilShip
	 * @param s :Ship doing the action
	 * @throws InterruptedException
	 */
	public void getOil(OilShip s) throws InterruptedException {
//		oil[s.id].acquire();
		s.oilCont += 1000;
		System.out.println("Ship " + s.id + " has filled oil. Going to do signal");

	}

	public void shipArrived(){
		try {
			cb.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
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

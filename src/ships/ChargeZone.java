package ships;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class ChargeZone {

	private CyclicBarrier cb = new CyclicBarrier(5);
	private SynchronousQueue<OilShip>[] bq = new SynchronousQueue[5];

	private Semaphore water = new Semaphore(1);
//	private Semaphore mutex = new Semaphore(1); //Those were used in older steps of the project
//	private int contShip = 0;
	
	private Filler filler;

	private Semaphore[] oil = new Semaphore[5];
	private static ChargeZone instance = null;

	/**
	 * Default constructor
	 */
	private ChargeZone() {
		filler = Filler.getFiller();
		for (int i = 0; i < 5; i++)
			oil[i] = new Semaphore(0);
		for (int i = 0; i < bq.length; i++) {
			bq[i] = new SynchronousQueue<OilShip>();
		}
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
	 * Method for getting oil to an OilShip
	 * @param s :Ship doing the action
	 * @throws InterruptedException
	 */
	public void getOil(OilShip s) throws InterruptedException {
		filler.countDown();
		bq[s.getId()].offer(s, 1, TimeUnit.SECONDS);
		s.oilCont += 1000;
		System.out.println("Ship " + s.id + " has filled oil. Going to do signal");
	}

	/**
	 * Notifies the cyclic barrier and wait for the other 4 ships to arrive.
	 */
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
	 * Get the SynchronousQueue of the ships
	 * @return returns a Synchronous Queue of the ships. It is useful for the Filler.
	 */
	public SynchronousQueue<OilShip>[] getBQ(){
		return this.bq;
	}
	
}

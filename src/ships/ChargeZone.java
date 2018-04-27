package ships;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


public class ChargeZone {

	private CyclicBarrier cb = new CyclicBarrier(5);

	private Semaphore water = new Semaphore(1);
//	private Semaphore mutex = new Semaphore(1);

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
		//TODO controlar que no pueda coger si esta vacio(aqui hay que usar el tercer tipo)
//		oil[s.id].acquire();
		s.oilCont += 1000;
		filler.countDown();
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
		//TODO aqui no creo que haga falta usar nada
		water.acquire();
		s.waterCont += 1000;
		water.release();
	}

}

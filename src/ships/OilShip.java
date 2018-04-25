package ships;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class OilShip extends Ship {

	public int oilCont = 0;
	public int waterCont = 0;
	public int id;
	
	private ThreadPoolExecutor executor;

	public OilShip(int type, String name, int id) {
		super(type, name);
		this.id = id;
		executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
	}

	@Override
	public void run() {
		this.action();
		try {
			this.charge();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();//antes de salir espera a acabar con las tareas, no acepta nuevas
		this.action();

	}

	/**
	 * Executor for doing both actions at the same time
	 * @throws InterruptedException
	 */
	public void charge() throws InterruptedException {
		ChargeZone z = ChargeZone.getChargeZone();
		executor.execute(new Task(0,z,this));
		executor.execute(new Task(1,z,this));
		
	}

	public void getOilAction() throws InterruptedException {
		ChargeZone z = ChargeZone.getChargeZone();
		while (oilCont < 3000) {
			System.out.println("ship " + this.id + " GOING to GET OIL");
//			this.getOil();
			z.getOil(this);
			System.out.println(this.id + " oil: " + this.oilCont);
		}

	}

	public void getWaterAction() throws InterruptedException {
		ChargeZone z = ChargeZone.getChargeZone();
		while (waterCont < 5000) {
			z.getWater(this);
			System.out.println(this.id + " water: " + this.waterCont);
		}

	}

	public int getOilCont() {
		return oilCont;
	}

	public void setOilCont(int oilCont) {
		this.oilCont = oilCont;
	}

	public int getWaterCont() {
		return waterCont;
	}

	public void setWaterCont(int waterCont) {
		this.waterCont = waterCont;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}

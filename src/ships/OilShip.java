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
		while(executor.getPoolSize()>0);//TODO cambiar esto que es busywaiting
		this.action(); //TODO no puede hacer esto hasta que haya cargado aceite y agua, hay que corregirlo.

	}

	/**
	 * Executor for doing both actions at the same time
	 * @throws InterruptedException
	 */
	public void charge() throws InterruptedException {
		ChargeZone.getChargeZone().shipArrived();
		System.out.println("Oil task del barco "+this.id);
		executor.execute(new Task(0,this));
		System.out.println("Water task del barco "+this.id);
		executor.execute(new Task(1,this));

	}

	public void getOilAction() throws InterruptedException {
		while (oilCont < 3000) {
			System.out.println("ship " + this.id + " GOING to GET OIL");
//			this.getOil();
			ChargeZone.getChargeZone().getOil(this);
			System.out.println(this.id + " oil: " + this.oilCont);
		}

	}

	public void getWaterAction() throws InterruptedException {
		while (waterCont < 5000) {
			ChargeZone.getChargeZone().getWater(this);
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

package ships;

public class OilShip extends Ship {

	public int oilCont = 0;
	public int waterCont = 0;
	public int id;


	public OilShip(int type, String name, int id) {
		super(type, name);
		this.id = id;
	}

	@Override
	public void run() {
		this.action();
		try {
			this.charge();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.action();

	}
	public void charge() throws InterruptedException{
		while(oilCont < 3000){
			this.getOil();
		}

		while(waterCont < 5000){
			getWater();
		}
	}

	public void getOil()throws InterruptedException {
		ChargeZone z = ChargeZone.getChargeZone();
		z.getOil(this);
	}

	public void getWater() throws InterruptedException{
		ChargeZone z = ChargeZone.getChargeZone();
		z.getWater(this);
	}

}
